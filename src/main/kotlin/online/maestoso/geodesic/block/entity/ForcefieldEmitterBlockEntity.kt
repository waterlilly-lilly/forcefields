package online.maestoso.geodesic.block.entity

import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.block.entity.BlockEntity
import net.minecraft.inventory.Inventories
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NbtCompound
import net.minecraft.util.collection.DefaultedList
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import online.maestoso.geodesic.block.ForcefieldBlock
import online.maestoso.geodesic.block.GeodesicBlocks
import online.maestoso.geodesic.util.EmitterBlockStore
import online.maestoso.geodesic.util.InventoryImpl
import online.maestoso.geodesic.util.Math
import org.quiltmc.qkl.library.math.plus

class ForcefieldEmitterBlockEntity(pos: BlockPos, state: BlockState) : BlockEntity(GeodesicBlockEntityTypes.FORCEFIELD_EMITTER, pos, state),
    InventoryImpl {
    private var store: EmitterBlockStore? = null
    private var range = 5
    override var items = DefaultedList.ofSize(5, ItemStack.EMPTY)
    fun tick(world: World, pos: BlockPos, state: BlockState) {
        if(store == null) {
            createStore()
            createForcefield()
        } else {
            replaceVoids()
        }
    }
    private fun createForcefield() {
        store!!
            .filter { i -> i.state == EmitterBlockStore.State.TRANSPARENT}
            .map { i -> i.state = EmitterBlockStore.State.FORCEFIELD; i}
            .map { i -> i.pos.plus(pos)}
            .forEach { i -> world?.setBlockState(i, ForcefieldBlock.FORCEFIELD.defaultState)}
    }
    fun destroyForcefield() {
        store
            ?.filter { i -> i.state == EmitterBlockStore.State.FORCEFIELD}
            ?.map { i -> i.state = EmitterBlockStore.State.TRANSPARENT; i}
            ?.map { i -> i.pos.plus(pos)}
            ?.forEach { i -> world?.setBlockState(i, Blocks.AIR.defaultState)}
        store = null
    }
    private fun replaceVoids() {
        store!!
            .filter { i -> i.state != EmitterBlockStore.State.TRANSPARENT}
            .filter { i -> world?.getBlockState(i.pos.plus(pos))?.isAir == true}
            .map { i -> i.state = EmitterBlockStore.State.FORCEFIELD; i}
            .map { i -> i.pos.plus(pos)}
            .forEach { i -> world?.setBlockState(i, ForcefieldBlock.FORCEFIELD.defaultState)}
    }
    fun createStore() {
        val blocks_list: ArrayList<EmitterBlockStore.Entry> = arrayListOf()
        for(i in -range..range) {
            for(j in -range..range) {
                for(k in -range..range) {
                    if(i == 0 && j == 0 && k == 0) continue
                    when(Math.solve(range, i, j, k, "2 i % 0 ==")) {
                        Math.Result.TRUE -> {
                            print("($i $j $k) ")
                            val state = world?.getBlockState(pos.add(i, j, k)) ?: return
                            if(state.isSolid) {
                                blocks_list.add(EmitterBlockStore.Entry(BlockPos(i, j, k), EmitterBlockStore.State.SOLID))
                            } else {
                                blocks_list.add(EmitterBlockStore.Entry(BlockPos(i, j, k), EmitterBlockStore.State.TRANSPARENT))
                            }
                        }
                        Math.Result.FALSE -> continue
                        else -> error("Error generating forcefield") // TODO REPLACE THIS!!!!!
                    }
                }
            }
        }
        val blocks: Array<EmitterBlockStore.Entry> = Array(blocks_list.size) { i -> blocks_list[i] }
        store = EmitterBlockStore(blocks)
    }

    override fun readNbt(nbt: NbtCompound?) {
        super.readNbt(nbt)
        Inventories.readNbt(nbt, items)
    }

    override fun writeNbt(nbt: NbtCompound?) {
        Inventories.writeNbt(nbt, items)
        super.writeNbt(nbt)
    }

    override fun markDirty() {
        super.markDirty()
        createStore()
    }
}

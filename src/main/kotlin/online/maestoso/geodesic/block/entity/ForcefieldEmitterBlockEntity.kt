package online.maestoso.geodesic.block.entity

import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.block.entity.BlockEntity
import net.minecraft.inventory.Inventories
import net.minecraft.nbt.NbtCompound
import net.minecraft.registry.Registries
import net.minecraft.util.Identifier
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import online.maestoso.geodesic.block.ForcefieldBlock
import online.maestoso.geodesic.block.GeodesicBlocks
import online.maestoso.geodesic.util.Math
import org.quiltmc.qkl.library.math.plus

class ForcefieldEmitterBlockEntity(pos: BlockPos, state: BlockState) : BlockEntity(GeodesicBlockEntityTypes.FORCEFIELD_EMITTER, pos, state) {
    var blockStore: BlockStore? = null
    var attributeStore: AttributeStore = AttributeStore(0, "false", ForcefieldBlock.FORCEFIELD)
    fun tick(world: World, pos: BlockPos, state: BlockState) {
        if(blockStore == null) {
            createBlockStore()
            createForcefield()
        } else {
            replaceVoids()
        }
    }
    private fun createForcefield() {
        blockStore!!
            .filter { i -> i.state == BlockStore.State.TRANSPARENT}
            .map { i -> i.state = BlockStore.State.FORCEFIELD; i}
            .map { i -> i.pos.plus(pos)}
            .forEach { i -> world?.setBlockState(i, attributeStore.blockType.defaultState)}
    }
    fun destroyForcefield() {
        blockStore
            ?.filter { i -> i.state == BlockStore.State.FORCEFIELD}
            ?.map { i -> i.state = BlockStore.State.TRANSPARENT; i}
            ?.map { i -> i.pos.plus(pos)}
            ?.forEach { i -> world?.setBlockState(i, Blocks.AIR.defaultState)}
        blockStore = null
    }
    private fun replaceVoids() {
        blockStore!!
            .filter { i -> world?.getBlockState(i.pos.plus(pos))?.isAir == true}
            .map { i -> i.state = BlockStore.State.FORCEFIELD; i}
            .map { i -> i.pos.plus(pos)}
            .forEach { i -> world?.setBlockState(i, attributeStore.blockType.defaultState)}
    }
    private fun createBlockStore() {
        val blocksList: ArrayList<BlockStore.Entry> = arrayListOf()
        for(i in -attributeStore.range..attributeStore.range) {
            for(j in -attributeStore.range..attributeStore.range) {
                for(k in -attributeStore.range..attributeStore.range) {
                    if(i == 0 && j == 0 && k == 0) continue
                    when(Math.solve(attributeStore.range, i, j, k, attributeStore.predicate)) {
                        Math.Result.TRUE -> {
                            print("($i $j $k) ")
                            val state = world?.getBlockState(pos.add(i, j, k)) ?: return
                            if(state.isIn(GeodesicBlocks.FORCEFIELD_BLOCKS)) {
                                blocksList.add(BlockStore.Entry(BlockPos(i, j, k), BlockStore.State.FORCEFIELD))
                            } else if(state.isSolid) {
                                blocksList.add(BlockStore.Entry(BlockPos(i, j, k), BlockStore.State.SOLID))
                            } else {
                                blocksList.add(BlockStore.Entry(BlockPos(i, j, k), BlockStore.State.TRANSPARENT))
                            }
                        }
                        Math.Result.FALSE -> continue
                        else -> error("Error generating forcefield") // TODO REPLACE THIS!!!!!
                    }
                }
            }
        }
        val blocks: Array<BlockStore.Entry> = Array(blocksList.size) { i -> blocksList[i] }
        blockStore = BlockStore(blocks)
    }

    override fun readNbt(nbt: NbtCompound?) {
        super.readNbt(nbt)
        attributeStore.blockType = Registries.BLOCK.get(Identifier(nbt?.getString("Color")))
        attributeStore.range = nbt?.getInt("Range") ?: 0
        attributeStore.predicate = nbt?.getString("Shape") ?: "false"
        destroyForcefield()
    }

    override fun writeNbt(nbt: NbtCompound?) {
        nbt?.putString("Shape", attributeStore.predicate)
        nbt?.putInt("Range", attributeStore.range)
        nbt?.putString("Color", Registries.BLOCK.getId(attributeStore.blockType).toString())
        super.writeNbt(nbt)
    }

    override fun markDirty() {
        super.markDirty()
        createBlockStore()
    }
    class BlockStore(private val tracked: Array<Entry>) : Iterable<BlockStore.Entry> {
        /**
         * @param pos The entry's block position.
         * @param refresh The rate at which this entry's state should be refreshed
         */
        data class Entry(val pos: BlockPos, var state: State)
        enum class State {
            FORCEFIELD,
            TRANSPARENT,
            SOLID
        }

        override fun iterator(): Iterator<Entry> = tracked.iterator()

        override fun toString(): String {
            var s = "["
            for(i in tracked) {
                s += "$i,"
            }
            s += "]"
            return s
        }

    }
    class AttributeStore(var range: Int, var predicate: String, var blockType: Block)
}

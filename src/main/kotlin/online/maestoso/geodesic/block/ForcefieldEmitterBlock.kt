package online.maestoso.geodesic.block

import net.minecraft.block.*
import net.minecraft.block.entity.BlockEntity
import net.minecraft.block.entity.BlockEntityTicker
import net.minecraft.block.entity.BlockEntityType
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.registry.Registries
import net.minecraft.text.Text
import net.minecraft.util.ActionResult
import net.minecraft.util.Hand
import net.minecraft.util.Identifier
import net.minecraft.util.hit.BlockHitResult
import net.minecraft.util.math.BlockPos
import net.minecraft.util.shape.VoxelShapes
import net.minecraft.world.BlockView
import net.minecraft.world.World
import net.minecraft.world.WorldAccess
import online.maestoso.geodesic.util.Math
import online.maestoso.geodesic.block.entity.ForcefieldEmitterBlockEntity
import online.maestoso.geodesic.block.entity.GeodesicBlockEntityTypes
import online.maestoso.geodesic.item.ProgramCardItem
import org.quiltmc.qsl.block.extensions.api.QuiltBlockSettings
import kotlin.jvm.optionals.getOrNull

object ForcefieldEmitterBlock : BlockWithEntity(QuiltBlockSettings.create().strength(8.0f).requiresTool()) {
    override fun getRenderType(state: BlockState?) = BlockRenderType.MODEL
    override fun createBlockEntity(pos: BlockPos, state: BlockState) = ForcefieldEmitterBlockEntity(pos, state)
    override fun getOutlineShape(
        state: BlockState?,
        world: BlockView?,
        pos: BlockPos?,
        context: ShapeContext?
    ) = VoxelShapes.union(
        Math.cuboidpx(1, 0, 1, 15, 10, 15), // base
        Math.cuboidpx(0, 0, 0, 2, 12, 2), // pillar1
        Math.cuboidpx(14, 0, 0, 16, 12, 2), // pillar2
        Math.cuboidpx(14, 0, 14, 16, 12, 16), // pillar3
        Math.cuboidpx(0, 0, 14, 2, 12, 16), // pillar4
        Math.cuboidpx(7, 10, 7, 9, 12, 9), // focus
        Math.cuboidpx(6, 10, 6, 7, 14, 7), // pillar5
        Math.cuboidpx(9, 10, 6, 10, 14, 7), // pillar6
        Math.cuboidpx(9, 10, 9, 10, 14, 10), // pillar7
        Math.cuboidpx(6, 10, 9, 7, 14, 10) // pillar8
    )

    override fun <T : BlockEntity?> getTicker(
        world: World?,
        state: BlockState?,
        type: BlockEntityType<T>?
    ): BlockEntityTicker<T>? {
        return checkType(type, GeodesicBlockEntityTypes.FORCEFIELD_EMITTER) {world, pos, state, entity ->
            entity.tick(world, pos, state)
        }
    }
    override fun onBreak(world: World?, pos: BlockPos?, state: BlockState?, player: PlayerEntity?) {
        super.onBreak(world, pos, state, player)
        (world?.getBlockEntity(pos) as? ForcefieldEmitterBlockEntity)?.destroyForcefield()
    }

    override fun onUse(
        state: BlockState?,
        world: World?,
        pos: BlockPos?,
        player: PlayerEntity?,
        hand: Hand?,
        hit: BlockHitResult?
    ): ActionResult {
        val entity = world?.getBlockEntity(pos) as? ForcefieldEmitterBlockEntity ?: return ActionResult.FAIL
        val nbt = player?.mainHandStack?.nbt
        when(player?.mainHandStack?.item) {
            ProgramCardItem.BLANK -> {
                entity.attributeStore.range = 0
                entity.attributeStore.predicate = "false"
                entity.attributeStore.blockType = ForcefieldBlock.FORCEFIELD

                player.sendMessage(Text.literal("Cleared all emitter attributes"), true)
                entity.destroyForcefield()
                return ActionResult.SUCCESS
            }
            ProgramCardItem.COLOR -> {
                nbt ?: return ActionResult.FAIL
                entity.attributeStore.blockType = Registries.BLOCK.get(Identifier(nbt.getString("Color")))
                player.sendMessage(Text.literal("Set color to ").append(Text.translatable(entity.attributeStore.blockType.translationKey)), true)
                entity.destroyForcefield()
                return ActionResult.SUCCESS
            }
            ProgramCardItem.RANGE -> {
                nbt ?: return ActionResult.FAIL
                entity.attributeStore.range = nbt.getInt("Range")
                player.sendMessage(Text.literal("Set range to ").append(Text.literal(entity.attributeStore.range.toString())), true)
                entity.destroyForcefield()
                return ActionResult.SUCCESS
            }
            ProgramCardItem.SHAPE -> {
                nbt ?: return ActionResult.FAIL
                entity.attributeStore.predicate = nbt.getString("Shape")
                player.sendMessage(Text.literal("Updated shape"), true)
                entity.destroyForcefield()
                return ActionResult.SUCCESS
            }
            else -> return ActionResult.PASS
        }
    }
}

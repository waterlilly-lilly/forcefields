package online.maestoso.geodesic.block

import net.minecraft.block.BlockState
import net.minecraft.block.ShapeContext
import net.minecraft.block.TransparentBlock
import net.minecraft.util.math.BlockPos
import net.minecraft.util.shape.VoxelShapes
import net.minecraft.world.BlockView
import org.quiltmc.qsl.block.extensions.api.QuiltBlockSettings

class ForcefieldBlock : TransparentBlock(QuiltBlockSettings.create().strength(Float.MAX_VALUE).nonOpaque().allowsSpawning { _, _, _, _ -> false }
    .solidBlock { _, _, _ -> false }.suffocates { _, _, _ -> false }.blockVision { _, _, _ -> false }) {
    override fun getCameraCollisionShape(state: BlockState?, world: BlockView?, pos: BlockPos?, context: ShapeContext?) = VoxelShapes.empty()
    override fun getAmbientOcclusionLightLevel(state: BlockState?, world: BlockView?, pos: BlockPos?) = 1f
    override fun isTranslucent(state: BlockState?, world: BlockView?, pos: BlockPos?) = true

    companion object {
        val FORCEFIELD = ForcefieldBlock()
        val INVISIBLE_FORCEFIELD = ForcefieldBlock()
        val WHITE_COLORED_FORCEFIELD = ForcefieldBlock()
        val ORANGE_COLORED_FORCEFIELD = ForcefieldBlock()
        val MAGENTA_COLORED_FORCEFIELD = ForcefieldBlock()
        val LIGHT_BLUE_COLORED_FORCEFIELD = ForcefieldBlock()
        val YELLOW_COLORED_FORCEFIELD = ForcefieldBlock()
        val LIME_COLORED_FORCEFIELD = ForcefieldBlock()
        val PINK_COLORED_FORCEFIELD = ForcefieldBlock()
        val GRAY_COLORED_FORCEFIELD = ForcefieldBlock()
        val LIGHT_GRAY_COLORED_FORCEFIELD = ForcefieldBlock()
        val CYAN_COLORED_FORCEFIELD = ForcefieldBlock()
        val PURPLE_COLORED_FORCEFIELD = ForcefieldBlock()
        val BLUE_COLORED_FORCEFIELD = ForcefieldBlock()
        val BROWN_COLORED_FORCEFIELD = ForcefieldBlock()
        val GREEN_COLORED_FORCEFIELD = ForcefieldBlock()
        val RED_COLORED_FORCEFIELD = ForcefieldBlock()
        val BLACK_COLORED_FORCEFIELD = ForcefieldBlock()
    }
}

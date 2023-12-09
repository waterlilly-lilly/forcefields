package online.maestoso.geodesic

import net.minecraft.block.*
import net.minecraft.registry.Registries
import net.minecraft.state.StateManager
import net.minecraft.state.property.BooleanProperty
import net.minecraft.util.math.BlockPos
import net.minecraft.util.shape.VoxelShape
import net.minecraft.util.shape.VoxelShapes
import net.minecraft.world.BlockView
import org.quiltmc.loader.api.ModContainer
import org.quiltmc.qkl.library.registry.*
import org.quiltmc.qsl.block.extensions.api.QuiltBlockSettings

class GeodesicBlocks(mod: ModContainer) {
    init {
        Registries.BLOCK(mod.metadata().id()) {
            FORCEFIELD withId "forcefield"
            INVISIBLE_FORCEFIELD withId "invisible_forcefield"
            WHITE_COLORED_FORCEFIELD withId "white_colored_forcefield"
            ORANGE_COLORED_FORCEFIELD withId "orange_colored_forcefield"
            MAGENTA_COLORED_FORCEFIELD withId "magenta_colored_forcefield"
            LIGHT_BLUE_COLORED_FORCEFIELD withId "light_blue_colored_forcefield"
            YELLOW_COLORED_FORCEFIELD withId "yellow_colored_forcefield"
            LIME_COLORED_FORCEFIELD withId "lime_colored_forcefield"
            PINK_COLORED_FORCEFIELD withId "pink_colored_forcefield"
            GRAY_COLORED_FORCEFIELD withId "gray_colored_forcefield"
            LIGHT_GRAY_COLORED_FORCEFIELD withId "light_gray_colored_forcefield"
            CYAN_COLORED_FORCEFIELD withId "cyan_colored_forcefield"
            PURPLE_COLORED_FORCEFIELD withId "purple_colored_forcefield"
            BLUE_COLORED_FORCEFIELD withId "blue_colored_forcefield"
            BROWN_COLORED_FORCEFIELD withId "brown_colored_forcefield"
            GREEN_COLORED_FORCEFIELD withId "green_colored_forcefield"
            RED_COLORED_FORCEFIELD withId "red_colored_forcefield"
            BLACK_COLORED_FORCEFIELD withId "black_colored_forcefield"
        }
    }
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
    class ForcefieldBlock : TransparentBlock(QuiltBlockSettings.create().strength(Float.MAX_VALUE).nonOpaque().allowsSpawning { _, _, _, _ -> false }
        .solidBlock { _, _, _ -> false }.suffocates { _, _, _ -> false }.blockVision { _, _, _ -> false }) {
        override fun getCameraCollisionShape(state: BlockState?, world: BlockView?, pos: BlockPos?, context: ShapeContext?) = VoxelShapes.empty()
        override fun getAmbientOcclusionLightLevel(state: BlockState?, world: BlockView?, pos: BlockPos?) = 1f
        override fun isTranslucent(state: BlockState?, world: BlockView?, pos: BlockPos?) = true
    }
}

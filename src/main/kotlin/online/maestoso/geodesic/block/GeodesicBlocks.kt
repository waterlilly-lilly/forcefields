package online.maestoso.geodesic.block

import net.minecraft.registry.Registries
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.tag.TagKey
import net.minecraft.util.Identifier
import org.quiltmc.loader.api.ModContainer
import org.quiltmc.qkl.library.registry.*

class GeodesicBlocks(mod: ModContainer) {
    companion object {
        val FORCEFIELD_BLOCKS = TagKey.of(RegistryKeys.BLOCK, Identifier("geodesic:forcefields"))
    }
    init {
        Registries.BLOCK(mod.metadata().id()) {
            // Forcefield Blocks
            ForcefieldBlock.FORCEFIELD withId "forcefield"
            ForcefieldBlock.INVISIBLE_FORCEFIELD withId "invisible_forcefield"
            ForcefieldBlock.WHITE_COLORED_FORCEFIELD withId "white_colored_forcefield"
            ForcefieldBlock.ORANGE_COLORED_FORCEFIELD withId "orange_colored_forcefield"
            ForcefieldBlock.MAGENTA_COLORED_FORCEFIELD withId "magenta_colored_forcefield"
            ForcefieldBlock.LIGHT_BLUE_COLORED_FORCEFIELD withId "light_blue_colored_forcefield"
            ForcefieldBlock.YELLOW_COLORED_FORCEFIELD withId "yellow_colored_forcefield"
            ForcefieldBlock.LIME_COLORED_FORCEFIELD withId "lime_colored_forcefield"
            ForcefieldBlock.PINK_COLORED_FORCEFIELD withId "pink_colored_forcefield"
            ForcefieldBlock.GRAY_COLORED_FORCEFIELD withId "gray_colored_forcefield"
            ForcefieldBlock.LIGHT_GRAY_COLORED_FORCEFIELD withId "light_gray_colored_forcefield"
            ForcefieldBlock.CYAN_COLORED_FORCEFIELD withId "cyan_colored_forcefield"
            ForcefieldBlock.PURPLE_COLORED_FORCEFIELD withId "purple_colored_forcefield"
            ForcefieldBlock.BLUE_COLORED_FORCEFIELD withId "blue_colored_forcefield"
            ForcefieldBlock.BROWN_COLORED_FORCEFIELD withId "brown_colored_forcefield"
            ForcefieldBlock.GREEN_COLORED_FORCEFIELD withId "green_colored_forcefield"
            ForcefieldBlock.RED_COLORED_FORCEFIELD withId "red_colored_forcefield"
            ForcefieldBlock.BLACK_COLORED_FORCEFIELD withId "black_colored_forcefield"
            ForcefieldEmitterBlock withId "emitter"
        }
    }


}

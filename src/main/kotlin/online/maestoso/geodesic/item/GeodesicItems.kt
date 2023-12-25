package online.maestoso.geodesic.item

import net.minecraft.item.BlockItem
import net.minecraft.registry.Registries
import online.maestoso.geodesic.block.ForcefieldEmitterBlock
import org.quiltmc.loader.api.ModContainer
import org.quiltmc.qkl.library.registry.*
import org.quiltmc.qsl.item.setting.api.QuiltItemSettings

class GeodesicItems(mod: ModContainer) {
    companion object {

    }
    init {
        Registries.ITEM(mod.metadata().id()) {
            BlockItem(ForcefieldEmitterBlock, QuiltItemSettings()) withId "emitter"
            ProgramCardItem.BLANK withId "program_card_blank"
            ProgramCardItem.RANGE withId "program_card_range"
            ProgramCardItem.SHAPE withId "program_card_shape"
            ProgramCardItem.COLOR withId "program_card_color"
        }
    }
}

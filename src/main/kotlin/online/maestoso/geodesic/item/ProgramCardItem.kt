package online.maestoso.geodesic.item

import net.minecraft.client.item.TooltipContext
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.registry.Registries
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.tag.TagKey
import net.minecraft.text.Text
import net.minecraft.util.Identifier
import net.minecraft.util.Rarity
import net.minecraft.world.World
import org.quiltmc.qsl.item.setting.api.QuiltItemSettings

class ProgramCardItem : Item(QuiltItemSettings().maxCount(1).fireproof().rarity(Rarity.EPIC)) {
    override fun appendTooltip(
        stack: ItemStack?,
        world: World?,
        tooltip: MutableList<Text>?,
        context: TooltipContext?
    ) {
        stack!!
        world!!
        tooltip!!
        context!!
        val nbt = stack.nbt ?: return
        if(nbt.contains("Range")) {
            tooltip.add(Text.literal("Range: " + nbt.getInt("Range")))
        }
        if(nbt.contains("Shape")) {
            tooltip.add(Text.literal("Shape: " + nbt.getString("Shape")))
        }
        if(nbt.contains("Color")) {
            tooltip.add(Text.literal("Color: " + Text.translatable(Registries.BLOCK.get(Identifier(nbt.getString("Color"))).translationKey)))
        }
    }
    companion object {
        val PROGRAM_CARDS = TagKey.of(RegistryKeys.ITEM, Identifier("geodesic:program_cards"))
        val BLANK = ProgramCardItem()
        val RANGE = ProgramCardItem()
        val SHAPE = ProgramCardItem()
        val COLOR = ProgramCardItem()
    }
}

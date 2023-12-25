package online.maestoso.geodesic.util

import net.minecraft.entity.player.PlayerEntity
import net.minecraft.inventory.Inventories
import net.minecraft.inventory.Inventory
import net.minecraft.item.ItemStack
import net.minecraft.util.collection.DefaultedList

interface InventoryImpl : Inventory {
    var items: DefaultedList<ItemStack>
    override fun clear() {
        items.clear()
    }

    override fun size() = items.size

    override fun isEmpty() = items.isEmpty()

    override fun getStack(slot: Int) = items[slot]

    override fun removeStack(slot: Int, amount: Int): ItemStack {
        val result = Inventories.splitStack(items, slot, amount)
        if(!result.isEmpty) markDirty()
        return result
    }

    override fun removeStack(slot: Int): ItemStack = Inventories.removeStack(items, slot)
    override fun setStack(slot: Int, stack: ItemStack?) {
        items[slot] = stack
        if(stack?.count!! > stack.maxCount) {
            stack.count = stack.maxCount
        }
    }
    override fun canPlayerUse(player: PlayerEntity?) = true
}

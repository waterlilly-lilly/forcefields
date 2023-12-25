package online.maestoso.geodesic.util

import net.minecraft.nbt.NbtCompound
import net.minecraft.nbt.NbtIo
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Vec3d
import net.minecraft.world.PersistentState
import java.io.File
import java.util.*

class EmitterBlockStore(private val tracked: Array<Entry>) : Iterable<EmitterBlockStore.Entry> {
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

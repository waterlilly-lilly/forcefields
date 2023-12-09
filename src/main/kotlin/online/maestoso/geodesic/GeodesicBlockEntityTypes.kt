package online.maestoso.geodesic

import net.minecraft.block.Block
import net.minecraft.block.entity.BlockEntity
import net.minecraft.block.entity.BlockEntityType
import net.minecraft.registry.Registries
import org.quiltmc.loader.api.ModContainer
import org.quiltmc.qkl.library.registry.*
import org.quiltmc.qsl.block.entity.api.QuiltBlockEntityTypeBuilder
import kotlin.reflect.full.primaryConstructor

class GeodesicBlockEntityTypes(mod: ModContainer) {

    init {
        Registries.BLOCK_ENTITY_TYPE(mod.metadata().id()) {
        }
    }
    companion object {
        private inline fun<reified T: BlockEntity> withBlocks(vararg blocks: Block): BlockEntityType<T> {
            return QuiltBlockEntityTypeBuilder.create({ pos, state -> T::class.primaryConstructor!!.call(pos, state)}, *blocks).build()
        }
    }
}

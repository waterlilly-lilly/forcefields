package online.maestoso.geodesic.client
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap
import net.minecraft.client.render.RenderLayer
import online.maestoso.geodesic.block.ForcefieldBlock
import online.maestoso.geodesic.block.ForcefieldEmitterBlock
import online.maestoso.geodesic.block.GeodesicBlocks
import org.quiltmc.loader.api.ModContainer
import org.quiltmc.loader.api.minecraft.ClientOnly
import org.quiltmc.qsl.base.api.entrypoint.client.ClientModInitializer

@ClientOnly
object GeodesicClient : ClientModInitializer {
    override fun onInitializeClient(mod: ModContainer) {
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getTranslucent(),
            ForcefieldBlock.FORCEFIELD,
            ForcefieldBlock.INVISIBLE_FORCEFIELD,
            ForcefieldBlock.WHITE_COLORED_FORCEFIELD,
            ForcefieldBlock.ORANGE_COLORED_FORCEFIELD,
            ForcefieldBlock.MAGENTA_COLORED_FORCEFIELD,
            ForcefieldBlock.LIGHT_BLUE_COLORED_FORCEFIELD,
            ForcefieldBlock.YELLOW_COLORED_FORCEFIELD,
            ForcefieldBlock.LIME_COLORED_FORCEFIELD,
            ForcefieldBlock.PINK_COLORED_FORCEFIELD,
            ForcefieldBlock.GRAY_COLORED_FORCEFIELD,
            ForcefieldBlock.LIGHT_GRAY_COLORED_FORCEFIELD,
            ForcefieldBlock.CYAN_COLORED_FORCEFIELD,
            ForcefieldBlock.PURPLE_COLORED_FORCEFIELD,
            ForcefieldBlock.BLUE_COLORED_FORCEFIELD,
            ForcefieldBlock.BROWN_COLORED_FORCEFIELD,
            ForcefieldBlock.GREEN_COLORED_FORCEFIELD,
            ForcefieldBlock.RED_COLORED_FORCEFIELD,
            ForcefieldBlock.BLACK_COLORED_FORCEFIELD,
            ForcefieldEmitterBlock
        )
    }
}

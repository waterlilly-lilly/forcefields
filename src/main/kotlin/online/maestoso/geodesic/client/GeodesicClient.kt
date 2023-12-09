package online.maestoso.geodesic.client
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap
import net.minecraft.client.render.RenderLayer
import online.maestoso.geodesic.GeodesicBlocks
import org.quiltmc.loader.api.ModContainer
import org.quiltmc.loader.api.minecraft.ClientOnly
import org.quiltmc.qsl.base.api.entrypoint.client.ClientModInitializer

@ClientOnly
object GeodesicClient : ClientModInitializer {
    override fun onInitializeClient(mod: ModContainer) {
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getTranslucent(),
            GeodesicBlocks.FORCEFIELD,
            GeodesicBlocks.INVISIBLE_FORCEFIELD,
            GeodesicBlocks.WHITE_COLORED_FORCEFIELD,
            GeodesicBlocks.ORANGE_COLORED_FORCEFIELD,
            GeodesicBlocks.MAGENTA_COLORED_FORCEFIELD,
            GeodesicBlocks.LIGHT_BLUE_COLORED_FORCEFIELD,
            GeodesicBlocks.YELLOW_COLORED_FORCEFIELD,
            GeodesicBlocks.LIME_COLORED_FORCEFIELD,
            GeodesicBlocks.PINK_COLORED_FORCEFIELD,
            GeodesicBlocks.GRAY_COLORED_FORCEFIELD,
            GeodesicBlocks.LIGHT_GRAY_COLORED_FORCEFIELD,
            GeodesicBlocks.CYAN_COLORED_FORCEFIELD,
            GeodesicBlocks.PURPLE_COLORED_FORCEFIELD,
            GeodesicBlocks.BLUE_COLORED_FORCEFIELD,
            GeodesicBlocks.BROWN_COLORED_FORCEFIELD,
            GeodesicBlocks.GREEN_COLORED_FORCEFIELD,
            GeodesicBlocks.RED_COLORED_FORCEFIELD,
            GeodesicBlocks.BLACK_COLORED_FORCEFIELD
        )
    }
}

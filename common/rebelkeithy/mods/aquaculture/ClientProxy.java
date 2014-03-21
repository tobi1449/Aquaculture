package rebelkeithy.mods.aquaculture;

import net.minecraft.client.renderer.entity.RenderFish;
import rebelkeithy.mods.aquaculture.block.TileEntityTackleBox;
import rebelkeithy.mods.aquaculture.client.render.RenderTackleBox;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy {
	public void registerParticles() {
	}

	public void registerModelRenderers() {
		RenderingRegistry.registerEntityRenderingHandler(EntityCustomFishHook.class, new RenderFish());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTackleBox.class, new RenderTackleBox());
	}
}

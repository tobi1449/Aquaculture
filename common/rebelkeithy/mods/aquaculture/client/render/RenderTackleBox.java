package rebelkeithy.mods.aquaculture.client.render;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;

import rebelkeithy.mods.aquaculture.client.render.model.ModelTackleBox;

public class RenderTackleBox extends TileEntitySpecialRenderer {

	private final ModelTackleBox model;

	public RenderTackleBox() {
		this.model = new ModelTackleBox();
	}

	@Override
	public void renderTileEntityAt(TileEntity te, double x, double y, double z, float scale) {
		GL11.glPushMatrix();
		GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
		ResourceLocation textures = new ResourceLocation("aquaculture:textures/models/TackleBox.png");
		Minecraft.getMinecraft().renderEngine.bindTexture(textures);

		GL11.glPushMatrix();
		GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
		this.model.render();
		GL11.glPopMatrix();
		GL11.glPopMatrix();
	}

}
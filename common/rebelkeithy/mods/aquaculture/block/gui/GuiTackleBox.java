package rebelkeithy.mods.aquaculture.block.gui;

import java.util.List;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.item.ItemFishingRod;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import rebelkeithy.mods.aquaculture.block.TileEntityTackleBox;
import cpw.mods.fml.client.FMLClientHandler;

public class GuiTackleBox extends GuiContainer {
	private final ResourceLocation TEXTURE = new ResourceLocation("aquaculture", "textures/gui/guiTackleBox.png");

	private TileEntityTackleBox tackleBox;
	private int lureType = 0;
	private int colorType = 0;

	public GuiTackleBox(InventoryPlayer playerInv, TileEntityTackleBox tackleBox) {
		super(new ContainerTackleBox(playerInv, tackleBox));
		this.tackleBox = tackleBox;
	}

	@Override
	public void initGui() {
		super.initGui();
		int y1 = guiTop + 22;
		int x1 = guiLeft + 15;

		buttonList.add(new AquacultureGuiButton(0, x1, y1, 8, 8, "Basic"));
		buttonList.add(new AquacultureGuiButton(1, x1, y1 + 9, 8, 8, "Feathered"));
		buttonList.add(new AquacultureGuiButton(2, x1, y1 + 18, 8, 8, "Bulb"));
		buttonList.add(new AquacultureGuiButton(3, x1, y1 + 27, 8, 8, "Double Hook"));
		x1 += 9;
		buttonList.add(new AquacultureGuiButton(4, x1, y1, 8, 8, "Large"));
		buttonList.add(new AquacultureGuiButton(5, x1, y1 + 9, 8, 8, "Bead"));
		buttonList.add(new AquacultureGuiButton(6, x1, y1 + 18, 8, 8, "Tube"));
		buttonList.add(new AquacultureGuiButton(7, x1, y1 + 27, 8, 8, "Flaired"));
		x1 += 9;
		buttonList.add(new AquacultureGuiButton(8, x1, y1, 8, 8, "Double Bead"));
		buttonList.add(new AquacultureGuiButton(9, x1, y1 + 9, 8, 8, "Spined"));
		buttonList.add(new AquacultureGuiButton(10, x1, y1 + 18, 8, 8, "Large Bead"));
		buttonList.add(new AquacultureGuiButton(11, x1, y1 + 27, 8, 8, "Creeper"));

		x1 += 45;
		buttonList.add(new AquacultureGuiButton(12, x1, y1, 8, 8, "Red"));
		buttonList.add(new AquacultureGuiButton(14, x1, y1 + 9, 8, 8, "Yellow"));
		buttonList.add(new AquacultureGuiButton(16, x1, y1 + 18, 8, 8, "Orange"));
		buttonList.add(new AquacultureGuiButton(18, x1, y1 + 27, 8, 8, "White"));
		x1 += 9;
		buttonList.add(new AquacultureGuiButton(13, x1, y1, 8, 8, "Blue"));
		buttonList.add(new AquacultureGuiButton(15, x1, y1 + 9, 8, 8, "Green"));
		buttonList.add(new AquacultureGuiButton(17, x1, y1 + 18, 8, 8, "Purple"));
		buttonList.add(new AquacultureGuiButton(19, x1, y1 + 27, 8, 8, "Gray"));
	}

	@Override
	protected void actionPerformed(GuiButton par1GuiButton) {
		if(tackleBox.getStackInSlot(0) != null) {
			ItemStack itemstack = tackleBox.getStackInSlot(0);
			if(par1GuiButton.id > 11) {
				colorType = par1GuiButton.id - 12;
				setTag(itemstack, "color", colorType);
			} else {
				lureType = par1GuiButton.id;
				setTag(itemstack, "lureType", lureType);
			}

		}
	}

	public void setTag(ItemStack itemstack, String tags, int id) {
		NBTTagCompound tag = itemstack.getTagCompound();
		if(tag == null) {
			tag = new NBTTagCompound();
		}
		tag.setInteger(tags, lureType);
		itemstack.setTagCompound(tag);
		itemstack.setItemName(lureType + " Lure ID");
		tackleBox.setInventorySlotContents(0, itemstack);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		FMLClientHandler.instance().getClient().renderEngine.bindTexture(this.TEXTURE);
		int x = (this.width - this.xSize) / 2;
		int y = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(x, y, 0, 0, this.xSize, this.ySize);
	}

	float y1;
	double plus = 0.005;

	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2) {
		GL11.glPushMatrix();
		GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0F);

		FMLClientHandler.instance().getClient().renderEngine.bindTexture(new ResourceLocation("aquaculture", "textures/gui/lure/lures.png"));
		if(y1 > 1) {
			plus = -0.005;
		}
		if(y1 < 0) {
			plus = 0.005;
		}
		y1 += plus;

		GL11.glTranslated(0, y1, 0);
		float x = (this.xSize) / 2 - 43;
		float y = (this.ySize) / 2 - 58;
		float baseX = 0.00969827586f;
		float baseY = 0.01761252446f;

		Tessellator tessellator = Tessellator.instance;
		tessellator.startDrawingQuads();
		tessellator.addVertexWithUV(x, y + 30, 0, baseX * colorType, baseY * (lureType + 1));
		tessellator.addVertexWithUV(x + 30, y + 30, 0, baseX * (colorType + 1), baseY * (lureType + 1));
		tessellator.addVertexWithUV(x + 30, y, 0, baseX * (colorType + 1), baseY * lureType);
		tessellator.addVertexWithUV(x, y, 0, baseX * colorType, baseY * lureType);
		tessellator.draw();
		GL11.glPopMatrix();
	}

}

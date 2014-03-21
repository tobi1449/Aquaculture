package rebelkeithy.mods.aquaculture.block.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import rebelkeithy.mods.aquaculture.block.TileEntityTackleBox;
import cpw.mods.fml.common.network.IGuiHandler;

public class AquacultureGuiHandler implements IGuiHandler {

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		switch(ID) {
		case 0:
			return new GuiTackleBox(new ContainerTackleBox(player.inventory, (TileEntityTackleBox) world.getBlockTileEntity(x, y, z)));
		default:
			return null;
		}

	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		switch(ID) {
		case 0:
			return new ContainerTackleBox(player.inventory, (TileEntityTackleBox) world.getBlockTileEntity(x, y, z));
		default:
			return null;
		}
	}

}

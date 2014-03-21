package rebelkeithy.mods.aquaculture.block;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import rebelkeithy.mods.aquaculture.Aquaculture;

public class BlockTackleBox extends BlockContainer {

	public BlockTackleBox(int par1) {
		super(par1, Material.iron);
		this.setUnlocalizedName("tackleBox");
		this.setBlockBounds(0.1f, 0f, 0.2f, 0.9f, 0.5f, 0.8f);
		this.setCreativeTab(Aquaculture.tab);
	}

	@Override
	public int getRenderType() {
		return -1;
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int metadata, float what, float these, float are) {
		TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
		if(tileEntity == null || player.isSneaking()) {
			return false;
		}
		player.openGui(Aquaculture.instance, 0, world, x, y, z);
		
		return true;
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityTackleBox();
	}

}

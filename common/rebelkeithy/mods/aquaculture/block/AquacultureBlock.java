package rebelkeithy.mods.aquaculture.block;

import net.minecraft.block.Block;
import rebelkeithy.mods.aquaculture.Config;
import cpw.mods.fml.common.registry.GameRegistry;

public enum AquacultureBlock {
	INSTANCE;

	public static final Block tackleBox = new BlockTackleBox(Config.tackleBoxID);

	public void register() {
		this.register(tackleBox);
		GameRegistry.registerTileEntity(TileEntityTackleBox.class, "containerTackleBox");
	}

	private void register(Block block) {
		GameRegistry.registerBlock(block, block.getUnlocalizedName());
		this.name(block, block.getUnlocalizedName());
	}

	private void name(Block block, String tag) {
		if(!tag.contains("block.")) {
			tag = "block." + tag;
		}
	}
}

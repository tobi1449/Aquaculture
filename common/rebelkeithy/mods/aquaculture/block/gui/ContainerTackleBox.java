package rebelkeithy.mods.aquaculture.block.gui;

import rebelkeithy.mods.aquaculture.block.TileEntityTackleBox;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryCraftResult;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerTackleBox extends Container {

	public TileEntityTackleBox tackleBox;

	public ContainerTackleBox(InventoryPlayer playerInv, TileEntityTackleBox tackleBox) {
		this.tackleBox = tackleBox;
		addSlotToContainer(new Slot(tackleBox, 0, 115, 20));
		//addSlotToContainer(new Slot(tackleBox, 1, 115, 45));
		this.bindPlayerInventory(playerInv);
	}

	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return true;
	}

	private void bindPlayerInventory(InventoryPlayer playerInv) {
		for(int x = 0; x < 3; x++) {
			for(int y = 0; y < 9; y++) {
				this.addSlotToContainer(new Slot(playerInv, (y + x * 9 + 9), (8 + y * 18), (84 + x * 18)));
			}
		}

		for(int y = 0; y < 9; y++) {
			this.addSlotToContainer(new Slot(playerInv, y, (8 + y * 18), 142));
		}
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer paramEntityPlayer, int paramInt) {
		return null;
	}
}

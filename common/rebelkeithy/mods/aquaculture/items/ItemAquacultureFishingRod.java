package rebelkeithy.mods.aquaculture.items;

import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFishingRod;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import rebelkeithy.mods.aquaculture.EntityCustomFishHook;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemAquacultureFishingRod extends ItemFishingRod {
	public Icon usingIcon;
	public String type;
	public int enchantability;

	public ItemAquacultureFishingRod(int i, int d, int enchantability, String type) {
		super(i);
		setMaxDamage(d);
		setMaxStackSize(1);
		this.type = type;
		this.enchantability = enchantability;
	}

	@Override
	public int getItemEnchantability() {
		return enchantability;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer) {
		if(entityplayer.fishEntity != null) {
			int i = entityplayer.fishEntity.catchFish();
			itemstack.damageItem(i, entityplayer);
			entityplayer.swingItem();

			NBTTagCompound tag = itemstack.getTagCompound();
			if(tag == null) {
				tag = new NBTTagCompound();
				itemstack.setTagCompound(tag);
			}

			tag.setBoolean("using", false);
		} else {
			NBTTagCompound tag = itemstack.getTagCompound();
			if(tag == null) {
				tag = new NBTTagCompound();
				itemstack.setTagCompound(tag);
			}

			world.playSoundAtEntity(entityplayer, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
			if(!world.isRemote) {
				world.spawnEntityInWorld(new EntityCustomFishHook(world, entityplayer, tag.getInteger("lureType"), tag.getInteger("color")));
			}
			entityplayer.swingItem();

			tag.setBoolean("using", true);
		}
		return itemstack;
	}

	@Override
	public Icon getIcon(ItemStack stack, int renderPass, EntityPlayer player, ItemStack usingItem, int useRemaining) {
		NBTTagCompound tag = stack.getTagCompound();
		if(tag == null) {
			tag = new NBTTagCompound();
			stack.setTagCompound(tag);
		}

		if(tag.hasKey("using"))
			;
		{
			boolean using = tag.getBoolean("using");

			if(using) {
				return usingIcon;
			}
		}
		return itemIcon;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		itemIcon = par1IconRegister.registerIcon(this.iconString);
		usingIcon = par1IconRegister.registerIcon("aquaculture:" + type + "FishingRodUsing");
	}

	@Override
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
		if(par1ItemStack.stackTagCompound != null) {
			int owner = par1ItemStack.stackTagCompound.getInteger("lureType");
			par3List.add("owner: " + owner);
		}
	}
}
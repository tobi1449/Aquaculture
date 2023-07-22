package com.teammetallurgy.aquaculture.item.neptunium;

import com.teammetallurgy.aquaculture.Aquaculture;
import com.teammetallurgy.aquaculture.init.AquaItems;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nonnull;
import java.util.UUID;

@Mod.EventBusSubscriber(modid = Aquaculture.MOD_ID)
public class NeptuniumArmor extends ArmorItem {
    private static final AttributeModifier INCREASED_SWIM_SPEED = new AttributeModifier(UUID.fromString("d820cadc-2d19-421c-b19f-4c1f5b84a418"), "Neptunium Boots swim speed boost", 0.5D, AttributeModifier.Operation.ADDITION);
    private String texture;

    public NeptuniumArmor(ArmorMaterial armorMaterial, EquipmentSlot equipmentSlot) {
        super(armorMaterial, equipmentSlot, new Item.Properties().tab(Aquaculture.GROUP));
    }

    @Override
    public void onArmorTick(@Nonnull ItemStack stack, Level world, Player player) {
        if (player.isEyeInFluid(FluidTags.WATER)) {
            if (this.slot == EquipmentSlot.HEAD) {
                player.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 20, 0, false, false, false));
            } else if (this.slot == EquipmentSlot.CHEST) {
                player.addEffect(new MobEffectInstance(MobEffects.WATER_BREATHING, 20, 0, false, false, false));
            } else if (this.slot == EquipmentSlot.LEGS) {
                if (!player.isCrouching() && !player.isSwimming()) {
                    player.setDeltaMovement(player.getDeltaMovement().add(0, player.fallDistance, 0));
                }
            }
        }
    }

    @SubscribeEvent
    public static void onLivingTick(TickEvent.PlayerTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            Player player = event.player;

            if (!player.level.isClientSide) {
                AttributeInstance swimSpeed = player.getAttribute(ForgeMod.SWIM_SPEED.get());
                if (swimSpeed != null) {
                    if (player.isInWater() && player.getItemBySlot(EquipmentSlot.FEET).getItem() == AquaItems.NEPTUNIUM_BOOTS.get()) {
                        if (!swimSpeed.hasModifier(INCREASED_SWIM_SPEED)) {
                            swimSpeed.addPermanentModifier(INCREASED_SWIM_SPEED);
                        }
                    } else {
                        if (swimSpeed.hasModifier(INCREASED_SWIM_SPEED)) {
                            swimSpeed.removeModifier(INCREASED_SWIM_SPEED);
                        }
                    }
                }
            }
        }
    }

    public Item setArmorTexture(String string) {
        this.texture = string;
        return this;
    }

    @Override
    public String getArmorTexture(@Nonnull ItemStack stack, Entity entity, EquipmentSlot slot, String layer) {
        return "aquaculture:textures/armor/" + this.texture + ".png";
    }
}
package net.gobies.potionrings2.item.potionrings;

import net.gobies.potionrings2.PRConfig;
import net.gobies.potionrings2.init.PRItems;
import net.gobies.potionrings2.util.RingHandler;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class PotionRingItem extends Item implements ICurioItem {
    public PotionRingItem(Properties properties) {
        super(properties.stacksTo(1).rarity(Rarity.COMMON));
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        LivingEntity entity = slotContext.entity();
        CompoundTag nbt = stack.getTag();

        if (nbt != null && nbt.contains("Effect")) {
            String effectIdString = nbt.getString("Effect");
            ResourceLocation effectId = new ResourceLocation(effectIdString);
            MobEffect effect = ForgeRegistries.MOB_EFFECTS.getValue(effectId);

            if (effect != null) {
                int ringCount = RingHandler.getEquippedRingCount(entity, PRItems.PotionRing.get(), effectIdString);
                int effectLevel = Math.min(ringCount - 1, 2);
                entity.addEffect(new MobEffectInstance(effect, -1, effectLevel, true, false));
            }
        }
    }

    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        LivingEntity entity = slotContext.entity();
        CompoundTag nbt = stack.getTag();

        if (nbt != null && nbt.contains("Effect")) {
            String effectIdString = nbt.getString("Effect");
            ResourceLocation effectId = new ResourceLocation(effectIdString);
            MobEffect effect = ForgeRegistries.MOB_EFFECTS.getValue(effectId);

            if (effect != null) {
                entity.removeEffect(effect);

                // Re-apply effects for other rings
                List<ItemStack> equippedRings = RingHandler.getEquippedRings(entity, PRItems.PotionRing.get());

                for (ItemStack equippedRing : equippedRings) {
                    CompoundTag equippedRingNbt = equippedRing.getTag();
                    if (equippedRingNbt != null && equippedRingNbt.contains("Effect")) {
                        String equippedEffectIdString = equippedRingNbt.getString("Effect");
                        ResourceLocation equippedEffectId = new ResourceLocation(equippedEffectIdString);
                        MobEffect equippedEffect = ForgeRegistries.MOB_EFFECTS.getValue(equippedEffectId);

                        if (equippedEffect != null) {
                            int ringCount = RingHandler.getEquippedRingCount(entity, PRItems.PotionRing.get(), equippedEffectIdString);
                            int effectLevel = Math.min(ringCount - 1, 2);
                            entity.addEffect(new MobEffectInstance(equippedEffect, -1, effectLevel, true, false));
                        }
                    }
                }
            }
        }
    }

    @Override
    public boolean isValidRepairItem(@Nonnull ItemStack item, @NotNull ItemStack material) {
        return this == Items.GOLD_INGOT;
    }

    public static boolean hasEffect(ItemStack stack) {
        CompoundTag nbt = stack.getTag();
        return nbt != null && nbt.contains("Effect");
    }

    public static List<ItemStack> createPotionRing() {
        List<ItemStack> potionRings = new ArrayList<>();
        for (String effectIdString : PRConfig.EFFECTS.get()) {
            ResourceLocation effectId = new ResourceLocation(effectIdString);
            MobEffect effect = ForgeRegistries.MOB_EFFECTS.getValue(effectId);
            if (effect != null) {
                ItemStack potionRing = new ItemStack(PRItems.PotionRing.get());
                CompoundTag nbt = new CompoundTag();
                nbt.putString("Effect", effectIdString);
                potionRing.setTag(nbt);
                potionRings.add(potionRing);
            }
        }
        return potionRings;
    }

    @Override
    public boolean isFoil(@NotNull ItemStack pStack) {
        return hasEffect(pStack);
    }

    @Override
    public boolean canEquipFromUse(SlotContext slotContext, ItemStack stack) {
        return true;
    }

    @Override
    public @NotNull Component getName(ItemStack pStack) {
        CompoundTag nbt = pStack.getTag();
        if (nbt != null && nbt.contains("Effect")) {
            String effectIdString = nbt.getString("Effect");
            ResourceLocation effectId = new ResourceLocation(effectIdString);
            MobEffect effect = ForgeRegistries.MOB_EFFECTS.getValue(effectId);
            if (effect != null) {
                return Component.translatable("item.potionrings2.potion_ring_of", effect.getDisplayName());
            }
        }
        return super.getName(pStack);
    }
}
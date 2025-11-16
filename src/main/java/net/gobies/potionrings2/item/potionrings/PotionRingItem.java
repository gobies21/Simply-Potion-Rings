package net.gobies.potionrings2.item.potionrings;

import net.gobies.potionrings2.PRConfig;
import net.gobies.potionrings2.init.PRDataComponents;
import net.gobies.potionrings2.init.PRItems;
import net.gobies.potionrings2.util.RingHandler;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Rarity;
import org.jetbrains.annotations.NotNull;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PotionRingItem extends Item implements ICurioItem {
    public PotionRingItem(Properties properties) {
        super(properties.stacksTo(1).rarity(Rarity.COMMON));
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        LivingEntity entity = slotContext.entity();
        MobEffectInstance effectInstance = stack.get(PRDataComponents.EFFECT);

        if (effectInstance != null) {
            Holder<MobEffect> effectHolder = effectInstance.getEffect();
            MobEffect effect = effectHolder.value();

            int ringCount = RingHandler.getEquippedRingCount(entity, PRItems.PotionRing.get(), Objects.requireNonNull(BuiltInRegistries.MOB_EFFECT.getKey(effect)).toString());
            int effectLevel = Math.min(ringCount - 1, 2);

            entity.addEffect(new MobEffectInstance(effectHolder, 10, effectLevel, true, false));
        }
    }

    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        LivingEntity entity = slotContext.entity();
        MobEffectInstance removedInstance = stack.get(PRDataComponents.EFFECT);

        if (removedInstance != null) {
            Holder<MobEffect> removedHolder = removedInstance.getEffect();
            entity.removeEffect(removedHolder);

            List<ItemStack> equippedRings = RingHandler.getEquippedRings(entity, PRItems.PotionRing.get());

            for (ItemStack equippedRing : equippedRings) {
                MobEffectInstance instance = equippedRing.get(PRDataComponents.EFFECT);
                if (instance != null) {
                    Holder<MobEffect> effectHolder = instance.getEffect();
                    MobEffect effect = effectHolder.value();
                    ResourceLocation effectId = BuiltInRegistries.MOB_EFFECT.getKey(effect);

                    int ringCount = RingHandler.getEquippedRingCount(entity, PRItems.PotionRing.get(), Objects.requireNonNull(effectId).toString());
                    int effectLevel = Math.min(ringCount - 1, 2);

                    entity.addEffect(new MobEffectInstance(effectHolder, 10, effectLevel, true, false));
                }
            }
        }
    }

    @Override
    public boolean isValidRepairItem(@Nonnull ItemStack item, @NotNull ItemStack material) {
        return material.is(Items.GOLD_INGOT);
    }

    public static boolean hasEffect(ItemStack stack) {
        return stack.get(PRDataComponents.EFFECT) != null;
    }

    public static List<ItemStack> createPotionRing() {
        List<ItemStack> potionRings = new ArrayList<>();
        for (String effectIdString : PRConfig.EFFECTS.get()) {
            ResourceLocation effectId = ResourceLocation.tryParse(effectIdString);
            MobEffect effect = BuiltInRegistries.MOB_EFFECT.get(effectId);
            if (effect != null) {
                Holder<MobEffect> effectHolder = BuiltInRegistries.MOB_EFFECT.getHolder(Objects.requireNonNull(effectId))
                        .orElseThrow(() -> new IllegalStateException("Effect not found: " + effectId));
                ItemStack potionRing = new ItemStack(PRItems.PotionRing.get());
                MobEffectInstance effectInstance = new MobEffectInstance(effectHolder, 0, 0, false, false);
                potionRing.set(PRDataComponents.EFFECT, effectInstance);
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
    public @NotNull Component getName(ItemStack stack) {
        MobEffectInstance instance = stack.get(PRDataComponents.EFFECT);
        if (instance != null) {
            MobEffect effect = instance.getEffect().value();
            return Component.translatable("item.potionrings2.potion_ring_of", effect.getDisplayName());
        }
        return super.getName(stack);
    }
}
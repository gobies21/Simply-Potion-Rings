package net.gobies.potionrings2.item.potionrings;

import net.gobies.potionrings2.util.RingHandler;
import net.gobies.potionrings2.item.PRItems;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import org.jetbrains.annotations.NotNull;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

public class RingSpeedItem extends Item implements ICurioItem {
    public RingSpeedItem(Properties properties) {
        super(properties.stacksTo(1).rarity(Rarity.COMMON));
    }

    @Override
    public boolean isFoil(@NotNull ItemStack stack) {
        return true;
    }
    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        LivingEntity entity = slotContext.entity();
        int ringCount = RingHandler.getEquippedRingCount(entity, PRItems.PotionRingSpeed.get());

        int effectLevel = Math.min(ringCount - 1, 2);
        entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, -1, effectLevel, true, false));
    }

    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        LivingEntity entity = slotContext.entity();
        int ringCount = RingHandler.getEquippedRingCount(entity, PRItems.PotionRingSpeed.get());

        if (ringCount > 0) {
            int effectLevel = Math.min(ringCount - 1, 2);
            entity.removeEffect(MobEffects.MOVEMENT_SPEED);
            entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, -1, effectLevel, true, false));
        } else {
            entity.removeEffect(MobEffects.MOVEMENT_SPEED);
        }
    }
    @Override
    public boolean canEquipFromUse(SlotContext slotContext, ItemStack stack) {
        return true;
    }
}


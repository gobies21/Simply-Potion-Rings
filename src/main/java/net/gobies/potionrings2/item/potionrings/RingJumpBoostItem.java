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

public class RingJumpBoostItem extends Item implements ICurioItem {
    public RingJumpBoostItem(Properties properties) {
        super(properties.stacksTo(1).rarity(Rarity.COMMON));
    }

    @Override
    public boolean isFoil(@NotNull ItemStack stack) {
        return true;
    }
    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        LivingEntity entity = slotContext.entity();
        int ringCount = RingHandler.getEquippedRingCount(entity, PRItems.PotionRingJumpBoost.get());

        int effectLevel = Math.min(ringCount - 1, 2);
        entity.addEffect(new MobEffectInstance(MobEffects.JUMP, -1, effectLevel, true, false));
    }

    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        LivingEntity entity = slotContext.entity();
        int ringCount = RingHandler.getEquippedRingCount(entity, PRItems.PotionRingJumpBoost.get());

        if (ringCount > 0) {
            int effectLevel = Math.min(ringCount - 1, 2);
            entity.removeEffect(MobEffects.JUMP);
            entity.addEffect(new MobEffectInstance(MobEffects.JUMP, -1, effectLevel, true, false));
        } else {
            entity.removeEffect(MobEffects.JUMP);
        }
    }
    @Override
    public boolean canEquipFromUse(SlotContext slotContext, ItemStack stack) {
        return true;
    }
}
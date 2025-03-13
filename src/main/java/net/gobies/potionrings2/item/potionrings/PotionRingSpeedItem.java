package net.gobies.potionrings2.item.potionrings;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;
import java.util.stream.IntStream;

public class PotionRingSpeedItem extends Item implements ICurioItem {
    public PotionRingSpeedItem(Properties properties) {
        super(new Properties().stacksTo(1).rarity(Rarity.COMMON));
    }
    @Override
    public boolean isFoil(ItemStack stack) {
        return true;
    }
    @Override
    public void curioTick(String identifier, int index, LivingEntity entity, ItemStack stack) {
        int ringCount = CuriosApi.getCuriosHelper().getCuriosHandler(entity).map(handler ->
                (int) handler.getCurios().values().stream()
                        .flatMap(curio -> {
                            int slots = curio.getSlots();
                            return IntStream.range(0, slots)
                                    .mapToObj(slotIndex -> curio.getStacks().getStackInSlot(slotIndex))
                                    .limit(slots);
                        })
                        .filter(itemStack -> itemStack.getItem() instanceof PotionRingSpeedItem)
                        .count()).orElse(0);


        int effectLevel = Math.min((int) ringCount - 1, 1);
        entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, -1, effectLevel, true, false));
    }

    @Override
    public void onUnequip(String identifier, int index, LivingEntity entity, ItemStack stack) {
        int ringCount = CuriosApi.getCuriosHelper().getCuriosHandler(entity).map(handler ->
                (int) handler.getCurios().values().stream()
                        .flatMap(curio -> {
                            int slots = curio.getSlots();
                            return IntStream.range(0, slots)
                                    .mapToObj(curio.getStacks()::getStackInSlot)
                                    .limit(slots);
                        })
                        .filter(itemStack -> itemStack.getItem() instanceof PotionRingSpeedItem)
                        .count()
        ).orElse(0);

        if (ringCount > 0) {
            int effectLevel = Math.min(ringCount - 1, 1);
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


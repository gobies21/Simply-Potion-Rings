package net.gobies.potionrings2.util;

import net.gobies.potionrings2.init.PRDataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.type.capability.ICuriosItemHandler;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

public class RingHandler {

    public static int getEquippedRingCount(LivingEntity entity, Item ringItem, String effectIdString) {
        AtomicInteger count = new AtomicInteger();
        Optional<ICuriosItemHandler> handler = CuriosApi.getCuriosInventory(entity);

        if (handler.isPresent()) {
            ResourceLocation targetEffectId = ResourceLocation.parse(effectIdString);

            for (ICurioStacksHandler curioStacksHandler : handler.get().getCurios().values()) {
                for (int i = 0; i < curioStacksHandler.getSlots(); i++) {
                    ItemStack stack = curioStacksHandler.getStacks().getStackInSlot(i);
                    if (stack.getItem() == ringItem) {
                        MobEffectInstance instance = stack.get(PRDataComponents.EFFECT);
                        if (instance != null) {
                            MobEffect effect = instance.getEffect().value();
                            ResourceLocation effectId = BuiltInRegistries.MOB_EFFECT.getKey(effect);
                            if (targetEffectId.equals(effectId)) {
                                count.getAndIncrement();
                            }
                        }
                    }
                }
            }
        }

        return count.get();
    }

    public static List<ItemStack> getEquippedRings(LivingEntity entity, Item ringItem) {
        List<ItemStack> rings = new ArrayList<>();
        Optional<ICuriosItemHandler> handler = CuriosApi.getCuriosInventory(entity);
        if (handler.isPresent()) {
            for (ICurioStacksHandler curioStacksHandler : handler.get().getCurios().values()) {
                for (int i = 0; i < curioStacksHandler.getSlots(); i++) {
                    ItemStack stack = curioStacksHandler.getStacks().getStackInSlot(i);
                    if (stack.getItem() == ringItem && stack.get(PRDataComponents.EFFECT) != null) {
                        rings.add(stack);
                    }
                }
            }
        }

        return rings;
    }
}
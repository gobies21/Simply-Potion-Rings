package net.gobies.potionrings2.util;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.CuriosCapability;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class RingHandler {

    public static int getEquippedRingCount(LivingEntity entity, Item ringItem, String effectIdString) {
        AtomicInteger count = new AtomicInteger();
        entity.getCapability(CuriosCapability.INVENTORY).ifPresent(handler -> {
            for (ICurioStacksHandler curioStacksHandler : handler.getCurios().values()) {
                for (int i = 0; i < curioStacksHandler.getSlots(); i++) {
                    ItemStack stack = curioStacksHandler.getStacks().getStackInSlot(i);
                    if (stack.getItem() == ringItem && stack.hasTag() && Objects.requireNonNull(stack.getTag()).getString("Effect").equals(effectIdString)) {
                        count.getAndIncrement();
                    }
                }
            }
        });
        return count.get();
    }

    public static List<ItemStack> getEquippedRings(LivingEntity entity, Item ringItem) {
        List<ItemStack> rings = new ArrayList<>();
        entity.getCapability(CuriosCapability.INVENTORY).ifPresent(handler -> {
            for (ICurioStacksHandler curioStacksHandler : handler.getCurios().values()) {
                for (int i = 0; i < curioStacksHandler.getSlots(); i++) {
                    ItemStack stack = curioStacksHandler.getStacks().getStackInSlot(i);
                    if (stack.getItem() == ringItem && stack.hasTag()) {
                        rings.add(stack);
                    }
                }
            }
        });
        return rings;
    }
}
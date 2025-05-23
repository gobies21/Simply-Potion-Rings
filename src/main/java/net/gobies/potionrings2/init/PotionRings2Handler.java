package net.gobies.potionrings2.init;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import top.theillusivec4.curios.api.CuriosApi;

import java.util.stream.IntStream;

public class PotionRings2Handler {

    public PotionRings2Handler() {
    }

    public static void register() {
    }

    public static int getEquippedCuriosCount(LivingEntity entity, Item targetItem) {
        return CuriosApi.getCuriosInventory(entity).resolve()
                .map(handler -> (int) handler.getCurios().values().stream().flatMap(curio -> {
                            int slots = curio.getSlots();
                            return IntStream.range(0, slots).mapToObj(slotIndex -> curio.getStacks().getStackInSlot(slotIndex)).limit(slots);
                        }).filter(itemStack -> itemStack.getItem() == targetItem).count()).orElse(0);

    }
}
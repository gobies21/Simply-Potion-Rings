package net.gobies.potionrings2.init;

import net.gobies.potionrings2.PotionRings2;
import net.gobies.potionrings2.item.potionrings.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class PRItems {
    public static final DeferredRegister<Item> ITEMS;
    public static final DeferredHolder<Item, PotionRingItem> PotionRing;

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

    static {
        ITEMS = DeferredRegister.create(Registries.ITEM, PotionRings2.MOD_ID);
        PotionRing = ITEMS.register("potion_ring", () -> new PotionRingItem(new Item.Properties()));
    }
}
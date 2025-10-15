package net.gobies.potionrings2.init;

import net.gobies.potionrings2.PotionRings2;
import net.gobies.potionrings2.item.potionrings.*;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class PRItems {
    public static final DeferredRegister<Item> ITEMS;
    public static final RegistryObject<Item> PotionRing;


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

    static {
        ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, PotionRings2.MOD_ID);
        PotionRing = ITEMS.register("potion_ring", () -> new PotionRingItem(new Item.Properties()));
    }
}
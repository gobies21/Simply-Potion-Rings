package net.gobies.potionrings2.item;

import net.gobies.potionrings2.PotionRings2;
import net.gobies.potionrings2.item.potionrings.*;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS;
    public static final RegistryObject<Item> PotionRing;
    public static final RegistryObject<Item> PotionRingSpeed;
    public static final RegistryObject<Item> PotionRingHaste;
    public static final RegistryObject<Item> PotionRingResistance;
    public static final RegistryObject<Item> PotionRingJumpBoost;
    public static final RegistryObject<Item> PotionRingStrength;
    public static final RegistryObject<Item> PotionRingRegeneration;

    public ModItems() {
    }

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

    static {
        ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, PotionRings2.MOD_ID);
        PotionRing = ITEMS.register("potion_ring", () -> new PotionRingItem(new Item.Properties()));
        PotionRingSpeed = ITEMS.register("ring_speed", () -> new PotionRingSpeedItem(new Item.Properties()));
        PotionRingHaste = ITEMS.register("ring_haste", () -> new PotionRingHasteItem(new Item.Properties()));
        PotionRingResistance = ITEMS.register("ring_resistance", () -> new PotionRingResistanceItem(new Item.Properties()));
        PotionRingJumpBoost = ITEMS.register("ring_jump_boost", () -> new PotionRingJumpBoostItem(new Item.Properties()));
        PotionRingStrength = ITEMS.register("ring_strength", () -> new PotionRingStrengthItem(new Item.Properties()));
        PotionRingRegeneration = ITEMS.register("ring_regeneration", () -> new PotionRingRegenerationItem(new Item.Properties()));
    }
}
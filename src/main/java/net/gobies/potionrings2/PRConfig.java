package net.gobies.potionrings2;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;

import java.util.List;

@Mod.EventBusSubscriber(modid = PotionRings2.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class PRConfig {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static ForgeConfigSpec SPEC;

    public static ForgeConfigSpec.ConfigValue<List<? extends String>> EFFECTS;
    public static List<? extends String> effects;

    @SubscribeEvent
    static void onLoad(ModConfigEvent.Loading event) {
        effects = EFFECTS.get();
    }

    static {
        BUILDER.push("Effects");
        EFFECTS = BUILDER.comment("Creates new rings based on what effects are listed here").defineList("effects", List.of("minecraft:speed", "minecraft:strength", "minecraft:haste", "minecraft:jump_boost", "minecraft:regeneration", "minecraft:resistance", "minecraft:luck", "minecraft:health_boost"), s -> s instanceof String);
        BUILDER.pop();

        SPEC = BUILDER.build();
    }
}


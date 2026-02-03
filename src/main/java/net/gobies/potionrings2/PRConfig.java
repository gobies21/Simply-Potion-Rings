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
    public static ForgeConfigSpec.ConfigValue<Integer> LEVEL;
    public static Integer level;
    public static ForgeConfigSpec.ConfigValue<Boolean> GLOW;
    public static Boolean glow;

    @SubscribeEvent
    static void onLoad(ModConfigEvent.Loading event) {
        effects = EFFECTS.get();
        level = LEVEL.get();
        glow = GLOW.get();
    }

    static {
        BUILDER.push("Effects");
        EFFECTS = BUILDER.comment("Creates new rings based on what effects are listed here").defineList("effects", List.of("minecraft:speed", "minecraft:strength", "minecraft:haste", "minecraft:jump_boost", "minecraft:regeneration", "minecraft:resistance", "minecraft:luck", "minecraft:health_boost"), s -> s instanceof String);
        LEVEL = BUILDER.comment("The maximum level an effect can reach with stacking potion rings").define("level", 3);
        GLOW = BUILDER.comment("Enable potion ring enchanted glow").define("glow", true);
        BUILDER.pop();

        SPEC = BUILDER.build();
    }
}


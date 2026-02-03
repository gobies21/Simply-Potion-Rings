package net.gobies.potionrings2;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.ModConfigSpec;

import java.util.List;

@EventBusSubscriber(modid = PotionRings2.MOD_ID)
public class PRConfig {
    public static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();
    public static ModConfigSpec SPEC;

    public static ModConfigSpec.ConfigValue<List<? extends String>> EFFECTS;
    public static List<? extends String> effects;
    public static ModConfigSpec.ConfigValue<Integer> LEVEL;
    public static Integer level;
    public static ModConfigSpec.ConfigValue<Boolean> GLOW;
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


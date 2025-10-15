package net.gobies.potionrings2;

import com.mojang.logging.LogUtils;
import net.gobies.potionrings2.compat.JeiCompat;
import net.gobies.potionrings2.init.PRCreativeTab;
import net.gobies.potionrings2.init.PRItems;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(PotionRings2.MOD_ID)
public class PotionRings2 {

    public static final String MOD_ID = "potionrings2";
    private static final Logger LOGGER = LogUtils.getLogger();

    public PotionRings2() {
        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();

        PRCreativeTab.register(modBus);

        PRItems.register(modBus);

        MinecraftForge.EVENT_BUS.register(this);

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, PRConfig.SPEC);

        modBus.addListener(this::commonSetup);
    }

    public void commonSetup(FMLCommonSetupEvent event) {
        if (ModList.get().isLoaded("jei")) {
            MinecraftForge.EVENT_BUS.register(JeiCompat.class);
        }
        LOGGER.info("Potion Rings 2 initialized.");
    }
}
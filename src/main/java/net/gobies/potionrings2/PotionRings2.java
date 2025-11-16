package net.gobies.potionrings2;

import com.mojang.logging.LogUtils;
import net.gobies.potionrings2.compat.JeiCompat;
import net.gobies.potionrings2.init.PRCreativeTab;
import net.gobies.potionrings2.init.PRDataComponents;
import net.gobies.potionrings2.init.PRItems;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import org.slf4j.Logger;

@Mod(PotionRings2.MOD_ID)
public class PotionRings2 {

    public static final String MOD_ID = "potionrings2";
    public static final Logger LOGGER = LogUtils.getLogger();

    public PotionRings2(IEventBus modBus, ModContainer container) {
        PRCreativeTab.register(modBus);

        PRItems.register(modBus);

        PRDataComponents.register(modBus);

        container.registerConfig(ModConfig.Type.COMMON, PRConfig.SPEC);

        modBus.addListener(this::commonSetup);
    }

    public void commonSetup(FMLCommonSetupEvent event) {
        if (ModList.get().isLoaded("jei")) {
            JeiCompat.loadCompat();
        }
        LOGGER.info("Potion Rings 2 initialized.");
    }
}
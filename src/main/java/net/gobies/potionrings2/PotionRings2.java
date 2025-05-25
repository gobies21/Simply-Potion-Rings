package net.gobies.potionrings2;

import com.mojang.logging.LogUtils;
import net.gobies.potionrings2.util.RingHandler;
import net.gobies.potionrings2.item.PRItems;
import net.gobies.potionrings2.item.PRModeTabs;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(PotionRings2.MOD_ID)
public class PotionRings2 {

    public static final String MOD_ID = "potionrings2";
    private static final Logger LOGGER = LogUtils.getLogger();

    public PotionRings2() {
        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();

        PRModeTabs.register(modBus);

        PRItems.register(modBus);

        RingHandler.register();

        MinecraftForge.EVENT_BUS.register(this);
    }
}
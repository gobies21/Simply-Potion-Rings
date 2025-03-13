package net.gobies.potionrings2;

import com.mojang.logging.LogUtils;
import net.gobies.potionrings2.item.ModCreativeModeTabs;
import net.gobies.potionrings2.item.ModItems;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

import static net.gobies.potionrings2.PotionRings2.MOD_ID;

@Mod(MOD_ID)
public class PotionRings2 {

    public static final String MOD_ID = "potionrings2";
    private static final Logger LOGGER = LogUtils.getLogger();

    public PotionRings2() {
        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModCreativeModeTabs.register(modBus);

        ModItems.register(modBus);

        MinecraftForge.EVENT_BUS.register(this);

        //ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }
}
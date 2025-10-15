package net.gobies.potionrings2.init;

import net.gobies.potionrings2.PotionRings2;
import net.gobies.potionrings2.item.potionrings.PotionRingItem;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class PRCreativeTab {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, PotionRings2.MOD_ID);
    public static final RegistryObject<CreativeModeTab> POTION_RINGS_TAB;

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }

    static {
        POTION_RINGS_TAB = CREATIVE_MODE_TABS.register("potionrings2_tab",
                () -> CreativeModeTab.builder().icon(() -> new ItemStack(PRItems.PotionRing.get()))
                        .title(Component.translatable("creativetab.potionrings2_tab"))
                        .displayItems((pParameters, pOutput) -> {
                            pOutput.accept(PRItems.PotionRing.get());
                            pOutput.acceptAll(PotionRingItem.createPotionRing());
                        })
                        .build());
    }
}
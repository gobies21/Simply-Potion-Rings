package net.gobies.potionrings2.item;

import net.gobies.potionrings2.PotionRings2;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import javax.swing.*;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, PotionRings2.MOD_ID);
    public static final RegistryObject<CreativeModeTab> POTION_RINGS_TAB = CREATIVE_MODE_TABS.register("potionrings2_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.PotionRingRegeneration.get()))
                    .title(Component.translatable("creativetab.potionrings2_tab"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(ModItems.PotionRing.get());
                        pOutput.accept(ModItems.PotionRingSpeed.get());
                        pOutput.accept(ModItems.PotionRingJumpBoost.get());
                        pOutput.accept(ModItems.PotionRingHaste.get());
                        pOutput.accept(ModItems.PotionRingStrength.get());
                        pOutput.accept(ModItems.PotionRingRegeneration.get());
                        pOutput.accept(ModItems.PotionRingResistance.get());
                    })
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
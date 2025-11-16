package net.gobies.potionrings2.util;

import net.gobies.potionrings2.PotionRings2;
import net.gobies.potionrings2.init.PRDataComponents;
import net.gobies.potionrings2.init.PRItems;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.Item;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;

@EventBusSubscriber(modid = PotionRings2.MOD_ID, value = Dist.CLIENT)
public class ColorHandler {

    @SubscribeEvent
    public static void gemColor(RegisterColorHandlersEvent.Item event) {
        setGemColor(event, PRItems.PotionRing.get());
    }

    private static void setGemColor(RegisterColorHandlersEvent.Item event, Item item) {
        event.register((stack, tintIndex) -> {
            if (tintIndex == 1) {
                MobEffectInstance effectInstance = stack.get(PRDataComponents.EFFECT);
                if (effectInstance != null) {
                    MobEffect effect = effectInstance.getEffect().value();
                    int argb = effect.getColor();

                    int rgb = argb & 0x00FFFFFF;
                    return 0xFF000000 | rgb;
                }
            }
            return -1;
        }, item);
    }
}
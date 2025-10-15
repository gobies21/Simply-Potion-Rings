package net.gobies.potionrings2.util;

import net.gobies.potionrings2.PotionRings2;
import net.gobies.potionrings2.init.PRItems;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.item.Item;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = PotionRings2.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ColorHandler {

    @SubscribeEvent
    public static void gemColor(RegisterColorHandlersEvent.Item event) {
        setGemColor(event, PRItems.PotionRing.get());
    }

    private static void setGemColor(RegisterColorHandlersEvent.Item event, Item item) {
        event.register((itemStack, tintIndex) -> {
            if (tintIndex == 1) {
                CompoundTag nbt = itemStack.getTag();
                if (nbt != null && nbt.contains("Effect")) {
                    String effectIdString = nbt.getString("Effect");
                    ResourceLocation effectId = new ResourceLocation(effectIdString);
                    MobEffect effect = ForgeRegistries.MOB_EFFECTS.getValue(effectId);

                    if (effect != null) {
                        return effect.getColor();
                    }
                }
            }
            return -1;
        }, item);
    }
}

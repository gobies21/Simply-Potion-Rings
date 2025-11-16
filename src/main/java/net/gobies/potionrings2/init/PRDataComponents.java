package net.gobies.potionrings2.init;

import net.gobies.potionrings2.PotionRings2;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffectInstance;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.UnaryOperator;

public class PRDataComponents {
    public static final DeferredRegister<DataComponentType<?>> DATA_COMPONENT_TYPES =
            DeferredRegister.create(Registries.DATA_COMPONENT_TYPE, PotionRings2.MOD_ID);

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<MobEffectInstance>> EFFECT =
            register(builder -> builder
                    .persistent(MobEffectInstance.CODEC));

    private static <T> DeferredHolder<DataComponentType<?>, DataComponentType<T>> register(
            UnaryOperator<DataComponentType.Builder<T>> builderOperator) {

        return DATA_COMPONENT_TYPES.register("effect".toLowerCase(), () ->
                builderOperator.apply(DataComponentType.builder()).build());
    }

    public static void register(IEventBus eventBus) {
        DATA_COMPONENT_TYPES.register(eventBus);
    }
}
package net.gobies.potionrings2.compat;

import mezz.jei.api.ingredients.subtypes.IIngredientSubtypeInterpreter;
import mezz.jei.api.ingredients.subtypes.UidContext;
import net.gobies.potionrings2.init.PRDataComponents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class PotionRingSubTypeInterpreter implements IIngredientSubtypeInterpreter<ItemStack> {

    public static final PotionRingSubTypeInterpreter INSTANCE = new PotionRingSubTypeInterpreter();

    @Override
    public @NotNull String apply(@NotNull ItemStack ingredient, @NotNull UidContext context) {
        MobEffectInstance effectInstance = ingredient.get(PRDataComponents.EFFECT);
        if (effectInstance != null) {
            return String.valueOf(effectInstance.getEffect().value());
        }
        return "default_potion";
    }
}

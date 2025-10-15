package net.gobies.potionrings2.compat;

import mezz.jei.api.ingredients.subtypes.IIngredientSubtypeInterpreter;
import mezz.jei.api.ingredients.subtypes.UidContext;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class PotionRingSubTypeInterpreter implements IIngredientSubtypeInterpreter<ItemStack> {

    public static final PotionRingSubTypeInterpreter INSTANCE = new PotionRingSubTypeInterpreter();

    @Override
    public @NotNull String apply(@NotNull ItemStack ingredient, @NotNull UidContext context) {
        CompoundTag nbt = ingredient.getTag();
        if (nbt != null && nbt.contains("Effect")) {
            return nbt.getString("Effect");
        }
        return "default_potion";
    }
}
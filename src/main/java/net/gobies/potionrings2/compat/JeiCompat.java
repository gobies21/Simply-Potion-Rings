package net.gobies.potionrings2.compat;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.runtime.IJeiRuntime;
import net.gobies.potionrings2.item.potionrings.PotionRingItem;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@JeiPlugin
public class JeiCompat implements IModPlugin {
    private final ResourceLocation PLUGIN_UID = new ResourceLocation("potionrings2", "jei_plugin");

    public @NotNull ResourceLocation getPluginUid() {
        return PLUGIN_UID;
    }

    @Override
    public void onRuntimeAvailable(@NotNull IJeiRuntime jeiRuntime) {
        addItem(jeiRuntime, PotionRingItem.createPotionRing());
    }

    private void addItem(IJeiRuntime jeiRuntime, List<ItemStack> stacks) {
        jeiRuntime.getIngredientManager().addIngredientsAtRuntime(VanillaTypes.ITEM_STACK, stacks);
    }
}
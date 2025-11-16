package net.gobies.potionrings2.compat;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.registration.ISubtypeRegistration;
import net.gobies.potionrings2.init.PRItems;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

@JeiPlugin
public class JeiCompat implements IModPlugin {
    private final ResourceLocation PLUGIN_UID = ResourceLocation.fromNamespaceAndPath("potionrings2", "jei_plugin");

    public static void loadCompat() {
    }

    public @NotNull ResourceLocation getPluginUid() {
        return PLUGIN_UID;
    }

    @Override
    public void registerItemSubtypes(@NotNull ISubtypeRegistration registration) {
        registration.registerSubtypeInterpreter(VanillaTypes.ITEM_STACK, PRItems.PotionRing.get(), PotionRingSubTypeInterpreter.INSTANCE);
    }
}

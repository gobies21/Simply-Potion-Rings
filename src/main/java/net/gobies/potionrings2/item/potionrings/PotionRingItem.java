package net.gobies.potionrings2.item.potionrings;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

public class PotionRingItem extends Item implements ICurioItem {
    public PotionRingItem(Properties properties) {
        super(properties.stacksTo(1).rarity(Rarity.COMMON));
    }

    @Override
    public boolean canEquipFromUse(SlotContext slotContext, ItemStack stack) {
        return true;
    }
}




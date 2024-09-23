package iq.jaime.itemqualityapi.API;

import iq.jaime.itemqualityapi.enums.ItemQuality;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nullable;

public interface IQAPI {
    void addItemQuality(ItemStack itemStack, ItemQuality quality);
    @Nullable ItemQuality getItemQuality(ItemStack itemStack);
}

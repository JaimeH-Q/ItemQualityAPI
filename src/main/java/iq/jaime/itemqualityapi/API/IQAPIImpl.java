package iq.jaime.itemqualityapi.API;

import iq.jaime.itemqualityapi.ItemQualityAPI;
import iq.jaime.itemqualityapi.enums.ItemQuality;
import iq.jaime.itemqualityapi.utils.ItemUtils;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;

public class IQAPIImpl implements IQAPI{

    private ItemQualityAPI plugin;

    public IQAPIImpl(ItemQualityAPI plugin) {
        this.plugin = plugin;
    }

    @Override
    public void addItemQuality(ItemStack itemStack, ItemQuality quality) {
        List<String> qualityLore = plugin.getConfigFile().getQualityLore();
        Map<ItemQuality, String> replacements = plugin.getConfigFile().getQualitiesStrings();

        ItemUtils.addItemQuality(itemStack, quality);
        ItemUtils.addQualityLore(itemStack, quality, qualityLore, replacements);
    }

    @Nullable
    @Override
    public ItemQuality getItemQuality(ItemStack itemStack) {
        return ItemUtils.getItemQuality(itemStack);
    }
}

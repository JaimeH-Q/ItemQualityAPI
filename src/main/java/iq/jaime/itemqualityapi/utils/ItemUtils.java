package iq.jaime.itemqualityapi.utils;

import iq.jaime.itemqualityapi.enums.ItemQuality;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.List;
import java.util.Map;

import static iq.jaime.itemqualityapi.utils.MessageUtils.colorize;

public class ItemUtils {
    public static void addItemQuality(ItemStack itemStack, ItemQuality quality){
        NamespacedKey key = new NamespacedKey("itemqualityapi", "item_quality");
        ItemMeta meta = itemStack.getItemMeta();
        if(meta == null) {
            return;
        }

        PersistentDataContainer persistentDataContainer = meta.getPersistentDataContainer();
        persistentDataContainer.set(key, PersistentDataType.STRING, quality.name());
        itemStack.setItemMeta(meta);
    }

    public static ItemQuality getItemQuality(ItemStack itemStack){
        NamespacedKey key = new NamespacedKey("itemqualityapi", "item_quality");
        ItemMeta meta = itemStack.getItemMeta();
        if(meta == null) {
            return null;
        }
        PersistentDataContainer persistentDataContainer = meta.getPersistentDataContainer();
        String qualityName = persistentDataContainer.get(key, PersistentDataType.STRING);
        try{
            return ItemQuality.valueOf(qualityName);
        } catch (IllegalArgumentException e){
            return null;
        }
    }

    public static void addQualityLore(ItemStack itemStack, ItemQuality quality, List<String> lore, Map<ItemQuality, String> replacements){
        ItemMeta meta = itemStack.getItemMeta();
        if(meta == null){
            return;
        }
        if(meta.lore() == null){
            return;
        }
        for(String m : lore) {
            meta.lore().add(Component.text(colorize(m
                    .replace("%quality%", replacements.get(quality)))));
        }
        itemStack.setItemMeta(meta);

    }
}

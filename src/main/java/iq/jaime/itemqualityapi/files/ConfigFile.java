package iq.jaime.itemqualityapi.files;

import iq.jaime.itemqualityapi.ItemQualityAPI;
import iq.jaime.itemqualityapi.enums.ItemQuality;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.*;

import static iq.jaime.itemqualityapi.utils.MessageUtils.colorize;

public class ConfigFile {
    private final YamlFile configFile;
    public ConfigFile(ItemQualityAPI plugin) {
        configFile = new YamlFile("config.yml", null, plugin, false);
        reloadConfig();
    }

    private Map<ItemQuality, String> qualities;
    private List<String> qualityLore;
    private boolean replaceLore;

    public void reloadConfig(){
        configFile.registerConfig();
        FileConfiguration config = configFile.getConfig();
        Bukkit.getConsoleSender().sendMessage(config.getKeys(false).toString());
        saveReplacements(config.getConfigurationSection("qualities"));
        qualityLore = config.getStringList("quality_lore");
        replaceLore = config.getBoolean("replace_lore");
    }

    private void saveReplacements(ConfigurationSection qualitiesSection) {
        qualities = new HashMap<>();
        for(String key : qualitiesSection.getKeys(false)){
            try {
                ItemQuality quality = ItemQuality.valueOf(key.toUpperCase());
                qualities.put(quality, colorize(qualitiesSection.getString(key)));
            } catch (IllegalArgumentException e){
                Bukkit.getConsoleSender().sendMessage(
                        colorize("&4ItemQualityAPI error: En la sección de calidades, la llave "
                                + key + " no representa una calidad existente."));
            }
        }
        for(ItemQuality quality : ItemQuality.values()){
            if(!qualities.containsKey(quality)){
                Bukkit.getConsoleSender().sendMessage(
                        colorize("La calidad " + quality + " no está añadida en la sección de calidades"));
            }
        }
    }

    public Map<ItemQuality, String> getQualitiesStrings() {
        return qualities;
    }

    public List<String> getQualityLore() {
        return qualityLore;
    }

    public boolean shouldReplaceAllLore() {
        return replaceLore;
    }
}

package iq.jaime.itemqualityapi;

import iq.jaime.itemqualityapi.API.IQAPI;
import iq.jaime.itemqualityapi.API.IQAPIImpl;
import iq.jaime.itemqualityapi.files.ConfigFile;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.java.JavaPlugin;

import static iq.jaime.itemqualityapi.utils.MessageUtils.colorize;

public final class ItemQualityAPI extends JavaPlugin {

    private ConfigFile configFile;


    private IQAPIImpl iqAPI;

    @Override
    public void onEnable() {
        configFile = new ConfigFile(this);
        registerAPI();
        getServer().getConsoleSender().sendMessage(colorize("&aItemQualityAPI cargada."));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void registerAPI(){
        iqAPI = new IQAPIImpl(this);
        getServer().getServicesManager().register(IQAPI.class, iqAPI, this, ServicePriority.Normal);
    }

    public ConfigFile getConfigFile() {
        return configFile;
    }
}

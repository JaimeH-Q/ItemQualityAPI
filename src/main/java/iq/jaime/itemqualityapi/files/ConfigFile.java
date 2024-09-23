package us.jaime.unospigot.files;

import org.bukkit.Sound;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import us.jaime.unospigot.UnoSpigot;
import us.jaime.unospigot.display.HologramType;
import us.jaime.unospigot.model.Reward;
import us.jaime.unospigot.model.enums.RewardType;
import us.jaime.unospigot.model.enums.Status;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ConfigFile {
    private final YamlFile tablesFile;
    public ConfigFile(UnoSpigot plugin) {
        tablesFile = new YamlFile("config.yml", null, plugin, false);
        reloadConfig();
    }

    private List<String> idleHologramContents;
    private List<String> disabledHologramContents;
    private List<String> gameHologramContents;
    private List<String> waitingHologramContents;
    private String status_free;
    private String status_in_game;
    private String status_disabled;
    private boolean enableStartupBanner;
    private String status_waiting;
    private String status_starting;
    private String status_finishing;
    private String phase_game;
    private String next_turn;
    private String selectColorMenuTitle;
    private String redColorMenu;
    private String greenColorMenu;
    private String blueColorMenu;
    private String yellowColorMenu;
    private String rightClickToJoinText;
    private String timePlayedFormatted;

    private float[] armorStandOffsets;
    private float[] hologramOffsets;
    private boolean tableCardHologramEnabled;
    private float[] tableCardHologramOffset;
    private long tableCardHologramDisappear;

    private int tableStartCooldown;
    private int requiredTimeToWinByLeave;

    private Sound cardNotPlayedSound;
    private Sound cardPlayedSound;
    private Sound cardDrawSound;
    private Sound gotSkippedSound;
    private Sound reverseSound;
    private Sound notYourTurnSound;
    private Sound yourTurnSound;
    private Sound plusPlayedSound;
    private Sound tableFinishSound;
    private Sound unoSound;

    private Set<Reward> rewards;
    private int maxDistanceToTable;


    public void reloadConfig(){
        tablesFile.registerConfig();
        FileConfiguration config = tablesFile.getConfig();

        timePlayedFormatted = config.getString("time_played_formatted");

        status_free = config.getString("status_free");
        status_in_game = config.getString("status_in_game");
        status_disabled = config.getString("status_disabled");
        idleHologramContents = config.getStringList("table_hologram");
        disabledHologramContents = config.getStringList("table_hologram_disabled");
        gameHologramContents = config.getStringList("table_hologram_game");
        waitingHologramContents = config.getStringList("table_hologram_waiting");
        enableStartupBanner = config.getBoolean("enable_startup_banner");
        status_waiting = config.getString("status_waiting");
        status_starting = config.getString("status_starting");
        phase_game = config.getString("phase_game");
        next_turn = config.getString("next_turn");
        selectColorMenuTitle = config.getString("select_color_menu_title");
        greenColorMenu = config.getString("green_color_menu");
        yellowColorMenu = config.getString("yellow_color_menu");
        blueColorMenu = config.getString("blue_color_menu");
        redColorMenu = config.getString("red_color_menu");
        status_finishing = config.getString("status_finishing");
        tableStartCooldown = config.getInt("table_start_cooldown");
        rightClickToJoinText = config.getString("right_click_to_join");

        armorStandOffsets = getOffset(config.getConfigurationSection("card_armor_stand_offset"));
        hologramOffsets = getOffset(config.getConfigurationSection("table_holograms_offset"));
        tableCardHologramEnabled = config.getBoolean("table_card_hologram_enabled");
        tableCardHologramOffset = getOffset(config.getConfigurationSection("table_card_hologram_offset"));
        tableCardHologramDisappear = config.getLong("table_card_hologram_disappear");

        cardPlayedSound = getSound(config.getString("card_played_sound"));
        cardNotPlayedSound = getSound(config.getString("card_not_played_sound"));
        cardDrawSound = getSound(config.getString("card_draw_sound"));
        gotSkippedSound = getSound(config.getString("got_skipped_sound"));
        reverseSound = getSound(config.getString("reverse_sound"));
        notYourTurnSound = getSound(config.getString("not_your_turn_sound"));
        yourTurnSound = getSound(config.getString("your_turn_sound"));
        plusPlayedSound = getSound(config.getString("plus_played_sound"));
        tableFinishSound = getSound(config.getString("table_finish_sound"));
        requiredTimeToWinByLeave = config.getInt("required_time_to_win_by_leave");
        unoSound = getSound(config.getString("uno_sound"));
        maxDistanceToTable = config.getInt("max_distance_to_table");

        rewards = loadRewards(config.getConfigurationSection("rewards"));

    }

    private Set<Reward> loadRewards(ConfigurationSection rewardsSection) {
        Set<Reward> rewardSet = new HashSet<>();
        Set<String> rewardsKeys = rewardsSection.getKeys(false);
        for(String key : rewardsKeys){
            ConfigurationSection rewardSection = rewardsSection.getConfigurationSection(key);
            RewardType type = RewardType.valueOf(rewardSection.getString("type"));
            List<String> actions = rewardSection.getStringList("actions");
            String permission = rewardSection.getString("permission");
            rewardSet.add(new Reward(type, actions, permission));
        }

        return rewardSet;

    }

    public String getStatus_finishing() {
        return status_finishing;
    }

    private float[] getOffset(ConfigurationSection section) {

        float x = (float) section.getDouble("x");
        float y = (float) section.getDouble("y");
        float z = (float) section.getDouble("z");

        return new float[]{x, y, z};
    }

    public int getRequiredTimeToWinByLeave() {
        return requiredTimeToWinByLeave;
    }

    public List<String> getIdleHologramContents() {
        return idleHologramContents;
    }

    public String getStatus_free() {
        return status_free;
    }

    public String getStatus_in_game() {
        return status_in_game;
    }

    public String getStatus_waiting() {
        return status_waiting;
    }

    public boolean isEnableStartupBanner() {
        return enableStartupBanner;
    }

    public String getStatus_disabled() {
        return status_disabled;
    }

    public List<String> getDisabledHologramContents() {
        return disabledHologramContents;
    }

    public List<String> getGameHologramContents() {
        return gameHologramContents;
    }

    public String getPhase_game() {
        return phase_game;
    }

    public String getStatus_starting() {
        return status_starting;
    }

    public String getNext_turn() {
        return next_turn;
    }

    public String getSelectColorMenuTitle() {
        return selectColorMenuTitle;
    }

    public String getRedColorMenu() {
        return redColorMenu;
    }

    public String getGreenColorMenu() {
        return greenColorMenu;
    }

    public String getBlueColorMenu() {
        return blueColorMenu;
    }

    public String getYellowColorMenu() {
        return yellowColorMenu;
    }

    public float[] getArmorStandOffsets() {
        return armorStandOffsets;
    }

    public float[] getHologramOffsets() {
        return hologramOffsets;
    }

    public List<String> getWaitingHologramContents() {
        return waitingHologramContents;
    }

    public List<String> getHologramContents(HologramType type){
        switch (type){
            case IDLE -> {
                return idleHologramContents;
            }
            case DISABLED -> {
                return disabledHologramContents;
            }
            case WAITING -> {
                return waitingHologramContents;
            }
            case GAME -> {
                return gameHologramContents;
            }
        }

        return null;
    }

    public String getParsedStatus(Status status){
        return switch (status) {
            case FREE -> status_free;
            case DISABLED -> status_disabled;
            case INGAME -> status_in_game;
            case STARTING -> status_starting;
            case WAITING -> status_waiting;
            case FINISHING -> status_finishing;
        };
    }

    public int getTableStartCooldown() {
        return tableStartCooldown;
    }

    private Sound getSound(String string){
        if(string == null || string.equalsIgnoreCase("NONE")){
            return null;
        }
        return Sound.valueOf(string);
    }



    public Sound getCardNotPlayedSound() {
        return cardNotPlayedSound;
    }

    public Sound getCardPlayedSound() {
        return cardPlayedSound;
    }

    public Sound getCardDrawSound() {
        return cardDrawSound;
    }

    public Sound getGotSkippedSound() {
        return gotSkippedSound;
    }

    public Sound getReverseSound() {
        return reverseSound;
    }

    public Sound getNotYourTurnSound() {
        return notYourTurnSound;
    }

    public Sound getYourTurnSound() {
        return yourTurnSound;
    }

    public Sound getPlusPlayedSound() {
        return plusPlayedSound;
    }

    public boolean isTableCardHologramEnabled() {
        return tableCardHologramEnabled;
    }

    public float[] getTableCardHologramOffset() {
        return tableCardHologramOffset;
    }

    public long getTableCardHologramDisappear() {
        return tableCardHologramDisappear;
    }

    public String getRightClickToJoinText() {
        return rightClickToJoinText;
    }

    public Sound getTableFinishSound() {
        return tableFinishSound;
    }

    public String getTimePlayedFormatted() {
        return timePlayedFormatted;
    }

    public Set<Reward> getRewards() {
        return rewards;
    }

    public Sound getUnoSound() {
        return unoSound;
    }

    public int getMaxDistanceToTable() {
        return maxDistanceToTable;
    }
}

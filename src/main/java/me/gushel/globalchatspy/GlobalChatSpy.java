package me.gushel.globalchatspy;

import me.gushel.globalchatspy.Commands.GCSpy;
import me.gushel.globalchatspy.Events.ChatMessage;
import org.bukkit.plugin.java.JavaPlugin;

public final class GlobalChatSpy extends JavaPlugin {

    private static GlobalChatSpy plugin;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        Config.setup();
        Config.get().options().copyDefaults(true);
        Config.save();
        getServer().getPluginManager().registerEvents(new ChatMessage(), this);
        plugin = this;
        this.getCommand("gcspy").setExecutor(new GCSpy());
    }

    public static GlobalChatSpy getInstance(){
        return plugin;
    }

    @Override
    public void onDisable() {

    }
}

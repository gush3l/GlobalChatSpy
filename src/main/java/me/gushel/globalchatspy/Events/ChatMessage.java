package me.gushel.globalchatspy.Events;

import me.gushel.globalchatspy.Commands.GCSpy;
import me.gushel.globalchatspy.GlobalChatSpy;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;

import java.util.List;

public class ChatMessage implements Listener {

    @EventHandler
    @SuppressWarnings( "deprecation" )
    public void onMessage(PlayerChatEvent event){
        List<String> chatspy = GCSpy.chatspy;
        FileConfiguration config = GlobalChatSpy.getInstance().getConfig();
        Player player = event.getPlayer();
        String message = event.getMessage();
        World world = player.getWorld();
        if (player.hasPermission("globalchatspy.use") && config.getBoolean("Do-Not-Send-If-Permission")){
            return;
        }
        if (player.hasPermission("globalchatspy.use") && config.getBoolean("Do-Not-Send-Self-Message")){
            chatspy.remove(player.getName());
        }
        for (String players : chatspy) {
            if (Bukkit.getPlayer(players) != null) {
                Bukkit.getPlayer(players).sendMessage(ChatColor.translateAlternateColorCodes('&', config.getString("Format")
                        .replace("%world%", world.getName())
                        .replace("%player%", player.getName())
                        .replace("%message%", message)));
            }
        }
    }
}

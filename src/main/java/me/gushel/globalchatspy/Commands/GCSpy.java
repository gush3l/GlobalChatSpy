package me.gushel.globalchatspy.Commands;

import me.gushel.globalchatspy.GlobalChatSpy;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class GCSpy implements CommandExecutor {

    public static List<String> chatspy = new ArrayList<>();
    FileConfiguration config = GlobalChatSpy.getInstance().getConfig();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 1 && args[0].equalsIgnoreCase("reload")){
            if (!sender.hasPermission("globalchatspy.reload")){
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&',config.getString("Messages.NoPerm")));
                return true;
            }
            GlobalChatSpy.getInstance().reloadConfig();
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&',config.getString("Messages.Reloaded")));
            return true;
        }
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (!player.hasPermission("globalchatspy.use")){
                player.sendMessage(ChatColor.translateAlternateColorCodes('&',config.getString("Messages.NoPerm")));
                return true;
            }
            if (chatspy.contains(player.getName())){
                chatspy.remove(player.getName());
                player.sendMessage(ChatColor.translateAlternateColorCodes('&',config.getString("Messages.SpyOff")));
                return true;
            }
            chatspy.add(player.getName());
            player.sendMessage(ChatColor.translateAlternateColorCodes('&',config.getString("Messages.SpyOn")));
            return true;
        }
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&',config.getString("Messages.NotPlayer")));
        return true;
    }
}

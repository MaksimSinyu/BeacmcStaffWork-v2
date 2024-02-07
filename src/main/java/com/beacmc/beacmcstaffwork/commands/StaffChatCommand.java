package com.beacmc.beacmcstaffwork.commands;

import com.beacmc.beacmcstaffwork.manager.Color;
import com.beacmc.beacmcstaffwork.manager.CommandManager;
import com.beacmc.beacmcstaffwork.manager.User;
import com.beacmc.beacmcstaffwork.manager.configuration.Config;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class StaffChatCommand extends CommandManager {

    public StaffChatCommand() {
        super("staffchat");
    }

    @Override
    public void execute(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage("only player");
            return;
        }

        User user = new User((Player) sender);
        if(!user.hasPermission("beacmcstaffwork.chat")) {
            user.sendMessage("settings.messages.no-permission");
            return;
        }

        if(args.length == 0) {
            user.sendMessage("settings.messages.error-use");
            return;
        }

        String format = PlaceholderAPI.setPlaceholders(user.getPlayer(), Config.getString("settings.chat-format"));
        format = Color.compile(format).replace("{MESSAGE}", String.join(" ", Arrays.asList(args)));
        String finalFormat = format;
        Bukkit.getOnlinePlayers().forEach(player -> {
            if (player.hasPermission("beacmcstaffwork.chat")) {
                player.sendMessage(finalFormat);
            }
        });
    }
}
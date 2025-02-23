package com.beacmc.beacmcstaffwork.manager;

import com.beacmc.beacmcstaffwork.BeacmcStaffWork;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.jetbrains.annotations.NotNull;

public abstract class CommandManager implements CommandExecutor {


    public CommandManager(String command) {
        PluginCommand cmd = BeacmcStaffWork.getInstance().getCommand(command);
        if (cmd != null) cmd.setExecutor(this);
    }

    public abstract void execute(CommandSender sender, org.bukkit.command.Command command, String label, String[] args);

    @Override
    public boolean onCommand(@NotNull CommandSender sender, org.bukkit.command.@NotNull Command command, @NotNull String label, @NotNull String[] args) {
        execute(sender, command, label, args);
        return true;
    }
}

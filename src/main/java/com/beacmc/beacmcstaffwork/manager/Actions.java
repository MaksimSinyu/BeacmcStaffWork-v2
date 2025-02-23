package com.beacmc.beacmcstaffwork.manager;

import com.beacmc.beacmcstaffwork.manager.configuration.Config;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.List;

public class Actions {

    public static void start(List<String> list, Player player) {
        if (list.isEmpty()) return;

        for(String s : list) {
            String[] splitText = s.split(" ");
            String action = splitText[0];
            String cmd = s.replace(action + " ", "");
            cmd = PlaceholderAPI.setPlaceholders(player, cmd);

            if(cmd == null)
                continue;


            switch (action.toLowerCase()) {
                case "[message]": {
                    player.sendMessage(Color.compile(cmd)
                            .replace("{PREFIX}", Config.getString("settings.prefix"))
                    );
                    break;
                }
                case "[console]": {
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), cmd);
                    break;
                }
                case "[sound]": {
                    player.playSound(player.getLocation(), Sound.valueOf(cmd), 0.5f, 1.0f);
                    break;
                }
                case "[player]": {
                    Bukkit.dispatchCommand(player, cmd);
                    break;
                }
                case "[broadcast]": {
                    Bukkit.broadcastMessage(Color.compile(cmd));
                    break;
                }
                case "[message_to_moderators]": {
                    Work.messageToModerator(player, cmd);
                    break;
                }
                case "[actionbar]": {
                    player.sendActionBar(Color.compile(cmd));
                    break;
                }
                case "[title]": {
                    String[] title = cmd.split(";");

                    if (title[0] == null) {
                        Bukkit.getLogger().warning(" | {ACTIONS} >> Триггер [title] не может быть пустым");
                        Bukkit.getLogger().warning(" | {ACTIONS} >> Пример: [title] title;subtitle");
                        break;
                    } else if (title[1] == null) {
                        Bukkit.getLogger().warning(" | {ACTIONS} >> Триггер [title] не может быть пустым");
                        Bukkit.getLogger().warning(" | {ACTIONS} >> Пример: [title] title;subtitle");
                        break;
                    } else {
                        player.sendTitle(title[0], title[1], 20, 40, 20);
                        break;
                    }
                }
            }
        }
    }
}

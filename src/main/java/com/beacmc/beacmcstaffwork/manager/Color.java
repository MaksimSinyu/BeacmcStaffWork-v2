package com.beacmc.beacmcstaffwork.manager;
import net.md_5.bungee.api.ChatColor;
import org.apache.commons.lang.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Color {

    private static final Pattern PATTERN = Pattern.compile(
            "(#[a-f0-9]{6})",
            Pattern.CASE_INSENSITIVE
    );

    public static String compile(String text) {
        if(text == null || text.isEmpty()) {
            return "";
        }

        final Matcher matcher = PATTERN.matcher(text);
        while (matcher.find()) {
            try {
                final ChatColor chatColor = net.md_5.bungee.api.ChatColor.of(matcher.group(1));


                if (chatColor != null) {
                    text = StringUtils.replace(text, matcher.group(), chatColor.toString());
                }
            } catch (IllegalArgumentException ignored) { }
        }

        return ChatColor.translateAlternateColorCodes('&', text);
    }
}

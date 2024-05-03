package me.rakkoon69.safecampfires.configuration;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum Message {

    PREFIX("prefix"),

    /* COMMANDS RELATED */
    EFFECT("effect"), NO_EFFECT("no_effect"),
    STILL_COOLDOWN("still_cooldown"), BELOW_Y("can_not_use_below_y");

    private static final Pattern pattern = Pattern.compile("<#(?:[A-Fa-f0-9]{3}){1,2}\\b>");

    public static List<String> hologramTexts;

    private final String path;

    Message(String p) {
        this.path = p;
    }

    public static String ListToString(List<String> list, String join) {
        StringBuilder s = new StringBuilder();
        int cur = 0;
        for (String x : list) {
            s.append(x);
            cur++;
            if(cur < list.size()) s.append(join);
        }

        return s.toString();
    }

    public static String color(String message) {
        Matcher matcher = pattern.matcher(message);
        while (matcher.find()) {
            String hexCode = message.substring(matcher.start(), matcher.end());
            String replaceSharp = hexCode.replace("<#", "x").replace(">", "");

            char[] ch = replaceSharp.toCharArray();
            StringBuilder builder = new StringBuilder("");
            for (char c : ch) {
                builder.append("&" + c);
            }

            message = message.replace(hexCode, builder.toString());
            matcher = pattern.matcher(message);
        }
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public static String colorWithPrefix(String s) {
        return color(s.replace("<prefix>",
                Settings.getMessages().getString(PREFIX.getPath()) + ""));
    }

    public static void log(String s) {
        Bukkit.getServer().getConsoleSender().sendMessage(color(s));
    }

    public static String prefix() {
        return color("&f[&eSafeCampfires&f] &7");
    }

    public static void msg(Player p, String s) {
        p.sendMessage(colorWithPrefix(s));
    }

    public static void msg(CommandSender p, String s) {
        p.sendMessage(colorWithPrefix(s));
    }

    public static void broadcast(String s) {
        Bukkit.getServer().broadcastMessage(colorWithPrefix(s));
    }

    public static void onEnableLog(String version) {
        log(prefix() + "&aPlugin Enabled! &7Version: &b" + version);
        log(prefix() + "&aAuthor: &6rakkoon69");
    }

    public static void onDisableLog(String version) {
        log(prefix() + "&ePlugin disabled! &7Version: &b" + version);
        log(prefix() + "&2Author: &6rakkoon69");
    }

    public static void setHologramTexts(List<String> hologramTexts) {
        Message.hologramTexts = hologramTexts;
    }

    public static String toRoman(int input) {
        StringBuilder s = new StringBuilder();
        while (input >= 100) {
            s.append("C");
            input -= 100;
        }
        while (input >= 90) {
            s.append("XC");
            input -= 90;
        }
        while (input >= 50) {
            s.append("L");
            input -= 50;
        }
        while (input >= 40) {
            s.append("XL");
            input -= 40;
        }
        while (input >= 10) {
            s.append("X");
            input -= 10;
        }
        while (input >= 9) {
            s.append("IX");
            input -= 9;
        }
        while (input >= 5) {
            s.append("V");
            input -= 5;
        }
        while (input >= 4) {
            s.append("IV");
            input -= 4;
        }
        while (input >= 1) {
            s.append("I");
            input -= 1;
        }
        return s.toString();
    }

    public String getPath() {
        return path;
    }

    public String get() {
        return Settings.getMessages().getString(path).replace("<prefix>",
                Settings.getMessages().getString(PREFIX.getPath()) + "");
    }

    public void send(Player p) {
        msg(p, get());
    }
    public void send(CommandSender p) { msg(p, get()); }

}

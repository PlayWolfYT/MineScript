package net.playwolf.minescript.utils;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.minimessage.MiniMessage;

public class Message {

    public static String colored(TextColor color, String message) {
        return "<" + color.asHexString() + ">" + message + "</" + color.asHexString() + ">";
    }

    public static Component mm(String message) {
        return MiniMessage.miniMessage().deserialize(PREFIX + message);
    }

    public static final String PREFIX = "<#888888>[<#cccccc>Mine<#007acc>Script<#888888>] ";

    public static void sendMultiline(CommandSender sender, String... messages) {
        // Prepend the prefix to each message, then concatenate them with newlines
        String[] prefixedMessages = Arrays.stream(messages)
                .map(message -> PREFIX + message)
                .toArray(String[]::new);
        sender.sendRichMessage(String.join("\n", prefixedMessages));
    }

    public static void send(CommandSender sender, String message) {
        sender.sendRichMessage(PREFIX + message);
    }

    public static void send(Audience sender, String message) {
        send(sender, message);
    }

    public static void log(String message) {
        send(Bukkit.getConsoleSender(), message);
    }

    public static void broadcast(String message) {
        Audience console = Bukkit.getConsoleSender();
        Audience players = Audience
                .audience(Bukkit.getOnlinePlayers().stream().map(p -> (Audience) p).collect(Collectors.toList()));
        Audience audience = Audience.audience(console, players);
        audience.sendMessage(mm(message));
    }
}

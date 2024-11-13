package net.playwolf.minescript.commands;

import org.bukkit.command.CommandSender;

import io.papermc.paper.command.brigadier.BasicCommand;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import net.kyori.adventure.text.format.NamedTextColor;
import net.playwolf.minescript.objects.NPC;
import net.playwolf.minescript.utils.Message;

import org.bukkit.entity.Player;

public class CommandNPC implements BasicCommand {

    private final String[] HELP_TEXT = {
            Message.colored(NamedTextColor.GRAY,
                    "=".repeat(10) + Message.colored(NamedTextColor.GOLD, " NPC Commands ") + "=".repeat(10)),
            Message.colored(NamedTextColor.GRAY, "/" + Message.colored(NamedTextColor.GOLD, "npc add <id> <name>")),
            Message.colored(NamedTextColor.GRAY, "/" + Message.colored(NamedTextColor.GOLD, "npc remove <id>")),
            Message.colored(NamedTextColor.GRAY, "/" + Message.colored(NamedTextColor.GOLD, "npc list")),
    };

    @Override
    public void execute(CommandSourceStack stack, String[] args) {
        CommandSender sender = stack.getSender();

        if (args.length == 0) {
            Message.sendMultiline(sender, HELP_TEXT);
            return;
        }

        String cmd = args[0];

        // modern switch-case
        switch (cmd) {
            case "create" -> {
                if (!(sender instanceof Player)) {
                    Message.send(sender, "You must be a player to create an NPC");
                    return;
                }

                Player player = (Player) sender;

                if (args.length < 3) {
                    Message.send(player, "<#" + NamedTextColor.GOLD.asHexString() + ">Usage: /npc create <id> <name>");
                    return;
                }

                String id = args[1];
                String name = args[2];

                NPC npc = new NPC(name, player.getLocation(), (p) -> true);

                Message.send(player,
                        Message.colored(
                                NamedTextColor.GREEN, "NPC created with id "
                                        + Message.colored(NamedTextColor.GOLD, id) +
                                        " and name "
                                        + Message.colored(NamedTextColor.GOLD, name)));
            }
        }
    }
}

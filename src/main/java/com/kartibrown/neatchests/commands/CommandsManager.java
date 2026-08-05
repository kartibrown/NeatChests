package com.kartibrown.neatchests.commands;

import com.kartibrown.neatchests.config.ConfigManager;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import org.bukkit.command.CommandSender;
import org.jspecify.annotations.NonNull;

public final class CommandsManager {
    private static final String RELOAD_PERMISSION = "neatchests.reload";
    public static final String SORT_PERMISSION = "neatchests.sort";

    final String version;

    final ConfigManager configManager;

    public CommandsManager(
            final ConfigManager configManager,
            final String version) {
        this.configManager = configManager;

        this.version = version;
    }

    public LiteralArgumentBuilder<CommandSourceStack> createCommandTree() {
        return Commands.literal("neatchests")
                .executes(this::help)
                .then(Commands.literal("reload").requires(source ->
                                source.getSender().hasPermission(RELOAD_PERMISSION))
                        .executes(this::reload)
                );
    }

    private int help(final @NonNull CommandContext<CommandSourceStack> ctx) {
        sendMessageToSender("version: " + version, ctx);
        return 1;
    }

    private int reload(final @NonNull CommandContext<CommandSourceStack> ctx) {
        configManager.reload();

        sendMessageToSender("Config Reloaded!", ctx);

        return Command.SINGLE_SUCCESS;
    }

    private void sendMessageToSender(
            final String message,
            final @NonNull CommandContext<CommandSourceStack> ctx) {

        final CommandSender sender = ctx.getSource().getSender();
        sender.sendMessage("[NeatChests] " + message);
    }
}

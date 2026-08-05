package com.kartibrown.neatchests.commands;

import com.kartibrown.neatchests.config.ConfigManager;
import com.kartibrown.neatchests.config.LoggerManager;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jspecify.annotations.NonNull;

public final class CommandsManager {
    public CommandsManager() {

    }

    public LiteralArgumentBuilder<CommandSourceStack> createCommandTree(
            final ConfigManager configManager,
            final LoggerManager logger) {
        return Commands.literal("neatchests")
                .executes(this::help)
                .then(Commands.literal("reload")
                        .executes(ctx -> reload(ctx, configManager, logger))
                );
    }

    private int help(final CommandContext<CommandSourceStack> ctx) {
        return 1;
    }

    private int reload(final @NonNull CommandContext<CommandSourceStack> ctx,
                       final @NonNull ConfigManager configManager,
                       final @NonNull LoggerManager logger) {
        configManager.reload();
        logger.info("Configuration reloaded via command.");

        final CommandSender sender = ctx.getSource().getSender();
        if (sender instanceof Player) {
            sender.sendMessage("Config Reloaded!");
        }

        return 1;
    }
}

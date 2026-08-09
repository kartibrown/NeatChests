package com.kartibrown.neatchests.commands;

import com.kartibrown.neatchests.config.ConfigManager;
import com.kartibrown.neatchests.cooldown.CooldownManager;
import com.kartibrown.neatchests.logger.LoggerManager;
import com.kartibrown.neatchests.sorting.SortingManager;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import org.bukkit.block.Block;
import org.bukkit.block.Container;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Contract;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public final class CommandsManager {
    private static final String RELOAD_PERMISSION = "neatchests.reload";
    public static final String SORT_PERMISSION = "neatchests.sort";

    final String version;

    final ConfigManager configManager;
    final SortingManager sortingManager;
    final LoggerManager logger;
    final CooldownManager cooldownManager;

    final Map<UUID, Long> lastCommandTracker;

    @Contract(pure = true)
    public CommandsManager(
            final ConfigManager configManager,
            final SortingManager sortingManager,
            final LoggerManager logger,
            final CooldownManager cooldownManager,
            final String version) {
        this.configManager = configManager;
        this.sortingManager = sortingManager;
        this.logger = logger;
        this.cooldownManager = cooldownManager;

        this.version = version;

        this.lastCommandTracker = new HashMap<>();
    }

    public LiteralArgumentBuilder<CommandSourceStack> createCommandTree(
            final String rootCommand
    ) {
        return Commands.literal(rootCommand)
                .executes(this::help)
                .then(Commands.literal("reload").requires(source ->
                                source.getSender().hasPermission(RELOAD_PERMISSION))
                        .executes(this::reload)
                )
                .then(Commands.literal("sort").requires(source ->
                                configManager.isCommandSortEnabled()
                                        && source.getSender().hasPermission(SORT_PERMISSION))
                        .executes(this::sortStorage)
                        .then(Commands.literal("inventory")
                                .executes(this::sortInventory)));
    }

    /*
     * COMMAND METHODS
     */

    private int sortInventory(
            final @NonNull CommandContext<CommandSourceStack> ctx) {

        final Player player = requirePlayer(ctx.getSource().getSender());
        if (player == null) {
            return 0;
        }

        if (cooldownManager.hasCommandCooldown(player)) {
            player.sendMessage("§cYou're using this command too quickly!");
            return 0;
        }

        sortingManager.sortInventory(player.getInventory());

        player.sendMessage("§aInventory sorted!");

        return Command.SINGLE_SUCCESS;
    }

    private int sortStorage(
            final @NonNull CommandContext<CommandSourceStack> ctx) {
        final Player player = requirePlayer(ctx.getSource().getSender());
        if (player == null) {
            return 0;
        }

        if (cooldownManager.hasCommandCooldown(player)) {
            player.sendMessage("§cYou're using this command too quickly!");
            return 0;
        }

        final Block block = player.getTargetBlockExact(5);

        if (block == null) {
            player.sendMessage("§cYou're not looking at a container!");
            return 0;
        }

        if (!((block.getState()) instanceof final Container container)) {
            player.sendMessage("§cYou're not looking at a sortable container!");
            return 0;
        }

        if (!sortingManager.isSortableInventory(container.getInventory())) {
            player.sendMessage("§cYou're not looking at a supported block!");
            return 0;
        }

        sortingManager.sortInventory(container.getInventory());
        player.sendMessage("§aChest sorted!");
        return Command.SINGLE_SUCCESS;
    }

    private int help(final @NonNull CommandContext<CommandSourceStack> ctx) {
        sendMessageToSender("version: " + version, ctx);
        return Command.SINGLE_SUCCESS;
    }

    private int reload(final @NonNull CommandContext<CommandSourceStack> ctx) {
        configManager.reload();

        sendMessageToSender("Config Reloaded!", ctx);

        return Command.SINGLE_SUCCESS;
    }

    /*
     * HELPER METHODS
     */

    private @Nullable Player requirePlayer(final CommandSender sender) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("§cThis command can only be used by players.");
            return null;
        }

        return player;
    }

    private void sendMessageToSender(
            final String message,
            final @NonNull CommandContext<CommandSourceStack> ctx) {

        final CommandSender sender = ctx.getSource().getSender();
        sender.sendMessage("[NeatChests] " + message);
    }
}

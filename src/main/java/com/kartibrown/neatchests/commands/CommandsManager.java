package com.kartibrown.neatchests.commands;

import io.papermc.paper.command.brigadier.Commands;

public final class CommandsManager {
    public CommandsManager() {
        Commands.literal("neatchests")
                .executes(ctx -> {
                    // Show help and version
                    return 1;
                })
                .then(Commands.literal("reload")
                        .executes(ctx -> {
                            // Reload
                            return 1;
                        })
                );
    }
}

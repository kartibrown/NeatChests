# NeatChests

Tired of sorting plugins that place diamonds and diamond blocks nowhere near each other?

**NeatChests sorts items by category instead of only by name**, keeping related materials together and making your storage easier to navigate.

Sort both chests and your player inventory instantly using either double-click or commands.

## Features

- 🧠 **Category-Based Sorting:** Related items, such as diamonds and diamond blocks, are placed together.
- 📦 **Chest Sorting:** Organize supported containers instantly.
- 🎒 **Player Inventory Sorting:** Keep your inventory neat using the same sorting system.
- ⚖️ **Configurable Weights:** Customize category order or individual item weights using **Simple** or **Advanced** mode.
- 🛡️ **Protection Plugin Support:** Compatible with **WorldGuard**, **Residence**, and **Towny** protected containers.
- ⚡ **Lightweight & Fast:** Designed for minimal server overhead.
- 🔄 **Safe Sorting:** Handles simultaneous inventory changes, including hopper interactions.
- ⚙️ **Configurable:** Includes configurable logging, command aliases, and sorting options.
- 🪶 **No Dependencies:** Works out of the box without requiring additional plugins.

## Commands

Both `/neatchests` and `/nc` can be used by default. Additional aliases can be configured in `config.yml`.

| Command | Description | Permission |
|---------|-------------|------------|
| `/neatchests` | Displays plugin information. | None |
| `/neatchests reload` | Reloads the plugin configuration. | `neatchests.reload` |
| `/neatchests sort` | Sorts the targeted container. | `neatchests.sort` |
| `/neatchests sort inventory` | Sorts the player inventory. | `neatchests.sort` |

## Compatibility

### Minecraft

> ⚠️ **Important:** NeatChests currently supports Minecraft **1.21.6 (API 26.2)** and newer. Older API versions are not officially supported.

### Protection Plugins

NeatChests supports sorting protected containers from:

- ✅ WorldGuard
- ✅ Residence
- ✅ Towny

Additional plugins may be supported in future releases.

## Installation

1. Download the latest release `.jar`.
2. Place it in your server's `plugins` folder.
3. Start or restart your server.
4. Edit `config.yml` if desired.
5. Enjoy your neatly organized storage!

## How to Sort

### Double-click

1. Open a supported container.
2. **Double-click an empty inventory slot.**

### Command

Look at a supported container and run:

```text
/neatchests sort
```

To sort your own inventory, run:

```text
/neatchests sort inventory
```

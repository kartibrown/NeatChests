# NeatChests

Tired of sorting plugins that place diamonds and diamond blocks nowhere near each other?

**NeatChests sorts items by category instead of only by name**, keeping related materials together and making your storage easier to navigate.

Sort both containers and your player inventory instantly using either double-click or commands.

## Features

- 🧠 **Category-Based Sorting:** Related items, such as diamonds and diamond blocks, are placed together.
- 🧩 **Multiple Sorting Modes:** Choose between `category`, `family`, and `block-type` sorting styles.
- 📦 **Container Sorting:** Organize supported containers instantly.
- 🎒 **Player Inventory Sorting:** Keep your inventory neat using the same sorting system.
- ⚖️ **Configurable Weights:** Customize category order or individual item weights using **Simple** or **Advanced** mode.
- 🛡️ **Protection Plugin Support:** Compatible with protected containers from **WorldGuard**, **Residence**, and **Towny**.
- ⚡ **Lightweight & Fast:** Designed for minimal server overhead.
- 🔄 **Safe Sorting:** Handles simultaneous inventory changes, including hopper interactions.
- ⚙️ **Configurable:** Customize sorting behavior, command aliases, logging, and more through `config.yml`.
- 🪶 **No Required Dependencies:** Works out of the box without requiring additional plugins.

## Sorting Modes

NeatChests supports multiple sorting styles:

- **Category:** Groups items by category, such as building blocks, miscellaneous items, leaves, and saplings.
- **Family:** Keeps material families together, such as all oak items followed by all spruce items.
- **Block Type:** Groups matching block types together, such as all logs followed by all planks.

The sorting mode can be changed in `config.yml`.

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

> ⚠️ Important: NeatChests currently supports Minecraft 26.2 / Paper 26.2 and newer. Older versions are not officially supported.

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
```
/neatchests sort inventory
```

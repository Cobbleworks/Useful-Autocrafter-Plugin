<p align="center">
  <img src="images/plugin-logo.png" alt="Useful Autocrafter Plugin" width="180" />
</p>
<h1 align="center">Useful Autocrafter Plugin</h1>
<p align="center">
  <b>Autocrafter inventory protection for Minecraft servers.</b><br>
  <b>Prevents autocrafters from depleting ingredient slots below 1 item.</b>
</p>
<p align="center">
  <a href="https://github.com/Cobbleworks/Useful-Autocrafter-Plugin/releases"><img src="https://img.shields.io/github/v/release/Cobbleworks/Useful-Autocrafter-Plugin?include_prereleases&style=flat-square&color=4CAF50" alt="Latest Release"></a>&nbsp;&nbsp;<a href="https://github.com/Cobbleworks/Useful-Autocrafter-Plugin/blob/main/LICENSE"><img src="https://img.shields.io/badge/License-MIT-blue?style=flat-square" alt="License"></a>&nbsp;&nbsp;<img src="https://img.shields.io/badge/Java-21+-orange?style=flat-square" alt="Java Version">&nbsp;&nbsp;<img src="https://img.shields.io/badge/Minecraft-1.21+-green?style=flat-square" alt="Minecraft Version">&nbsp;&nbsp;<img src="https://img.shields.io/badge/Platform-Paper-yellow?style=flat-square" alt="Platform">&nbsp;&nbsp;<img src="https://img.shields.io/badge/Status-Active-brightgreen?style=flat-square" alt="Status">&nbsp;&nbsp;<a href="https://github.com/Cobbleworks/Useful-Autocrafter-Plugin/issues"><img src="https://img.shields.io/github/issues/Cobbleworks/Useful-Autocrafter-Plugin?style=flat-square&color=orange" alt="Open Issues"></a>
</p>

Useful Autocrafter is an open-source Paper plugin that fixes a common autocrafter problem: the vanilla autocrafter consumes all items in a slot and stops, leaving the recipe unfillable until the slot is manually restocked. This plugin intercepts every craft attempt and cancels it if any enabled slot is down to its last item, ensuring autocrafters always retain at least 1 item per slot as a buffer. Enabled by default with no configuration required.

### **Core Features**

- **Last-item protection:** Cancels any crafting attempt where at least one enabled slot holds exactly 1 item, guaranteeing a minimum of 1 item is always retained per slot
- **Per-slot awareness:** Respects the vanilla crafter's disabled-slot toggle - only enabled slots are checked, disabled slots are ignored
- **Runtime toggle:** Enable or disable protection on a running server without a restart using `/usefulcrafter on|off`
- **Zero configuration:** No `config.yml` required - the plugin works immediately after installation with sensible defaults

### **Supported Platforms**

- **Server Software:** `Paper` only (`CrafterCraftEvent` and `Crafter` block state API are Paper-exclusive)
- **Minecraft Versions:** `1.21` and higher (autocrafter block requires 1.21+)
- **Java Requirements:** `Java 21+`
- **Dependencies:** None - no external plugins required

## **Table of Contents**

1. [Getting Started](#getting-started)
    - [Prerequisites](#prerequisites)
    - [Installation Steps](#installation-steps)
    - [Verifying Installation](#verifying-installation)
2. [How It Works](#how-it-works)
3. [Commands](#commands)
    - [Command Reference](#command-reference)
4. [Permissions](#permissions)
5. [Building from Source](#building-from-source)
6. [License](#license)

## **Getting Started**

### **Prerequisites**

Before installing Useful Autocrafter, confirm the following requirements are met:

- A Minecraft server running **Paper** or a Paper fork (Purpur, etc.)
- Server version **1.21 or higher**
- **Java 21** or newer installed on the machine running the server
- Operator or console access to install plugin files

Spigot and CraftBukkit are **not supported** - the plugin relies on Paper's `CrafterCraftEvent` and `Crafter` inventory API which are Paper-only.

### **Installation Steps**

1. Download the latest `Useful-Autocrafters-x.x.x.jar` from the [Releases](https://github.com/Cobbleworks/Useful-Autocrafter-Plugin/releases) page
2. **Stop your server completely** before placing any files
3. Copy the `.jar` into your server's `plugins/` directory
4. Start the server - protection is active immediately, no configuration needed

### **Verifying Installation**

- Run `/plugins` in-game - `Useful-Autocrafters` should appear green in the list
- Run `/usefulcrafter` to confirm the plugin reports `ENABLED` status
- Place an autocrafter with a crafting recipe and fill ingredient slots with exactly 1 item each - crafting should be blocked
- Fill ingredient slots with 2+ items each - crafting should proceed normally

## **How It Works**

The plugin listens to Paper's `CrafterCraftEvent` at `HIGHEST` priority. Every time an autocrafter attempts to produce an output item, the event handler:

1. Retrieves the `Crafter` block state from the event's block
2. Iterates all 9 crafting slots (indices 0-8)
3. For each slot that is **not** disabled in the crafter UI, checks if the slot holds exactly 1 item
4. If any enabled slot is at exactly 1 item, the event is **cancelled** and no output is produced
5. If all enabled slots have 2 or more items (or are empty), crafting proceeds normally

When protection fires, the autocrafter silently skips the craft cycle. No message is sent to players. The autocrafter will resume crafting automatically once the slot is restocked above 1 item.

**Important:** The plugin state (enabled/disabled) is **not persisted** across server restarts. The plugin always starts in the `enabled` state. If you need protection disabled permanently, use `/usefulcrafter off` after each restart or remove the plugin.

## **Commands**

All commands require the `usefulautocrafters.toggle` permission (operator by default).

**Root command:** `/usefulcrafter`  
**Aliases:** `/uac`, `/ucraft`

### **Command Reference**

| Command | Description |
|---------|-------------|
| `/usefulcrafter` | Show current enabled/disabled status |
| `/usefulcrafter on` | Enable last-item protection (also accepts `enable`) |
| `/usefulcrafter off` | Disable last-item protection (also accepts `disable`) |

## **Permissions**

| Permission | Description | Default |
|------------|-------------|---------|
| `usefulautocrafters.toggle` | Toggle protection on or off via `/usefulcrafter` | `op` |

## **Building from Source**

Useful Autocrafter uses **Apache Maven** as its build system.

**Requirements:**
- Java 21 or newer
- Apache Maven 3.6 or newer

**Steps:**

```bash
# Clone the repository
git clone https://github.com/Cobbleworks/Useful-Autocrafter-Plugin.git
cd Useful-Autocrafter-Plugin

# Compile and package
mvn clean package
```

The output JAR is written to `target/Useful-Autocrafters-x.x.x.jar`. Copy it into your server's `plugins/` folder as described in the [Installation Steps](#installation-steps) section.

**Project Structure:**

```
src/main/
├── java/com/usefulautocrafters/
│   ├── UsefulAutocrafters.java        - Plugin entry point (onEnable / onDisable)
│   ├── AutocrafterListener.java       - CrafterCraftEvent handler, last-item check logic
│   └── UsefulCrafterCommand.java      - /usefulcrafter command + tab completion
└── resources/
    └── plugin.yml                     - Plugin metadata, commands, permissions
```

## **License**

This project is licensed under the **MIT License** - see the [LICENSE](LICENSE) file for details.

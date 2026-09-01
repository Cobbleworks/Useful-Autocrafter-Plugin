<p align="center">
  <img src="images/plugin-logo.png" alt="Useful Autocrafter Plugin" width="200" height="200" />
</p>
<h1 align="center">Useful Autocrafter Plugin</h1>
<p align="center">
  <b>Keep automated recipes primed by reserving the last item in every active crafter slot.</b>
</p>
<p align="center">
  <a href="https://github.com/Cobbleworks/Useful-Autocrafter-Plugin/releases"><img src="https://img.shields.io/github/v/release/Cobbleworks/Useful-Autocrafter-Plugin?include_prereleases&style=flat-square&color=4CAF50" alt="Latest Release"></a>&nbsp;&nbsp;<a href="https://github.com/Cobbleworks/Useful-Autocrafter-Plugin/blob/main/LICENSE"><img src="https://img.shields.io/badge/License-MIT-blue?style=flat-square" alt="License"></a>&nbsp;&nbsp;<img src="https://img.shields.io/badge/Java-21+-orange?style=flat-square" alt="Java Version">&nbsp;&nbsp;<img src="https://img.shields.io/badge/Minecraft-1.21+-green?style=flat-square" alt="Minecraft Version">&nbsp;&nbsp;<img src="https://img.shields.io/badge/Platform-Paper-yellow?style=flat-square" alt="Platform">&nbsp;&nbsp;<a href="https://github.com/Cobbleworks/Useful-Autocrafter-Plugin/issues"><img src="https://img.shields.io/github/issues/Cobbleworks/Useful-Autocrafter-Plugin?style=flat-square&color=orange" alt="Open Issues"></a>
</p>

Useful Autocrafter prevents an automated recipe from consuming the final item in any enabled crafter slot. It also stops hoppers from pulling ingredient stacks out of the Crafter inventory, allowing a hopper at the output face to collect crafted results without slowly dismantling the recipe. Production pauses at a one-item reserve and resumes automatically when the ingredients are restocked.

### **Core Features**

- Preserves one ingredient in every enabled, occupied crafter slot
- Prevents hoppers and other automated inventories from extracting Crafter ingredients
- Works with shaped and shapeless recipes without changing their outputs
- Respects slots disabled through the vanilla crafter interface
- Can be enabled, disabled, or inspected at runtime
- Requires no configuration file or external plugin

### **Supported Platforms**

- **Server software:** Paper and compatible Paper forks
- **Minecraft:** 1.21 or newer
- **Java:** 21 or newer
- **Dependencies:** None

## **Table of Contents**

1. [Getting Started](#getting-started)
2. [Third-Party Plugins](#third-party-plugins)
3. [How It Works](#how-it-works)
4. [Commands](#commands)
5. [Permissions](#permissions)
6. [Operational Notes](#operational-notes)
7. [Building from Source](#building-from-source)
8. [License](#license)
9. [Screenshots](#screenshots)

## **Getting Started**

1. Download the latest JAR from [Releases](https://github.com/Cobbleworks/Useful-Autocrafter-Plugin/releases).
2. Stop the server and copy the JAR into `plugins/`.
3. Start the server. Protection is enabled immediately.
4. Run `/usefulcrafter` to confirm the current state.

To test the reserve behavior, configure a Crafter recipe and put two or more ingredients in every required slot. Point the Crafter's output face toward a hopper or other container. It will craft normally until one required slot reaches a single item, then wait for restocking; the output container can keep collecting completed recipes without extracting the ingredients.

> Useful Autocrafter uses Paper's crafter event API and does not support Spigot or CraftBukkit.

## **Third-Party Plugins**

No third-party plugins are required. The release JAR is self-contained and uses only the Paper server API.

## **How It Works**

For each attempted craft, the plugin checks the Crafter's nine input slots:

1. Slots disabled in the vanilla interface are skipped.
2. Empty slots are ignored.
3. If an enabled slot contains exactly one item, that craft is cancelled.
4. Otherwise, Minecraft completes the craft normally.

The plugin does not insert, duplicate, or restore items. Cancelling the craft before consumption is what leaves the one-item recipe pattern in place. No chat message is sent for blocked cycles, so redstone production lines can wait quietly until a hopper supplies more ingredients.

### Hopper and Container Automation

While protection is enabled, automated inventory transfers whose source is a Crafter are cancelled. This prevents a hopper below or beside the block from taking its recipe ingredients—even when a slot contains more than one item.

Crafted results are not pulled from the Crafter inventory. Minecraft dispenses them through the block's output face, so a correctly positioned hopper, chest, or barrel still receives every completed output. Face the Crafter toward the collection container; do not rely on the container extracting items from the input grid.

Players can still open the Crafter and remove ingredients manually. Turning the plugin off restores normal automated extraction as well as normal last-item crafting.

### **Examples**

| Situation | Result |
|-----------|--------|
| Every required slot contains at least 2 items | Craft proceeds |
| One enabled ingredient slot contains exactly 1 item | Craft is cancelled |
| A disabled slot is empty or contains 1 item | Slot is ignored |
| A hopper tries to pull ingredients from the Crafter | Transfer is cancelled |
| The Crafter dispenses a finished recipe into its output container | Output is collected normally |
| Protection is turned off | Vanilla crafter behavior applies |

## **Commands**

**Root command:** `/usefulcrafter`  
**Aliases:** `/uac`, `/ucraft`

| Command | Description |
|---------|-------------|
| `/usefulcrafter` | Show whether protection is currently enabled |
| `/usefulcrafter on` | Enable protection; `enable` is also accepted |
| `/usefulcrafter off` | Disable protection; `disable` is also accepted |

Commands can be run by a player or from the server console.

## **Permissions**

| Permission | Description | Default |
|------------|-------------|---------|
| `usefulautocrafters.toggle` | View or change the runtime protection state | `op` |

## **Operational Notes**

- The toggle is server-wide rather than per player or per crafter.
- The enabled/disabled state is held in memory and is not persisted. Protection starts enabled after every restart.
- There is no `config.yml`; remove the plugin if the reserve behavior should never be active.
- Blocked cycles are silent and automatically work again after restocking.
- Hopper protection is intentionally all-or-nothing for the Crafter's input inventory. This is more reliable than trying to infer a hopper's source slot after Paper has begun a transfer.

## **Building from Source**

Requirements: Java 21 or newer and Maven 3.6 or newer.

```bash
git clone https://github.com/Cobbleworks/Useful-Autocrafter-Plugin.git
cd Useful-Autocrafter-Plugin
mvn clean verify
```

The packaged plugin is written to `target/`.

## **License**

Useful Autocrafter is released under the [MIT License](LICENSE).

## **Screenshots**

<table>
  <tr>
    <th>Useful Autocrafter - Cookie Production</th>
    <th>Useful Autocrafter - Recipe Setup</th>
  </tr>
  <tr>
    <td><a href="https://github.com/Cobbleworks/Useful-Autocrafter-Plugin/raw/main/images/screenshot-cookie-production.png"><img src="https://github.com/Cobbleworks/Useful-Autocrafter-Plugin/raw/main/images/screenshot-cookie-production.png" alt="A crafter producing cookies until the ingredient reserve is reached" width="450"></a></td>
    <td><a href="https://github.com/Cobbleworks/Useful-Autocrafter-Plugin/raw/main/images/screenshot-recipe-setup.png"><img src="https://github.com/Cobbleworks/Useful-Autocrafter-Plugin/raw/main/images/screenshot-recipe-setup.png" alt="A recipe configured in the vanilla crafter interface" width="450"></a></td>
  </tr>
  <tr>
    <th>Useful Autocrafter - Copper Production</th>
    <th>Useful Autocrafter - Reserved Ingredients</th>
  </tr>
  <tr>
    <td><a href="https://github.com/Cobbleworks/Useful-Autocrafter-Plugin/raw/main/images/screenshot-copper-production.png"><img src="https://github.com/Cobbleworks/Useful-Autocrafter-Plugin/raw/main/images/screenshot-copper-production.png" alt="A redstone-driven crafter producing copper ingots" width="450"></a></td>
    <td><a href="https://github.com/Cobbleworks/Useful-Autocrafter-Plugin/raw/main/images/screenshot-reserved-ingredients.png"><img src="https://github.com/Cobbleworks/Useful-Autocrafter-Plugin/raw/main/images/screenshot-reserved-ingredients.png" alt="One ingredient remaining in each required slot after crafting" width="450"></a></td>
  </tr>
  <tr>
    <th>Useful Autocrafter - Last-Item Protection</th>
    <th>Useful Autocrafter - Enable Command</th>
  </tr>
  <tr>
    <td><a href="https://github.com/Cobbleworks/Useful-Autocrafter-Plugin/raw/main/images/screenshot-last-item-protection.png"><img src="https://github.com/Cobbleworks/Useful-Autocrafter-Plugin/raw/main/images/screenshot-last-item-protection.png" alt="A craft blocked because a slot has reached its final item" width="450"></a></td>
    <td><a href="https://github.com/Cobbleworks/Useful-Autocrafter-Plugin/raw/main/images/screenshot-enable-command.png"><img src="https://github.com/Cobbleworks/Useful-Autocrafter-Plugin/raw/main/images/screenshot-enable-command.png" alt="Enabling Useful Autocrafter with a command" width="450"></a></td>
  </tr>
</table>

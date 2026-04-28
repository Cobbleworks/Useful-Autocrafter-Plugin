# Useful-Autocrafters

![image](icon.png)

A Minecraft plugin for PaperMC 1.21+ that prevents autocrafters from running out of items and keeps at least 1 item in each enabled slot. This ensures your autocrafters always have the necessary materials to keep crafting without interruption...honestly how they should have been in the first place.

## Features

- Prevents autocrafters from crafting when any enabled slot has only 1 item remaining
- Ensures autocrafters always maintain at least 1 item in each enabled slot
- Toggle functionality on/off with a simple command
- Enabled by default - works automatically after installation

## How It Works

The plugin monitors all autocrafter crafting events. Before an autocrafter can craft an item, it checks all enabled slots:

- If any enabled slot has exactly 1 item, the crafting is cancelled
- This ensures items in enabled slots never decrease below 1
- Only allows crafting when all enabled slots have 2 or more items

## Commands

| Command              | Aliases           | Description          | Permission                  |
| -------------------- | ----------------- | -------------------- | --------------------------- |
| `/usefulcrafter`     | `/uac`, `/ucraft` | Shows current status | `usefulautocrafters.toggle` |
| `/usefulcrafter on`  | `/uac enable`     | Enables the feature  | `usefulautocrafters.toggle` |
| `/usefulcrafter off` | `/uac disable`    | Disables the feature | `usefulautocrafters.toggle` |

## Permissions

| Permission                  | Description                              | Default |
| --------------------------- | ---------------------------------------- | ------- |
| `usefulautocrafters.toggle` | Allows toggling the plugin functionality | `op`    |

## Installation

1. Download the latest release JAR file
2. Place it in your server's `plugins` folder
3. Restart your server
4. The plugin is enabled by default - use `/usefulcrafter off` to disable if needed

## Building

Requirements:

- Java 21 or higher
- Maven 3.6 or higher

```bash
mvn clean package
```

The compiled JAR will be in the `target` folder.

## Compatibility

- Minecraft Version: 1.21+
- Server Software: PaperMC, Purpur, or any Paper-based server

## License

This plugin is provided as-is for use on Minecraft servers.

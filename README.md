# Useful-Autocrafters

A Minecraft plugin for PaperMC 1.21+ that prevents autocrafters from running out of items.

## Features

- Prevents autocrafters from crafting when any enabled slot has only 1 item remaining
- Ensures autocrafters always maintain at least 1 item in each enabled slot
- No configuration needed - works automatically

## How It Works

The plugin monitors all autocrafter crafting events. Before an autocrafter can craft an item, it checks all enabled slots:

- If any enabled slot has exactly 1 item, the crafting is cancelled
- This ensures items in enabled slots never decrease below 1
- Only allows crafting when all enabled slots have 2 or more items

## Installation

1. Download the latest release JAR file
2. Place it in your server's `plugins` folder
3. Restart your server

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

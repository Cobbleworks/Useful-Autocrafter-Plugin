# Changelog

All notable changes to Useful Autocrafter will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/).

## [Unreleased]

### Added

- Prevent hoppers and other automated inventories from extracting ingredient stacks from Crafters while protection is enabled. Crafted output still enters containers through the Crafter's output face.

## [1.0.0] - 2026-04-29

Initial release of Useful Autocrafter - a Paper plugin that prevents autocrafters from depleting ingredient slots below 1 item.

### Core Features

- **Last-item protection:** Cancels any crafting attempt where at least one enabled slot holds exactly 1 item, ensuring autocrafters always retain a buffer of at least 1 item per slot
- **Per-slot awareness:** Respects the vanilla crafter's disabled-slot toggle - only enabled slots are checked, disabled slots are ignored
- **Runtime toggle:** Enable or disable protection on a running server without a restart using `/usefulcrafter on|off`
- **Zero configuration:** Works immediately after installation with no `config.yml` required

**Note:** If you encounter any bugs or issues, please don't hesitate to open an [issue](https://github.com/Cobbleworks/Useful-Autocrafter-Plugin/issues). For any questions or to start a discussion, feel free to initiate a [discussion](https://github.com/Cobbleworks/Useful-Autocrafter-Plugin/discussions) on the GitHub repository.

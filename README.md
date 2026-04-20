# Lily's Game

A small 2D Java game built with a custom game loop, tile-based maps, sprite rendering, and Swing for the desktop window.

## Run

From the project folder:

```bash
./run.sh
```

The script will:

- compile all Java files in `src/`
- place compiled classes in `bin/`
- launch the game with `main.Main`

## Tech Stack

### Java

The project is written in Java and organized into packages like `main`, `entity`, `tile`, and `object`.

### Swing and AWT

The game window is created with `JFrame`, and the main game surface extends `JPanel`. Rendering is handled with `Graphics2D`, and keyboard input is captured through the Java desktop UI stack.

### Custom 2D Game Loop

The game runs on a manual loop inside `GamePanel`, updating game state and repainting at a target FPS. This is a lightweight custom engine rather than a framework like LibGDX or Unity.

### Tile-Based Rendering

World layout is driven by map text files in `src/res/maps/`, with tile images loaded from `src/res/tiles/`.

### Sprite and Asset Loading

Character sprites, object art, fonts, and maps are loaded from the classpath under `src/res/`.

### Java Sound API

Sound effects and music are played with `javax.sound.sampled`, using `.wav` files from `src/res/sound/`.

## Project Structure

```text
src/
  main/      core game loop, window setup, input, audio, UI
  entity/    player and NPC logic
  object/    collectible and interactive world objects
  tile/      tile definitions and map rendering
  res/       images, maps, fonts, and sound assets

bin/         compiled output
run.sh       compile-and-run helper script
```

## Notes

- `run.sh` expects Java to be installed and available on your `PATH`.
- Assets are loaded as classpath resources, so compiling into `bin/` is part of the expected workflow.

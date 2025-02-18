package com.example.mud.controller;
            System.out.println("NPCs here: " + String.join(", ", currentRoom.getNPCs()));
        }
        }

private void move(String direction) {
    if (direction.isEmpty()) {
        System.out.println("Please specify a direction (e.g., 'move north').");
        return;
    }

    Room nextRoom = player.getCurrentRoom().getExit(direction);
    if (nextRoom == null) {
        System.out.println("You can't go that way!");
    } else {
        player.setCurrentRoom(nextRoom);
        System.out.println("You move " + direction + ".");
        lookAround();
    }
}

private void pickUp(String argument) {
    if (!argument.startsWith("up ")) {
        System.out.println("Format: 'pick up <item>'");
        return;
    }

    String itemName = argument.substring(3).trim();
    if (itemName.isEmpty()) {
        System.out.println("Specify an item to pick up.");
        return;
    }

    Room currentRoom = player.getCurrentRoom();
    if (currentRoom.removeItem(itemName)) {
        player.addToInventory(itemName);
        System.out.println("You picked up: " + itemName);
    } else {
        System.out.println("No item named '" + itemName + "' found here.");
    }
}

private void checkInventory() {
    if (player.getInventory().isEmpty()) {
        System.out.println("Your inventory is empty.");
    } else {
        System.out.println("Your inventory contains:");
        for (String item : player.getInventory()) {
            System.out.println("- " + item);
        }
    }
}

private void showHelp() {
    System.out.println("\nAvailable commands:");
    System.out.println("look             - Describe the current room");
    System.out.println("move <direction> - Move in a specified direction");
    System.out.println("pick up <item>   - Pick up an item");
    System.out.println("inventory        - Check your inventory");
    System.out.println("help             - Show available commands");
    System.out.println("quit / exit      - End the game");
}

public static void main(String[] args) {
    Room startRoom = new Room("A dimly lit cave.");
    startRoom.addItem("sword");
    startRoom.addItem("shield");

    Player player = new Player("Adventurer", startRoom);
    MUDController controller = new MUDController(player);
    controller.runGameLoop();
}
}
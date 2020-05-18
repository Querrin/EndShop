package com.hooklite.endshop.commands;

public class CommandStatus {
    private static boolean shopCommandEnabled = true;

    /**
     * Disables the shop command.
     */
    public static void disableShopCommand() {
        shopCommandEnabled = false;
    }

    /**
     * Enables the shop command.
     */
    public static void enableShopCommand() {
        shopCommandEnabled = true;
    }

    /**
     * Returns the status of the shop command.
     *
     * @return If the shop command is enabled.
     */
    static boolean isShopCommandEnabled() {
        return shopCommandEnabled;
    }
}

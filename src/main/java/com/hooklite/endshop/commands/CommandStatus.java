package com.hooklite.endshop.commands;

public class CommandStatus {
    private static boolean shopCommandEnabled = true;

    public static void disableShopCommand() {
        shopCommandEnabled = false;
    }

    public static void enableShopCommand() {
        shopCommandEnabled = true;
    }

    static boolean isShopCommandEnabled() {
        return shopCommandEnabled;
    }
}

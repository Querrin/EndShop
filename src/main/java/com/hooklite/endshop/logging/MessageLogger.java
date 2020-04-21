package com.hooklite.endshop.logging;

public class MessageLogger {
    public static void toConsole(String message) {
        System.out.println(LoggingData.PREFIX + message);
    }
}

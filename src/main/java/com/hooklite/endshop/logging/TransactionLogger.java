package com.hooklite.endshop.logging;

import com.hooklite.endshop.config.Configuration;
import com.hooklite.endshop.data.rewards.Action;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

public class TransactionLogger {
    private static final File LOG_FILE;

    static {
        File logFile;

        try {

            File logsFolder = new File(Configuration.getPlugin().getDataFolder() + "/logs");
            if(!logsFolder.exists()) {
                if(!logsFolder.mkdir()) {
                    MessageSender.toConsole("Failed creating logs folder! Unloading...");
                    throw new IOException("Failed creating logs folder! Unloading...");
                }
            }

            for(int i = 1; true; i++) {
                logFile = new File(logsFolder.getPath() + "/" + LocalDate.now().toString() + "-" + i + ".txt");

                if(!logFile.exists())
                    break;
            }

            if(!logFile.createNewFile())
                throw new IOException("Failed creating log file!");
        }
        catch(IOException e) {
            logFile = null;
            Bukkit.getPluginManager().disablePlugin(Configuration.getPlugin());
            MessageSender.toConsole("Failed creating log file! Unloading...");
            e.printStackTrace();
            Bukkit.getPluginManager().disablePlugin(Configuration.getPlugin());
        }

        LOG_FILE = logFile;
    }

    public static void log(Player player, String requirement, String reward, Action action) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(LOG_FILE, true));

            String output = player.getUniqueId() + "  " + player.getName() + "  |  ";
            output += (action == Action.BUY ? "BUY    " : "SELL   ") + requirement + " >> " + reward + System.lineSeparator();

            writer.write(output);

            writer.close();
        }
        catch(IOException e) {
            e.printStackTrace();
            MessageSender.toConsole("Failed logging to file!");
        }
    }
}

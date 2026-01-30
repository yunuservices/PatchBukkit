package org.patchbukkit.command;

import java.lang.reflect.Constructor;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.Plugin;

public class CommandFactory {
    public static PluginCommand create(String name, Plugin plugin) {
        try {
            Constructor<PluginCommand> constructor = PluginCommand.class.getDeclaredConstructor(String.class, Plugin.class);
            constructor.setAccessible(true);
            return constructor.newInstance(name, plugin);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
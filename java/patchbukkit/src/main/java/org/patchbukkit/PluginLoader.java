package org.patchbukkit.loader;

import io.papermc.paper.plugin.provider.util.ProviderUtil;
import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bukkit.plugin.java.JavaPlugin;

public class PluginLoader {

    private static final Logger LOGGER = Logger.getLogger("PatchBukkit");

    public static JavaPlugin createPlugin(String jarPath, String mainClass) {
        try {
            File jarFile = new File(jarPath);
            if (!jarFile.exists()) {
                LOGGER.severe("Plugin file does not exist: " + jarPath);
                return null;
            }

            PatchBukkitPluginClassLoader classLoader =
                new PatchBukkitPluginClassLoader(
                    PluginLoader.class.getClassLoader(),
                    jarFile
                );

            return ProviderUtil.loadClass(
                mainClass,
                JavaPlugin.class,
                classLoader
            );
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

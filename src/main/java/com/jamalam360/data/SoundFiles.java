package com.jamalam360.data;

import com.jamalam360.DiscusModInit;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.text.TranslatableText;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Level;

import java.io.File;
import java.util.Locale;

import static com.jamalam360.DiscusModInit.id;
import static com.jamalam360.DiscusModInit.log;

public class SoundFiles {
    private static final String SOUNDS_CONFIG_PATH = FabricLoader.getInstance().getConfigDir().resolve("discus").toString();
    private static final String SOUNDS_PATH = "sounds/";

    public static void copyAllSoundFiles() {
        try {
            File soundsConfigDirectory = new File(SOUNDS_CONFIG_PATH);
            File[] soundFiles = soundsConfigDirectory.listFiles();

            if (soundFiles.length <= 0 || soundFiles == null) { //No sound files in config directory
                log(Level.WARN, new TranslatableText("text.discus.no_sound_files").toString());
                return;
            }

            for (File soundFile : soundFiles) {
                DiscusModInit.RESOURCE_PACK.addAsset(id(SOUNDS_PATH + soundFile.getName().toLowerCase(Locale.ROOT)), FileUtils.readFileToByteArray(soundFile));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static File[] getAllSoundFiles() {
        return new File(SOUNDS_CONFIG_PATH).listFiles();
    }
}

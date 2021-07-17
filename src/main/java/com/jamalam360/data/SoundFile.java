package com.jamalam360.data;

import com.jamalam360.DiscusModInit;
import net.fabricmc.loader.api.FabricLoader;
import org.apache.commons.io.FileUtils;

import java.io.File;

import static com.jamalam360.DiscusModInit.id;

public class SoundFile {
    private static final String SOUNDS_CONFIG_PATH = FabricLoader.getInstance().getConfigDir().resolve("discus").toString();
    private static final String SOUNDS_PATH = "sounds/";

    public static void copyAllSoundFiles() {
        try {
            File soundsConfigDirectory = new File(SOUNDS_CONFIG_PATH);
            File[] soundFiles = soundsConfigDirectory.listFiles();

            for (File soundFile : soundFiles) {
                DiscusModInit.RESOURCE_PACK.addAsset(id(SOUNDS_PATH + soundFile.getName()), FileUtils.readFileToByteArray(soundFile));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package com.jamalam360.data;

import com.jamalam360.DiscusModInit;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.text.TranslatableText;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Level;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

import static com.jamalam360.DiscusModInit.log;
import static com.jamalam360.Identifiers.id;

public class SoundFiles {
    private static final File SOUNDS_CONFIG_DIRECTORY = new File(FabricLoader.getInstance().getConfigDir().resolve("discus").toString());
    private static final File SOUNDS_CACHE_DIRECTORY = new File(FabricLoader.getInstance().getConfigDir().resolve("discus/caches").toString());
    private static final String SOUNDS_PATH = "sounds/";

    public static void copyAllSoundFiles() {
        try {
            File[] soundFiles = SOUNDS_CONFIG_DIRECTORY.listFiles();

            if (soundFiles == null || soundFiles.length <= 0) { //No sound files in config directory
                log(Level.WARN, new TranslatableText("text.discus.no_sound_files").toString());
                return;
            }

            for (File soundFile : soundFiles) {
                DiscusModInit.RESOURCE_PACK.addAsset(id(SOUNDS_PATH + soundFile.getName().toLowerCase(Locale.ROOT)), FileUtils.readFileToByteArray(soundFile));
            }
        } catch (Exception e) {
            //Cry all you want Java, you and I both know this catch block will never be reached
        }
    }

    public static File[] getAllSoundFiles() {
        return SOUNDS_CONFIG_DIRECTORY.listFiles();
    }

    public static File getSoundFile(String fileName) {
        for (File file : getAllSoundFiles()) {
            if (file.getName().equals(fileName)) {
                return file;
            }
        }

        return null;
    }

    public static String[] getAllCachedFileNames() {
        ArrayList<String> fileNames = new ArrayList<>();
        File[] files = SOUNDS_CACHE_DIRECTORY.listFiles();

        if (files != null && files.length > 0) {
            Arrays.stream(files).forEach((file -> fileNames.add(file.getName())));
        }

        return fileNames.toArray(new String[0]);
    }

    public static boolean isCached(String fileName) {
        return Arrays.stream(getAllCachedFileNames()).toList().contains(fileName);
    }

    public static void writeFileToCache(File file) {
        try {
            FileUtils.copyFileToDirectory(file, SOUNDS_CACHE_DIRECTORY);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

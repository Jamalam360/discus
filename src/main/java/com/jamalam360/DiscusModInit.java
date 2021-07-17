/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2021 Jamalam360
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.jamalam360;

import com.jamalam360.data.SoundFile;
import com.jamalam360.data.SoundsJson;
import net.devtech.arrp.api.RRPCallback;
import net.devtech.arrp.api.RRPPreGenEntrypoint;
import net.devtech.arrp.api.RuntimeResourcePack;
import net.fabricmc.api.ModInitializer;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

public class DiscusModInit implements RRPPreGenEntrypoint, ModInitializer {
    public static Logger LOGGER = LogManager.getLogger();

    public static final String MOD_ID = "discus";
    public static final String MOD_NAME = "Discus";

    private static final ArrayList<Identifier> SOUNDS = new ArrayList<>();
    public static final RuntimeResourcePack RESOURCE_PACK = RuntimeResourcePack.create(id("rrp").toString());

    private static final SoundEvent TEST_EVENT = new SoundEvent(id("test_sound"));

    @Override
    public void pregen() {
        log(Level.INFO, "Initializing '" + MOD_NAME + "' under the ID '" + MOD_ID + "'");

        SoundFile.copyAllSoundFiles();

        SOUNDS.add(id("test_sound"));
        //SOUNDS.add(id("test_sound_2"));
        //SOUNDS.add(id("test_sound_3"));

        SoundsJson.initializeJson();
        RESOURCE_PACK.addAsset(id("sounds.json"), SoundsJson.writeNewSounds(SOUNDS).getBytes());

        RRPCallback.BEFORE_VANILLA.register(a -> a.add(RESOURCE_PACK));
    }

    @Override
    public void onInitialize() {

        Registry.register(Registry.SOUND_EVENT, id("test_sound"), TEST_EVENT);
    }

    public static void log(Level level, String message) {
        LOGGER.log(level, message);
    }

    public static Identifier id(String path) {
        return new Identifier(MOD_ID, path);
    }
}
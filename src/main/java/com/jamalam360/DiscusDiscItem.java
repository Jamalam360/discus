package com.jamalam360;

import net.minecraft.item.MusicDiscItem;
import net.minecraft.sound.SoundEvent;

//To get around mojank MusicDiscItem being protected
public class DiscusDiscItem extends MusicDiscItem {
    public DiscusDiscItem(int comparatorOutput, SoundEvent sound, Settings settings) {
        super(comparatorOutput, sound, settings);
    }
}

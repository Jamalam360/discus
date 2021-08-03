package io.github.jamalam360;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.MusicDiscItem;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Rarity;

//To get around mojank MusicDiscItem constructor being protected
public class DiscusDiscItem extends MusicDiscItem {
    private static int comparatorOutput = 14; //13 is the end of Minecraft's comparator output, so we will register ours as 14 and upwards

    public DiscusDiscItem(SoundEvent sound) {
        super(comparatorOutput, sound, getMusicDiscSettings());
        comparatorOutput++;
    }

    private static Item.Settings getMusicDiscSettings() {
        return new Item.Settings().maxCount(1).group(ItemGroup.MISC).rarity(Rarity.RARE);
    }
}

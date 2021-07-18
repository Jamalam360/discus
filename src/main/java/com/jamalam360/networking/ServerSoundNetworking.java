package com.jamalam360.networking;

import com.jamalam360.Identifiers;
import com.jamalam360.data.SoundFiles;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.network.PacketByteBuf;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

@Environment(EnvType.SERVER)
public class ServerSoundNetworking {
    /*
     * The networking system functions as below,
     * The occurs when the client joins a server
     *
     * S2C --> String[] containing an identifier for all of the .ogg files the client needs to receive
     * C2S --> After checking which of these files the client already has cached, the client responds with a packet for each file that it does not have cached
     * S2C --> The server sends a packet for each required file, containing the file name and a byte[] representing the actual file
     */

    public static void sendRequiredIdentifiers(File[] files, PacketSender sender) {
        PacketByteBuf packet = PacketByteBufs.create();

        //TODO: Use something more unique than file name to identify the files

        ArrayList<String> identifiers = new ArrayList<>();

        for (File file : files) {
            identifiers.add(file.getName());
        }

        packet.writeInt(identifiers.size());
        identifiers.forEach(packet::writeString);

        sender.sendPacket(Identifiers.S2C_REQUIRED_FILES, packet);
        System.out.println(identifiers);
    }

    public static void sendRequiredFiles(PacketByteBuf buf, PacketSender sender) {
        for (int i = 0; i < buf.readInt(); i++) {
            PacketByteBuf newPacket = PacketByteBufs.create();
            String fileName = buf.readString();

            File file = SoundFiles.getSoundFile(fileName);

            try {
                newPacket.writeByteArray(FileUtils.readFileToByteArray(file));
                sender.sendPacket(Identifiers.S2C_SEND_FILE, newPacket);
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println(fileName);
        }
    }
}

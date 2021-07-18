package com.jamalam360.networking;

import com.jamalam360.Identifiers;
import com.jamalam360.data.SoundFiles;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.network.PacketByteBuf;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

@Environment(EnvType.CLIENT)
public class ClientSoundNetworking {
    public static void receiveRequiredFiles(PacketByteBuf packet) {
        int numberOfFiles = packet.readInt();
        ArrayList<String> missingFiles = new ArrayList<>();

        for (int i = 0; i < numberOfFiles; i++) {
            String fileName = packet.readString();

            if (!SoundFiles.isCached(fileName)) {
                missingFiles.add(fileName);
            }
        }
        PacketByteBuf packetToSend = PacketByteBufs.create();

        packetToSend.writeInt(missingFiles.size());
        missingFiles.forEach(packet::writeString);

        ClientPlayNetworking.send(Identifiers.C2S_REQUEST_FILES, packetToSend);
    }

    public static void receiveFile(PacketByteBuf packet) {
        String fileName = packet.readString();

        try {
            File file = new File(fileName);
            FileUtils.writeByteArrayToFile(file, packet.readByteArray());
            SoundFiles.writeFileToCache(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

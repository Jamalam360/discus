package com.jamalam360.networking;

import com.jamalam360.Identifiers;
import com.jamalam360.data.SoundFiles;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.network.PacketByteBuf;

import java.util.ArrayList;

@Environment(EnvType.CLIENT)
public class ClientSoundNetworking {
    public void receiveRequiredFiles(PacketByteBuf packet) {
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
}

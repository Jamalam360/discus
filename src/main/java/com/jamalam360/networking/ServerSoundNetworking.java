package com.jamalam360.networking;

import com.jamalam360.DiscusUtils;
import com.jamalam360.Identifiers;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;

import java.io.File;
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

    public void s2cSendRequiredIdentifiers(File[] files, ServerPlayerEntity client) {
        PacketByteBuf packet = PacketByteBufs.create();

        //TODO: Use something more unique than file name to identify the files

        ArrayList<String> identifiers = new ArrayList<>();

        for (File file : files) {
            identifiers.add(DiscusUtils.cleanFileName(file.getName()));
        }

        packet.writeInt(identifiers.size());
        identifiers.forEach(packet::writeString);

        ServerPlayNetworking.send(client, Identifiers.S2C_REQUIRED_FILES, packet);
    }
}

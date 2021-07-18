package com.jamalam360.networking;

import com.jamalam360.Identifiers;
import com.jamalam360.data.SoundFiles;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;

/**
 * @author Jamalam360
 */
public class NetworkingManager {
    public static void onPlayerJoin(ServerPlayNetworkHandler handler, PacketSender sender, MinecraftServer server) {
        ServerSoundNetworking.sendRequiredIdentifiers(SoundFiles.getAllSoundFiles(), sender);
    }

    public static void registerServerPackets() {
        ServerPlayNetworking.registerGlobalReceiver(Identifiers.C2S_REQUEST_FILES, (((server, player, handler, buf, responseSender) -> ServerSoundNetworking.sendRequiredFiles(buf, responseSender))));
    }

    public static void registerClientPackets() {
        ClientPlayNetworking.registerGlobalReceiver(Identifiers.S2C_REQUIRED_FILES, ((client, handler, buf, responseSender) -> ClientSoundNetworking.receiveRequiredFiles(buf, responseSender)));
        ClientPlayNetworking.registerGlobalReceiver(Identifiers.S2C_SEND_FILE, (((client, handler, buf, responseSender) -> ClientSoundNetworking.receiveFile(buf))));
    }
}

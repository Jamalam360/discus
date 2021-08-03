package io.github.jamalam360.networking;

import io.github.jamalam360.DiscusModInit;
import net.devtech.arrp.api.RuntimeResourcePack;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.util.Identifier;

/**
 * @author Jamalam360
 */
public class NetworkingManager {
    public static void onPlayerJoin(ServerPlayNetworkHandler handler, PacketSender sender, MinecraftServer server, RuntimeResourcePack pack) {
        PacketByteBuf buf = PacketByteBufs.create();
        new RuntimeResourcePackSendS2CPacket(DiscusModInit.SERVER_PACK).write(buf);
        sender.sendPacket(new Identifier("test_packet", "test_packet"), buf);
    }

    public static void registerClientPackets() {
        ClientPlayNetworking.registerGlobalReceiver(new Identifier("test_packet", "test_packet"), (((client, handler, buf, responseSender) -> ClientRuntimeResourcePackSendListener.receive(buf))));
    }
}


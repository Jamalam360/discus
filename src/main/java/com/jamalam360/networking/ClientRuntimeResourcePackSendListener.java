package com.jamalam360.networking;

import com.jamalam360.DiscusModInit;
import net.minecraft.network.PacketByteBuf;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileInputStream;
import java.util.zip.ZipInputStream;

/**
 * @author Jamalam360
 */
public class ClientRuntimeResourcePackSendListener {
    public static void receive(PacketByteBuf buf) {
        try {
            String fileName = buf.readString();

            if (!fileName.contains(".zip")) {
                fileName += ".zip";
            }

            byte[] zipBytes = buf.readByteArray();
            File file = new File(fileName);
            FileUtils.writeByteArrayToFile(file, zipBytes);

            ZipInputStream stream = new ZipInputStream(new FileInputStream(file));

            DiscusModInit.CLIENT_PACK.loadFromZip(stream);
        } catch (Exception ignored) {
        }
    }
}

package io.github.jamalam360.networking;

import net.devtech.arrp.api.RuntimeResourcePack;
import net.minecraft.network.PacketByteBuf;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.util.zip.ZipOutputStream;

/**
 * @author Jamalam360
 */
public class RuntimeResourcePackSendS2CPacket {
    private final RuntimeResourcePack resourcePack;

    public RuntimeResourcePackSendS2CPacket(RuntimeResourcePack pack) {
        this.resourcePack = pack;
    }

    public void write(PacketByteBuf buf) {
        try {
            File file = new File(resourcePack.getId().getPath().toLowerCase() + ".zip");
            buf.writeString(file.getName());

            ZipOutputStream stream = new ZipOutputStream(new FileOutputStream(file));
            resourcePack.write(stream);
            stream.flush();
            stream.close();

            buf.writeByteArray(FileUtils.readFileToByteArray(file));
        } catch (Exception ignored) {
        }
    }
}

package io.github.jamalam360;

import net.minecraft.util.Identifier;

public class Identifiers {
    public static final String MOD_ID = "discus";
    public static final String MOD_NAME = "Discus";

    public static final Identifier S2C_REQUIRED_FILES = id("s2c_required_files");
    public static final Identifier C2S_REQUEST_FILES = id("c2s_request_file");
    public static final Identifier S2C_SEND_FILE = id("s2c_send_file");

    public static Identifier id(String path) {
        return new Identifier(MOD_ID, path);
    }
}

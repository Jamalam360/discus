package io.github.jamalam360;

import io.github.jamalam360.networking.NetworkingManager;
import net.fabricmc.api.ClientModInitializer;

/**
 * @author Jamalam360
 */
public class DiscusModClientInit implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        NetworkingManager.registerClientPackets();
    }
}

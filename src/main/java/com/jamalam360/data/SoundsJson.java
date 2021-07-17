package com.jamalam360.data;

import com.google.gson.*;
import com.jamalam360.DiscusModInit;
import net.minecraft.resource.ResourceType;
import net.minecraft.util.Identifier;
import org.apache.commons.io.IOUtils;

import java.io.StringWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;

import static com.jamalam360.DiscusModInit.id;

public class SoundsJson {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    public static String writeNewSounds(ArrayList<Identifier> soundIds) {
        try {
            StringWriter writer = new StringWriter();
            IOUtils.copy(DiscusModInit.RESOURCE_PACK.open(ResourceType.CLIENT_RESOURCES, id("sounds.json")), writer, Charset.defaultCharset());
            JsonElement jsonElement = new JsonParser().parse(writer.toString());

            for (Identifier id : soundIds) {
                JsonObject sound = new JsonObject();
                JsonArray soundsArray = new JsonArray();

                soundsArray.add(id.toString());
                sound.add("sounds", soundsArray);

                jsonElement.getAsJsonObject().add(id.getPath(), sound);

                System.out.println(GSON.toJson(jsonElement));
            }

            return GSON.toJson(jsonElement);
        } catch (Exception e) {
            /*
            You know the rules and so do I
            We're no strangers to empty catch blocks
            Never gonna handle that exception
            Never gonna print the stack trace
            Never gonna actually write safe code

            Thank you I'm here all week
            */
        }

        return "";
    }

    public static void initializeJson() {
        DiscusModInit.RESOURCE_PACK.addAsset(id("sounds.json"), "{}".getBytes());
    }
}

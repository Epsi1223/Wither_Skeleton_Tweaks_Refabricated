package net.epsi_t.wstr.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.epsi_t.wstr.WSTR;
import net.fabricmc.loader.api.FabricLoader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class WSTRConfig {
    public float skullFragmentDropChance = 0.9f; //0 = 0%; 1 = 100%; 0.9f = 90%;
    public int skeletonTransformationTime = 100; //In ticks (20 ticks = 1 second; 100 ticks = 5 seconds)
    public boolean skeletonTransformation = true;
    public float fireStickIgnitionTime = 1.5f;
    public float immolationBladeIgnitionTime = 6f;

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final Path PATH = FabricLoader.getInstance()
            .getConfigDir().resolve("wither-skeleton-tweaks-refabricated.json");

    private static WSTRConfig instance = new WSTRConfig();

    public static WSTRConfig get() {
        return instance;
    }

    public static void load() {
        if (Files.exists(PATH)) {
            try {
                WSTRConfig loaded = GSON.fromJson(Files.readString(PATH), WSTRConfig.class);
                if (loaded != null) instance = loaded;
            } catch (Exception e) {
                WSTR.LOGGER.error("Failed to read config, using defaults", e);
                return;
            }
        }
        instance.validate();
        save();
    }

    public static void save() {
        try {
            Files.writeString(PATH, GSON.toJson(instance));
        } catch (IOException e) {
            WSTR.LOGGER.error("Failed to save config", e);
        }
    }

    private void validate() {
        skullFragmentDropChance = Math.max(0, Math.min(1, skullFragmentDropChance));
        skeletonTransformationTime = Math.max(0, skeletonTransformationTime);
        fireStickIgnitionTime = Math.max(0, fireStickIgnitionTime);
        immolationBladeIgnitionTime = Math.max(0, immolationBladeIgnitionTime);
        //Why there's no skeletonTransformation check? If I understand correctly, there's a built-in check for booleans in the gson, it sets everything written to false except for true
    }
}
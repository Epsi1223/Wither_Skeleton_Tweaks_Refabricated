package net.epsi_t.wstr;

import net.epsi_t.wstr.config.WSTRConfig;
import net.epsi_t.wstr.item.ModItems;
import net.epsi_t.wstr.loot.ModLootTableModifiers;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.resources.Identifier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WSTR implements ModInitializer {
	public static final String MOD_ID = "wstr";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		WSTRConfig.load(); //it should be first (I think)
		ModItems.registerModItems();

		LootTableEvents.REPLACE.register(ModLootTableModifiers::replaceLootTables);
	}

	public static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}
}

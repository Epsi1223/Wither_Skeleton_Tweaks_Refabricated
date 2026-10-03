package net.epsi_t.wstr;

import net.epsi_t.wstr.datagen.ModItemTagProvider;
import net.epsi_t.wstr.datagen.ModModelProvider;
import net.epsi_t.wstr.datagen.ModRecipeProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class WSTRDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		var pack = fabricDataGenerator.createPack();

		pack.addProvider(ModModelProvider::new);
		pack.addProvider(ModItemTagProvider::new);
		pack.addProvider(ModRecipeProvider::new);
	}
}

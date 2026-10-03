package net.epsi_t.wstr.datagen;

import net.epsi_t.wstr.item.ModItems;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ModelTemplates;
import org.jspecify.annotations.NonNull;

public class ModModelProvider extends FabricModelProvider {

    public ModModelProvider(FabricPackOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(@NonNull BlockModelGenerators blockModelGenerators) {

    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerators) {
        itemModelGenerators.generateFlatItem(ModItems.WITHER_SKULL_FRAGMENT, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.IMMOLATION_BLADE, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.FIRE_STICK, ModelTemplates.FLAT_ITEM);
    }
}

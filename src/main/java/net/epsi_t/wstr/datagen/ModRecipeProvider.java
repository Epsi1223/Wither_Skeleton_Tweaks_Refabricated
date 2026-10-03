package net.epsi_t.wstr.datagen;

import net.epsi_t.wstr.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeProvider createRecipeProvider(HolderLookup.Provider registries, RecipeOutput output) {
        return new RecipeProvider(registries, output) {
            @Override
            public void buildRecipes() {
                shaped(RecipeCategory.MISC, Items.WITHER_SKELETON_SKULL, 1)
                        .pattern("FFF")
                        .pattern("FFF")
                        .pattern("FFF")
                        .define('F', ModItems.WITHER_SKULL_FRAGMENT)
                        .unlockedBy(getHasName(ModItems.WITHER_SKULL_FRAGMENT), has(ModItems.WITHER_SKULL_FRAGMENT))
                        .save(output, "wstr_wither-skeleton-skull_from_wither-skull-fragments");
                shaped(RecipeCategory.COMBAT, ModItems.IMMOLATION_BLADE, 1)
                        .pattern("  S")
                        .pattern("NS ")
                        .pattern("FN ")
                        .define('N', Items.NETHERITE_INGOT)
                        .define('S', Items.NETHER_STAR)
                        .define('F', ModItems.FIRE_STICK)
                        .unlockedBy(getHasName(Items.NETHERITE_INGOT), has(Items.NETHERITE_INGOT))
                        .unlockedBy(getHasName(Items.NETHER_STAR), has(Items.NETHER_STAR))
                        .unlockedBy(getHasName(ModItems.FIRE_STICK), has(ModItems.FIRE_STICK))
                        .save(output, "wstr_immolation-blade_from_netherite-ingots_nether-stars_and_fire-stick");
                shaped(RecipeCategory.MISC, ModItems.FIRE_STICK, 1)
                        .pattern("RNR")
                        .pattern("NSN")
                        .pattern("RNR")
                        .define('R', Items.BLAZE_ROD)
                        .define('N', Items.NETHERITE_SCRAP)
                        .define('S', Items.STICK)
                        .unlockedBy(getHasName(Items.BLAZE_ROD), has(Items.BLAZE_ROD))
                        .unlockedBy(getHasName(Items.NETHERITE_SCRAP), has(Items.NETHERITE_SCRAP))
                        .unlockedBy(getHasName(Items.STICK), has(Items.STICK))
                        .save(output, "wstr_fire-stick_from_blaze-rods_netherite-scrap_and_stick");
            }
        };
    }

    @Override
    public String getName() {
        return "WSTR Recipes";
    }
}

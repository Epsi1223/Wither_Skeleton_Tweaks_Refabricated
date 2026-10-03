package net.epsi_t.wstr.loot;

import net.epsi_t.wstr.config.WSTRConfig;
import net.epsi_t.wstr.item.ModItems;
import net.fabricmc.fabric.api.loot.v3.LootTableSource;
import net.minecraft.core.HolderLookup;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.predicates.LootItemKilledByPlayerCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceWithEnchantedBonusCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import org.jspecify.annotations.Nullable;

public class ModLootTableModifiers {
    @Nullable
    public static LootTable replaceLootTables(ResourceKey<LootTable> key, LootTable original,
                                              LootTableSource source, HolderLookup.Provider provider) {
        if (!key.identifier().equals(Identifier.withDefaultNamespace("entities/wither_skeleton"))) {
            return null;
        }

        return LootTable.lootTable()
                .setParamSet(LootContextParamSets.ENTITY)
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1f))
                        .when(LootItemRandomChanceCondition.randomChance(WSTRConfig.get().skullFragmentDropChance)) //By default, it's 90%
                        .add(LootItem.lootTableItem(ModItems.WITHER_SKULL_FRAGMENT)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1f, 1f)))))
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1f))
                        .when(LootItemKilledByPlayerCondition.killedByPlayer())
                        .when(LootItemRandomChanceWithEnchantedBonusCondition
                                .randomChanceAndLootingBoost(provider, 0.025f, 0.01f))  //decreased a little the vanilla chance (0.025f -> 0.02f) to make it a little more balanced
                        .add(LootItem.lootTableItem(Items.WITHER_SKELETON_SKULL)))
                .build();
    }
}
package net.epsi_t.wstr.item;

import net.epsi_t.wstr.WSTR;
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ToolMaterial;

import java.util.function.Function;

public class ModItems {
    public static final Item WITHER_SKULL_FRAGMENT = registerItem("wither_skull_fragment", Item::new);
    public static final Item FIRE_STICK = registerItem("fire_stick",
            properties -> new FireStick(properties.fireResistant()));
    public static final Item IMMOLATION_BLADE = registerItem("immolation_blade",
            properties -> new ImmolationBlade(properties.sword(ToolMaterial.NETHERITE, 3.5f, -2.4f).fireResistant()));

    private static Item registerItem(String name, Function<Item.Properties, Item> function) {
        return Registry.register(BuiltInRegistries.ITEM, Identifier.fromNamespaceAndPath(WSTR.MOD_ID, name),
                function.apply(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(WSTR.MOD_ID, name)))));
    }


    public static void registerModItems() {
        WSTR.LOGGER.info("Registering Items for: " + WSTR.MOD_ID + "(Wither Skeleton Tweaks: Refabricated)");

        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.INGREDIENTS).register(output -> {
            output.accept(WITHER_SKULL_FRAGMENT);
        });
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.COMBAT).register(output -> {
            output.accept(IMMOLATION_BLADE);
        });
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.INGREDIENTS).register(output -> {
            output.accept(FIRE_STICK);
        });
    }
}

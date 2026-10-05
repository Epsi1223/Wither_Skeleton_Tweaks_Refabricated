package net.epsi_t.wstr.item;

import net.epsi_t.wstr.config.WSTRConfig;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class FireStick extends Item {
    public FireStick(Properties properties) {
        super(properties);
    }

    @Override
    public void hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        target.igniteForSeconds(WSTRConfig.get().fireStickIgnitionTime); //By default, it's 1.5 seconds
    }
}

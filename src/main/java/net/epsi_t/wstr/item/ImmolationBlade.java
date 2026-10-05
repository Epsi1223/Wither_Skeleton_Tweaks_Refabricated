package net.epsi_t.wstr.item;

import net.epsi_t.wstr.config.WSTRConfig;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.monster.skeleton.AbstractSkeleton;

public class ImmolationBlade extends Item {
    public ImmolationBlade(Properties properties) {
        super(properties);
    }
    @Override
    public void hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        target.igniteForSeconds(WSTRConfig.get().immolationBladeIgnitionTime); //By default, it's 6 seconds
        if (target instanceof AbstractSkeleton && target.level() instanceof ServerLevel level) {
            if (!target.isDeadOrDying()) {
                target.setHealth(1);
                target.hurtServer(level, level.damageSources().source(DamageTypes.FIREWORKS), 150);
            }
        }
    } //the only thing that I took from the original WSTweaks source code :)
}

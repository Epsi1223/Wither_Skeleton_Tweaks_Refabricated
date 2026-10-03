package net.epsi_t.wstr.mixin;

import net.epsi_t.wstr.config.WSTRConfig;
import net.minecraft.world.entity.ConversionParams;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.skeleton.Skeleton;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Skeleton.class)
public abstract class SkeletonMixin {
    @Unique
    private int netherTime = 0;

    @Inject(method = "tick", at = @At("HEAD"))
    private void onTick(CallbackInfo ci) {
        if (WSTRConfig.get().skeletonTransformation) {
            Skeleton self = (Skeleton) (Object) this;

            if (self.level().isClientSide()) return;
            if (!self.isAlive()) return;

            if (self.level().dimension() == Level.NETHER) {
                netherTime++;
                if (netherTime >= WSTRConfig.get().skeletonTransformationTime) {
                    self.convertTo(EntityType.WITHER_SKELETON, ConversionParams.single(self, true, true), wither -> {});
                }
            } else {
                netherTime = 0;
            }
        }
    }
}
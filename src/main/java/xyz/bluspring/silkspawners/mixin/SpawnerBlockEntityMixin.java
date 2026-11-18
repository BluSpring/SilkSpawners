package xyz.bluspring.silkspawners.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.world.level.block.entity.SpawnerBlockEntity;

@Mixin(SpawnerBlockEntity.class)
public abstract class SpawnerBlockEntityMixin {
    @Inject(method = "onlyOpCanSetNbt", at = @At("HEAD"), cancellable = true)
    private void alwaysMakeFalse(CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(false);
    }
}

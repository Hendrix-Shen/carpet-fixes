package carpetfixes.mixins.entityFixes;

import carpetfixes.CarpetFixesSettings;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.boss.dragon.EnderDragonFight;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EnderDragonFight.class)
public abstract class EnderDragonFight_respawnMixin {


    @Shadow public abstract void respawnDragon();


    @Inject(
            method= "dragonKilled(Lnet/minecraft/entity/boss/dragon/EnderDragonEntity;)V",
            at=@At(
                    shift= At.Shift.AFTER,
                    value="FIELD",
                    target="Lnet/minecraft/entity/boss/dragon/EnderDragonFight;dragonKilled:Z"
            )
    )
    public void dragonKilled(EnderDragonEntity dragon, CallbackInfo ci) {
        if (CarpetFixesSettings.endCrystalPlacingTooEarlyFix) this.respawnDragon();
    }
}

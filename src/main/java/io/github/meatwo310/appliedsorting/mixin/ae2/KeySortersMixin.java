package io.github.meatwo310.appliedsorting.mixin.ae2;

import appeng.api.config.SortDir;
import appeng.api.config.SortOrder;
import appeng.api.stacks.AEKey;
import io.github.meatwo310.appliedsorting.config.ClientConfig;
import io.github.meatwo310.appliedsorting.config.SortBy;
import io.github.meatwo310.appliedsorting.sort.Sorter;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Comparator;

@Mixin(targets = "appeng.client.gui.me.common.KeySorters", remap = false)
public class KeySortersMixin {
    @Shadow
    @Final
    public static Comparator<AEKey> NAME_ASC;

    @Shadow
    @Final
    public static Comparator<AEKey> NAME_DESC;

    @Shadow
    @Final
    public static Comparator<AEKey> MOD_ASC;

    @Shadow
    @Final
    public static Comparator<AEKey> MOD_DESC;

    @Inject(method = "getComparator", at = @At("HEAD"), cancellable = true)
    private static void injectComparator(SortOrder order, SortDir dir, CallbackInfoReturnable<Comparator<AEKey>> cir) {
        SortBy customOrder = ClientConfig.ALTERNATIVE_SORT.get();
        Sorter.sort(dir, customOrder).ifPresentOrElse(cir::setReturnValue, () -> {
            switch (customOrder) {
                case NAME -> cir.setReturnValue(dir == SortDir.ASCENDING ? NAME_ASC : NAME_DESC);
                case MOD -> cir.setReturnValue(dir == SortDir.ASCENDING ? MOD_ASC : MOD_DESC);
            }
        });
    }
}

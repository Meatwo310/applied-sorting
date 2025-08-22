package io.github.meatwo310.appliedsorting.mixin.ae2;

import appeng.api.config.SortDir;
import appeng.api.config.SortOrder;
import appeng.api.stacks.AEKey;
import io.github.meatwo310.appliedsorting.Sorter;
import io.github.meatwo310.appliedsorting.config.ClientConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Comparator;

@Mixin(targets = "appeng.client.gui.me.common.KeySorters", remap = false)
public class KeySortersMixin {
    @Inject(method = "getComparator", at = @At("RETURN"), cancellable = true)
    private static void injectComparator(SortOrder order, SortDir dir, CallbackInfoReturnable<Comparator<AEKey>> cir) {
        var sortBy = switch (order) {
            case NAME -> ClientConfig.ALTERNATIVE_NAME_SORT.get();
            case MOD -> ClientConfig.ALTERNATIVE_MOD_SORT.get();
            default -> null;
        };
        if (sortBy == null) return;
        Sorter.sort(dir, sortBy).ifPresent(cir::setReturnValue);
    }
}

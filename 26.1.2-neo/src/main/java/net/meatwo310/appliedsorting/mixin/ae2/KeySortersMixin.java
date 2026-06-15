package net.meatwo310.appliedsorting.mixin.ae2;

import appeng.api.config.SortDir;
import appeng.api.config.SortOrder;
import appeng.api.stacks.AEKey;
import net.meatwo310.appliedsorting.config.ClientConfig;
import net.meatwo310.appliedsorting.sort.Sorter;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Comparator;

@Mixin(targets = "appeng.client.gui.me.common.KeySorters", remap = false)
public class KeySortersMixin {
    @Inject(method = "getComparator", at = @At("HEAD"), cancellable = true)
    private static void injectComparator(SortOrder order, SortDir dir, CallbackInfoReturnable<Comparator<AEKey>> cir) {
        Sorter.sort(dir, ClientConfig.SORT_OVERRIDE.get()).ifPresent(cir::setReturnValue);
    }
}

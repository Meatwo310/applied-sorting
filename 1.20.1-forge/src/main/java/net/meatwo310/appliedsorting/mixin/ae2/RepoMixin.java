package net.meatwo310.appliedsorting.mixin.ae2;

import appeng.api.config.SortDir;
import appeng.api.config.SortOrder;
import appeng.client.gui.me.common.Repo;
import appeng.menu.me.common.GridInventoryEntry;
import net.meatwo310.appliedsorting.config.ClientConfig;
import net.meatwo310.appliedsorting.sort.Sorter;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Comparator;

@Mixin(value = Repo.class, remap = false)
public abstract class RepoMixin {
    @Inject(method = "getComparator", at = @At("HEAD"), cancellable = true)
    private void injectGetComparator(SortOrder sortOrder, SortDir sortDir, CallbackInfoReturnable<Comparator<? super GridInventoryEntry>> cir) {
        var customOrder = ClientConfig.ALTERNATIVE_SORT.get();
        Sorter.sort(sortDir, customOrder)
                .ifPresent(comparator -> cir.setReturnValue(Comparator.comparing(GridInventoryEntry::getWhat, comparator)));
    }
}

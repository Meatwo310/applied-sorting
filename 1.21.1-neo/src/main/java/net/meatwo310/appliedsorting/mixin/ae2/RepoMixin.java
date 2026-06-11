package net.meatwo310.appliedsorting.mixin.ae2;

import appeng.api.config.SortDir;
import appeng.api.config.SortOrder;
import appeng.api.stacks.AEKey;
import appeng.client.gui.me.common.Repo;
import appeng.menu.me.common.GridInventoryEntry;
import net.meatwo310.appliedsorting.config.ClientConfig;
import net.meatwo310.appliedsorting.config.SortBy;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Comparator;

@Mixin(value = Repo.class, remap = false)
public abstract class RepoMixin {
    @Shadow protected abstract Comparator<AEKey> getKeyComparator(SortOrder sortBy, SortDir sortDir);
    @Shadow @Final public static Comparator<GridInventoryEntry> AMOUNT_ASC;
    @Shadow @Final public static Comparator<GridInventoryEntry> AMOUNT_DESC;

    @Inject(method = "getComparator", at = @At("HEAD"), cancellable = true)
    private void injectGetComparator(SortOrder sortOrder, SortDir sortDir, CallbackInfoReturnable<Comparator<? super GridInventoryEntry>> cir) {
        var customOrder = ClientConfig.ALTERNATIVE_SORT.get();
        if (customOrder == SortBy.AMOUNT) {
            cir.setReturnValue(sortDir == SortDir.ASCENDING ? AMOUNT_ASC : AMOUNT_DESC);
            return;
        }

        cir.setReturnValue(Comparator.comparing(GridInventoryEntry::getWhat, getKeyComparator(sortOrder, sortDir)));
    }
}

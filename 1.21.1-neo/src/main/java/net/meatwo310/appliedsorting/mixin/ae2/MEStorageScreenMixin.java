package net.meatwo310.appliedsorting.mixin.ae2;

import appeng.api.config.SortOrder;
import appeng.client.gui.me.common.MEStorageScreen;
import appeng.client.gui.me.common.Repo;
import appeng.client.gui.style.ScreenStyle;
import appeng.client.gui.widgets.SettingToggleButton;
import appeng.menu.me.common.MEStorageMenu;
import net.meatwo310.appliedsorting.ae2.ConfigToggleButton;
import net.meatwo310.appliedsorting.config.ClientConfig;
import net.meatwo310.appliedsorting.config.SortBy;
import net.meatwo310.appliedsorting.util.CollectionUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = MEStorageScreen.class, remap = false)
public class MEStorageScreenMixin {
    @Shadow
    @Final
    protected Repo repo;

    /*
    @Shadow
    @Final
    private TerminalStyle style;
     */

    @Shadow
    private SettingToggleButton<SortOrder> sortByToggle;

    @Inject(method = "<init>", at = @At("RETURN"))
    private void injectButton(MEStorageMenu menu, Inventory playerInventory, Component title, ScreenStyle screenStyle, CallbackInfo ci) {
        // if (!style.isSortable) return;

        var verticalToolbar = ((AEBaseScreenAccessor) this).getVerticalToolbar();
        var buttons = ((VerticalButtonBarAccessor) verticalToolbar).getButtons();

        var selectedSort = ClientConfig.SORT_OVERRIDE.get();
        if (!isOverrideSort(selectedSort)) {
            selectedSort = SortBy.DEFAULT;
            ClientConfig.SORT_OVERRIDE.set(selectedSort);
        }

        var button = new ConfigToggleButton<>(ClientConfig.SORT_OVERRIDE, selectedSort, MEStorageScreenMixin::isOverrideSort, (btn, backwards) -> {
            btn.toggleConfig(backwards);
            repo.updateView();
        });

        var index = CollectionUtils.indexOfOr(buttons, sortByToggle, 2);
        buttons.add(index + 1, button);
    }

    private static boolean isOverrideSort(SortBy sortBy) {
        return sortBy == SortBy.DEFAULT || sortBy == SortBy.INTERNAL_ID || sortBy == SortBy.RESOURCE_LOCATION;
    }
}

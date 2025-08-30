package io.github.meatwo310.appliedsorting.mixin.ae2;

import appeng.client.gui.me.common.MEStorageScreen;
import appeng.client.gui.me.common.Repo;
import appeng.client.gui.style.ScreenStyle;
import appeng.client.gui.style.TerminalStyle;
import appeng.menu.me.common.MEStorageMenu;
import io.github.meatwo310.appliedsorting.ae2.ConfigToggleButton;
import io.github.meatwo310.appliedsorting.config.ClientConfig;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = MEStorageScreen.class, remap = false)
public abstract class MEStorageScreenMixin {
    @Shadow @Final private TerminalStyle style;
    @Shadow @Final protected Repo repo;

    @Inject(method = "<init>", at = @At("RETURN"))
    private void injectButton(MEStorageMenu menu, Inventory playerInventory, Component title, ScreenStyle screenStyle, CallbackInfo ci) {
        if (!style.isSortable()) return;

        var verticalToolbar = ((AEBaseScreenAccessor) this).getVerticalToolbar();
        var buttons = ((VerticalButtonBarInvoker) verticalToolbar).getButtons();

        var button = new ConfigToggleButton<>(ClientConfig.ALTERNATIVE_SORT, (btn, backwards) -> {
            btn.toggleConfig(backwards);
            repo.updateView();
        });

        buttons.add(2, button);
    }
}

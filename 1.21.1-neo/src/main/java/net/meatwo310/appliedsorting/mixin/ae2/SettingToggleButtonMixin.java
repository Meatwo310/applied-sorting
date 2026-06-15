package net.meatwo310.appliedsorting.mixin.ae2;

import appeng.api.config.Setting;
import appeng.api.config.Settings;
import appeng.client.gui.widgets.SettingToggleButton;
import net.meatwo310.appliedsorting.Constants;
import net.meatwo310.appliedsorting.config.ClientConfig;
import net.meatwo310.appliedsorting.config.SortBy;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;
import java.util.List;

@Mixin(value = SettingToggleButton.class, remap = false)
public abstract class SettingToggleButtonMixin {
    @Shadow
    public abstract Setting<?> getSetting();

    @Inject(method = "getTooltipMessage", at = @At("RETURN"), cancellable = true)
    private void appendSortOverrideTooltip(CallbackInfoReturnable<List<Component>> cir) {
        if (getSetting() != Settings.SORT_BY || ClientConfig.SORT_OVERRIDE.get() == SortBy.DEFAULT) {
            return;
        }

        var originalTooltip = cir.getReturnValue();
        var tooltip = new ArrayList<Component>(originalTooltip.size() + 2);
        for (int i = 0; i < originalTooltip.size(); i++) {
            var line = originalTooltip.get(i);
            tooltip.add(i == 0 ? line : line.copy().withStyle(style -> style.withStrikethrough(true)));
        }
        tooltip.add(Component.translatable(Constants.TOOLTIP_SORT_OVERRIDDEN).withStyle(ChatFormatting.YELLOW));
        cir.setReturnValue(tooltip);
    }
}

package io.github.meatwo310.appliedsorting.mixin.ae2;

import appeng.client.gui.AEBaseScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(value = AEBaseScreen.class, remap = false)
public interface AEBaseScreenInvoker {
    @Invoker("openHelp")
    void openHelp();
}

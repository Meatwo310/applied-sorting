package net.meatwo310.appliedsorting.ae2;

import appeng.client.gui.style.Blitter;
import net.meatwo310.appliedsorting.ModUtils;
import net.minecraft.resources.ResourceLocation;

/**
 * Edit in {@code assets/appliedsorting/textures/guis/states.png}.
 */
public enum CustomIcon {

    SORT_BY_ID(0, 0),
    SORT_BY_RESOURCE_LOCATION(16, 0);

    public final int x;
    public final int y;
    public final int width;
    public final int height;

    public static final ResourceLocation TEXTURE = ModUtils.loc("textures/guis/states.png");
    public static final int TEXTURE_WIDTH = 32;
    public static final int TEXTURE_HEIGHT = 32;

    CustomIcon(int x, int y) {
        this(x, y, 16, 16);
    }

    CustomIcon(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public Blitter getBlitter() {
        return Blitter.texture(TEXTURE, TEXTURE_WIDTH, TEXTURE_HEIGHT)
                .src(x, y, width, height);
    }
}

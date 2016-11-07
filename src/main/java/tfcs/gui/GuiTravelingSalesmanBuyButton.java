package tfcs.gui;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;

public class GuiTravelingSalesmanBuyButton extends GuiButton {

	public GuiTravelingSalesmanBuyButton(int index, int posX, int posY, int width, int height, String name) {
		super(index, posX, posY, width, height, name);
	}

	@Override
	public void drawString(FontRenderer fontRenderer, String string, int x, int y, int color) {
		super.drawString(fontRenderer, string, x, y, color);
	}

	@Override
	public void drawCenteredString(FontRenderer fontRenderer, String string, int x, int y, int color) {
		super.drawCenteredString(fontRenderer, string, x, y, color);
	}
}
package tfcs.gui;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;

public class GuiSetPostionButton extends GuiButton {
	SettlerGui gui;

	public GuiSetPostionButton(int index, int posX, int posY, int width, int height, String name, SettlerGui gui) {
		super(index, posX, posY, width, height, name);
		this.gui = gui;
	}

	@Override
	public void drawString(FontRenderer fontRenderer, String string, int x, int y, int color) {
		super.drawString(fontRenderer, string, x, y, color);
	}

	@Override
	public void drawCenteredString(FontRenderer fontRenderer, String string, int x, int y, int color) {
		string = string + " " + gui.x + " " + gui.y + " " + gui.z;
		super.drawCenteredString(fontRenderer, string, x, y, color);
	}
}

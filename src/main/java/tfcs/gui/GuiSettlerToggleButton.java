package tfcs.gui;

import tfcs.entity.EntitySettler;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;

public class GuiSettlerToggleButton extends GuiButton {
	int type;
	EntitySettler es;

	public GuiSettlerToggleButton(int index, int posX, int posY, int width, int height, String name, int type, EntitySettler es) {
		super(index, posX, posY, width, height, name);
		this.type = type;
		this.es = es;
	}

	@Override
	public void drawString(FontRenderer fontRenderer, String string, int x, int y, int color) {
		super.drawString(fontRenderer, string, x, y, color);
	}

	@Override
	public void drawCenteredString(FontRenderer fontRenderer, String string, int x, int y, int color) {
		boolean isTrue = false;
		switch (type) {
		case 0:
			isTrue = es.doesAttackAnimals();
			break;
		case 1:
			isTrue = es.doesAttackPlayers();
			break;
		case 2:
			isTrue = es.doesAttackOwner();
			break;
		case 3:
			isTrue = es.doesAttackDrawnPlayers();
			break;
		case 4:
			isTrue = es.doesAttackMobs();
			break;
		case 5:
			isTrue = es.doesFollowOwner();
			break;
		case 14:
			isTrue = es.doesWander();
			break;
		default:
			break;
		}
		if (isTrue)
			color = 65280;
		else
			color = 16711680;
		super.drawCenteredString(fontRenderer, string, x, y, color);
	}

}

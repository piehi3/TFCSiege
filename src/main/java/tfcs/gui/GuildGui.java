package tfcs.gui;

import org.lwjgl.opengl.GL11;

import tfcs.TFCSiege;
import tfcs.containers.ContainerGuild;
import tfcs.containers.ContainerTravelingSalesman;
import tfcs.entity.EntityTravelingSalesman;
import tfcs.guild.Guild;
import tfcs.handlers.network.MessageSyncEntityToServer;
import tfcs.reference.Reference;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

public class GuildGui extends GuiContainer {

	public static ResourceLocation texture = new ResourceLocation(
			Reference.ModID, Reference.ASSET_PATH_GUI + "gui_Guild.png");
	private EntityPlayer player;

	public GuildGui(EntityPlayer player) {
		super(new ContainerGuild(player));
		this.player = player;
		this.xSize = 256;
		this.ySize = 165;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void initGui() {
		super.initGui();
		buttonList.clear();
		buttonList.add(new GuiButton(0, guiLeft + 26, guiTop + 66, 16, 16,
				"Create Guild"));
	}

	@Override
	protected void actionPerformed(GuiButton button) {
		// Button
		switch (button.id) {
		case 0:
			Guild guild = new Guild(player, "Polonia");
			break;
		default:
			break;
		}
		// PacketHandler.network.sendToServer(new
		// MessageSyncEntityToServer(es.getEntityId(), tag));
		super.actionPerformed(button);
	}

	protected void drawGuiContainerBackgroundLayer(int mouseX, int mouseY) {
		this.fontRendererObj.drawString("Guild Manager", 8, 6, 4210752);
		this.fontRendererObj.drawString(
				StatCollector.translateToLocal("container.inventory"), 8,
				this.ySize - 96 + 2, 4210752);
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int x, int y) {
		for (int i = 0; i < TFCSiege.instance.guildHandler.guilds.size(); i++) {
			if (TFCSiege.instance.guildHandler.guilds.get(i) != null)
				drawGuildButton(3, i * 25 + 3,
						TFCSiege.instance.guildHandler.guilds.get(i));
		}
		super.drawGuiContainerForegroundLayer(x, y);
	}

	private void drawGuildButton(int x, int y, Guild guild) {
		drawScaledString(x, y, 1.4F, guild.getName());
		drawScaledString(x, y + 8, 1.8F, "Relationship:");
		drawScaledString(x + 2, y + 13, 1.7F,
				guild.isPlayerInGuild(player) ? "Member" : "Outsider");
	}

	private void drawScaledString(int x, int y, float scale, String string) {
		GL11.glScalef(1 / scale, 1 / scale, 1.0F);
		this.fontRendererObj.drawString(string, (int) (x * scale),
				(int) (y * scale), 0, false);
		GL11.glScalef(scale, scale, 1.0F);
	}

	protected void drawGuiContainerBackgroundLayer(float partialTicks,
			int mouseX, int mouseY) {
		// GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		/*
		 * this.xSize = 256; this.ySize = 165;
		 */
		this.mc.getTextureManager().bindTexture(texture);
		int k = (this.width - this.xSize) / 2;
		int l = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
	}

}

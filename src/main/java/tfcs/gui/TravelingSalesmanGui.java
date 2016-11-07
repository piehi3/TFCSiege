package tfcs.gui;

import org.lwjgl.opengl.GL11;

import tfcs.containers.ContainerTravelingSalesman;
import tfcs.entity.EntityTravelingSalesman;
import tfcs.handlers.network.MessageSyncEntityToServer;
import tfcs.handlers.network.PacketHandler;
import tfcs.reference.Reference;
import tfcs.util.CalculationHelper;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

public class TravelingSalesmanGui extends GuiContainer {

	public static ResourceLocation texture = new ResourceLocation(Reference.ModID, Reference.ASSET_PATH_GUI + "gui_travelingSalesman.png");
	private EntityPlayer player;
	private EntityTravelingSalesman es;

	public TravelingSalesmanGui(EntityPlayer player, EntityTravelingSalesman es) {
		super(new ContainerTravelingSalesman(player, es));
		this.player = player;
		this.es = es;
	}

	@Override
	public void initGui() {
		super.initGui();
		buttonList.clear();
		for (int i = 0; i < 8; i++)
			buttonList.add(new GuiTravelingSalesmanBuyButton(i, guiLeft + 27 + (i * 18), guiTop + 24, 18, 18, ""));
	}

	@Override
	protected void actionPerformed(GuiButton button) {
		if (button.id < 8)
			if (es.getStackInSlot(button.id) == null
					|| (es.getStackInSlot(button.id).getItem().equals(es.getTrade().getTrades()[button.id].getItemstack().getItem()) && es.getStackInSlot(button.id).stackSize < es.getStackInSlot(
							button.id).getMaxStackSize()))
				es.buyItem(button.id, es.getTrade().getTrades()[button.id].getItemstack(), es.getTrade().getTrades()[button.id].getCost());
		NBTTagCompound tag = new NBTTagCompound();
		es.writeEntityToNBT(tag);
		PacketHandler.network.sendToServer(new MessageSyncEntityToServer(es.getEntityId(), tag));
		super.actionPerformed(button);
	}

	protected void drawGuiContainerBackgroundLayer(int mouseX, int mouseY) {
		this.fontRendererObj.drawString(es.getInventoryName(), 8, 6, 4210752);
		this.fontRendererObj.drawString(StatCollector.translateToLocal("container.inventory"), 8, this.ySize - 96 + 2, 4210752);
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int x, int y) {
		int coins = es.getValueCount();
		for (int i = 0; i < es.getTrade().getTrades().length; i++)
			this.drawTexturedModelRectFromIcon(28 + (i * 18), 25, es.getTrade().getTrades()[i].getItemstack().getIconIndex(), 16, 16);
		int color;
		for (int i = 0; i < es.getTrade().getTrades().length; i++) {
			if (es.getTrade().getTrades()[i].getCost() <= coins)
				color = 65280;
			else
				color = 16711680;
			if (es.getTrade().getTrades()[i].getCost() >= 1000) {
				float f = 1.5F;
				GL11.glScalef(1/f, 1/f, 1/f);
				this.fontRendererObj.drawString(es.getTrade().getTrades()[i].getCost() + "", (int)((29 + (i * 18))* f), (int) (46 * f), color, true);
				GL11.glScalef(f, f, f);
			} else {
				this.fontRendererObj.drawString(es.getTrade().getTrades()[i].getCost() + "", 29 + (i * 18), 46, color, true);
			}
		}
		this.fontRendererObj.drawString("Total: " + coins, 29, 10, 4210752, false);
		super.drawGuiContainerForegroundLayer(x, y);
	}

	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		// GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(texture);
		int k = (this.width - this.xSize) / 2;
		int l = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
	}

	
	
}

package tfcs.gui;

import tfcs.containers.ContainerSettler;
import tfcs.entity.EntitySettler;
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

public class SettlerGui extends GuiContainer {

	public static ResourceLocation texture = new ResourceLocation(Reference.ModID, Reference.ASSET_PATH_GUI + "gui_settler.png");
	private EntityPlayer player;
	private EntitySettler es;

	int x = 0;
	int y = 0;
	int z = 0;

	public SettlerGui(EntityPlayer player, EntitySettler es) {
		super(new ContainerSettler(player, es));
		this.player = player;
		this.es = es;
	}

	@Override
	public void initGui() {
		super.initGui();
		buttonList.clear();
		buttonList.add(new GuiSettlerToggleButton(0, guiLeft + 26, guiTop + 66, 16, 16, "A.A", 0, es));
		buttonList.add(new GuiSettlerToggleButton(1, guiLeft + 44, guiTop + 66, 16, 16, "A.P", 1, es));
		buttonList.add(new GuiSettlerToggleButton(2, guiLeft + 62, guiTop + 66, 16, 16, "D.P", 2, es));
		buttonList.add(new GuiSettlerToggleButton(3, guiLeft + 80, guiTop + 66, 16, 16, "A.O", 3, es));
		buttonList.add(new GuiSettlerToggleButton(4, guiLeft + 98, guiTop + 66, 16, 16, "A.M", 4, es));
		buttonList.add(new GuiSettlerToggleButton(5, guiLeft + 116, guiTop + 66, 16, 16, "F.O", 5, es));
		buttonList.add(new GuiSettlerToggleButton(14, guiLeft + 134, guiTop + 66, 16, 16, "W.A", 14, es));
		buttonList.add(new GuiSettlerToggleButton(15, guiLeft + 150, guiTop + 66, 16, 16, "W.A", 15, es));

		buttonList.add(new GuiSettlerIncreaseButton(6, guiLeft + 49, guiTop + 7, 8, 16, "X", es));
		buttonList.add(new GuiSettlerIncreaseButton(7, guiLeft + 49, guiTop + 25, 8, 16, "X", es));

		buttonList.add(new GuiSettlerIncreaseButton(8, guiLeft + 58, guiTop + 7, 8, 16, "Y", es));
		buttonList.add(new GuiSettlerIncreaseButton(9, guiLeft + 58, guiTop + 25, 8, 16, "Y", es));

		buttonList.add(new GuiSettlerIncreaseButton(10, guiLeft + 67, guiTop + 7, 8, 16, "Z", es));
		buttonList.add(new GuiSettlerIncreaseButton(11, guiLeft + 67, guiTop + 25, 8, 16, "Z", es));

		buttonList.add(new GuiSetPostionButton(12, guiLeft + 28, guiTop + 45, 48, 16, "Set", this));
		buttonList.add(new GuiRemovePoint(13, guiLeft + 78, guiTop + 45, 16, 16, "R.M"));
	}

	@Override
	protected void actionPerformed(GuiButton button) {
		switch (button.id) {
		case 0:
			es.toggleAttacksAnimal();
			break;
		case 1:
			es.toggleAttacksPlayer();
			break;
		case 2:
			es.toggleAttacksDrawnPlayer();
			break;
		case 3:
			es.toggleAttacksOwner();
			break;
		case 4:
			es.toggleAttacksMobs();
			break;
		case 5:
			es.toggleFollowOwner();
			break;
		case 14:
			es.toggleWander();
			break;
		case 15:
			es.mountHorse();
			break;
		case 6:
			if (CalculationHelper.pythagoreanTheorem3D(Math.abs(x), Math.abs(y), Math.abs(z)) < 20)
				x++;
			break;
		case 7:
			if (CalculationHelper.pythagoreanTheorem3D(Math.abs(x), Math.abs(y), Math.abs(z)) < 20)
				x--;
			break;
		case 8:
			if (CalculationHelper.pythagoreanTheorem3D(Math.abs(x), Math.abs(y), Math.abs(z)) < 20)
				y++;
			break;
		case 9:
			if (CalculationHelper.pythagoreanTheorem3D(Math.abs(x), Math.abs(y), Math.abs(z)) < 20)
				y--;
			break;
		case 10:
			if (CalculationHelper.pythagoreanTheorem3D(Math.abs(x), Math.abs(y), Math.abs(z)) < 20)
				z++;
			break;
		case 11:
			if (CalculationHelper.pythagoreanTheorem3D(Math.abs(x), Math.abs(y), Math.abs(z)) < 20)
				z--;
			break;
		case 12:
			es.addPostion(x, y, z);
			break;
		case 13:
			es.removePotsion();
			break;
		default:
			break;
		}
		NBTTagCompound tag = new NBTTagCompound();
		es.writeToNBT(tag);
		PacketHandler.network.sendToServer(new MessageSyncEntityToServer(es.getEntityId(), tag));
	}

	protected void drawGuiContainerBackgroundLayer(int mouseX, int mouseY) {
		this.fontRendererObj.drawString(es.getInventoryName(), 8, 6, 4210752);
		this.fontRendererObj.drawString(StatCollector.translateToLocal("container.inventory"), 8, this.ySize - 96 + 2, 4210752);
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int x, int y) {
		this.fontRendererObj.drawString("Steps:", 76, 7, 0, false);
		for (int i = 0; i < es.getPoints().length; i++)
			if(es.getPoint(i)!=null){
				int color = 0;
				if(es.getPosition() == i)
					color = 65280;
				this.fontRendererObj.drawString(es.getPoint(i)[0] + " " + es.getPoint(i)[1] + " " + es.getPoint(i)[2], 77, 7 + ((i + 1) * 7), color, false);
			}
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

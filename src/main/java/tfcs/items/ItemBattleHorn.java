package tfcs.items;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import tfcs.core.tab.TFCSCreativeTabs;
import tfcs.entity.EntitySettler;
import tfcs.reference.ReferenceName;
import tfcs.util.CalculationHelper;
import tfcs.util.NBTHelper;

import com.bioxx.tfc.Core.TFC_Sounds;
import com.bioxx.tfc.api.TFCItems;

public class ItemBattleHorn extends ItemTFCSTool {

	int maxMode = 3;

	public ItemBattleHorn() {
		super(TFCItems.copperToolMaterial, "", 67);
		this.setUnlocalizedName(ReferenceName.ITEM_BATTLE_HORN_NAME);
		this.setCreativeTab(TFCSCreativeTabs.TCFS_SIEGE);
	}

	@Override
	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
		return false;
	}

	@Override
	public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
		if (!player.isSneaking())
			return false;
		NBTHelper.setIntArray(stack, "BLOCK", new int[] { x, y + 1, z });
		return true;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer player) {
		if (!player.isSneaking()) {
			itemstack.attemptDamageItem(1, world.rand);
			return super.onItemRightClick(itemstack, world, player);
		}
		int c = NBTHelper.getInt(itemstack, "MODE");
		NBTHelper.setInt(itemstack, "MODE", c + 1 < maxMode ? c + 1 : 0);
		return itemstack;
	}

	private String getDispayModeFromID(int i) {
		switch (i) {
		case 0:
			return "Retreat";
		case 1:
			return "Charge";
		case 3:
			return "Run Route";
		default:
			return "Null";
		}
	}

	@Override
	public void addExtraInformation(ItemStack is, EntityPlayer player, List<String> arraylist) {
		super.addExtraInformation(is, player, arraylist);
		arraylist.add("Mode: " + getDispayModeFromID(NBTHelper.getInt(is, "MODE")));

		if (NBTHelper.hasKey(is, "BLOCK")) {
			int[] i = NBTHelper.getIntArray(is, "BLOCK");
			arraylist.add("Linked Block: " + i[0] + " " + i[1] + " " + i[2]);
		}
	}

	@Override
	public void onUsingTick(ItemStack stack, EntityPlayer player, int count) {
		float f = (float) CalculationHelper.sin(count) * 1.5F;
		String s;
		switch (NBTHelper.getInt(stack, "MODE")) {
		case 0:
			s = TFC_Sounds.BEARHURT;
			break;
		case 1:
			s = TFC_Sounds.FALLININGROCKSHORT;
			break;
		case 2:
			s = TFC_Sounds.BEARHURT;
			break;
		case 3:
			s = TFC_Sounds.BELLOWS;
			break;
		default:
			s = TFC_Sounds.BEARHURT;
			break;
		}
		// x postion y postion z postion sound name pitch loudness ture?
		player.worldObj.playSound(player.posX, player.posY, player.posZ, s, (float) 0.05F + f, 0.01F + f / 2, true);
		
		if(count == 7000){
			List list = player.worldObj.getEntitiesWithinAABB(EntitySettler.class, player.boundingBox.expand(10, 10, 10));
			for (int i = 0; i < list.size(); i++)
				if(list.get(i) !=null)
					getEffect((EntitySettler) list.get(i), NBTHelper.getInt(stack, "MODE"));
		}
		
		super.onUsingTick(stack, player, count);
	}

	private void getEffect(EntitySettler es,int mode) {
		switch (mode) {
		case 0:
			es.setRetreat();
			break;
		case 1:
			
			break;
		case 2:
			
			break;
		case 3:
			
			break;
		default:
			break;
		}
	}
	
	@Override
	public EnumAction getItemUseAction(ItemStack par1ItemStack) {
		return EnumAction.bow;
	}

}

package tfcs.proxy;

import java.io.File;

import org.lwjgl.input.Keyboard;

import com.bioxx.tfc.Handlers.ServerTickHandler;

import tfcs.core.ModOptions;
import tfcs.core.TFCSBlocks;
import tfcs.core.TFCSItems;
import tfcs.entity.EntitySettler;
import tfcs.entity.ProjectileCeramicShell;
import tfcs.handlers.EventHandler;
import tfcs.handlers.client.KeyHandler;
import tfcs.reference.Reference;
import tfcs.render.EntityProjectileRenderer;
import tfcs.render.RenderAqueduct;
import tfcs.render.RenderBomb;
import tfcs.render.RenderCandle;
import tfcs.render.RenderGate;
import tfcs.render.RenderGearFrame;
import tfcs.render.RenderLauncherBase;
import tfcs.render.RenderLongBow;
import tfcs.render.RenderMortar;
import tfcs.render.TFCSRenderItem;
import tfcs.render.renderEntities.RenderSettler;
import tfcs.tileentities.TileEntityAqueduct;
import tfcs.tileentities.TileEntityBomb;
import tfcs.tileentities.TileEntityCandle;
import tfcs.tileentities.TileEntityGate;
import tfcs.tileentities.TileEntityGearFrame;
import tfcs.tileentities.TileEntityLauncherBase;
import tfcs.tileentities.TileEntityMortar;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ClientProxy extends CommonProxy
{
	@Override
	public String getCurrentLanguage()
	{
		return Minecraft.getMinecraft().getLanguageManager().getCurrentLanguage().getLanguageCode();
	}

	@Override
	public World getCurrentWorld()
	{
		return Minecraft.getMinecraft().theWorld;
	}

	@Override
	public boolean getGraphicsLevel()
	{
		Minecraft.getMinecraft();
		return Minecraft.isFancyGraphicsEnabled();
	}

	@Override
	public File getMinecraftDirectory()
	{
		return Minecraft.getMinecraft().mcDataDir;
	}
	
	@Override
	public void hideNEIItems()
	{
		if (Loader.isModLoaded(Reference.MODID_NEI))
		{
		}
	}

	@Override
	public boolean isRemote()
	{
		return true;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void loadOptions()
	{
		//Load our settings from the server
		ModOptions.loadSettings();
	}

	@Override
	public void registerGuiHandler()
	{
		NetworkRegistry.INSTANCE.registerGuiHandler(tfcs.TFCSiege.instance, new tfcs.handlers.client.GuiHandler());
	}

	@Override
	public void registerHandlers()
	{
	}

	public static KeyBinding[] keyBindings;
	@Override
	public void registerKeys()
	{
		keyBindings = new KeyBinding[1];
		keyBindings[0] = new KeyBinding("Open Guild Menu", Keyboard.KEY_G, "key.categories.hud");
		uploadKeyBindingsToGame();
	}

	@Override
	public void registerKeyBindingHandler()
	{
		FMLCommonHandler.instance().bus().register(new KeyHandler());
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerRenderInformation()
	{
		
		RenderingRegistry.registerEntityRenderingHandler(EntitySettler.class, new RenderSettler());
		
		RenderingRegistry.registerEntityRenderingHandler(ProjectileCeramicShell.class, new EntityProjectileRenderer());
		
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityGearFrame.class, new RenderGearFrame());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(TFCSBlocks.gearFrame), new TFCSRenderItem());
		
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityGate.class, new RenderGate());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(TFCSBlocks.copperGate), new RenderGate());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(TFCSBlocks.bronzeGate), new RenderGate());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(TFCSBlocks.blackBronzeGate), new RenderGate());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(TFCSBlocks.bismuthBronzeGate), new RenderGate());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(TFCSBlocks.ironGate), new RenderGate());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(TFCSBlocks.steelGate), new RenderGate());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(TFCSBlocks.blackSteelGate), new RenderGate());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(TFCSBlocks.redSteelGate), new RenderGate());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(TFCSBlocks.blueSteelGate), new RenderGate());
		
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCandle.class, new RenderCandle());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(TFCSBlocks.candleON), new RenderCandle());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(TFCSBlocks.candleOFF), new RenderCandle());
		
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityBomb.class, new RenderBomb());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(TFCSBlocks.ceramicBomb), new RenderBomb());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(TFCSBlocks.ironBomb), new RenderBomb());
		
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityLauncherBase.class, new RenderLauncherBase());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(TFCSBlocks.launcherBase), new RenderLauncherBase());
		
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityAqueduct.class, new RenderAqueduct());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(TFCSBlocks.aqueduct), new RenderAqueduct());
		
		MinecraftForgeClient.registerItemRenderer(TFCSItems.longBow, new RenderLongBow());
		
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityMortar.class, new RenderMortar());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(TFCSBlocks.mortar), new RenderMortar());
	}
	
	@Override
	public void registerTileEntities(boolean flag)
	{
		super.registerTileEntities(false);
	}
	
	@Override
	public void uploadKeyBindingsToGame()
	{
		for(int i = 0;i < keyBindings.length;i++)
			ClientRegistry.registerKeyBinding(keyBindings[i]);
	}
}

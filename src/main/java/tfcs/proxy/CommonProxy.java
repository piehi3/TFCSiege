package tfcs.proxy;

import java.io.File;

import tfcs.core.EntityRegistryTFCS;
import tfcs.core.ModOptions;
import tfcs.core.TFCSBlocks;
import tfcs.handlers.EventHandler;
import tfcs.reference.ReferenceName;
import tfcs.tileentities.TileEntityAqueduct;
import tfcs.tileentities.TileEntityBomb;
import tfcs.tileentities.TileEntityCandle;
import tfcs.tileentities.TileEntityGate;
import tfcs.tileentities.TileEntityGearFrame;
import tfcs.tileentities.TileEntityGuild;
import tfcs.tileentities.TileEntityHouse;
import tfcs.tileentities.TileEntityIndustrialForge;
import tfcs.tileentities.TileEntityLauncherBase;
import tfcs.tileentities.TileEntityMortar;
import tfcs.tileentities.TileEntityMultiFuel;
import tfcs.tileentities.TileEntityPort;
import tfcs.tileentities.TileEntityTFCSCrucible;
import tfcs.tileentities.TileEntityWall;
import tfcs.util.IndustrialFuel;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;

import com.bioxx.tfc.Handlers.ServerTickHandler;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

public class CommonProxy
{
	
	public String getCurrentLanguage()
	{
		return null;
	}

	public World getCurrentWorld()
	{
		return MinecraftServer.getServer().getEntityWorld();
	}

	public boolean getGraphicsLevel()
	{
		return false;
	}
	
	public File getMinecraftDirectory()
	{
		return FMLCommonHandler.instance().getMinecraftServerInstance().getFile("");
	}

	public void hideNEIItems()
	{
	}
	
	public boolean isRemote()
	{
		return false;
	}

	public void loadOptions()
	{
		//Load our settings from the Options file
		ModOptions.loadSettings();
	}
	
	public void onClientLogin()
	{
	}

	public void registerFluidIcons()
	{
	}

	public void registerGuiHandler()
	{
		NetworkRegistry.INSTANCE.registerGuiHandler(tfcs.TFCSiege.instance, new tfcs.handlers.GuiHandler());
	}

	public void registerHandlers()
	{
		
	}

	public void registerKeys()
	{
	}

	public void registerKeyBindingHandler()
	{
	}

	public void registerRenderInformation()
	{
		// NOOP on server
	}

	public void registerSoundHandler()
	{
	}

	public void registerTickHandler()
	{
		FMLCommonHandler.instance().bus().register(new ServerTickHandler());
	}
	
	public void registerTileEntities(boolean flag)
	{
		registerTileEntity(TileEntityGearFrame.class, ReferenceName.BLOCK_GEAR_FRAME_NAME);
		registerTileEntity(TileEntityMortar.class, ReferenceName.BlOCK_MORTAR_NAME);
		registerTileEntity(TileEntityGate.class, ReferenceName.BLOCK_GATE_NAME);
		registerTileEntity(TileEntityWall.class, ReferenceName.BLOCK_WALL_NAME);
		registerTileEntity(TileEntityBomb.class, ReferenceName.BLOCK_BOMB_NAME);
		registerTileEntity(TileEntityCandle.class, ReferenceName.BLOCK_CANDLE_NAME);
		registerTileEntity(TileEntityLauncherBase.class, ReferenceName.BlOCK_LAUNCHER_BASE_NAME);
		registerTileEntity(TileEntityAqueduct.class, ReferenceName.BLOCK_AQUEDUCT_NAME);
		registerTileEntity(TileEntityPort.class, ReferenceName.BlOCK_PORT_NAME);
		registerTileEntity(TileEntityHouse.class, ReferenceName.BlOCK_FOUNDATION_NAME);
		registerTileEntity(TileEntityIndustrialForge.class, ReferenceName.BlOCK_INDUSTRIAL_FORGE_NAME);
		registerTileEntity(TileEntityMultiFuel.class, ReferenceName.BlOCK_MULTI_FUEL_NAME);
		registerTileEntity(TileEntityTFCSCrucible.class, ReferenceName.BlOCK_CRUCIBLE_NAME);
		registerTileEntity(TileEntityGuild.class, ReferenceName.BLOCK_GUILD_NAME);
		
		MinecraftForge.EVENT_BUS.register(new EventHandler());
		if (flag)
		{
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static void registerTileEntity(Class tileEntityClass,String tileEntityID){
		GameRegistry.registerTileEntity(tileEntityClass, tileEntityID+"_TileEntity");
	}
	
	public void registerToolClasses()
	{
	}

	public void registerWailaClasses()
	{
	}

	public void uploadKeyBindingsToGame()
	{
	}

	public void registerEntities() {
		EntityRegistryTFCS.RegisterEntities();
	}
}

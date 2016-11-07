package tfcs;

import java.io.File;

import net.minecraftforge.common.MinecraftForge;
import tfcs.core.BlockRegistry;
import tfcs.core.EventInit;
import tfcs.core.ItemRegistry;
import tfcs.core.Recipes;
import tfcs.core.player.ModPlayerTracker;
import tfcs.handlers.ChunkEventHandler;
import tfcs.handlers.CraftingHandler;
import tfcs.handlers.GuildHandler;
import tfcs.handlers.network.InitClientWorldPacket;
import tfcs.handlers.network.PacketHandler;
import tfcs.proxy.CommonProxy;
import tfcs.reference.Reference;
import tfcs.reference.ReferenceMultiBlock;
import tfcs.util.Trade;

import com.bioxx.tfc.TerraFirmaCraft;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Reference.ModID, name = Reference.ModName, version = Reference.ModVersion, dependencies = Reference.ModDependencies)
public class TFCSiege
{
	@Instance(Reference.ModID)
	public static TFCSiege instance;
	
	@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
	public static CommonProxy proxy;
	
	public GuildHandler guildHandler;
	
	public File getMinecraftDirectory()
	{
		return proxy.getMinecraftDirectory();
	}
	
	@EventHandler
	public void preInitialize(FMLPreInitializationEvent e)
	{
		instance = this;		
		// Load our settings
		proxy.loadOptions();
		
		proxy.registerTickHandler();
		
		BlockRegistry.initialise();	

		// Register Key Bindings(Client only)
		proxy.registerKeys();
		// Register KeyBinding Handler (Client only)
		proxy.registerKeyBindingHandler();
		// Register Handler (Client only)
		proxy.registerHandlers();
		// Register Tile Entities
		proxy.registerTileEntities(true);
		// Register Sound Handler (Client only)
		proxy.registerSoundHandler();
		
		ItemRegistry.initialise();
		
		// Register Gui Handler
		proxy.registerGuiHandler();		
		
		PacketHandler.init();
		EventInit.init();
		Trade.registerTrades();
		guildHandler = new GuildHandler();
		
		proxy.registerEntities();
		ReferenceMultiBlock.init();
	}

	@EventHandler
	public void initialize(FMLInitializationEvent e)
	{
		// Register packets in the TFC PacketPipeline
		TerraFirmaCraft.PACKET_PIPELINE.registerPacket(InitClientWorldPacket.class);
		
		// Register the player tracker
		FMLCommonHandler.instance().bus().register(new ModPlayerTracker());
		
		// Register the tool classes
		proxy.registerToolClasses();

		// Register Crafting Handler
		FMLCommonHandler.instance().bus().register(new CraftingHandler());

		// Register the Chunk Load/Save Handler
		MinecraftForge.EVENT_BUS.register(new ChunkEventHandler());
		
		// Register all the render stuff for the client
		proxy.registerRenderInformation();

		Recipes.initialise();
		
		// Register WAILA classes
		proxy.registerWailaClasses();
		proxy.hideNEIItems();		
	}

	@EventHandler
	public void postInitialize(FMLPostInitializationEvent e)
	{
	}
}

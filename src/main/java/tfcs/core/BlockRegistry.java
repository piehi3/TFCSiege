package tfcs.core;

import net.minecraft.block.material.Material;
import cpw.mods.fml.common.registry.GameRegistry;
import tfcs.blocks.BlockAqueductPart;
import tfcs.blocks.BlockDrying;
import tfcs.blocks.devices.BlockAqueduct;
import tfcs.blocks.devices.BlockBomb;
import tfcs.blocks.devices.BlockCandle;
import tfcs.blocks.devices.BlockCrucible;
import tfcs.blocks.devices.BlockGate;
import tfcs.blocks.devices.BlockGearFrame;
import tfcs.blocks.devices.BlockGuild;
import tfcs.blocks.devices.BlockHouse;
import tfcs.blocks.devices.BlockIndustrialForge;
import tfcs.blocks.devices.BlockLauncherBase;
import tfcs.blocks.devices.BlockMortar;
import tfcs.blocks.devices.BlockMultiFuel;
import tfcs.blocks.devices.BlockPort;
import tfcs.blocks.devices.BlockWall;
import tfcs.reference.Reference;
import tfcs.reference.ReferenceName;

public class BlockRegistry {
	// Blocks Render Id's

	

	public static void initialise() {
		System.out.println("[" + Reference.ModName + "] Registering Blocks");

		loadBlocks();
		registerBlocks();

		System.out.println("[" + Reference.ModName
				+ "] Done Registering Blocks");
	}

	private static void loadBlocks() {
		TFCSBlocks.gearFrame = new BlockGearFrame();
		TFCSBlocks.mortar = new BlockMortar();
		TFCSBlocks.port = new BlockPort();
		TFCSBlocks.foundation = new BlockHouse();
		TFCSBlocks.industrialForge = new BlockIndustrialForge();
		TFCSBlocks.crucible = new BlockCrucible();
		
		TFCSBlocks.launcherBase = new BlockLauncherBase();

		TFCSBlocks.candleON = new BlockCandle(true);
		TFCSBlocks.candleOFF = new BlockCandle(false);
		
		TFCSBlocks.thatchWall = new BlockWall(Material.wood, ReferenceName.BLOCK_WALL_NAME, "thatch","thatch",50);
		TFCSBlocks.woodWall = new BlockWall(Material.wood, ReferenceName.BLOCK_WALL_NAME, "wood","oak", 100);
		
		TFCSBlocks.copperGate = new BlockGate(Material.wood,"copper",10,(byte) 0);
		TFCSBlocks.bronzeGate = new BlockGate(Material.wood,"bronze",200,(byte) 1);
		TFCSBlocks.blackBronzeGate = new BlockGate(Material.wood,"blackBronze",300,(byte) 2);
		TFCSBlocks.bismuthBronzeGate = new BlockGate(Material.wood,"bismuthBronze",300,(byte) 3);
		TFCSBlocks.ironGate = new BlockGate(Material.wood,"iron",400,(byte) 4);
		TFCSBlocks.steelGate = new BlockGate(Material.wood,"steel",500,(byte) 5);
		TFCSBlocks.blackSteelGate = new BlockGate(Material.wood,"blackSteel",550,(byte) 6);
		TFCSBlocks.redSteelGate = new BlockGate(Material.wood,"redSteel",600,(byte) 7);
		TFCSBlocks.blueSteelGate = new BlockGate(Material.wood,"blueSteel",600,(byte) 8);
		
		TFCSBlocks.woodenGuild = new BlockGuild(Material.wood,"wood",30);
		
		TFCSBlocks.ceramicBomb = new BlockBomb("ceramic",3F);
		TFCSBlocks.ironBomb = new BlockBomb("iron",4.0F);
		
		TFCSBlocks.aqueduct = new BlockAqueduct();
		TFCSBlocks.spout = new BlockAqueductPart("spout");
		
		TFCSBlocks.multiFuelOFF = new BlockMultiFuel(false);
		TFCSBlocks.multiFuelON = new BlockMultiFuel(true);
		
		TFCSBlocks.dryingMudBrick = new BlockDrying("undryed");
		TFCSBlocks.dryedMudBrick = new BlockDrying("dryed");
		
		TFCSBlocks.mudWall = new BlockWall(Material.rock, ReferenceName.BLOCK_WALL_NAME, "stone","mud", 150);
		
		TFCSBlocks.andesiteWall = new BlockWall(Material.rock, ReferenceName.BLOCK_WALL_NAME, "stone","andesite", 300);
		TFCSBlocks.basaltWall = new BlockWall(Material.rock, ReferenceName.BLOCK_WALL_NAME, "stone","basalt", 300);
		TFCSBlocks.daciteWall = new BlockWall(Material.rock, ReferenceName.BLOCK_WALL_NAME, "stone","dacite", 300);
		TFCSBlocks.rhyoliteWall = new BlockWall(Material.rock, ReferenceName.BLOCK_WALL_NAME, "stone","rhyolite", 300);
		
		TFCSBlocks.marbleWall = new BlockWall(Material.rock, ReferenceName.BLOCK_WALL_NAME, "stone","marble", 200);
		TFCSBlocks.graniteWall = new BlockWall(Material.rock, ReferenceName.BLOCK_WALL_NAME, "stone","granite", 250);
		TFCSBlocks.gabbroWall = new BlockWall(Material.rock, ReferenceName.BLOCK_WALL_NAME, "stone","gabbro", 200);
		TFCSBlocks.dioriteWall = new BlockWall(Material.rock, ReferenceName.BLOCK_WALL_NAME, "stone","diorite", 200);
	}
	
	private static void registerBlocks() {
		GameRegistry.registerBlock(TFCSBlocks.gearFrame, ReferenceName.BLOCK_GEAR_FRAME_NAME);
		GameRegistry.registerBlock(TFCSBlocks.mortar, ReferenceName.BlOCK_MORTAR_NAME);
		GameRegistry.registerBlock(TFCSBlocks.port, ReferenceName.BlOCK_PORT_NAME);
		GameRegistry.registerBlock(TFCSBlocks.foundation, ReferenceName.BlOCK_FOUNDATION_NAME);
		GameRegistry.registerBlock(TFCSBlocks.industrialForge, ReferenceName.BlOCK_INDUSTRIAL_FORGE_NAME);
		GameRegistry.registerBlock(TFCSBlocks.crucible, ReferenceName.BlOCK_CRUCIBLE_NAME);
		
		GameRegistry.registerBlock(TFCSBlocks.launcherBase, ReferenceName.BlOCK_LAUNCHER_BASE_NAME);
		
		GameRegistry.registerBlock(TFCSBlocks.candleON, ReferenceName.BLOCK_CANDLE_NAME+".on");
		GameRegistry.registerBlock(TFCSBlocks.candleOFF, ReferenceName.BLOCK_CANDLE_NAME+".off");
		
		GameRegistry.registerBlock(TFCSBlocks.thatchWall, ReferenceName.BLOCK_WALL_NAME + ".thatch");
		GameRegistry.registerBlock(TFCSBlocks.woodWall, ReferenceName.BLOCK_WALL_NAME + ".wood");
		
		GameRegistry.registerBlock(TFCSBlocks.copperGate, ReferenceName.BLOCK_GATE_NAME + ".copper");
		GameRegistry.registerBlock(TFCSBlocks.bronzeGate, ReferenceName.BLOCK_GATE_NAME + ".bronzeGate");
		GameRegistry.registerBlock(TFCSBlocks.blackBronzeGate, ReferenceName.BLOCK_GATE_NAME + ".blackBronze");
		GameRegistry.registerBlock(TFCSBlocks.bismuthBronzeGate, ReferenceName.BLOCK_GATE_NAME + ".bismuthBronze");
		GameRegistry.registerBlock(TFCSBlocks.ironGate, ReferenceName.BLOCK_GATE_NAME + ".iron");
		GameRegistry.registerBlock(TFCSBlocks.steelGate, ReferenceName.BLOCK_GATE_NAME + ".steel");
		GameRegistry.registerBlock(TFCSBlocks.blackSteelGate, ReferenceName.BLOCK_GATE_NAME + ".blackSteel");
		GameRegistry.registerBlock(TFCSBlocks.redSteelGate, ReferenceName.BLOCK_GATE_NAME + ".redSteel");
		GameRegistry.registerBlock(TFCSBlocks.blueSteelGate, ReferenceName.BLOCK_GATE_NAME + ".blueSteel");
		
		GameRegistry.registerBlock(TFCSBlocks.woodenGuild, ReferenceName.BLOCK_GUILD_NAME + ".wood");
		
		GameRegistry.registerBlock(TFCSBlocks.ceramicBomb, ReferenceName.BLOCK_BOMB_NAME + ".ceramic");
		GameRegistry.registerBlock(TFCSBlocks.ironBomb, ReferenceName.BLOCK_BOMB_NAME + ".iron");
		
		GameRegistry.registerBlock(TFCSBlocks.aqueduct, ReferenceName.BLOCK_AQUEDUCT_NAME);
		GameRegistry.registerBlock(TFCSBlocks.spout, ReferenceName.BLOCK_AQUEDUCT_PART_NAME + ".stone" + ".mud");
		
		GameRegistry.registerBlock(TFCSBlocks.dryedMudBrick, ReferenceName.BLOCK_DRYING_NAME+".dryed");
		GameRegistry.registerBlock(TFCSBlocks.dryingMudBrick, ReferenceName.BLOCK_DRYING_NAME+".undryed");
		
		GameRegistry.registerBlock(TFCSBlocks.mudWall, ReferenceName.BLOCK_WALL_NAME + ".stone" + ".mud");

		GameRegistry.registerBlock(TFCSBlocks.multiFuelON, ReferenceName.BlOCK_MULTI_FUEL_NAME + "ON");
		GameRegistry.registerBlock(TFCSBlocks.multiFuelOFF, ReferenceName.BlOCK_MULTI_FUEL_NAME + "OFF");
		
		GameRegistry.registerBlock(TFCSBlocks.andesiteWall, ReferenceName.BLOCK_WALL_NAME + ".stone" + ".andesite");
		GameRegistry.registerBlock(TFCSBlocks.basaltWall, ReferenceName.BLOCK_WALL_NAME + ".stone"+".basalt");
		GameRegistry.registerBlock(TFCSBlocks.daciteWall, ReferenceName.BLOCK_WALL_NAME + ".stone"+".dacite");
		GameRegistry.registerBlock(TFCSBlocks.rhyoliteWall, ReferenceName.BLOCK_WALL_NAME + ".stone" + ".rhyolite");
		
		GameRegistry.registerBlock(TFCSBlocks.marbleWall, ReferenceName.BLOCK_WALL_NAME + ".stone" + ".marble");
		GameRegistry.registerBlock(TFCSBlocks.graniteWall, ReferenceName.BLOCK_WALL_NAME + ".stone" + ".granite");
		GameRegistry.registerBlock(TFCSBlocks.gabbroWall, ReferenceName.BLOCK_WALL_NAME + ".stone" + ".gabbro");
		GameRegistry.registerBlock(TFCSBlocks.dioriteWall, ReferenceName.BLOCK_WALL_NAME + ".stone" + ".diorite");
	}
}

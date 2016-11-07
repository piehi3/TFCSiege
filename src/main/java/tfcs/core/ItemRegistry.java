package tfcs.core;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import tfcs.gears.CoilToolComponent;
import tfcs.gears.axle.BismuthBronzeAxleComponent;
import tfcs.gears.axle.BlackBronzeAxleComponent;
import tfcs.gears.axle.BlackSteelAxleComponent;
import tfcs.gears.axle.BlueSteelAxleComponent;
import tfcs.gears.axle.BronzeAxleComponent;
import tfcs.gears.axle.CopperAxleComponent;
import tfcs.gears.axle.IronAxleComponent;
import tfcs.gears.axle.RedSteelAxleComponent;
import tfcs.gears.axle.SteelAxleComponent;
import tfcs.gears.axle.StoneAxleComponent;
import tfcs.gears.axle.WoodenAxleComponent;
import tfcs.gears.gear.BismuthBronzeGearComponent;
import tfcs.gears.gear.BlackBronzeGearComponent;
import tfcs.gears.gear.BlackSteelGearComponent;
import tfcs.gears.gear.BlueSteelGearComponent;
import tfcs.gears.gear.BronzeGearComponent;
import tfcs.gears.gear.CopperGearComponent;
import tfcs.gears.gear.IronGearComponent;
import tfcs.gears.gear.RedSteelGearComponent;
import tfcs.gears.gear.SteelGearComponent;
import tfcs.gears.gear.StoneGearComponent;
import tfcs.gears.gear.WoodenGearComponent;
import tfcs.gears.tool.BenSmells;
import tfcs.gears.tool.BismuthBronzeCannonBarrelCompenent;
import tfcs.gears.tool.BismuthBronzeCoilCompenent;
import tfcs.gears.tool.BismuthBronzeSawCompenent;
import tfcs.gears.tool.BlackBronzeCannonBarrelCompenent;
import tfcs.gears.tool.BlackBronzeCoilCompenent;
import tfcs.gears.tool.BlackBronzeSawCompenent;
import tfcs.gears.tool.BlackSteelCannonBarrelCompenent;
import tfcs.gears.tool.BlackSteelCoilCompenent;
import tfcs.gears.tool.BlackSteelSawCompenent;
import tfcs.gears.tool.BlueSteelCannonBarrelCompenent;
import tfcs.gears.tool.BlueSteelCoilCompenent;
import tfcs.gears.tool.BlueSteelSawCompenent;
import tfcs.gears.tool.BronzeCannonBarrelCompenent;
import tfcs.gears.tool.BronzeCoilCompenent;
import tfcs.gears.tool.BronzeSawCompenent;
import tfcs.gears.tool.CopperCannonBarrelCompenent;
import tfcs.gears.tool.CopperCoilCompenent;
import tfcs.gears.tool.CopperSawCompenent;
import tfcs.gears.tool.GateMoverCompenent;
import tfcs.gears.tool.GrindstoneToolComponent;
import tfcs.gears.tool.HandCrankToolComponent;
import tfcs.gears.tool.IronCannonBarrelCompenent;
import tfcs.gears.tool.IronCoilCompenent;
import tfcs.gears.tool.IronSawCompenent;
import tfcs.gears.tool.RedSteelCannonBarrelCompenent;
import tfcs.gears.tool.RedSteelCoilCompenent;
import tfcs.gears.tool.RedSteelSawCompenent;
import tfcs.gears.tool.SawToolComponent;
import tfcs.gears.tool.SteelCannonBarrelCompenent;
import tfcs.gears.tool.SteelCoilCompenent;
import tfcs.gears.tool.SteelSawCompenent;
import tfcs.gears.tool.WaterWheelToolComponent;
import tfcs.gears.tool.WindmillToolComponent;
import tfcs.items.CraftingItems;
import tfcs.items.ItemBattleHorn;
import tfcs.items.ItemBolt;
import tfcs.items.ItemCurrency;
import tfcs.items.ItemFuel;
import tfcs.items.ItemMudBrick;
import tfcs.items.ItemProjectile;
import tfcs.items.ItemSettler;
import tfcs.items.ItemWarSaddle;
import tfcs.items.gears.ItemAxleComponent;
import tfcs.items.gears.ItemGearComponent;
import tfcs.items.gears.ItemLauncherComponent;
import tfcs.items.gears.ItemToolComponent;
import tfcs.items.tool.ItemCoilComponent;
import tfcs.items.tool.ItemCrossbow;
import tfcs.items.tool.ItemLauncher;
import tfcs.items.tool.ItemMusket;
import tfcs.items.tool.ItemSawComponent;
import tfcs.items.tool.ItemShield;
import tfcs.items.tool.ItemTemperedBroadsword;
import tfcs.items.tool.ItemTemperedPickaxe;
import tfcs.items.tool.ItemThermometer;
import tfcs.items.tool.ItemWrench;
import tfcs.reference.Reference;

import com.bioxx.tfc.api.Armor;
import com.bioxx.tfc.api.TFCBlocks;
import com.bioxx.tfc.api.TFCItems;

import cpw.mods.fml.common.registry.GameRegistry;

public class ItemRegistry {
	// Items
	public static Item CustomItem;

	public static void initialise() {
		System.out.println("[" + Reference.ModName + "] Registering Items");

		loadItems();
		registerItems();
		registerFrameComponents();
		registerItemValues();
		registerFuels();

		System.out.println("[" + Reference.ModName + "] Done Registering Items");
	}

	private static void loadItems() {

		TFCSItems.woodenGear = new ItemGearComponent(0);
		TFCSItems.stoneGear = new ItemGearComponent(1);
		TFCSItems.copperGear = new ItemGearComponent(2);
		TFCSItems.bronzeGear = new ItemGearComponent(3);
		TFCSItems.blackBronzeGear = new ItemGearComponent(4);
		TFCSItems.bismuthBronzeGear = new ItemGearComponent(5);
		TFCSItems.ironGear = new ItemGearComponent(6);
		TFCSItems.steelGear = new ItemGearComponent(7);
		TFCSItems.blackSteelGear = new ItemGearComponent(8);
		TFCSItems.blueSteelGear = new ItemGearComponent(9);
		TFCSItems.redSteelGear = new ItemGearComponent(10);

		TFCSItems.woodenAxle = new ItemAxleComponent(0);
		TFCSItems.stoneAxle = new ItemAxleComponent(1);
		TFCSItems.copperAxle = new ItemAxleComponent(2);
		TFCSItems.bronzeAxle = new ItemAxleComponent(3);
		TFCSItems.blackBronzeAxle = new ItemAxleComponent(4);
		TFCSItems.bismuthBronzeAxle = new ItemAxleComponent(5);
		TFCSItems.ironAxle = new ItemAxleComponent(6);
		TFCSItems.steelAxle = new ItemAxleComponent(7);
		TFCSItems.blackSteelAxle = new ItemAxleComponent(8);
		TFCSItems.blueSteelAxle = new ItemAxleComponent(9);
		TFCSItems.redSteelAxle = new ItemAxleComponent(10);

		TFCSItems.windmill = new ItemToolComponent(0);
		TFCSItems.gateMover = new ItemToolComponent(1);
		TFCSItems.benSmells = new ItemToolComponent(2);
		TFCSItems.waterWheel = new ItemToolComponent(3);
		TFCSItems.grindstone = new ItemToolComponent(4);
		TFCSItems.handCrank = new ItemToolComponent(5);

		TFCSItems.copperCannonBarrel = new ItemLauncherComponent("copper");
		TFCSItems.bronzeCannonBarrel = new ItemLauncherComponent("bronze");
		TFCSItems.bismuthBronzeCannonBarrel = new ItemLauncherComponent("bismuthBronze");
		TFCSItems.blackBronzeCannonBarrel = new ItemLauncherComponent("blackBronze");
		TFCSItems.ironCannonBarrel = new ItemLauncherComponent("iron");
		TFCSItems.steelCannonBarrel = new ItemLauncherComponent("steel");
		TFCSItems.blackSteelCannonBarrel = new ItemLauncherComponent("blackSteel");
		TFCSItems.redSteelCannonBarrel = new ItemLauncherComponent("redSteel");
		TFCSItems.blueSteelCannonBarrel = new ItemLauncherComponent("blueSteel");

		TFCSItems.copperCoil = new ItemCoilComponent("copper");
		TFCSItems.bronzeCoil = new ItemCoilComponent("bronze");
		TFCSItems.blackBronzeCoil = new ItemCoilComponent("blackBronze");
		TFCSItems.bismuthBronzeCoil = new ItemCoilComponent("bismuthBronze");
		TFCSItems.ironCoil = new ItemCoilComponent("iron");
		TFCSItems.steelCoil = new ItemCoilComponent("steel");
		TFCSItems.blackSteelCoil = new ItemCoilComponent("blackSteel");
		TFCSItems.blueSteelCoil = new ItemCoilComponent("blueSteel");
		TFCSItems.redSteelCoil = new ItemCoilComponent("redSteel");

		TFCSItems.copperSaw = new ItemSawComponent("copper");
		TFCSItems.bronzeSaw = new ItemSawComponent("bronze");
		TFCSItems.blackBronzeSaw = new ItemSawComponent("blackBronze");
		TFCSItems.bismuthBronzeSaw = new ItemSawComponent("bismuthBronze");
		TFCSItems.ironSaw = new ItemSawComponent("iron");
		TFCSItems.steelSaw = new ItemSawComponent("steel");
		TFCSItems.blackSteelSaw = new ItemSawComponent("blackSteel");
		TFCSItems.blueSteelSaw = new ItemSawComponent("blueSteel");
		TFCSItems.redSteelSaw = new ItemSawComponent("redSteel");

		TFCSItems.ceramicShell = new ItemProjectile(0);
		TFCSItems.longArrow = new ItemProjectile(1);
		TFCSItems.bullet = new ItemProjectile(2);
		TFCSItems.fireLongArrow = new ItemProjectile(3);
		TFCSItems.cannonBall = new ItemProjectile(4);

		TFCSItems.wrench = new ItemWrench();
		TFCSItems.thermometer = new ItemThermometer();
		TFCSItems.longBow = new ItemLauncher(ToolMaterial.WOOD);

		TFCSItems.woodenShield = new ItemShield(ToolMaterial.WOOD, Armor.leather);
		TFCSItems.copperShield = new ItemShield(TFCItems.copperToolMaterial, Armor.copperPlate);
		TFCSItems.bronzeShield = new ItemShield(TFCItems.bronzeToolMaterial, Armor.bronzePlate);
		TFCSItems.bismuthBronzeShield = new ItemShield(TFCItems.bismuthBronzeToolMaterial, Armor.bismuthBronzePlate);
		TFCSItems.blackBronzeShield = new ItemShield(TFCItems.blackBronzeToolMaterial, Armor.blackBronzePlate);
		TFCSItems.wroughtIronShield = new ItemShield(TFCItems.ironToolMaterial, Armor.wroughtIronPlate);
		TFCSItems.steelShield = new ItemShield(TFCItems.steelToolMaterial, Armor.steelPlate);
		TFCSItems.blackSteelShield = new ItemShield(TFCItems.blackSteelToolMaterial, Armor.blackSteelPlate);
		TFCSItems.redSteelShield = new ItemShield(TFCItems.redSteelToolMaterial, Armor.redSteelPlate);
		TFCSItems.blueSteelShield = new ItemShield(TFCItems.blueSteelToolMaterial, Armor.blueSteelPlate);

		TFCSItems.copperTemperedBroadsword = new ItemTemperedBroadsword(TFCItems.copperToolMaterial, 165);
		TFCSItems.bronzeTemperedBroadsword = new ItemTemperedBroadsword(TFCItems.bronzeToolMaterial, 220);
		TFCSItems.bismuthBronzeTemperedBroadsword = new ItemTemperedBroadsword(TFCItems.bismuthBronzeToolMaterial, 210);
		TFCSItems.blackBronzeTemperedBroadsword = new ItemTemperedBroadsword(TFCItems.blackBronzeToolMaterial, 230);
		TFCSItems.wroughtIronTemperedBroadsword = new ItemTemperedBroadsword(TFCItems.ironToolMaterial, 240);
		TFCSItems.steelTemperedBroadsword = new ItemTemperedBroadsword(TFCItems.steelToolMaterial, 265);
		TFCSItems.blackSteelTemperedBroadsword = new ItemTemperedBroadsword(TFCItems.blackSteelToolMaterial, 270);
		TFCSItems.redSteelTemperedBroadsword = new ItemTemperedBroadsword(TFCItems.redSteelToolMaterial, 315);
		TFCSItems.blueSteelTemperedBroadsword = new ItemTemperedBroadsword(TFCItems.blueSteelToolMaterial, 315);

		TFCSItems.copperTemperedPickaxe = new ItemTemperedPickaxe(TFCItems.copperToolMaterial, 67);
		TFCSItems.bronzeTemperedPickaxe = new ItemTemperedPickaxe(TFCItems.bronzeToolMaterial, 102);
		TFCSItems.bismuthBronzeTemperedPickaxe = new ItemTemperedPickaxe(TFCItems.bismuthBronzeToolMaterial, 92);
		TFCSItems.blackBronzeTemperedPickaxe = new ItemTemperedPickaxe(TFCItems.blackBronzeToolMaterial, 97);
		TFCSItems.wroughtIronTemperedPickaxe = new ItemTemperedPickaxe(TFCItems.ironToolMaterial, 137);
		TFCSItems.steelTemperedPickaxe = new ItemTemperedPickaxe(TFCItems.steelToolMaterial, 172);
		TFCSItems.blackSteelTemperedPickaxe = new ItemTemperedPickaxe(TFCItems.blackSteelToolMaterial, 207);
		TFCSItems.redSteelTemperedPickaxe = new ItemTemperedPickaxe(TFCItems.redSteelToolMaterial, 242);
		TFCSItems.blueSteelTemperedPickaxe = new ItemTemperedPickaxe(TFCItems.blueSteelToolMaterial, 242);

		TFCSItems.copperBolt = new ItemBolt(TFCItems.copperToolMaterial);
		TFCSItems.bronzeBolt = new ItemBolt(TFCItems.bronzeToolMaterial);
		TFCSItems.bismuthBronzeBolt = new ItemBolt(TFCItems.bismuthBronzeToolMaterial);
		TFCSItems.blackBronzeBolt = new ItemBolt(TFCItems.blackBronzeToolMaterial);
		TFCSItems.wroughtIronBolt = new ItemBolt(TFCItems.ironToolMaterial);

		TFCSItems.wroughtIronMusket = new ItemMusket(TFCItems.ironToolMaterial, 5);
		TFCSItems.steelMusket = new ItemMusket(TFCItems.steelToolMaterial, 6);
		TFCSItems.blackSteelMusket = new ItemMusket(TFCItems.blackSteelToolMaterial, 7);
		TFCSItems.redSteelMusket = new ItemMusket(TFCItems.redSteelToolMaterial, 8);
		TFCSItems.blueSteelMusket = new ItemMusket(TFCItems.blueSteelToolMaterial, 8);

		TFCSItems.copperTemperedCrossbow = new ItemCrossbow(TFCItems.copperToolMaterial, 3);
		TFCSItems.bronzeTemperedCrossbow = new ItemCrossbow(TFCItems.bronzeToolMaterial, 4);
		TFCSItems.blackBronzeTemperedCrossbow = new ItemCrossbow(TFCItems.blackBronzeToolMaterial, 4);
		TFCSItems.bismuthBronzeTemperedCrossbow = new ItemCrossbow(TFCItems.bismuthBronzeToolMaterial, 4);
		TFCSItems.wroughtIronTemperedCrossbow = new ItemCrossbow(TFCItems.ironToolMaterial, 5);

		TFCSItems.settler = new ItemSettler(0);
		TFCSItems.travelingSalesman = new ItemSettler(1);
		TFCSItems.rideBear = new ItemSettler(2);

		TFCSItems.mudBrick = new ItemMudBrick();
		TFCSItems.currency = new ItemCurrency();
		TFCSItems.battleHorn = new ItemBattleHorn();
		TFCSItems.warSaddle = new ItemWarSaddle();

		// anvil crafting stuff
		// cbow limbs
		TFCSItems.copperCrossbowLimb = new CraftingItems("crossbowLimb", "copper").setMaxStackSize(2);
		TFCSItems.bronzeCrossbowLimb = new CraftingItems("crossbowLimb", "bronze").setMaxStackSize(2);
		TFCSItems.bismuthBronzeCrossbowLimb = new CraftingItems("crossbowLimb", "bismuthBronze").setMaxStackSize(2);
		TFCSItems.blackBronzeCrossbowLimb = new CraftingItems("crossbowLimb", "blackBronze").setMaxStackSize(2);
		TFCSItems.wroughtIronCrossbowLimb = new CraftingItems("crossbowLimb", "wroughtIron").setMaxStackSize(2);

		// cbow bolt heads
		TFCSItems.copperBoltHead = new CraftingItems("boltHead", "copper").setMaxStackSize(16);
		TFCSItems.bronzeBoltHead = new CraftingItems("boltHead", "bronze").setMaxStackSize(16);
		TFCSItems.bismuthBronzeBoltHead = new CraftingItems("boltHead", "bismuthBronze").setMaxStackSize(16);
		TFCSItems.blackBronzeBoltHead = new CraftingItems("boltHead", "blackBronze").setMaxStackSize(16);
		TFCSItems.wroughtIronBoltHead = new CraftingItems("boltHead", "wroughtIron").setMaxStackSize(16);

		TFCSItems.copperTemperedPickHead = new CraftingItems("TemperedPickHead", "copper");
		TFCSItems.bronzeTemperedPickHead = new CraftingItems("TemperedPickHead", "bronze");
		TFCSItems.bismuthBronzeTemperedPickHead = new CraftingItems("TemperedPickHead", "bismuthBronze");
		TFCSItems.blackBronzeTemperedPickHead = new CraftingItems("TemperedPickHead", "blackBronze");
		TFCSItems.wroughtIronTemperedPickHead = new CraftingItems("TemperedPickHead", "wroughtIron");
		TFCSItems.steelTemperedPickHead = new CraftingItems("TemperedPickHead", "steel");
		TFCSItems.blackSteelTemperedPickHead = new CraftingItems("TemperedPickHead", "blackSteel");
		TFCSItems.redSteelTemperedPickHead = new CraftingItems("TemperedPickHead", "redSteel");
		TFCSItems.blueSteelTemperedPickHead = new CraftingItems("TemperedPickHead", "blueSteel");

		TFCSItems.copperCannonBarrelPiece = new CraftingItems("CannonBarrelPiece", "copper");
		TFCSItems.bronzeCannonBarrelPiece = new CraftingItems("CannonBarrelPiece", "bronze");
		TFCSItems.blackBronzeCannonBarrelPiece = new CraftingItems("CannonBarrelPiece", "blackBronze");
		TFCSItems.bismuthBronzeCannonBarrelPiece = new CraftingItems("CannonBarrelPiece", "bismuthBronze");
		TFCSItems.wroughtIronCannonBarrelPiece = new CraftingItems("CannonBarrelPiece", "wroughtIron");
		TFCSItems.steelCannonBarrelPiece = new CraftingItems("CannonBarrelPiece", "steel");
		TFCSItems.blackSteelCannonBarrelPiece = new CraftingItems("CannonBarrelPiece", "blackSteel");
		TFCSItems.redSteelCannonBarrelPiece = new CraftingItems("CannonBarrelPiece", "redSteel");
		TFCSItems.blueSteelCannonBarrelPiece = new CraftingItems("CannonBarrelPiece", "blueSteel");

		TFCSItems.copperTemperedSwordBlade = new CraftingItems("TemperedSwordBlade", "copper");
		TFCSItems.bronzeTemperedSwordBlade = new CraftingItems("TemperedSwordBlade", "bronze");
		TFCSItems.blackBronzeTemperedSwordBlade = new CraftingItems("TemperedSwordBlade", "blackBronze");
		TFCSItems.bismuthBronzeTemperedSwordBlade = new CraftingItems("TemperedSwordBlade", "bismuthBronze");
		TFCSItems.wroughtIronTemperedSwordBlade = new CraftingItems("TemperedSwordBlade", "wroughtIron");
		TFCSItems.steelTemperedSwordBlade = new CraftingItems("TemperedSwordBlade", "steel");
		TFCSItems.blackSteelTemperedSwordBlade = new CraftingItems("TemperedSwordBlade", "blackSteel");
		TFCSItems.redSteelTemperedSwordBlade = new CraftingItems("TemperedSwordBlade", "redSteel");
		TFCSItems.blueSteelTemperedSwordBlade = new CraftingItems("TemperedSwordBlade", "blueSteel");

		TFCSItems.copperShieldBody = new CraftingItems("ShieldBody", "copper");
		TFCSItems.bronzeShieldBody = new CraftingItems("ShieldBody", "bronze");
		TFCSItems.blackBronzeShieldBody = new CraftingItems("ShieldBody", "blackBronze");
		TFCSItems.bismuthBronzeShieldBody = new CraftingItems("ShieldBody", "bismuthBronze");
		TFCSItems.wroughtIronShieldBody = new CraftingItems("ShieldBody", "wroughtIron");
		TFCSItems.steelShieldBody = new CraftingItems("ShieldBody", "steel");
		TFCSItems.blackSteelShieldBody = new CraftingItems("ShieldBody", "blackSteel");
		TFCSItems.redSteelShieldBody = new CraftingItems("ShieldBody", "redSteel");
		TFCSItems.blueSteelShieldBody = new CraftingItems("ShieldBody", "blueSteel");
	}

	private static void registerItems() {

		GameRegistry.registerItem(TFCSItems.woodenGear, tfcs.reference.ReferenceName.ITEM_WOODEN_GEAR_NAME);
		GameRegistry.registerItem(TFCSItems.stoneGear, tfcs.reference.ReferenceName.ITEM_STONE_GEAR_NAME);
		GameRegistry.registerItem(TFCSItems.copperGear, tfcs.reference.ReferenceName.ITEM_COPPER_GEAR_NAME);
		GameRegistry.registerItem(TFCSItems.bronzeGear, tfcs.reference.ReferenceName.ITEM_BRONZE_GEAR_NAME);
		GameRegistry.registerItem(TFCSItems.blackBronzeGear, tfcs.reference.ReferenceName.ITEM_BLACK_BRONZE_GEAR_NAME);
		GameRegistry.registerItem(TFCSItems.bismuthBronzeGear, tfcs.reference.ReferenceName.ITEM_BISMUTH_BRONZE_GEAR_NAME);
		GameRegistry.registerItem(TFCSItems.ironGear, tfcs.reference.ReferenceName.ITEM_IRON_GEAR_NAME);
		GameRegistry.registerItem(TFCSItems.steelGear, tfcs.reference.ReferenceName.ITEM_STEEL_GEAR_NAME);
		GameRegistry.registerItem(TFCSItems.blackSteelGear, tfcs.reference.ReferenceName.ITEM_BLACK_STEEL_GEAR_NAME);
		GameRegistry.registerItem(TFCSItems.blueSteelGear, tfcs.reference.ReferenceName.ITEM_BLUE_STEEL_GEAR_NAME);
		GameRegistry.registerItem(TFCSItems.redSteelGear, tfcs.reference.ReferenceName.ITEM_RED_STEEL_GEAR_NAME);

		GameRegistry.registerItem(TFCSItems.woodenAxle, tfcs.reference.ReferenceName.ITEM_WOODEN_AXLE_NAME);
		GameRegistry.registerItem(TFCSItems.stoneAxle, tfcs.reference.ReferenceName.ITEM_STONE_AXLE_NAME);
		GameRegistry.registerItem(TFCSItems.copperAxle, tfcs.reference.ReferenceName.ITEM_COPPER_AXLE_NAME);
		GameRegistry.registerItem(TFCSItems.bronzeAxle, tfcs.reference.ReferenceName.ITEM_BRONZE_AXLE_NAME);
		GameRegistry.registerItem(TFCSItems.blackBronzeAxle, tfcs.reference.ReferenceName.ITEM_BLACK_BRONZE_AXLE_NAME);
		GameRegistry.registerItem(TFCSItems.bismuthBronzeAxle, tfcs.reference.ReferenceName.ITEM_BISMUTH_BRONZE_AXLE_NAME);
		GameRegistry.registerItem(TFCSItems.ironAxle, tfcs.reference.ReferenceName.ITEM_IRON_AXLE_NAME);
		GameRegistry.registerItem(TFCSItems.steelAxle, tfcs.reference.ReferenceName.ITEM_STEEL_AXLE_NAME);
		GameRegistry.registerItem(TFCSItems.blackSteelAxle, tfcs.reference.ReferenceName.ITEM_BLACK_STEEL_AXLE_NAME);
		GameRegistry.registerItem(TFCSItems.blueSteelAxle, tfcs.reference.ReferenceName.ITEM_BLUE_STEEL_AXLE_NAME);
		GameRegistry.registerItem(TFCSItems.redSteelAxle, tfcs.reference.ReferenceName.ITEM_RED_STEEL_AXLE_NAME);

		GameRegistry.registerItem(TFCSItems.copperCoil, tfcs.reference.ReferenceName.ITEM_COIL_NAME + ".copper");
		GameRegistry.registerItem(TFCSItems.bronzeCoil, tfcs.reference.ReferenceName.ITEM_COIL_NAME + ".bronze");
		GameRegistry.registerItem(TFCSItems.blackBronzeCoil, tfcs.reference.ReferenceName.ITEM_COIL_NAME + ".blackBronze");
		GameRegistry.registerItem(TFCSItems.bismuthBronzeCoil, tfcs.reference.ReferenceName.ITEM_COIL_NAME + ".bismuthBronze");
		GameRegistry.registerItem(TFCSItems.ironCoil, tfcs.reference.ReferenceName.ITEM_COIL_NAME + ".iron");
		GameRegistry.registerItem(TFCSItems.steelCoil, tfcs.reference.ReferenceName.ITEM_COIL_NAME + ".steel");
		GameRegistry.registerItem(TFCSItems.blackSteelCoil, tfcs.reference.ReferenceName.ITEM_COIL_NAME + ".blackSteel");
		GameRegistry.registerItem(TFCSItems.blueSteelCoil, tfcs.reference.ReferenceName.ITEM_COIL_NAME + ".blueSteel");
		GameRegistry.registerItem(TFCSItems.redSteelCoil, tfcs.reference.ReferenceName.ITEM_COIL_NAME + ".redSteel");

		GameRegistry.registerItem(TFCSItems.copperSaw, tfcs.reference.ReferenceName.ITEM_SAW_NAME + ".copper");
		GameRegistry.registerItem(TFCSItems.bronzeSaw, tfcs.reference.ReferenceName.ITEM_SAW_NAME + ".bronze");
		GameRegistry.registerItem(TFCSItems.blackBronzeSaw, tfcs.reference.ReferenceName.ITEM_SAW_NAME + ".blackBronze");
		GameRegistry.registerItem(TFCSItems.bismuthBronzeSaw, tfcs.reference.ReferenceName.ITEM_SAW_NAME + ".bismuthBronze");
		GameRegistry.registerItem(TFCSItems.ironSaw, tfcs.reference.ReferenceName.ITEM_SAW_NAME + ".iron");
		GameRegistry.registerItem(TFCSItems.steelSaw, tfcs.reference.ReferenceName.ITEM_SAW_NAME + ".steel");
		GameRegistry.registerItem(TFCSItems.blackSteelSaw, tfcs.reference.ReferenceName.ITEM_SAW_NAME + ".blackSteel");
		GameRegistry.registerItem(TFCSItems.blueSteelSaw, tfcs.reference.ReferenceName.ITEM_SAW_NAME + ".blueSteel");
		GameRegistry.registerItem(TFCSItems.redSteelSaw, tfcs.reference.ReferenceName.ITEM_SAW_NAME + ".redSteel");

		GameRegistry.registerItem(TFCSItems.copperCannonBarrel, tfcs.reference.ReferenceName.ITEM_CANNON_BARREL_NAME + ".copper");
		GameRegistry.registerItem(TFCSItems.bronzeCannonBarrel, tfcs.reference.ReferenceName.ITEM_CANNON_BARREL_NAME + ".bronze");
		GameRegistry.registerItem(TFCSItems.bismuthBronzeCannonBarrel, tfcs.reference.ReferenceName.ITEM_CANNON_BARREL_NAME + ".bismuthBronze");
		GameRegistry.registerItem(TFCSItems.blackBronzeCannonBarrel, tfcs.reference.ReferenceName.ITEM_CANNON_BARREL_NAME + ".blackBronze");
		GameRegistry.registerItem(TFCSItems.ironCannonBarrel, tfcs.reference.ReferenceName.ITEM_CANNON_BARREL_NAME + ".iron");
		GameRegistry.registerItem(TFCSItems.steelCannonBarrel, tfcs.reference.ReferenceName.ITEM_CANNON_BARREL_NAME + ".steel");
		GameRegistry.registerItem(TFCSItems.blackSteelCannonBarrel, tfcs.reference.ReferenceName.ITEM_CANNON_BARREL_NAME + ".blackSteel");
		GameRegistry.registerItem(TFCSItems.blueSteelCannonBarrel, tfcs.reference.ReferenceName.ITEM_CANNON_BARREL_NAME + ".blueSteel");
		GameRegistry.registerItem(TFCSItems.redSteelCannonBarrel, tfcs.reference.ReferenceName.ITEM_CANNON_BARREL_NAME + ".redSteel");

		GameRegistry.registerItem(TFCSItems.windmill, tfcs.reference.ReferenceName.ITEM_WINDMILL_NAME);
		GameRegistry.registerItem(TFCSItems.waterWheel, tfcs.reference.ReferenceName.ITEM_WATER_WHEEL_NAME);
		GameRegistry.registerItem(TFCSItems.handCrank, tfcs.reference.ReferenceName.ITEM_HAND_CRANK_NAME);
		GameRegistry.registerItem(TFCSItems.grindstone, tfcs.reference.ReferenceName.ITEM_GRINDSTONE_NAME);
		GameRegistry.registerItem(TFCSItems.gateMover, tfcs.reference.ReferenceName.ITEM_GATE_MOVER_NAME);

		GameRegistry.registerItem(TFCSItems.wrench, tfcs.reference.ReferenceName.ITEM_WRENCH_NAME);
		GameRegistry.registerItem(TFCSItems.thermometer, tfcs.reference.ReferenceName.ITEM_THERMOMETER_NAME);
		GameRegistry.registerItem(TFCSItems.longBow, tfcs.reference.ReferenceName.ITEM_LONG_BOW_NAME);

		GameRegistry.registerItem(TFCSItems.ceramicShell, tfcs.reference.ReferenceName.ITEM_CERAMIC_SHELL_NAME);
		GameRegistry.registerItem(TFCSItems.longArrow, tfcs.reference.ReferenceName.ITEM_LONG_ARROW_NAME);
		GameRegistry.registerItem(TFCSItems.bullet, tfcs.reference.ReferenceName.ITEM_BULLET_NAME);
		GameRegistry.registerItem(TFCSItems.fireLongArrow, tfcs.reference.ReferenceName.ITEM_LONG_ARROW_NAME + "_fire");
		GameRegistry.registerItem(TFCSItems.cannonBall, tfcs.reference.ReferenceName.ITEM_CANNON_BALL_NAME);

		GameRegistry.registerItem(TFCSItems.woodenShield, tfcs.reference.ReferenceName.ITEM_SHIELD_NAME + "." + ToolMaterial.WOOD.name());
		GameRegistry.registerItem(TFCSItems.copperShield, tfcs.reference.ReferenceName.ITEM_SHIELD_NAME + "." + TFCItems.copperToolMaterial.name());
		GameRegistry.registerItem(TFCSItems.bronzeShield, tfcs.reference.ReferenceName.ITEM_SHIELD_NAME + "." + TFCItems.bronzeToolMaterial.name());
		GameRegistry.registerItem(TFCSItems.bismuthBronzeShield, tfcs.reference.ReferenceName.ITEM_SHIELD_NAME + "." + TFCItems.bismuthBronzeToolMaterial.name());
		GameRegistry.registerItem(TFCSItems.blackBronzeShield, tfcs.reference.ReferenceName.ITEM_SHIELD_NAME + "." + TFCItems.blackBronzeToolMaterial.name());
		GameRegistry.registerItem(TFCSItems.wroughtIronShield, tfcs.reference.ReferenceName.ITEM_SHIELD_NAME + "." + TFCItems.ironToolMaterial);
		GameRegistry.registerItem(TFCSItems.steelShield, tfcs.reference.ReferenceName.ITEM_SHIELD_NAME + "." + TFCItems.steelToolMaterial.name());
		GameRegistry.registerItem(TFCSItems.blackSteelShield, tfcs.reference.ReferenceName.ITEM_SHIELD_NAME + "." + TFCItems.blackSteelToolMaterial.name());
		GameRegistry.registerItem(TFCSItems.redSteelShield, tfcs.reference.ReferenceName.ITEM_SHIELD_NAME + "." + TFCItems.redSteelToolMaterial.name());
		GameRegistry.registerItem(TFCSItems.blueSteelShield, tfcs.reference.ReferenceName.ITEM_SHIELD_NAME + "." + TFCItems.blueSteelToolMaterial.name());

		GameRegistry.registerItem(TFCSItems.copperTemperedBroadsword, tfcs.reference.ReferenceName.ITEM_TEMPERED_BROADSWORD_NAME + "." + TFCItems.copperToolMaterial.name());
		GameRegistry.registerItem(TFCSItems.bronzeTemperedBroadsword, tfcs.reference.ReferenceName.ITEM_TEMPERED_BROADSWORD_NAME + "." + TFCItems.bronzeToolMaterial.name());
		GameRegistry.registerItem(TFCSItems.bismuthBronzeTemperedBroadsword, tfcs.reference.ReferenceName.ITEM_TEMPERED_BROADSWORD_NAME + "." + TFCItems.bismuthBronzeToolMaterial.name());
		GameRegistry.registerItem(TFCSItems.blackBronzeTemperedBroadsword, tfcs.reference.ReferenceName.ITEM_TEMPERED_BROADSWORD_NAME + "." + TFCItems.blackBronzeToolMaterial.name());
		GameRegistry.registerItem(TFCSItems.wroughtIronTemperedBroadsword, tfcs.reference.ReferenceName.ITEM_TEMPERED_BROADSWORD_NAME + "." + TFCItems.ironToolMaterial);
		GameRegistry.registerItem(TFCSItems.steelTemperedBroadsword, tfcs.reference.ReferenceName.ITEM_TEMPERED_BROADSWORD_NAME + "." + TFCItems.steelToolMaterial.name());
		GameRegistry.registerItem(TFCSItems.blackSteelTemperedBroadsword, tfcs.reference.ReferenceName.ITEM_TEMPERED_BROADSWORD_NAME + "." + TFCItems.blackSteelToolMaterial.name());
		GameRegistry.registerItem(TFCSItems.redSteelTemperedBroadsword, tfcs.reference.ReferenceName.ITEM_TEMPERED_BROADSWORD_NAME + "." + TFCItems.redSteelToolMaterial.name());
		GameRegistry.registerItem(TFCSItems.blueSteelTemperedBroadsword, tfcs.reference.ReferenceName.ITEM_TEMPERED_BROADSWORD_NAME + "." + TFCItems.blueSteelToolMaterial.name());

		GameRegistry.registerItem(TFCSItems.copperTemperedPickaxe, tfcs.reference.ReferenceName.ITEM_TEMPERED_PICKAXE_NAME + "." + TFCItems.copperToolMaterial.name());
		GameRegistry.registerItem(TFCSItems.bronzeTemperedPickaxe, tfcs.reference.ReferenceName.ITEM_TEMPERED_PICKAXE_NAME + "." + TFCItems.bronzeToolMaterial.name());
		GameRegistry.registerItem(TFCSItems.bismuthBronzeTemperedPickaxe, tfcs.reference.ReferenceName.ITEM_TEMPERED_PICKAXE_NAME + "." + TFCItems.bismuthBronzeToolMaterial.name());
		GameRegistry.registerItem(TFCSItems.blackBronzeTemperedPickaxe, tfcs.reference.ReferenceName.ITEM_TEMPERED_PICKAXE_NAME + "." + TFCItems.blackBronzeToolMaterial.name());
		GameRegistry.registerItem(TFCSItems.wroughtIronTemperedPickaxe, tfcs.reference.ReferenceName.ITEM_TEMPERED_PICKAXE_NAME + "." + TFCItems.ironToolMaterial);
		GameRegistry.registerItem(TFCSItems.steelTemperedPickaxe, tfcs.reference.ReferenceName.ITEM_TEMPERED_PICKAXE_NAME + "." + TFCItems.steelToolMaterial.name());
		GameRegistry.registerItem(TFCSItems.blackSteelTemperedPickaxe, tfcs.reference.ReferenceName.ITEM_TEMPERED_PICKAXE_NAME + "." + TFCItems.blackSteelToolMaterial.name());
		GameRegistry.registerItem(TFCSItems.redSteelTemperedPickaxe, tfcs.reference.ReferenceName.ITEM_TEMPERED_PICKAXE_NAME + "." + TFCItems.redSteelToolMaterial.name());
		GameRegistry.registerItem(TFCSItems.blueSteelTemperedPickaxe, tfcs.reference.ReferenceName.ITEM_TEMPERED_PICKAXE_NAME + "." + TFCItems.blueSteelToolMaterial.name());

		GameRegistry.registerItem(TFCSItems.copperBolt, tfcs.reference.ReferenceName.ITEM_BOLT_NAME + "." + TFCItems.copperToolMaterial.name());
		GameRegistry.registerItem(TFCSItems.bronzeBolt, tfcs.reference.ReferenceName.ITEM_BOLT_NAME + "." + TFCItems.bronzeToolMaterial.name());
		GameRegistry.registerItem(TFCSItems.bismuthBronzeBolt, tfcs.reference.ReferenceName.ITEM_BOLT_NAME + "." + TFCItems.bismuthBronzeToolMaterial.name());
		GameRegistry.registerItem(TFCSItems.blackBronzeBolt, tfcs.reference.ReferenceName.ITEM_BOLT_NAME + "." + TFCItems.blackBronzeToolMaterial.name());
		GameRegistry.registerItem(TFCSItems.wroughtIronBolt, tfcs.reference.ReferenceName.ITEM_BOLT_NAME + "." + TFCItems.ironToolMaterial);

		GameRegistry.registerItem(TFCSItems.wroughtIronMusket, tfcs.reference.ReferenceName.ITEM_MUSKET_NAME + "." + TFCItems.ironToolMaterial);
		GameRegistry.registerItem(TFCSItems.steelMusket, tfcs.reference.ReferenceName.ITEM_MUSKET_NAME + "." + TFCItems.steelToolMaterial.name());
		GameRegistry.registerItem(TFCSItems.blackSteelMusket, tfcs.reference.ReferenceName.ITEM_MUSKET_NAME + "." + TFCItems.blackSteelToolMaterial.name());
		GameRegistry.registerItem(TFCSItems.redSteelMusket, tfcs.reference.ReferenceName.ITEM_MUSKET_NAME + "." + TFCItems.redSteelToolMaterial.name());
		GameRegistry.registerItem(TFCSItems.blueSteelMusket, tfcs.reference.ReferenceName.ITEM_MUSKET_NAME + "." + TFCItems.blueSteelToolMaterial.name());

		GameRegistry.registerItem(TFCSItems.copperTemperedCrossbow, tfcs.reference.ReferenceName.ITEM_CROSSBOW_NAME + "." + TFCItems.copperToolMaterial.name());
		GameRegistry.registerItem(TFCSItems.bronzeTemperedCrossbow, tfcs.reference.ReferenceName.ITEM_CROSSBOW_NAME + "." + TFCItems.bronzeToolMaterial.name());
		GameRegistry.registerItem(TFCSItems.blackBronzeTemperedCrossbow, tfcs.reference.ReferenceName.ITEM_CROSSBOW_NAME + "." + TFCItems.blackBronzeToolMaterial.name());
		GameRegistry.registerItem(TFCSItems.bismuthBronzeTemperedCrossbow, tfcs.reference.ReferenceName.ITEM_CROSSBOW_NAME + "." + TFCItems.bismuthBronzeToolMaterial.name());
		GameRegistry.registerItem(TFCSItems.wroughtIronTemperedCrossbow, tfcs.reference.ReferenceName.ITEM_CROSSBOW_NAME + "." + TFCItems.ironToolMaterial.name());

		GameRegistry.registerItem(TFCSItems.settler, tfcs.reference.ReferenceName.ITEM_SETTLER_NAME);
		GameRegistry.registerItem(TFCSItems.travelingSalesman, tfcs.reference.ReferenceName.ITEM_TRAVELING_SALESMAN_NAME);
		GameRegistry.registerItem(TFCSItems.rideBear, tfcs.reference.ReferenceName.ITEM_RIDE_BEAR_NAME);

		GameRegistry.registerItem(TFCSItems.mudBrick, tfcs.reference.ReferenceName.ITEM_MUD_BRICK);
		GameRegistry.registerItem(TFCSItems.currency, tfcs.reference.ReferenceName.ITEM_COIN_NAME);
		GameRegistry.registerItem(TFCSItems.battleHorn, tfcs.reference.ReferenceName.ITEM_BATTLE_HORN_NAME);
		GameRegistry.registerItem(TFCSItems.warSaddle, tfcs.reference.ReferenceName.ITEM_WAR_SADDLE_NAME);

		// cbow limbs
		GameRegistry.registerItem(TFCSItems.copperCrossbowLimb, tfcs.reference.ReferenceName.ITEM_CRAFTING_NAME + ".crossbowLimb" + ".copper");
		GameRegistry.registerItem(TFCSItems.bronzeCrossbowLimb, tfcs.reference.ReferenceName.ITEM_CRAFTING_NAME + ".crossbowLimb" + ".bronze");
		GameRegistry.registerItem(TFCSItems.blackBronzeCrossbowLimb, tfcs.reference.ReferenceName.ITEM_CRAFTING_NAME + ".crossbowLimb" + ".blackBronze");
		GameRegistry.registerItem(TFCSItems.bismuthBronzeCrossbowLimb, tfcs.reference.ReferenceName.ITEM_CRAFTING_NAME + ".crossbowLimb" + ".bismuthBronze");
		GameRegistry.registerItem(TFCSItems.wroughtIronCrossbowLimb, tfcs.reference.ReferenceName.ITEM_CRAFTING_NAME + ".crossbowLimb" + ".wroughtIron");
		// cbow bolt heads
		GameRegistry.registerItem(TFCSItems.copperBoltHead, tfcs.reference.ReferenceName.ITEM_CRAFTING_NAME + ".boltHead" + ".copper");
		GameRegistry.registerItem(TFCSItems.bronzeBoltHead, tfcs.reference.ReferenceName.ITEM_CRAFTING_NAME + ".boltHead" + ".bronze");
		GameRegistry.registerItem(TFCSItems.blackBronzeBoltHead, tfcs.reference.ReferenceName.ITEM_CRAFTING_NAME + ".boltHead" + ".blackBronze");
		GameRegistry.registerItem(TFCSItems.bismuthBronzeBoltHead, tfcs.reference.ReferenceName.ITEM_CRAFTING_NAME + ".boltHead" + ".bismuthBronze");
		GameRegistry.registerItem(TFCSItems.wroughtIronBoltHead, tfcs.reference.ReferenceName.ITEM_CRAFTING_NAME + ".boltHead" + ".wroughtIron");

		// cannon barrel pieces
		GameRegistry.registerItem(TFCSItems.copperCannonBarrelPiece, tfcs.reference.ReferenceName.ITEM_CRAFTING_NAME + ".cannonBarrelPiece" + ".copper");
		GameRegistry.registerItem(TFCSItems.bronzeCannonBarrelPiece, tfcs.reference.ReferenceName.ITEM_CRAFTING_NAME + ".cannonBarrelPiece" + ".bronze");
		GameRegistry.registerItem(TFCSItems.blackBronzeCannonBarrelPiece, tfcs.reference.ReferenceName.ITEM_CRAFTING_NAME + ".cannonBarrelPiece" + ".blackBronze");
		GameRegistry.registerItem(TFCSItems.bismuthBronzeCannonBarrelPiece, tfcs.reference.ReferenceName.ITEM_CRAFTING_NAME + ".cannonBarrelPiece" + ".bismuthBronze");
		GameRegistry.registerItem(TFCSItems.wroughtIronCannonBarrelPiece, tfcs.reference.ReferenceName.ITEM_CRAFTING_NAME + ".cannonBarrelPiece" + ".wroughtIron");
		GameRegistry.registerItem(TFCSItems.steelCannonBarrelPiece, tfcs.reference.ReferenceName.ITEM_CRAFTING_NAME + ".cannonBarrelPiece" + ".steel");
		GameRegistry.registerItem(TFCSItems.blackSteelCannonBarrelPiece, tfcs.reference.ReferenceName.ITEM_CRAFTING_NAME + ".cannonBarrelPiece" + ".blackSteel");
		GameRegistry.registerItem(TFCSItems.redSteelCannonBarrelPiece, tfcs.reference.ReferenceName.ITEM_CRAFTING_NAME + ".cannonBarrelPiece" + ".redSteel");
		GameRegistry.registerItem(TFCSItems.blueSteelCannonBarrelPiece, tfcs.reference.ReferenceName.ITEM_CRAFTING_NAME + ".cannonBarrelPiece" + ".blueSteel");

		// tempered pick heads
		GameRegistry.registerItem(TFCSItems.copperTemperedPickHead, tfcs.reference.ReferenceName.ITEM_CRAFTING_NAME + ".temperedPickHead" + ".copper");
		GameRegistry.registerItem(TFCSItems.bronzeTemperedPickHead, tfcs.reference.ReferenceName.ITEM_CRAFTING_NAME + ".temperedPickHead" + ".bronze");
		GameRegistry.registerItem(TFCSItems.blackBronzeTemperedPickHead, tfcs.reference.ReferenceName.ITEM_CRAFTING_NAME + ".temperedPickHead" + ".blackBronze");
		GameRegistry.registerItem(TFCSItems.bismuthBronzeTemperedPickHead, tfcs.reference.ReferenceName.ITEM_CRAFTING_NAME + ".temperedPickHead" + ".bismuthBronze");
		GameRegistry.registerItem(TFCSItems.wroughtIronTemperedPickHead, tfcs.reference.ReferenceName.ITEM_CRAFTING_NAME + ".temperedPickHead" + ".wroughtIron");
		GameRegistry.registerItem(TFCSItems.steelTemperedPickHead, tfcs.reference.ReferenceName.ITEM_CRAFTING_NAME + ".temperedPickHead" + ".steel");
		GameRegistry.registerItem(TFCSItems.blackSteelTemperedPickHead, tfcs.reference.ReferenceName.ITEM_CRAFTING_NAME + ".temperedPickHead" + ".blackSteel");
		GameRegistry.registerItem(TFCSItems.redSteelTemperedPickHead, tfcs.reference.ReferenceName.ITEM_CRAFTING_NAME + ".temperedPickHead" + ".redSteel");
		GameRegistry.registerItem(TFCSItems.blueSteelTemperedPickHead, tfcs.reference.ReferenceName.ITEM_CRAFTING_NAME + ".temperedPickHead" + ".blueSteel");

		// shield bodies
		GameRegistry.registerItem(TFCSItems.copperShieldBody, tfcs.reference.ReferenceName.ITEM_CRAFTING_NAME + ".shieldBody" + ".copper");
		GameRegistry.registerItem(TFCSItems.bronzeShieldBody, tfcs.reference.ReferenceName.ITEM_CRAFTING_NAME + ".shieldBody" + ".bronze");
		GameRegistry.registerItem(TFCSItems.blackBronzeShieldBody, tfcs.reference.ReferenceName.ITEM_CRAFTING_NAME + ".shieldBody" + ".blackBronze");
		GameRegistry.registerItem(TFCSItems.bismuthBronzeShieldBody, tfcs.reference.ReferenceName.ITEM_CRAFTING_NAME + ".shieldBody" + ".bismuthBronze");
		GameRegistry.registerItem(TFCSItems.wroughtIronShieldBody, tfcs.reference.ReferenceName.ITEM_CRAFTING_NAME + ".shieldBody" + ".wroughtIron");
		GameRegistry.registerItem(TFCSItems.steelShieldBody, tfcs.reference.ReferenceName.ITEM_CRAFTING_NAME + ".shieldBody" + ".steel");
		GameRegistry.registerItem(TFCSItems.blackSteelShieldBody, tfcs.reference.ReferenceName.ITEM_CRAFTING_NAME + ".shieldBody" + ".blackSteel");
		GameRegistry.registerItem(TFCSItems.redSteelShieldBody, tfcs.reference.ReferenceName.ITEM_CRAFTING_NAME + ".shieldBody" + ".redSteel");
		GameRegistry.registerItem(TFCSItems.blueSteelShieldBody, tfcs.reference.ReferenceName.ITEM_CRAFTING_NAME + ".shieldBody" + ".blueSteel");

		// tempered sword blade
		GameRegistry.registerItem(TFCSItems.copperTemperedSwordBlade, tfcs.reference.ReferenceName.ITEM_CRAFTING_NAME + ".temperedSwordBlade" + ".copper");
		GameRegistry.registerItem(TFCSItems.bronzeTemperedSwordBlade, tfcs.reference.ReferenceName.ITEM_CRAFTING_NAME + ".temperedSwordBlade" + ".bronze");
		GameRegistry.registerItem(TFCSItems.blackBronzeTemperedSwordBlade, tfcs.reference.ReferenceName.ITEM_CRAFTING_NAME + ".temperedSwordBlade" + ".blackBronze");
		GameRegistry.registerItem(TFCSItems.bismuthBronzeTemperedSwordBlade, tfcs.reference.ReferenceName.ITEM_CRAFTING_NAME + ".temperedSwordBlade" + ".bismuthBronze");
		GameRegistry.registerItem(TFCSItems.wroughtIronTemperedSwordBlade, tfcs.reference.ReferenceName.ITEM_CRAFTING_NAME + ".temperedSwordBlade" + ".wroughtIron");
		GameRegistry.registerItem(TFCSItems.steelTemperedSwordBlade, tfcs.reference.ReferenceName.ITEM_CRAFTING_NAME + ".temperedSwordBlade" + ".steel");
		GameRegistry.registerItem(TFCSItems.blackSteelTemperedSwordBlade, tfcs.reference.ReferenceName.ITEM_CRAFTING_NAME + ".temperedSwordBlade" + ".blackSteel");
		GameRegistry.registerItem(TFCSItems.redSteelTemperedSwordBlade, tfcs.reference.ReferenceName.ITEM_CRAFTING_NAME + ".temperedSwordBlade" + ".redSteel");
		GameRegistry.registerItem(TFCSItems.blueSteelTemperedSwordBlade, tfcs.reference.ReferenceName.ITEM_CRAFTING_NAME + ".temperedSwordBlade" + ".blueSteel");
	}

	public static void registerFrameComponents() {
		GearRegistry.instance.addFrameComponent(new WoodenAxleComponent());
		GearRegistry.instance.addFrameComponent(new StoneAxleComponent());
		GearRegistry.instance.addFrameComponent(new CopperAxleComponent());
		GearRegistry.instance.addFrameComponent(new BismuthBronzeAxleComponent());
		GearRegistry.instance.addFrameComponent(new BronzeAxleComponent());
		GearRegistry.instance.addFrameComponent(new BlackBronzeAxleComponent());
		GearRegistry.instance.addFrameComponent(new IronAxleComponent());
		GearRegistry.instance.addFrameComponent(new SteelAxleComponent());
		GearRegistry.instance.addFrameComponent(new BlackSteelAxleComponent());
		GearRegistry.instance.addFrameComponent(new RedSteelAxleComponent());
		GearRegistry.instance.addFrameComponent(new BlueSteelAxleComponent());

		for (int i = 1; i <= 6; i++) {
			GearRegistry.instance.addFrameComponent(new WoodenGearComponent().setSize(i));
			GearRegistry.instance.addFrameComponent(new StoneGearComponent().setSize(i));
			GearRegistry.instance.addFrameComponent(new CopperGearComponent().setSize(i));
			GearRegistry.instance.addFrameComponent(new BismuthBronzeGearComponent().setSize(i));
			GearRegistry.instance.addFrameComponent(new BronzeGearComponent().setSize(i));
			GearRegistry.instance.addFrameComponent(new BlackBronzeGearComponent().setSize(i));
			GearRegistry.instance.addFrameComponent(new IronGearComponent().setSize(i));
			GearRegistry.instance.addFrameComponent(new SteelGearComponent().setSize(i));
			GearRegistry.instance.addFrameComponent(new BlackSteelGearComponent().setSize(i));
			GearRegistry.instance.addFrameComponent(new RedSteelGearComponent().setSize(i));
			GearRegistry.instance.addFrameComponent(new BlueSteelGearComponent().setSize(i));

			GearRegistry.instance.addFrameComponent(new WindmillToolComponent().setSize(i));
			GearRegistry.instance.addFrameComponent(new WaterWheelToolComponent().setSize(i));
		}

		GearRegistry.instance.addFrameComponent(new GateMoverCompenent());

		GearRegistry.instance.addFrameComponent(new CopperCoilCompenent());
		GearRegistry.instance.addFrameComponent(new BismuthBronzeCoilCompenent());
		GearRegistry.instance.addFrameComponent(new BronzeCoilCompenent());
		GearRegistry.instance.addFrameComponent(new BlackBronzeCoilCompenent());
		GearRegistry.instance.addFrameComponent(new IronCoilCompenent());
		GearRegistry.instance.addFrameComponent(new SteelCoilCompenent());
		GearRegistry.instance.addFrameComponent(new BlackSteelCoilCompenent());
		GearRegistry.instance.addFrameComponent(new RedSteelCoilCompenent());
		GearRegistry.instance.addFrameComponent(new BlueSteelCoilCompenent());

		GearRegistry.instance.addFrameComponent(new CopperSawCompenent());
		GearRegistry.instance.addFrameComponent(new BismuthBronzeSawCompenent());
		GearRegistry.instance.addFrameComponent(new BronzeSawCompenent());
		GearRegistry.instance.addFrameComponent(new BlackBronzeSawCompenent());
		GearRegistry.instance.addFrameComponent(new IronSawCompenent());
		GearRegistry.instance.addFrameComponent(new SteelSawCompenent());
		GearRegistry.instance.addFrameComponent(new BlackSteelSawCompenent());
		GearRegistry.instance.addFrameComponent(new RedSteelSawCompenent());
		GearRegistry.instance.addFrameComponent(new BlueSteelSawCompenent());

		GearRegistry.instance.addFrameComponent(new CopperCannonBarrelCompenent());
		GearRegistry.instance.addFrameComponent(new BismuthBronzeCannonBarrelCompenent());
		GearRegistry.instance.addFrameComponent(new BronzeCannonBarrelCompenent());
		GearRegistry.instance.addFrameComponent(new BlackBronzeCannonBarrelCompenent());
		GearRegistry.instance.addFrameComponent(new IronCannonBarrelCompenent());
		GearRegistry.instance.addFrameComponent(new SteelCannonBarrelCompenent());
		GearRegistry.instance.addFrameComponent(new BlackSteelCannonBarrelCompenent());
		GearRegistry.instance.addFrameComponent(new RedSteelCannonBarrelCompenent());
		GearRegistry.instance.addFrameComponent(new BlueSteelCannonBarrelCompenent());

		GearRegistry.instance.addFrameComponent(new GrindstoneToolComponent());
		GearRegistry.instance.addFrameComponent(new CoilToolComponent());
		GearRegistry.instance.addFrameComponent(new BenSmells());
		GearRegistry.instance.addFrameComponent(new HandCrankToolComponent());
		GearRegistry.instance.addFrameComponent(new SawToolComponent());
	}

	public static void registerItemValues() {
		// coins
		ValueRegistry.instance.registerItemValue(TFCSItems.currency, 0, 1);
		ValueRegistry.instance.registerItemValue(TFCSItems.currency, 1, 2);
		ValueRegistry.instance.registerItemValue(TFCSItems.currency, 2, 5);
		ValueRegistry.instance.registerItemValue(TFCSItems.currency, 3, 10);
		ValueRegistry.instance.registerItemValue(TFCSItems.currency, 4, 50);
		ValueRegistry.instance.registerItemValue(TFCSItems.currency, 5, 100);
		ValueRegistry.instance.registerItemValue(TFCSItems.currency, 6, 200);
		ValueRegistry.instance.registerItemValue(TFCSItems.currency, 7, 500);

		// other
		ValueRegistry.instance.registerItemValue(Items.gunpowder, 0, 2);
		ValueRegistry.instance.registerItemValue(TFCSItems.cannonBall, 0, 20);
		ValueRegistry.instance.registerItemValue(TFCSItems.bullet, 0, 4);
		ValueRegistry.instance.registerItemValue(TFCSItems.steelTemperedBroadsword, 0, 1000);
		ValueRegistry.instance.registerItemValue(TFCSItems.copperCannonBarrel, 0, 2000);
		ValueRegistry.instance.registerItemValue(TFCItems.powder, 2, 5);
	}

	public static void registerFuels() {
		IndustrialFuelManager.instance.addFuelToRegistry(TFCItems.coal, 0, 1400, 2200);
		IndustrialFuelManager.instance.addFuelToRegistry(TFCItems.coal, 1, 1350, 1800);
		IndustrialFuelManager.instance.addFuelToRegistry(Item.getItemFromBlock(TFCBlocks.peat), 0, 680, 2500);
	}
}

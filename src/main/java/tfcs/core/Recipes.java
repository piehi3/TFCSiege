package tfcs.core;

import com.bioxx.tfc.api.TFCBlocks;
import com.bioxx.tfc.api.TFCFluids;
import com.bioxx.tfc.api.TFCItems;
import com.bioxx.tfc.api.Constant.Global;
import com.bioxx.tfc.api.Crafting.AnvilManager;
import com.bioxx.tfc.api.Crafting.AnvilRecipe;
import com.bioxx.tfc.api.Crafting.BarrelManager;
import com.bioxx.tfc.api.Crafting.BarrelRecipe;
import com.bioxx.tfc.api.Crafting.CraftingManagerTFC;
import com.bioxx.tfc.api.Crafting.PlanRecipe;
import com.bioxx.tfc.api.Enums.RuleEnum;
import com.bioxx.tfc.api.Crafting.AnvilReq;

import cpw.mods.fml.common.registry.GameRegistry;
import tfcs.reference.Reference;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.OreDictionary;

public class Recipes {
	public static final int WILDCARD_VALUE = OreDictionary.WILDCARD_VALUE;

	private static AnvilManager anvilManager = AnvilManager.getInstance();
	private static BarrelManager barrelManager = BarrelManager.getInstance();
	private static CraftingManagerTFC craftingManager = CraftingManagerTFC.getInstance();

	// private static KilnCraftingManager kilnCraftingManager =
	// KilnCraftingManager.getInstance();
	// private static QuernManager quernManager = QuernManager.getInstance();

	// Plans

	public static void initialise() {
		System.out.println("[" + Reference.ModName + "] Registering Recipes");

		registerRecipes();

		System.out.println("[" + Reference.ModName + "] Done Registering Recipes");
	}

	public static void initialiseAnvil() {
		// check if the plans/recipes have already been initialised.
		if (Recipes.areAnvilRecipesInitialised())
			return;

		System.out.println("[" + Reference.ModName + "] Registering Anvil Recipes");
		registerAnvilPlans();
		registerAnvilRecipes();
		System.out.println("[" + Reference.ModName + "] Done Registering Anvil Recipes");
	}

	public static boolean areAnvilRecipesInitialised() {
		return false;
	}

	private static void registerAnvilPlans() {
		// AnvilManager manager = AnvilManager.getInstance();
		// AnvilManager.world = world;

		anvilManager.addPlan("coin", new PlanRecipe(new RuleEnum[] { RuleEnum.BENDLAST, RuleEnum.BENDANY, RuleEnum.DRAWNOTLAST }));
		anvilManager.addPlan("crossbowLimb", new PlanRecipe(new RuleEnum[] { RuleEnum.BENDLAST, RuleEnum.BENDANY, RuleEnum.DRAWANY }));
		anvilManager.addPlan("boltHead", new PlanRecipe(new RuleEnum[] { RuleEnum.DRAWANY, RuleEnum.DRAWANY, RuleEnum.BENDLAST }));
		anvilManager.addPlan("temperedPickHead", new PlanRecipe(new RuleEnum[] { RuleEnum.SHRINKANY, RuleEnum.HITANY, RuleEnum.PUNCHANY }));
		anvilManager.addPlan("gear", new PlanRecipe(new RuleEnum[] { RuleEnum.UPSETNOTLAST, RuleEnum.HITANY, RuleEnum.PUNCHANY }));
		anvilManager.addPlan("axle", new PlanRecipe(new RuleEnum[] { RuleEnum.UPSETNOTLAST, RuleEnum.HITANY, RuleEnum.DRAWANY }));
		anvilManager.addPlan("cannonBarrel", new PlanRecipe(new RuleEnum[] { RuleEnum.UPSETNOTLAST, RuleEnum.DRAWANY, RuleEnum.PUNCHANY }));
		anvilManager.addPlan("cannonBarrelPiece", new PlanRecipe(new RuleEnum[] { RuleEnum.DRAWANY, RuleEnum.DRAWANY, RuleEnum.SHRINKANY }));
		anvilManager.addPlan("coils", new PlanRecipe(new RuleEnum[] { RuleEnum.DRAWANY, RuleEnum.BENDLAST, RuleEnum.BENDANY }));
		anvilManager.addPlan("saws", new PlanRecipe(new RuleEnum[] { RuleEnum.HITLAST, RuleEnum.BENDANY, RuleEnum.PUNCHANY }));
		anvilManager.addPlan("wrenches", new PlanRecipe(new RuleEnum[] { RuleEnum.BENDTHIRDFROMLAST, RuleEnum.PUNCHSECONDFROMLAST, RuleEnum.UPSETLAST }));
		anvilManager.addPlan("bullets", new PlanRecipe(new RuleEnum[] { RuleEnum.SHRINKANY, RuleEnum.SHRINKANY, RuleEnum.PUNCHLAST }));
		anvilManager.addPlan("cannonBalls", new PlanRecipe(new RuleEnum[] { RuleEnum.HITANY, RuleEnum.HITANY, RuleEnum.HITANY }));
		anvilManager.addPlan("Shields", new PlanRecipe(new RuleEnum[] { RuleEnum.DRAWANY, RuleEnum.DRAWANY, RuleEnum.HITANY }));
		anvilManager.addPlan("temperedSwordHead", new PlanRecipe(new RuleEnum[] { RuleEnum.DRAWANY, RuleEnum.HITANY, RuleEnum.HITANY }));
		anvilManager.addPlan("gates", new PlanRecipe(new RuleEnum[] { RuleEnum.DRAWANY, RuleEnum.DRAWANY, RuleEnum.SHRINKANY }));

	}

	private static void registerAnvilRecipes() {
		// cbow limbs
		anvilManager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.bismuthBronzeIngot), null, "crossbowLimb", AnvilReq.BISMUTHBRONZE, new ItemStack(TFCSItems.bismuthBronzeCrossbowLimb, 1))
				.addRecipeSkill(Global.SKILL_TOOLSMITH));
		anvilManager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.blackBronzeIngot), null, "crossbowLimb", AnvilReq.BLACKBRONZE, new ItemStack(TFCSItems.blackBronzeCrossbowLimb, 1))
				.addRecipeSkill(Global.SKILL_TOOLSMITH));
		anvilManager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.bronzeIngot), null, "crossbowLimb", AnvilReq.BRONZE, new ItemStack(TFCSItems.bronzeCrossbowLimb, 1))
				.addRecipeSkill(Global.SKILL_TOOLSMITH));
		anvilManager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.copperIngot), null, "crossbowLimb", AnvilReq.COPPER, new ItemStack(TFCSItems.copperCrossbowLimb, 1))
				.addRecipeSkill(Global.SKILL_TOOLSMITH));
		anvilManager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.wroughtIronIngot), null, "crossbowLimb", AnvilReq.WROUGHTIRON, new ItemStack(TFCSItems.wroughtIronCrossbowLimb, 1))
				.addRecipeSkill(Global.SKILL_TOOLSMITH));
		// bolt heads
		anvilManager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.bismuthBronzeIngot), null, "boltHead", AnvilReq.BISMUTHBRONZE, new ItemStack(TFCSItems.bismuthBronzeBoltHead, 4))
				.addRecipeSkill(Global.SKILL_TOOLSMITH));
		anvilManager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.blackBronzeIngot), null, "boltHead", AnvilReq.BLACKBRONZE, new ItemStack(TFCSItems.blackBronzeBoltHead, 4))
				.addRecipeSkill(Global.SKILL_TOOLSMITH));
		anvilManager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.bronzeIngot), null, "boltHead", AnvilReq.BRONZE, new ItemStack(TFCSItems.bronzeBoltHead, 4))
				.addRecipeSkill(Global.SKILL_TOOLSMITH));
		anvilManager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.copperIngot), null, "boltHead", AnvilReq.COPPER, new ItemStack(TFCSItems.copperBoltHead, 4))
				.addRecipeSkill(Global.SKILL_TOOLSMITH));
		anvilManager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.wroughtIronIngot), null, "boltHead", AnvilReq.WROUGHTIRON, new ItemStack(TFCSItems.wroughtIronBoltHead, 4))
				.addRecipeSkill(Global.SKILL_TOOLSMITH));
		// tempered pick heads
		anvilManager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.copperIngot), new ItemStack(TFCItems.copperPickaxeHead), "temperedPickHead", AnvilReq.COPPER, new ItemStack(
				TFCSItems.copperTemperedPickHead, 1)).addRecipeSkill(Global.SKILL_TOOLSMITH));
		anvilManager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.blackBronzeIngot), new ItemStack(TFCItems.copperPickaxeHead), "temperedPickHead", AnvilReq.BLACKBRONZE, new ItemStack(
				TFCSItems.blackBronzeTemperedPickHead, 1)).addRecipeSkill(Global.SKILL_TOOLSMITH));
		anvilManager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.bronzeIngot), new ItemStack(TFCItems.blackBronzePickaxeHead), "temperedPickHead", AnvilReq.BRONZE, new ItemStack(
				TFCSItems.bronzeTemperedPickHead, 1)).addRecipeSkill(Global.SKILL_TOOLSMITH));
		anvilManager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.bismuthBronzeIngot), new ItemStack(TFCItems.bismuthBronzePickaxeHead), "temperedPickHead", AnvilReq.BISMUTHBRONZE, new ItemStack(
				TFCSItems.bismuthBronzeTemperedPickHead, 1)).addRecipeSkill(Global.SKILL_TOOLSMITH));
		anvilManager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.wroughtIronIngot), new ItemStack(TFCItems.bronzePickaxeHead), "temperedPickHead", AnvilReq.WROUGHTIRON, new ItemStack(
				TFCSItems.wroughtIronTemperedPickHead, 1)).addRecipeSkill(Global.SKILL_TOOLSMITH));
		anvilManager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.steelIngot), new ItemStack(TFCItems.wroughtIronPickaxeHead), "temperedPickHead", AnvilReq.STEEL, new ItemStack(
				TFCSItems.steelTemperedPickHead, 1)).addRecipeSkill(Global.SKILL_TOOLSMITH));
		anvilManager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.blackSteelIngot), new ItemStack(TFCItems.blackSteelPickaxeHead), "temperedPickHead", AnvilReq.BLACKSTEEL, new ItemStack(
				TFCSItems.blackSteelTemperedPickHead, 1)).addRecipeSkill(Global.SKILL_TOOLSMITH));
		anvilManager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.redSteelIngot), new ItemStack(TFCItems.redSteelPickaxeHead), "temperedPickHead", AnvilReq.REDSTEEL, new ItemStack(
				TFCSItems.redSteelTemperedPickHead, 1)).addRecipeSkill(Global.SKILL_TOOLSMITH));
		anvilManager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.blueSteelIngot), new ItemStack(TFCItems.blueSteelPickaxeHead), "temperedPickHead", AnvilReq.BLUESTEEL, new ItemStack(
				TFCSItems.blueSteelTemperedPickHead, 1)).addRecipeSkill(Global.SKILL_TOOLSMITH));
		
		// tempered coins
		anvilManager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.copperSheet),null, "coin", AnvilReq.COPPER, new ItemStack(
				TFCSItems.currency,8, 0)).addRecipeSkill(Global.SKILL_TOOLSMITH));
		anvilManager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.brassSheet),null, "coin", AnvilReq.BRONZE, new ItemStack(
				TFCSItems.currency,8, 1)).addRecipeSkill(Global.SKILL_TOOLSMITH));
		anvilManager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.nickelSheet),null, "coin", AnvilReq.BRONZE, new ItemStack(
				TFCSItems.currency,8, 2)).addRecipeSkill(Global.SKILL_TOOLSMITH));
		anvilManager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.sterlingSilverSheet),null, "coin", AnvilReq.BRONZE, new ItemStack(
				TFCSItems.currency,8, 3)).addRecipeSkill(Global.SKILL_TOOLSMITH));
		anvilManager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.silverSheet),null, "coin", AnvilReq.BRONZE, new ItemStack(
				TFCSItems.currency,8, 4)).addRecipeSkill(Global.SKILL_TOOLSMITH));
		anvilManager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.roseGoldSheet),null, "coin", AnvilReq.BRONZE, new ItemStack(
				TFCSItems.currency,8, 5)).addRecipeSkill(Global.SKILL_TOOLSMITH));
		anvilManager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.goldSheet),null, "coin", AnvilReq.BRONZE, new ItemStack(
				TFCSItems.currency,8, 6)).addRecipeSkill(Global.SKILL_TOOLSMITH));
		anvilManager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.platinumSheet),null, "coin", AnvilReq.BRONZE, new ItemStack(
				TFCSItems.currency,8, 7)).addRecipeSkill(Global.SKILL_TOOLSMITH));
		// size 1
		anvilManager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.copperIngot2x), null, "gear", AnvilReq.COPPER, new ItemStack(TFCSItems.copperGear, 1, 0)).addRecipeSkill(Global.SKILL_TOOLSMITH));
		anvilManager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.blackBronzeIngot2x), null, "gear", AnvilReq.BLACKBRONZE, new ItemStack(TFCSItems.blackBronzeGear, 1, 0))
				.addRecipeSkill(Global.SKILL_TOOLSMITH));
		anvilManager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.bronzeIngot2x), null, "gear", AnvilReq.BRONZE, new ItemStack(TFCSItems.bronzeGear, 1, 0)).addRecipeSkill(Global.SKILL_TOOLSMITH));
		anvilManager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.bismuthBronzeIngot2x), null, "gear", AnvilReq.BISMUTHBRONZE, new ItemStack(TFCSItems.bismuthBronzeGear, 1, 0))
				.addRecipeSkill(Global.SKILL_TOOLSMITH));
		anvilManager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.wroughtIronIngot2x), null, "gear", AnvilReq.WROUGHTIRON, new ItemStack(TFCSItems.ironGear, 1, 0))
				.addRecipeSkill(Global.SKILL_TOOLSMITH));
		anvilManager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.steelIngot2x), null, "gear", AnvilReq.STEEL, new ItemStack(TFCSItems.blackBronzeGear, 1, 0))
				.addRecipeSkill(Global.SKILL_TOOLSMITH));
		anvilManager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.blackSteelIngot2x), null, "gear", AnvilReq.BLACKSTEEL, new ItemStack(TFCSItems.bronzeGear, 1, 0))
				.addRecipeSkill(Global.SKILL_TOOLSMITH));
		anvilManager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.redSteelIngot2x), null, "gear", AnvilReq.REDSTEEL, new ItemStack(TFCSItems.ironGear, 1, 0))
				.addRecipeSkill(Global.SKILL_TOOLSMITH));
		anvilManager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.blueSteelIngot2x), null, "gear", AnvilReq.BLUESTEEL, new ItemStack(TFCSItems.blueSteelGear, 1, 0))
				.addRecipeSkill(Global.SKILL_TOOLSMITH));

		// size 2
		anvilManager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.copperIngot), new ItemStack(TFCSItems.copperGear, 1, 0), "Gear", AnvilReq.COPPER, new ItemStack(TFCSItems.copperGear, 1, 1))
				.addRecipeSkill(Global.SKILL_TOOLSMITH));
		anvilManager.addRecipe(new AnvilRecipe(new ItemStack(TFCSItems.blackBronzeGear, 1, 0), new ItemStack(TFCItems.blackBronzeIngot), "Gear", AnvilReq.BLACKBRONZE, new ItemStack(
				TFCSItems.blackBronzeGear, 1, 1)).addRecipeSkill(Global.SKILL_TOOLSMITH));
		anvilManager.addRecipe(new AnvilRecipe(new ItemStack(TFCSItems.bronzeGear, 1, 0), new ItemStack(TFCItems.bronzeIngot), "Gear", AnvilReq.BRONZE, new ItemStack(TFCSItems.bronzeGear, 1, 1))
				.addRecipeSkill(Global.SKILL_TOOLSMITH));
		anvilManager.addRecipe(new AnvilRecipe(new ItemStack(TFCSItems.bismuthBronzeGear, 1, 0), new ItemStack(TFCItems.bismuthBronzeIngot), "Gear", AnvilReq.BISMUTHBRONZE, new ItemStack(
				TFCSItems.bismuthBronzeGear, 1, 1)).addRecipeSkill(Global.SKILL_TOOLSMITH));
		anvilManager
				.addRecipe(new AnvilRecipe(new ItemStack(TFCSItems.ironGear, 1, 0), new ItemStack(TFCItems.wroughtIronIngot), "Gear", AnvilReq.WROUGHTIRON, new ItemStack(TFCSItems.ironGear, 1, 1))
						.addRecipeSkill(Global.SKILL_TOOLSMITH));
		anvilManager.addRecipe(new AnvilRecipe(new ItemStack(TFCSItems.steelGear, 1, 0), new ItemStack(TFCItems.steelIngot), "Gear", AnvilReq.STEEL, new ItemStack(TFCSItems.steelGear, 1, 1))
				.addRecipeSkill(Global.SKILL_TOOLSMITH));
		anvilManager.addRecipe(new AnvilRecipe(new ItemStack(TFCSItems.blackSteelGear, 1, 0), new ItemStack(TFCItems.blackSteelIngot), "Gear", AnvilReq.BLACKSTEEL, new ItemStack(
				TFCSItems.blackSteelGear, 1, 1)).addRecipeSkill(Global.SKILL_TOOLSMITH));
		anvilManager.addRecipe(new AnvilRecipe(new ItemStack(TFCSItems.redSteelGear, 1, 0), new ItemStack(TFCItems.redSteelIngot), "Gear", AnvilReq.REDSTEEL, new ItemStack(TFCSItems.redSteelGear, 1,
				1)).addRecipeSkill(Global.SKILL_TOOLSMITH));
		anvilManager.addRecipe(new AnvilRecipe(new ItemStack(TFCSItems.blueSteelGear, 1, 0), new ItemStack(TFCItems.blueSteelIngot), "Gear", AnvilReq.BLUESTEEL, new ItemStack(TFCSItems.blueSteelGear,
				1, 1)).addRecipeSkill(Global.SKILL_TOOLSMITH));
		// size 3
		// switch
		anvilManager.addRecipe(new AnvilRecipe(new ItemStack(TFCSItems.copperGear, 1, 1), new ItemStack(TFCItems.copperIngot2x), "Gear", AnvilReq.COPPER, new ItemStack(TFCSItems.copperGear, 1, 2))
				.addRecipeSkill(Global.SKILL_TOOLSMITH));
		anvilManager.addRecipe(new AnvilRecipe(new ItemStack(TFCSItems.blackBronzeGear, 1, 1), new ItemStack(TFCItems.blackBronzeIngot2x), "Gear", AnvilReq.BLACKBRONZE, new ItemStack(
				TFCSItems.blackBronzeGear, 1, 2)).addRecipeSkill(Global.SKILL_TOOLSMITH));
		anvilManager.addRecipe(new AnvilRecipe(new ItemStack(TFCSItems.bronzeGear, 1, 1), new ItemStack(TFCItems.bronzeIngot2x), "Gear", AnvilReq.BRONZE, new ItemStack(TFCSItems.bronzeGear, 1, 2))
				.addRecipeSkill(Global.SKILL_TOOLSMITH));
		anvilManager.addRecipe(new AnvilRecipe(new ItemStack(TFCSItems.bismuthBronzeGear, 1, 1), new ItemStack(TFCItems.bismuthBronzeIngot2x), "Gear", AnvilReq.BISMUTHBRONZE, new ItemStack(
				TFCSItems.bismuthBronzeGear, 1, 2)).addRecipeSkill(Global.SKILL_TOOLSMITH));
		anvilManager.addRecipe(new AnvilRecipe(new ItemStack(TFCSItems.ironGear, 1, 1), new ItemStack(TFCItems.wroughtIronIngot2x), "Gear", AnvilReq.WROUGHTIRON, new ItemStack(TFCSItems.ironGear, 1,
				2)).addRecipeSkill(Global.SKILL_TOOLSMITH));
		anvilManager.addRecipe(new AnvilRecipe(new ItemStack(TFCSItems.steelGear, 1, 1), new ItemStack(TFCItems.steelIngot2x), "Gear", AnvilReq.STEEL, new ItemStack(TFCSItems.steelGear, 1, 2))
				.addRecipeSkill(Global.SKILL_TOOLSMITH));
		anvilManager.addRecipe(new AnvilRecipe(new ItemStack(TFCSItems.blackSteelGear, 1, 1), new ItemStack(TFCItems.blackSteelIngot2x), "Gear", AnvilReq.BLACKSTEEL, new ItemStack(
				TFCSItems.blackSteelGear, 1, 2)).addRecipeSkill(Global.SKILL_TOOLSMITH));
		anvilManager.addRecipe(new AnvilRecipe(new ItemStack(TFCSItems.redSteelGear, 1, 1), new ItemStack(TFCItems.redSteelIngot2x), "Gear", AnvilReq.REDSTEEL, new ItemStack(TFCSItems.redSteelGear,
				1, 2)).addRecipeSkill(Global.SKILL_TOOLSMITH));
		anvilManager.addRecipe(new AnvilRecipe(new ItemStack(TFCSItems.blueSteelGear, 1, 1), new ItemStack(TFCItems.blueSteelIngot2x), "Gear", AnvilReq.BLUESTEEL, new ItemStack(
				TFCSItems.blueSteelGear, 1, 2)).addRecipeSkill(Global.SKILL_TOOLSMITH));

		// axles
		anvilManager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.copperIngot), new ItemStack(TFCItems.copperIngot), "axle", AnvilReq.COPPER, new ItemStack(TFCSItems.copperAxle))
				.addRecipeSkill(Global.SKILL_TOOLSMITH));
		anvilManager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.blackBronzeIngot), new ItemStack(TFCItems.blackBronzeIngot), "axle", AnvilReq.BLACKBRONZE, new ItemStack(
				TFCSItems.blackBronzeAxle)).addRecipeSkill(Global.SKILL_TOOLSMITH));
		anvilManager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.bronzeIngot), new ItemStack(TFCItems.bronzeIngot), "axle", AnvilReq.BRONZE, new ItemStack(TFCSItems.bronzeAxle))
				.addRecipeSkill(Global.SKILL_TOOLSMITH));
		anvilManager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.bismuthBronzeIngot), new ItemStack(TFCItems.bismuthBronzeIngot), "axle", AnvilReq.BISMUTHBRONZE, new ItemStack(
				TFCSItems.bismuthBronzeGear)).addRecipeSkill(Global.SKILL_TOOLSMITH));
		anvilManager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.wroughtIronIngot), new ItemStack(TFCItems.wroughtIronIngot), "axle", AnvilReq.WROUGHTIRON, new ItemStack(TFCSItems.ironAxle))
				.addRecipeSkill(Global.SKILL_TOOLSMITH));
		anvilManager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.steelIngot), new ItemStack(TFCItems.steelIngot), "axle", AnvilReq.STEEL, new ItemStack(TFCSItems.steelAxle))
				.addRecipeSkill(Global.SKILL_TOOLSMITH));
		anvilManager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.blackSteelIngot), new ItemStack(TFCItems.blackSteelIngot), "axle", AnvilReq.BLACKSTEEL, new ItemStack(TFCSItems.blackSteelAxle))
				.addRecipeSkill(Global.SKILL_TOOLSMITH));
		anvilManager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.redSteelIngot), new ItemStack(TFCItems.redSteelIngot), "axle", AnvilReq.REDSTEEL, new ItemStack(TFCSItems.redSteelAxle))
				.addRecipeSkill(Global.SKILL_TOOLSMITH));
		anvilManager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.blueSteelIngot), new ItemStack(TFCItems.blueSteelIngot), "axle", AnvilReq.BLUESTEEL, new ItemStack(TFCSItems.blueSteelAxle))
				.addRecipeSkill(Global.SKILL_TOOLSMITH));

		// cannon parts + full cannon
		// barrel
		anvilManager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.copperSheet2x), new ItemStack(TFCSItems.copperCannonBarrelPiece), "cannonBarrel", AnvilReq.COPPER, new ItemStack(
				TFCSItems.copperCannonBarrel)).addRecipeSkill(Global.SKILL_TOOLSMITH));
		anvilManager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.blackBronzeSheet2x), new ItemStack(TFCSItems.blackBronzeCannonBarrelPiece), "cannonBarrel", AnvilReq.BLACKBRONZE, new ItemStack(
				TFCSItems.blackBronzeCannonBarrel)).addRecipeSkill(Global.SKILL_TOOLSMITH));
		anvilManager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.bronzeSheet2x), new ItemStack(TFCSItems.bronzeCannonBarrelPiece), "cannonBarrel", AnvilReq.BRONZE, new ItemStack(
				TFCSItems.bronzeCannonBarrel)).addRecipeSkill(Global.SKILL_TOOLSMITH));
		anvilManager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.bismuthBronzeSheet2x), new ItemStack(TFCSItems.bismuthBronzeCannonBarrelPiece), "cannonBarrel", AnvilReq.BISMUTHBRONZE,
				new ItemStack(TFCSItems.bismuthBronzeCannonBarrel)).addRecipeSkill(Global.SKILL_TOOLSMITH));
		anvilManager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.wroughtIronSheet2x), new ItemStack(TFCSItems.wroughtIronCannonBarrelPiece), "cannonBarrel", AnvilReq.WROUGHTIRON, new ItemStack(
				TFCSItems.ironCannonBarrel)).addRecipeSkill(Global.SKILL_TOOLSMITH));
		anvilManager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.steelSheet2x), new ItemStack(TFCSItems.steelCannonBarrelPiece), "cannonBarrel", AnvilReq.STEEL, new ItemStack(
				TFCSItems.steelCannonBarrel)).addRecipeSkill(Global.SKILL_TOOLSMITH));
		anvilManager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.blackSteelSheet2x), new ItemStack(TFCSItems.blackSteelCannonBarrelPiece), "cannonBarrel", AnvilReq.BLACKSTEEL, new ItemStack(
				TFCSItems.blackSteelCannonBarrel)).addRecipeSkill(Global.SKILL_TOOLSMITH));
		anvilManager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.redSteelSheet2x), new ItemStack(TFCSItems.redSteelCannonBarrelPiece), "cannonBarrel", AnvilReq.REDSTEEL, new ItemStack(
				TFCSItems.redSteelCannonBarrel)).addRecipeSkill(Global.SKILL_TOOLSMITH));
		anvilManager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.blueSteelSheet2x), new ItemStack(TFCSItems.blueSteelCannonBarrelPiece), "cannonBarrel", AnvilReq.BLUESTEEL, new ItemStack(
				TFCSItems.blueSteelCannonBarrel)).addRecipeSkill(Global.SKILL_TOOLSMITH));

		// cannonBarrelPiece
		anvilManager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.copperSheet2x), new ItemStack(TFCItems.copperSheet2x), "cannonBarrelPiece", AnvilReq.COPPER, new ItemStack(
				TFCSItems.copperCannonBarrelPiece)).addRecipeSkill(Global.SKILL_TOOLSMITH));
		anvilManager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.blackBronzeSheet2x), new ItemStack(TFCItems.blackBronzeSheet2x), "cannonBarrelPiece", AnvilReq.BLACKBRONZE, new ItemStack(
				TFCSItems.blackBronzeCannonBarrelPiece)).addRecipeSkill(Global.SKILL_TOOLSMITH));
		anvilManager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.bronzeSheet2x), new ItemStack(TFCItems.bronzeSheet2x), "cannonBarrelPiece", AnvilReq.BRONZE, new ItemStack(
				TFCSItems.bronzeCannonBarrelPiece)).addRecipeSkill(Global.SKILL_TOOLSMITH));
		anvilManager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.bismuthBronzeSheet2x), new ItemStack(TFCItems.bismuthBronzeSheet2x), "cannonBarrelPiece", AnvilReq.BISMUTHBRONZE, new ItemStack(
				TFCSItems.bismuthBronzeCannonBarrelPiece)).addRecipeSkill(Global.SKILL_TOOLSMITH));
		anvilManager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.wroughtIronSheet2x), new ItemStack(TFCItems.wroughtIronSheet2x), "cannonBarrelPiece", AnvilReq.WROUGHTIRON, new ItemStack(
				TFCSItems.wroughtIronCannonBarrelPiece)).addRecipeSkill(Global.SKILL_TOOLSMITH));
		anvilManager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.steelSheet2x), new ItemStack(TFCItems.steelSheet2x), "cannonBarrelPiece", AnvilReq.STEEL, new ItemStack(
				TFCSItems.steelCannonBarrelPiece)).addRecipeSkill(Global.SKILL_TOOLSMITH));
		anvilManager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.blackSteelSheet2x), new ItemStack(TFCItems.blackSteelSheet2x), "cannonBarrelPiece", AnvilReq.BLACKSTEEL, new ItemStack(
				TFCSItems.blackSteelCannonBarrelPiece)).addRecipeSkill(Global.SKILL_TOOLSMITH));
		anvilManager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.redSteelSheet2x), new ItemStack(TFCItems.redSteelSheet2x), "cannonBarrelPiece", AnvilReq.REDSTEEL, new ItemStack(
				TFCSItems.redSteelCannonBarrelPiece)).addRecipeSkill(Global.SKILL_TOOLSMITH));
		anvilManager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.blueSteelSheet2x), new ItemStack(TFCItems.blueSteelSheet2x), "cannonBarrelPiece", AnvilReq.BLUESTEEL, new ItemStack(
				TFCSItems.blueSteelCannonBarrelPiece)).addRecipeSkill(Global.SKILL_TOOLSMITH));
		// coils
		anvilManager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.copperSheet), null, "coils", AnvilReq.COPPER, new ItemStack(TFCSItems.copperCoil)).addRecipeSkill(Global.SKILL_TOOLSMITH));
		anvilManager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.blackBronzeSheet), null, "coils", AnvilReq.BLACKBRONZE, new ItemStack(TFCSItems.blackBronzeCoil))
				.addRecipeSkill(Global.SKILL_TOOLSMITH));
		anvilManager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.bronzeSheet), null, "coils", AnvilReq.BRONZE, new ItemStack(TFCSItems.bronzeCoil)).addRecipeSkill(Global.SKILL_TOOLSMITH));
		anvilManager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.bismuthBronzeSheet), null, "coils", AnvilReq.BISMUTHBRONZE, new ItemStack(TFCSItems.bismuthBronzeCoil))
				.addRecipeSkill(Global.SKILL_TOOLSMITH));
		anvilManager
				.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.wroughtIronSheet), null, "coils", AnvilReq.WROUGHTIRON, new ItemStack(TFCSItems.ironCoil)).addRecipeSkill(Global.SKILL_TOOLSMITH));
		anvilManager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.steelSheet), null, "coils", AnvilReq.STEEL, new ItemStack(TFCSItems.blackBronzeCoil)).addRecipeSkill(Global.SKILL_TOOLSMITH));
		anvilManager
				.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.blackSteelSheet), null, "coils", AnvilReq.BLACKSTEEL, new ItemStack(TFCSItems.bronzeCoil)).addRecipeSkill(Global.SKILL_TOOLSMITH));
		anvilManager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.redSteelSheet), null, "coils", AnvilReq.REDSTEEL, new ItemStack(TFCSItems.ironCoil)).addRecipeSkill(Global.SKILL_TOOLSMITH));
		anvilManager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.blueSteelSheet), null, "coils", AnvilReq.BLUESTEEL, new ItemStack(TFCSItems.blueSteelCoil))
				.addRecipeSkill(Global.SKILL_TOOLSMITH));

		// saws
		anvilManager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.copperSheet), new ItemStack(TFCSItems.copperGear, 1, 0), "saws", AnvilReq.COPPER, new ItemStack(TFCSItems.copperCoil))
				.addRecipeSkill(Global.SKILL_TOOLSMITH));
		anvilManager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.blackBronzeSheet), new ItemStack(TFCSItems.blackBronzeGear, 1, 0), "saws", AnvilReq.BLACKBRONZE, new ItemStack(
				TFCSItems.blackBronzeCoil)).addRecipeSkill(Global.SKILL_TOOLSMITH));
		anvilManager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.bronzeSheet), new ItemStack(TFCSItems.bronzeGear, 1, 0), "saws", AnvilReq.BRONZE, new ItemStack(TFCSItems.bronzeCoil))
				.addRecipeSkill(Global.SKILL_TOOLSMITH));
		anvilManager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.bismuthBronzeSheet), new ItemStack(TFCSItems.bismuthBronzeGear, 1, 0), "saws", AnvilReq.BISMUTHBRONZE, new ItemStack(
				TFCSItems.bismuthBronzeCoil)).addRecipeSkill(Global.SKILL_TOOLSMITH));
		anvilManager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.wroughtIronSheet), new ItemStack(TFCSItems.ironGear, 1, 0), "saws", AnvilReq.WROUGHTIRON, new ItemStack(TFCSItems.ironCoil))
				.addRecipeSkill(Global.SKILL_TOOLSMITH));
		anvilManager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.steelSheet), new ItemStack(TFCSItems.steelGear, 1, 0), "saws", AnvilReq.STEEL, new ItemStack(TFCSItems.blackBronzeCoil))
				.addRecipeSkill(Global.SKILL_TOOLSMITH));
		anvilManager
				.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.blackSteelSheet), new ItemStack(TFCSItems.blackSteelGear, 1, 0), "saws", AnvilReq.BLACKSTEEL, new ItemStack(TFCSItems.bronzeCoil))
						.addRecipeSkill(Global.SKILL_TOOLSMITH));
		anvilManager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.redSteelSheet), new ItemStack(TFCSItems.redSteelGear, 1, 0), "saws", AnvilReq.REDSTEEL, new ItemStack(TFCSItems.ironCoil))
				.addRecipeSkill(Global.SKILL_TOOLSMITH));
		anvilManager
				.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.blueSteelSheet), new ItemStack(TFCSItems.blueSteelGear, 1, 0), "saws", AnvilReq.BLUESTEEL, new ItemStack(TFCSItems.blueSteelCoil))
						.addRecipeSkill(Global.SKILL_TOOLSMITH));
		// wrench
		anvilManager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.copperIngot), null, "wrenches", AnvilReq.COPPER, new ItemStack(TFCSItems.wrench)).addRecipeSkill(Global.SKILL_TOOLSMITH));
		anvilManager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.blackBronzeIngot), null, "wrenches", AnvilReq.BLACKBRONZE, new ItemStack(TFCSItems.wrench))
				.addRecipeSkill(Global.SKILL_TOOLSMITH));
		anvilManager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.bronzeIngot), null, "wrenches", AnvilReq.BRONZE, new ItemStack(TFCSItems.wrench)).addRecipeSkill(Global.SKILL_TOOLSMITH));
		anvilManager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.bismuthBronzeIngot), null, "wrenches", AnvilReq.BISMUTHBRONZE, new ItemStack(TFCSItems.wrench))
				.addRecipeSkill(Global.SKILL_TOOLSMITH));
		anvilManager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.wroughtIronIngot), null, "wrenches", AnvilReq.WROUGHTIRON, new ItemStack(TFCSItems.wrench))
				.addRecipeSkill(Global.SKILL_TOOLSMITH));
		anvilManager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.steelIngot), null, "wrenches", AnvilReq.STEEL, new ItemStack(TFCSItems.wrench)).addRecipeSkill(Global.SKILL_TOOLSMITH));
		anvilManager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.blackSteelIngot), null, "wrenches", AnvilReq.BLACKSTEEL, new ItemStack(TFCSItems.wrench)).addRecipeSkill(Global.SKILL_TOOLSMITH));
		anvilManager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.redSteelIngot), null, "wrenches", AnvilReq.REDSTEEL, new ItemStack(TFCSItems.wrench)).addRecipeSkill(Global.SKILL_TOOLSMITH));
		anvilManager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.blueSteelIngot), null, "wrenches", AnvilReq.BLUESTEEL, new ItemStack(TFCSItems.wrench)).addRecipeSkill(Global.SKILL_TOOLSMITH));

		// cannon balls and bullets
		anvilManager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.leadIngot2x), null, "cannonBalls", AnvilReq.BRONZE, new ItemStack(TFCSItems.cannonBall)).addRecipeSkill(Global.SKILL_TOOLSMITH));
		anvilManager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.leadIngot), null, "bullets", AnvilReq.BRONZE, new ItemStack(TFCSItems.bullet, 4)).addRecipeSkill(Global.SKILL_TOOLSMITH));

		// shield bodies
		anvilManager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.copperSheet2x), null, "Shields", AnvilReq.COPPER, new ItemStack(TFCSItems.copperShieldBody))
				.addRecipeSkill(Global.SKILL_TOOLSMITH));
		anvilManager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.blackBronzeSheet2x), null, "Shields", AnvilReq.BLACKBRONZE, new ItemStack(TFCSItems.blackBronzeShieldBody))
				.addRecipeSkill(Global.SKILL_TOOLSMITH));
		anvilManager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.bronzeSheet2x), null, "Shields", AnvilReq.BRONZE, new ItemStack(TFCSItems.bronzeShieldBody))
				.addRecipeSkill(Global.SKILL_TOOLSMITH));
		anvilManager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.bismuthBronzeSheet2x), null, "Shields", AnvilReq.BISMUTHBRONZE, new ItemStack(TFCSItems.bismuthBronzeShieldBody))
				.addRecipeSkill(Global.SKILL_TOOLSMITH));
		anvilManager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.wroughtIronSheet2x), null, "Shields", AnvilReq.WROUGHTIRON, new ItemStack(TFCSItems.wroughtIronShieldBody))
				.addRecipeSkill(Global.SKILL_TOOLSMITH));
		anvilManager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.steelSheet2x), null, "Shields", AnvilReq.STEEL, new ItemStack(TFCSItems.steelShieldBody)).addRecipeSkill(Global.SKILL_TOOLSMITH));
		anvilManager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.blackSteelSheet2x), null, "Shields", AnvilReq.BLACKSTEEL, new ItemStack(TFCSItems.blackSteelShieldBody))
				.addRecipeSkill(Global.SKILL_TOOLSMITH));
		anvilManager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.redSteelSheet2x), null, "Shields", AnvilReq.REDSTEEL, new ItemStack(TFCSItems.redSteelShieldBody))
				.addRecipeSkill(Global.SKILL_TOOLSMITH));
		anvilManager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.blueSteelSheet2x), null, "Shields", AnvilReq.BLUESTEEL, new ItemStack(TFCSItems.blueSteelShieldBody))
				.addRecipeSkill(Global.SKILL_TOOLSMITH));

		// tempered sword heads
		anvilManager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.copperSheet), new ItemStack(TFCItems.copperSwordHead), "Tempered Sword head", AnvilReq.COPPER, new ItemStack(
				TFCSItems.copperTemperedSwordBlade)).addRecipeSkill(Global.SKILL_TOOLSMITH));
		anvilManager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.blackBronzeSheet), new ItemStack(TFCItems.blackBronzeSwordHead), "Tempered Sword head", AnvilReq.BLACKBRONZE, new ItemStack(
				TFCSItems.blackBronzeTemperedSwordBlade)).addRecipeSkill(Global.SKILL_TOOLSMITH));
		anvilManager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.bronzeSheet), new ItemStack(TFCItems.bronzeSwordHead), "Tempered Sword head", AnvilReq.BRONZE, new ItemStack(
				TFCSItems.bronzeTemperedSwordBlade)).addRecipeSkill(Global.SKILL_TOOLSMITH));
		anvilManager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.bismuthBronzeSheet), new ItemStack(TFCItems.bismuthBronzeSwordHead), "Tempered Sword head", AnvilReq.BISMUTHBRONZE, new ItemStack(
				TFCSItems.bismuthBronzeTemperedSwordBlade)).addRecipeSkill(Global.SKILL_TOOLSMITH));
		anvilManager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.wroughtIronSheet), new ItemStack(TFCItems.wroughtIronSwordHead), "Tempered Sword head", AnvilReq.WROUGHTIRON, new ItemStack(
				TFCSItems.wroughtIronTemperedSwordBlade)).addRecipeSkill(Global.SKILL_TOOLSMITH));
		anvilManager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.steelSheet), new ItemStack(TFCItems.steelSwordHead), "Tempered Sword head", AnvilReq.STEEL, new ItemStack(TFCSItems.steelTemperedSwordBlade))
				.addRecipeSkill(Global.SKILL_TOOLSMITH));
		anvilManager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.blackSteelSheet), new ItemStack(TFCItems.blackBronzeSwordHead), "Tempered Sword head", AnvilReq.BLACKSTEEL, new ItemStack(
				TFCSItems.blackSteelTemperedSwordBlade)).addRecipeSkill(Global.SKILL_TOOLSMITH));
		anvilManager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.redSteelSheet), new ItemStack(TFCItems.redSteelSwordHead), "Tempered Sword head", AnvilReq.REDSTEEL, new ItemStack(
				TFCSItems.redSteelTemperedSwordBlade)).addRecipeSkill(Global.SKILL_TOOLSMITH));
		anvilManager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.blueSteelSheet), new ItemStack(TFCItems.blueSteelSwordHead), "Tempered Sword head", AnvilReq.BLUESTEEL, new ItemStack(
				TFCSItems.blueSteelTemperedSwordBlade)).addRecipeSkill(Global.SKILL_TOOLSMITH));

		// Gates
		anvilManager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.copperIngot2x), null, "gates", AnvilReq.COPPER, new ItemStack(TFCSBlocks.copperGate)).addRecipeSkill(Global.SKILL_TOOLSMITH));
		anvilManager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.blackBronzeIngot2x), null, "gates", AnvilReq.BLACKBRONZE, new ItemStack(TFCSBlocks.blackBronzeGate))
				.addRecipeSkill(Global.SKILL_TOOLSMITH));
		anvilManager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.bronzeIngot2x), null, "gates", AnvilReq.BRONZE, new ItemStack(TFCSBlocks.bronzeGate)).addRecipeSkill(Global.SKILL_TOOLSMITH));
		anvilManager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.bismuthBronzeIngot2x), null, "gates", AnvilReq.BISMUTHBRONZE, new ItemStack(TFCSBlocks.bismuthBronzeGate))
				.addRecipeSkill(Global.SKILL_TOOLSMITH));
		anvilManager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.wroughtIronIngot2x), null, "gates", AnvilReq.WROUGHTIRON, new ItemStack(TFCSBlocks.ironGate))
				.addRecipeSkill(Global.SKILL_TOOLSMITH));
		anvilManager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.steelIngot2x), null, "gates", AnvilReq.STEEL, new ItemStack(TFCSBlocks.steelGate)).addRecipeSkill(Global.SKILL_TOOLSMITH));
		anvilManager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.blackSteelIngot2x), null, "gates", AnvilReq.BLACKSTEEL, new ItemStack(TFCSBlocks.blackSteelGate))
				.addRecipeSkill(Global.SKILL_TOOLSMITH));
		anvilManager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.redSteelIngot2x), null, "gates", AnvilReq.REDSTEEL, new ItemStack(TFCSBlocks.redSteelGate))
				.addRecipeSkill(Global.SKILL_TOOLSMITH));
		anvilManager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.blueSteelIngot2x), null, "gates", AnvilReq.BLUESTEEL, new ItemStack(TFCSBlocks.blueSteelGate))
				.addRecipeSkill(Global.SKILL_TOOLSMITH));

	}

	private static void registerRecipes() {

		barrelManager.addRecipe(new BarrelRecipe(new ItemStack(Items.rotten_flesh, 4, 0), new FluidStack(TFCFluids.FRESHWATER, 100), new ItemStack(TFCItems.fertilizer, 1), new FluidStack(
				TFCFluids.FRESHWATER, 100)).setMinTechLevel(0));

		// Items

		GameRegistry.addRecipe(new ItemStack(TFCSBlocks.aqueduct, 1, 0), "xyx", "yxy", "   ", 'x', new ItemStack(TFCItems.mortar), 'y', new ItemStack(TFCSItems.mudBrick, 1, 1));
		GameRegistry.addRecipe(new ItemStack(TFCSBlocks.spout, 1, 0), "xyx", "y y", "xyx", 'x', new ItemStack(TFCItems.mortar), 'y', new ItemStack(TFCSItems.mudBrick, 1, 1));

		GameRegistry.addRecipe(new ItemStack(TFCSItems.woodenGear, 1, 0), " y ", "y y", " y ", 'y', new ItemStack(TFCItems.stick));
		for (int i = 1; i <= 5; i++) {
			GameRegistry.addRecipe(new ItemStack(TFCSItems.woodenGear, 1, i), " y ", "yxy", " y ", 'x', new ItemStack(TFCSItems.woodenGear, 1, i - 1), 'y', new ItemStack(TFCItems.stick));
		}

		for (int k = 0; k < 20; k++) {
			GameRegistry.addRecipe(new ItemStack(TFCSItems.stoneGear, 1, 0), "yyy", "y y", "yyy", 'y', new ItemStack(TFCItems.stoneBrick, 1, k));
			for (int i = 1; i <= 5; i++) {
				GameRegistry.addRecipe(new ItemStack(TFCSItems.stoneGear, 1, i), "yyy", "yxy", "yyy", 'x', new ItemStack(TFCSItems.stoneGear, 1, i - 1), 'y', new ItemStack(TFCItems.stoneBrick, 1, k));
			}
		}

		GameRegistry.addRecipe(new ItemStack(TFCSItems.woodenAxle, 1, 0), "y  ", " y ", "  y", 'y', new ItemStack(TFCItems.stick, 1, 0));

		for (int k = 0; k < 20; k++) {
			GameRegistry.addRecipe(new ItemStack(TFCSItems.stoneAxle, 1, 0), "y  ", " y ", "  y", 'y', new ItemStack(TFCItems.stoneBrick, 1, k));
		}

		GameRegistry.addRecipe(new ItemStack(TFCSItems.windmill, 1, 0), "zyz", "yyy", "zyz", 'y', new ItemStack(TFCItems.stick), 'z', new ItemStack(TFCItems.burlapCloth));
		GameRegistry.addRecipe(new ItemStack(TFCSItems.windmill, 1, 0), "zyz", "yyy", "zyz", 'y', new ItemStack(TFCItems.stick), 'z', new ItemStack(TFCItems.woolCloth));
		GameRegistry.addRecipe(new ItemStack(TFCSItems.windmill, 1, 0), "zyz", "yyy", "zyz", 'y', new ItemStack(TFCItems.stick), 'z', new ItemStack(TFCItems.silkCloth));

		for (int i = 1; i <= 5; i++) {
			GameRegistry.addRecipe(new ItemStack(TFCSItems.windmill, 1, i), "zyz", "yxy", "zyz", 'x', new ItemStack(TFCSItems.windmill, 1, i - 1), 'y', new ItemStack(TFCItems.stick), 'z',
					new ItemStack(TFCItems.burlapCloth));
			GameRegistry.addRecipe(new ItemStack(TFCSItems.windmill, 1, i), "zyz", "yxy", "zyz", 'x', new ItemStack(TFCSItems.windmill, 1, i - 1), 'y', new ItemStack(TFCItems.stick), 'z',
					new ItemStack(TFCItems.woolCloth));
			GameRegistry.addRecipe(new ItemStack(TFCSItems.windmill, 1, i), "zyz", "yxy", "zyz", 'x', new ItemStack(TFCSItems.windmill, 1, i - 1), 'y', new ItemStack(TFCItems.stick), 'z',
					new ItemStack(TFCItems.silkCloth));
		}

		GameRegistry.addRecipe(new ItemStack(TFCSItems.waterWheel, 1, 0), "xxx", "x x", "xxx", 'x', new ItemStack(TFCItems.stick));

		for (int i = 1; i <= 5; i++) {
			GameRegistry.addRecipe(new ItemStack(TFCSItems.waterWheel, 1, i), "xxx", "xyx", "xxx", 'y', new ItemStack(TFCSItems.waterWheel, 1, i - 1), 'x', new ItemStack(TFCItems.stick));
		}

		for (int i = 0; i < 17; i++) {
			GameRegistry.addRecipe(new ItemStack(TFCSItems.gateMover, 1, 0), "zyz", "y y", "zyz", 'y', new ItemStack(TFCItems.stick), 'z', new ItemStack(TFCItems.singlePlank, 1, i));
		}

		GameRegistry.addShapelessRecipe(new ItemStack(TFCSItems.grindstone, 1, 0), new ItemStack(TFCSItems.stoneGear, 1, 0));

		GameRegistry.addRecipe(new ItemStack(TFCSItems.handCrank, 1, 0), "xx ", " x ", " x ", 'x', new ItemStack(TFCItems.stick));

		GameRegistry.addRecipe(new ItemStack(TFCSItems.ceramicShell, 1, 0), "xzx", "xyx", "xxx", 'y', new ItemStack(TFCItems.potterySmallVessel, 1, 1), 'z', new ItemStack(Items.string, 1, 0), 'x',
				new ItemStack(Items.gunpowder, 1, 0));

		GameRegistry.addRecipe(new ItemStack(TFCSItems.longArrow, 1, 0), " z ", " y ", " y ", 'y', new ItemStack(TFCItems.stick), 'z', new ItemStack(Items.flint, 1, 0));
		GameRegistry.addRecipe(new ItemStack(TFCSItems.fireLongArrow, 1, 0), " z ", " y ", "   ", 'y', new ItemStack(TFCSItems.longArrow), 'z', new ItemStack(TFCItems.burlapCloth, 1, 0));

		GameRegistry.addRecipe(new ItemStack(TFCSItems.longBow, 1, 0), "xxy", "x y", "xxy", 'y', new ItemStack(Items.string), 'x', new ItemStack(TFCItems.stick, 1, 0));

		for (int i = 0; i < 17; i++) {
			GameRegistry.addRecipe(new ItemStack(TFCSItems.woodenShield, 1, 0), " x ", "xxx", " x ", 'x', new ItemStack(TFCItems.singlePlank, 1, i));
		}
		
		for (int i = 0; i < 17; i++) {
			GameRegistry.addRecipe(new ItemStack(TFCSBlocks.port, 1, 0), "xxx", "xxx", "xxx", 'x', new ItemStack(TFCItems.singlePlank, 1, i));
		}

		GameRegistry
				.addRecipe(new ItemStack(TFCSItems.copperTemperedBroadsword, 1, 0), "   ", " y ", " z ", 'y', new ItemStack(TFCSItems.copperTemperedSwordBlade), 'z', new ItemStack(TFCItems.stick));
		GameRegistry
				.addRecipe(new ItemStack(TFCSItems.bronzeTemperedBroadsword, 1, 0), "   ", " y ", " z ", 'y', new ItemStack(TFCSItems.bronzeTemperedSwordBlade), 'z', new ItemStack(TFCItems.stick));
		GameRegistry.addRecipe(new ItemStack(TFCSItems.blackBronzeTemperedBroadsword, 1, 0), "   ", " y ", " z ", 'y', new ItemStack(TFCSItems.blackBronzeTemperedSwordBlade), 'z', new ItemStack(
				TFCItems.stick));
		GameRegistry.addRecipe(new ItemStack(TFCSItems.bismuthBronzeTemperedBroadsword, 1, 0), "   ", " y ", " z ", 'y', new ItemStack(TFCSItems.bismuthBronzeTemperedSwordBlade), 'z', new ItemStack(
				TFCItems.stick));
		GameRegistry.addRecipe(new ItemStack(TFCSItems.wroughtIronTemperedBroadsword, 1, 0), "   ", " y ", " z ", 'y', new ItemStack(TFCSItems.wroughtIronTemperedSwordBlade), 'z', new ItemStack(
				TFCItems.stick));
		GameRegistry.addRecipe(new ItemStack(TFCSItems.steelTemperedBroadsword, 1, 0), "   ", " y ", " z ", 'y', new ItemStack(TFCSItems.steelTemperedSwordBlade), 'z', new ItemStack(TFCItems.stick));
		GameRegistry.addRecipe(new ItemStack(TFCSItems.blackSteelTemperedBroadsword, 1, 0), "   ", " y ", " z ", 'y', new ItemStack(TFCSItems.blackSteelTemperedSwordBlade), 'z', new ItemStack(
				TFCItems.stick));
		GameRegistry.addRecipe(new ItemStack(TFCSItems.redSteelTemperedBroadsword, 1, 0), "   ", " y ", " z ", 'y', new ItemStack(TFCSItems.redSteelTemperedSwordBlade), 'z', new ItemStack(
				TFCItems.stick));
		GameRegistry.addRecipe(new ItemStack(TFCSItems.blueSteelTemperedBroadsword, 1, 0), "   ", " y ", " z ", 'y', new ItemStack(TFCSItems.blueSteelTemperedSwordBlade), 'z', new ItemStack(
				TFCItems.stick));

		GameRegistry.addRecipe(new ItemStack(TFCSItems.copperShield, 1, 0), "   ", " y ", " z ", 'y', new ItemStack(TFCSItems.copperShieldBody), 'z', new ItemStack(TFCItems.leather));
		GameRegistry.addRecipe(new ItemStack(TFCSItems.bronzeShield, 1, 0), "   ", " y ", " z ", 'y', new ItemStack(TFCSItems.bronzeShieldBody), 'z', new ItemStack(TFCItems.leather));
		GameRegistry.addRecipe(new ItemStack(TFCSItems.blackBronzeShield, 1, 0), "   ", " y ", " z ", 'y', new ItemStack(TFCSItems.blackBronzeShieldBody), 'z', new ItemStack(TFCItems.leather));
		GameRegistry.addRecipe(new ItemStack(TFCSItems.bismuthBronzeShield, 1, 0), "   ", " y ", " z ", 'y', new ItemStack(TFCSItems.bismuthBronzeShieldBody), 'z', new ItemStack(TFCItems.leather));
		GameRegistry.addRecipe(new ItemStack(TFCSItems.wroughtIronShield, 1, 0), "   ", " y ", " z ", 'y', new ItemStack(TFCSItems.wroughtIronShieldBody), 'z', new ItemStack(TFCItems.leather));
		GameRegistry.addRecipe(new ItemStack(TFCSItems.steelShield, 1, 0), "   ", " y ", " z ", 'y', new ItemStack(TFCSItems.steelShieldBody), 'z', new ItemStack(TFCItems.leather));
		GameRegistry.addRecipe(new ItemStack(TFCSItems.blackSteelShield, 1, 0), "   ", " y ", " z ", 'y', new ItemStack(TFCSItems.blackSteelShieldBody), 'z', new ItemStack(TFCItems.leather));
		GameRegistry.addRecipe(new ItemStack(TFCSItems.redSteelShield, 1, 0), "   ", " y ", " z ", 'y', new ItemStack(TFCSItems.redSteelShieldBody), 'z', new ItemStack(TFCItems.leather));
		GameRegistry.addRecipe(new ItemStack(TFCSItems.blueSteelShield, 1, 0), "   ", " y ", " z ", 'y', new ItemStack(TFCSItems.blueSteelShieldBody), 'z', new ItemStack(TFCItems.leather));

		GameRegistry.addRecipe(new ItemStack(TFCSItems.copperTemperedCrossbow, 1, 0), "   ", " y ", "xzx", 'y', new ItemStack(TFCSItems.copperCrossbowLimb), 'z', new ItemStack(TFCItems.stick), 'x',
				new ItemStack(TFCItems.rope));
		GameRegistry.addRecipe(new ItemStack(TFCSItems.bronzeTemperedCrossbow, 1, 0), "   ", " y ", "xzx", 'y', new ItemStack(TFCSItems.bronzeCrossbowLimb), 'z', new ItemStack(TFCItems.stick), 'x',
				new ItemStack(TFCItems.rope));
		GameRegistry.addRecipe(new ItemStack(TFCSItems.blackBronzeTemperedCrossbow, 1, 0), "   ", " y ", "xzx", 'y', new ItemStack(TFCSItems.blackBronzeCrossbowLimb), 'z', new ItemStack(
				TFCItems.stick), 'x', new ItemStack(TFCItems.rope));
		GameRegistry.addRecipe(new ItemStack(TFCSItems.bismuthBronzeTemperedCrossbow, 1, 0), "   ", " y ", "xzx", 'y', new ItemStack(TFCSItems.bismuthBronzeCrossbowLimb), 'z', new ItemStack(
				TFCItems.stick), 'x', new ItemStack(TFCItems.rope));
		GameRegistry.addRecipe(new ItemStack(TFCSItems.wroughtIronTemperedCrossbow, 1, 0), "   ", " y ", "xzx", 'y', new ItemStack(TFCSItems.wroughtIronCrossbowLimb), 'z', new ItemStack(
				TFCItems.stick), 'x', new ItemStack(TFCItems.rope));

		// bolts
		GameRegistry.addRecipe(new ItemStack(TFCSItems.copperBolt, 4, 0), "y  ", " s ", "   ", 'y', new ItemStack(TFCSItems.copperBoltHead), 's', new ItemStack(TFCItems.stick));
		GameRegistry.addRecipe(new ItemStack(TFCSItems.bronzeBolt, 4, 0), "y  ", " s ", "   ", 'y', new ItemStack(TFCSItems.bronzeBoltHead), 's', new ItemStack(TFCItems.stick));
		GameRegistry.addRecipe(new ItemStack(TFCSItems.bismuthBronzeBolt, 4, 0), "y  ", " s ", "   ", 'y', new ItemStack(TFCSItems.bismuthBronzeBoltHead), 's', new ItemStack(TFCItems.stick));
		GameRegistry.addRecipe(new ItemStack(TFCSItems.blackBronzeBolt, 4, 0), "y  ", " s ", "   ", 'y', new ItemStack(TFCSItems.blackBronzeBoltHead), 's', new ItemStack(TFCItems.stick));
		GameRegistry.addRecipe(new ItemStack(TFCSItems.wroughtIronBolt, 4, 0), "y  ", " s ", "   ", 'y', new ItemStack(TFCSItems.wroughtIronBoltHead), 's', new ItemStack(TFCItems.stick));

		// muskets
		GameRegistry.addRecipe(new ItemStack(TFCSItems.wroughtIronMusket, 1, 0), "yzz", " ys", "  t", 'z', new ItemStack(TFCItems.tuyereWroughtIron), 's', new ItemStack(TFCItems.flintSteel), 'y',
				new ItemStack(TFCItems.stick));
		GameRegistry.addRecipe(new ItemStack(TFCSItems.steelMusket, 1, 0), "yzz", " ys", "  t", 'z', new ItemStack(TFCItems.tuyereSteel), 's', new ItemStack(TFCItems.flintSteel), 'y', new ItemStack(
				TFCItems.stick));
		GameRegistry.addRecipe(new ItemStack(TFCSItems.blackSteelMusket, 1, 0), "yzz", " ys", "  t", 'z', new ItemStack(TFCItems.tuyereBlackSteel), 's', new ItemStack(TFCItems.flintSteel), 'y',
				new ItemStack(TFCItems.stick));
		GameRegistry.addRecipe(new ItemStack(TFCSItems.blueSteelMusket, 1, 0), "yzz", " ys", "  t", 'z', new ItemStack(TFCItems.tuyereBlueSteel), 's', new ItemStack(TFCItems.flintSteel), 'y',
				new ItemStack(TFCItems.stick));
		GameRegistry.addRecipe(new ItemStack(TFCSItems.redSteelMusket, 1, 0), "yzz", " ys", "  t", 'z', new ItemStack(TFCItems.tuyereRedSteel), 's', new ItemStack(TFCItems.flintSteel), 'y',
				new ItemStack(TFCItems.stick));

		// tempered pickaxe
		GameRegistry.addRecipe(new ItemStack(TFCSItems.copperTemperedPickaxe, 1, 0), "   ", " y ", " z ", 'y', new ItemStack(TFCSItems.copperTemperedPickHead), 'z', new ItemStack(TFCItems.stick));
		GameRegistry.addRecipe(new ItemStack(TFCSItems.bronzeTemperedPickaxe, 1, 0), "   ", " y ", " z ", 'y', new ItemStack(TFCSItems.bronzeTemperedPickHead), 'z', new ItemStack(TFCItems.stick));
		GameRegistry.addRecipe(new ItemStack(TFCSItems.blackBronzeTemperedPickaxe, 1, 0), "   ", " y ", " z ", 'y', new ItemStack(TFCSItems.blackBronzeTemperedPickHead), 'z', new ItemStack(
				TFCItems.stick));
		GameRegistry.addRecipe(new ItemStack(TFCSItems.bismuthBronzeTemperedPickaxe, 1, 0), "   ", " y ", " z ", 'y', new ItemStack(TFCSItems.bismuthBronzeTemperedPickHead), 'z', new ItemStack(
				TFCItems.stick));
		GameRegistry.addRecipe(new ItemStack(TFCSItems.wroughtIronTemperedPickaxe, 1, 0), "   ", " y ", " z ", 'y', new ItemStack(TFCSItems.wroughtIronTemperedPickHead), 'z', new ItemStack(
				TFCItems.stick));
		GameRegistry.addRecipe(new ItemStack(TFCSItems.steelTemperedPickaxe, 1, 0), "   ", " y ", " z ", 'y', new ItemStack(TFCSItems.steelTemperedPickHead), 'z', new ItemStack(TFCItems.stick));
		GameRegistry.addRecipe(new ItemStack(TFCSItems.blackSteelTemperedPickaxe, 1, 0), "   ", " y ", " z ", 'y', new ItemStack(TFCSItems.blackSteelTemperedPickHead), 'z', new ItemStack(
				TFCItems.stick));
		GameRegistry.addRecipe(new ItemStack(TFCSItems.redSteelTemperedPickaxe, 1, 0), "   ", " y ", " z ", 'y', new ItemStack(TFCSItems.redSteelTemperedPickHead), 'z', new ItemStack(TFCItems.stick));
		GameRegistry.addRecipe(new ItemStack(TFCSItems.blueSteelTemperedPickaxe, 1, 0), "   ", " y ", " z ", 'y', new ItemStack(TFCSItems.blueSteelTemperedPickHead), 'z',
				new ItemStack(TFCItems.stick));

		// Blocks

		for (int i = 0; i < 17; i++) {
			GameRegistry.addRecipe(new ItemStack(TFCSBlocks.gearFrame, 4, 0), "yxy", "x x", "yxy", 'x', new ItemStack(TFCItems.singlePlank, 1, i), 'y', new ItemStack(TFCBlocks.planks, 1, i));
		}

		for (int i = 0; i < 17; i++) {
			GameRegistry.addRecipe(new ItemStack(TFCSBlocks.launcherBase, 1, 0), "x x", "xxx", "xxx", 'x', new ItemStack(TFCItems.singlePlank, 1, i));
		}

		GameRegistry.addRecipe(new ItemStack(TFCSBlocks.dioriteWall, 1, 0), "yxy", "xyx", "yxy", 'x', new ItemStack(TFCItems.stoneBrick, 1, 1), 'y', new ItemStack(TFCItems.mortar, 1, 0));
		GameRegistry.addRecipe(new ItemStack(TFCSBlocks.gabbroWall, 1, 0), "yxy", "xyx", "yxy", 'x', new ItemStack(TFCItems.stoneBrick, 1, 2), 'y', new ItemStack(TFCItems.mortar, 1, 0));
		GameRegistry.addRecipe(new ItemStack(TFCSBlocks.graniteWall, 1, 0), "yxy", "xyx", "yxy", 'x', new ItemStack(TFCItems.stoneBrick, 1, 0), 'y', new ItemStack(TFCItems.mortar, 1, 0));
		GameRegistry.addRecipe(new ItemStack(TFCSBlocks.marbleWall, 1, 0), "yxy", "xyx", "yxy", 'x', new ItemStack(TFCItems.stoneBrick, 1, 20), 'y', new ItemStack(TFCItems.mortar, 1, 0));
		GameRegistry.addRecipe(new ItemStack(TFCSBlocks.rhyoliteWall, 1, 0), "yxy", "xyx", "yxy", 'x', new ItemStack(TFCItems.stoneBrick, 1, 11), 'y', new ItemStack(TFCItems.mortar, 1, 0));
		GameRegistry.addRecipe(new ItemStack(TFCSBlocks.daciteWall, 1, 0), "yxy", "xyx", "yxy", 'x', new ItemStack(TFCItems.stoneBrick, 1, 14), 'y', new ItemStack(TFCItems.mortar, 1, 0));
		GameRegistry.addRecipe(new ItemStack(TFCSBlocks.basaltWall, 1, 0), "yxy", "xyx", "yxy", 'x', new ItemStack(TFCItems.stoneBrick, 1, 12), 'y', new ItemStack(TFCItems.mortar, 1, 0));
		GameRegistry.addRecipe(new ItemStack(TFCSBlocks.andesiteWall, 1, 0), "yxy", "xyx", "yxy", 'x', new ItemStack(TFCItems.stoneBrick, 1, 13), 'y', new ItemStack(TFCItems.mortar, 1, 0));

		GameRegistry.addRecipe(new ItemStack(TFCSBlocks.foundation, 8, 0), "yyy", "yxy", "yyy", 'y', new ItemStack(TFCItems.looseRock, 1, 1), 'x', new ItemStack(TFCItems.mortar, 1, 0));
		GameRegistry.addRecipe(new ItemStack(TFCSBlocks.foundation, 8, 0), "yyy", "yxy", "yyy", 'y', new ItemStack(TFCItems.looseRock, 1, 2), 'x', new ItemStack(TFCItems.mortar, 1, 0));
		GameRegistry.addRecipe(new ItemStack(TFCSBlocks.foundation, 8, 0), "yyy", "yxy", "yyy", 'y', new ItemStack(TFCItems.looseRock, 1, 0), 'x', new ItemStack(TFCItems.mortar, 1, 0));
		GameRegistry.addRecipe(new ItemStack(TFCSBlocks.foundation, 8, 0), "yyy", "yxy", "yyy", 'y', new ItemStack(TFCItems.looseRock, 1, 20), 'x', new ItemStack(TFCItems.mortar, 1, 0));
		GameRegistry.addRecipe(new ItemStack(TFCSBlocks.foundation, 8, 0), "yyy", "yxy", "yyy", 'y', new ItemStack(TFCItems.looseRock, 1, 11), 'x', new ItemStack(TFCItems.mortar, 1, 0));
		GameRegistry.addRecipe(new ItemStack(TFCSBlocks.foundation, 8, 0), "yyy", "yxy", "yyy", 'y', new ItemStack(TFCItems.looseRock, 1, 14), 'x', new ItemStack(TFCItems.mortar, 1, 0));
		GameRegistry.addRecipe(new ItemStack(TFCSBlocks.foundation, 8, 0), "yyy", "yxy", "yyy", 'y', new ItemStack(TFCItems.looseRock, 1, 12), 'x', new ItemStack(TFCItems.mortar, 1, 0));
		GameRegistry.addRecipe(new ItemStack(TFCSBlocks.foundation, 8, 0), "yyy", "yxy", "yyy", 'y', new ItemStack(TFCItems.looseRock, 1, 13), 'x', new ItemStack(TFCItems.mortar, 1, 0));
		
		GameRegistry.addRecipe(new ItemStack(TFCSBlocks.mudWall, 4, 0), "yxy", "xyx", "yxy", 'x', new ItemStack(TFCSItems.mudBrick, 1, 1), 'y', new ItemStack(TFCItems.mortar, 1, 0));
		
		GameRegistry.addRecipe(new ItemStack(TFCSBlocks.ironBomb, 4, 0), " y ", "y y", " y ", 'y', new ItemStack(TFCItems.wroughtIronSheet, 1, 0));
		GameRegistry.addShapelessRecipe(new ItemStack(TFCSBlocks.ceramicBomb, 1, 0), new ItemStack(TFCBlocks.vessel, 1, 1), new ItemStack(Items.string, 1, 0));

		for (int i = 0; i < 16; i++)
			GameRegistry.addShapelessRecipe(new ItemStack(TFCSItems.mudBrick, 2, 0), new ItemStack(TFCItems.woodenBucketWater, 1, 0), new ItemStack(TFCItems.straw, 1, 0), new ItemStack(
					TFCItems.clayBall, 1, 0), new ItemStack(TFCBlocks.dirt, 1, i));

		GameRegistry.addRecipe(new ItemStack(TFCSBlocks.copperGate, 2, 0), " x ", "xxx", " x ", 'x', new ItemStack(TFCItems.copperIngot));
		GameRegistry.addRecipe(new ItemStack(TFCSBlocks.bronzeGate, 2, 0), " x ", "xxx", " x ", 'x', new ItemStack(TFCItems.bronzeIngot));
		GameRegistry.addRecipe(new ItemStack(TFCSBlocks.blackBronzeGate, 2, 0), " x ", "xxx", " x ", 'x', new ItemStack(TFCItems.blackBronzeIngot));
		GameRegistry.addRecipe(new ItemStack(TFCSBlocks.bismuthBronzeGate, 2, 0), " x ", "xxx", " x ", 'x', new ItemStack(TFCItems.bismuthBronzeIngot));
		GameRegistry.addRecipe(new ItemStack(TFCSBlocks.ironGate, 2, 0), " x ", "xxx", " x ", 'x', new ItemStack(TFCItems.wroughtIronIngot));
		GameRegistry.addRecipe(new ItemStack(TFCSBlocks.steelGate, 2, 0), " x ", "xxx", " x ", 'x', new ItemStack(TFCItems.steelIngot));
		GameRegistry.addRecipe(new ItemStack(TFCSBlocks.blackSteelGate, 2, 0), " x ", "xxx", " x ", 'x', new ItemStack(TFCItems.blackSteelIngot));
		GameRegistry.addRecipe(new ItemStack(TFCSBlocks.redSteelGate, 2, 0), " x ", "xxx", " x ", 'x', new ItemStack(TFCItems.redSteelIngot));
		GameRegistry.addRecipe(new ItemStack(TFCSBlocks.blueSteelGate, 2, 0), " x ", "xxx", " x ", 'x', new ItemStack(TFCItems.blueSteelIngot));

		GameRegistry.addRecipe(new ItemStack(TFCSBlocks.mortar, 1, 0), "i i", "i i", "iii", 'i', new ItemStack(TFCItems.clayBall));
		GameRegistry.addRecipe(new ItemStack(TFCSBlocks.candleOFF, 1, 0), "   ", " i ", " s ", 'i', new ItemStack(TFCItems.juteFiber), 's', new ItemStack(TFCItems.porkchopRaw));
		GameRegistry.addRecipe(new ItemStack(TFCSBlocks.candleOFF, 1, 0), "   ", " i ", " s ", 'i', new ItemStack(TFCItems.woolYarn), 's', new ItemStack(TFCItems.porkchopRaw));
		GameRegistry.addRecipe(new ItemStack(TFCSBlocks.thatchWall, 8, 0), "iii", "sis ", "iii", 'i', new ItemStack(TFCBlocks.thatch), 's', new ItemStack(TFCItems.woolYarn));
		GameRegistry.addRecipe(new ItemStack(TFCSBlocks.thatchWall, 8, 0), "iii", "sis ", "iii", 'i', new ItemStack(TFCBlocks.thatch), 's', new ItemStack(TFCItems.rope));
		GameRegistry.addRecipe(new ItemStack(TFCSBlocks.woodWall, 8, 0), "iii", "sis ", "iii", 'i', new ItemStack(TFCItems.logs), 's', new ItemStack(TFCItems.woolYarn));
		GameRegistry.addRecipe(new ItemStack(TFCSBlocks.woodWall, 8, 0), "iii", "sis ", "iii", 'i', new ItemStack(TFCItems.logs), 's', new ItemStack(TFCItems.rope));

	}

}
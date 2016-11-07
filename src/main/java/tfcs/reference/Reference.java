package tfcs.reference;

public class Reference 
{
	public static final String ModID = "tfcs";
	public static final String ModName = "TFCSiege";
	public static final String ModIDUpperCase = "TFCS";

	public static final int VersionMajor = 0;
	public static final int VersionMinor = 1;
	public static final int VersionRevision = 2;

	public static final String ModVersion = VersionMajor + "." + VersionMinor + "." + VersionRevision;
	public static final String ModDependencies = "after:TerraFirmaCraft";
	public static final String ModChannel = "TFCSiege";
	public static final String SERVER_PROXY_CLASS = "tfcs.proxy.CommonProxy";
	public static final String CLIENT_PROXY_CLASS = "tfcs.proxy.ClientProxy";
	
	public static final String AssetPath = "/assets/" + ModID + "/";
	public static final String AssetPathGui = "textures/gui/";
	
	public static final String ConfigFilePath = "/config/";
	public static final String ConfigFileName = "tfcs.cfg";
	
	public static final int GuiOffset = 10000;
	
	public static final String MODID_NEI = "NotEnoughItems";
	public static final String MODID_TFC = "terrafirmacraft";
	public static final String MODID_WAILA = "Waila";

	public static final String MODNAME_NEI = "Not Enough Items";
	public static final String MODNAME_TFC = "TerraFirmaCraft";
	public static final String MODNAME_WAILA = "Waila";
	public static final int MAX_CANDLE_BRUN_TIME = 24000 * 7;
	public static final int MAX_FORGE_SIZE = 8;
	public static final String ASSET_PATH_GUI = "textures/gui/";
}

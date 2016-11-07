package tfcs.handlers.network;

import tfcs.reference.Reference;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;

public class PacketHandler {

	public static final SimpleNetworkWrapper network = NetworkRegistry.INSTANCE.newSimpleChannel(Reference.ModID.toLowerCase());
	
	public static void init() {
		network.registerMessage(MessageTileEntityTFCS.class, MessageTileEntityTFCS.class, 0, Side.CLIENT);
		network.registerMessage(MessageSyncEntityToServer.class, MessageSyncEntityToServer.class, 1, Side.SERVER);
		network.registerMessage(MessageSyncEntityToClient.class, MessageSyncEntityToClient.class, 2, Side.CLIENT);
	}
	
}

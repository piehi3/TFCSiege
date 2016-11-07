package tfcs.core;

import java.util.HashMap;
import java.util.Map;
import tfcs.gears.FrameComponent;

public class GearRegistry {

	public static FrameComponent[] frameComponents;

	public static GearRegistry instance = new GearRegistry();

	private Map<String, FrameComponent> hash;

	public GearRegistry() {
		hash = new HashMap<String, FrameComponent>();
	}

	public boolean addFrameComponent(FrameComponent frameComponent) {
		if (hash.containsKey(frameComponent.getName() + "_" + frameComponent.getSize()))
			return false;

		hash.put(frameComponent.getName() + "_" + frameComponent.getSize(), frameComponent);
		return true;
	}

	public FrameComponent getFrameComponentFromString(String s) {
		if (hash.containsKey(s))
			return hash.get(s);
		else
			return null;
	}
}

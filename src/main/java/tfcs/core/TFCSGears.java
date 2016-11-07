package tfcs.core;

import tfcs.gears.FrameComponent;

public class TFCSGears {
	
	public static FrameComponent[] frameComponents;

	public static void registerGearComponent(FrameComponent frameComponent) {
		FrameComponent[] f1 = frameComponents;
		FrameComponent[] f2 = new FrameComponent[f1.length+1];
		f2[0] = frameComponent;
		for (int i = 1; i < f1.length; i++) {
			f2[i] = f1[i];
		}
		frameComponents = f2;
	}
	
	public static FrameComponent getFrameComponentFromArray(int i) {
		return frameComponents[i];
	}
	
	public static int getFrameComponentIdFromArray(FrameComponent frameComponent) {
		for (int i = 0; i < frameComponents.length; i++) {
			if(frameComponents[i] == frameComponent)
				return i;
		}
		return -1;
	}
	
}

package ruben.common.processing.utils;

import processing.core.PApplet;
import ruben.common.OpenUtils;

public class DirLoadStrategy implements ILoadStrategy {

	@Override
	public String get_source() {
		String dir = OpenUtils.get_instance().open_dir();
		PApplet.println("LOADING DIRECTORY: " + dir);
		return dir;
	}

}

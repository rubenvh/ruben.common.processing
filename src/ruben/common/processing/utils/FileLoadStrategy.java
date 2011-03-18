package ruben.common.processing.utils;

import processing.core.PApplet;
import ruben.common.OpenUtils;

public class FileLoadStrategy implements ILoadStrategy {

	@Override
	public String get_source() {
		String file = OpenUtils.get_instance().open_file();
		PApplet.println("LOADING FILE: " + file);
		return file;
	}
	

}

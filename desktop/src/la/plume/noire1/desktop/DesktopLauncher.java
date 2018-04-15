package la.plume.noire1.desktop;


import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import la.plume.noire1.LaPlumeNoire1;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "La Plume Noire 1";
		config.addIcon("bin/icon.png", Files.FileType.Internal);
		config.width = 1280;
		config.height = 720;
		config.fullscreen = false;
		config.resizable = false;
		new LwjglApplication(new LaPlumeNoire1(null), config);
	}
}

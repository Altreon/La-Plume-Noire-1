package Scene;

public class Libre_de_s_echapper extends Scene{
	private static String sceneName = "Libre de s echapper";
	private static boolean asBoucle = false;
	private static boolean asEnd = false;
	private static boolean asItem = true;
	private static int[] boucleInfo = {0, 0};
	private static int[][] textInfo = {{1110, 98, 90, 124, 6, 120+4}, {1110, 98, 90, 26, 5, 120+10}};
	private static float[] audioInfo = {1F, 120+18.9F};
	private static String itemName = "Manche";
	private static float itemTime =  60+22F;
	
	public Libre_de_s_echapper () {
		super(sceneName, asBoucle, asEnd, asItem, boucleInfo, textInfo, audioInfo, itemName, itemTime);
	}
}

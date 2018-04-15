package Scene;

public class Ecouter_ou_ecourter extends Scene{
	private static String sceneName = "Ecouter ou ecourter";
	private static boolean asBoucle = true;
	private static boolean asEnd = false;
	private static boolean asItem = false;
	private static int[] boucleInfo = {0, 300};
	private static int[][] textInfo = {{1110, 98, 90, 124, 17, 60+12}, {1110, 98, 90, 26, 18, 60+19}};
	private static float[] audioInfo = {1.5F, 60+25.9F};
	private static String itemName = null;
	private static float itemTime =  0;
	
	public Ecouter_ou_ecourter () {
		super(sceneName, asBoucle, asEnd, asItem, boucleInfo, textInfo, audioInfo, itemName, itemTime);
	}
}

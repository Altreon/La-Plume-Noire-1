package Scene;

public class Trouve_les_trois_pour_n_en_faire_qu_une extends Scene{
	private static String sceneName = "Trouve les trois pour n en faire qu une";
	private static boolean asBoucle = false;
	private static boolean asEnd = false;
	private static boolean asItem = false;
	private static int[] boucleInfo = {0, 0};
	private static int[][] textInfo = {{1110, 196, 90, 26, 21, 21}};
	private static float[] audioInfo = {1.8F, 27.5F};
	private static String itemName = null;
	private static float itemTime =  0;
	
	public Trouve_les_trois_pour_n_en_faire_qu_une () {
		super(sceneName, asBoucle, asEnd, asItem, boucleInfo, textInfo, audioInfo, itemName, itemTime);
	}
}

package Scene;

public class Un_doute_decisif extends Scene{
	private static String sceneName = "Un doute decisif";
	private static boolean asBoucle = true;
	private static boolean asEnd = false;
	private static boolean asItem = false;
	private static int[] boucleInfo = {0, 300};
	private static int[][] textInfo = {{1110, 98, 90, 124, 15, 60+5}, {1110, 98, 90, 26, 6, 60+19}};
	private static float[] audioInfo = {1.5F, 60+24.5F};
	private static String itemName = null;
	private static float itemTime =  0;
	
	public Un_doute_decisif () {
		super(sceneName, asBoucle, asEnd, asItem, boucleInfo, textInfo, audioInfo, itemName, itemTime);
	}
}

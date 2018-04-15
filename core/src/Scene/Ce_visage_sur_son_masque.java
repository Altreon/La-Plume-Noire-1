package Scene;

public class Ce_visage_sur_son_masque extends Scene{
	private static String sceneName = "Ce visage sur son masque";
	private static boolean asBoucle = false;
	private static boolean asEnd = false;
	private static boolean asItem = false;
	private static int[] boucleInfo = {0, 0};
	private static int[][] textInfo = {{1110, 98, 90, 124, 1, 60+20}, {1110, 98, 90, 26, 14, 60+31}};
	private static float[] audioInfo = {1.7F, 60+40.5F};
	private static String itemName = null;
	private static float itemTime =  0;
	
	public Ce_visage_sur_son_masque () {
		super(sceneName, asBoucle, asEnd, asItem, boucleInfo, textInfo, audioInfo, itemName, itemTime);
	}
}

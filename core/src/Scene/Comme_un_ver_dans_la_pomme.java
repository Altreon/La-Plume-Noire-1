package Scene;

public class Comme_un_ver_dans_la_pomme extends Scene{
	private static String sceneName = "Comme un ver dans la pomme";
	private static boolean asBoucle = false;
	private static boolean asEnd = false;
	private static boolean asItem = false;
	private static int[] boucleInfo = {0, 0};
	private static int[][] textInfo = {{1110, 98, 90, 124, 14, 55}, {1110, 98, 90, 26, 10, 60+3}};
	private static float[] audioInfo = {1.6F, 60+8.7F};
	private static String itemName = null;
	private static float itemTime =  0;
	
	public Comme_un_ver_dans_la_pomme () {
		super(sceneName, asBoucle, asEnd, asItem, boucleInfo, textInfo, audioInfo, itemName, itemTime);
	}
}

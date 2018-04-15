package Scene;

public class Cache_a_la_vue_de_tous extends Scene{
	private static String sceneName = "Cache a la vue de tous";
	private static boolean asBoucle = false;
	private static boolean asEnd = false;
	private static boolean asItem = true;
	private static int[] boucleInfo = {0, 0};
	private static int[][] textInfo = {{1110, 98, 90, 124, 6, 53}, {1110, 98, 90, 26, 12, 60}};
	private static float[] audioInfo = {1.7F, 60+5F};
	private static String itemName = "Lame";
	private static float itemTime =  49.8F;
	
	public Cache_a_la_vue_de_tous () {
		super(sceneName, asBoucle, asEnd, asItem, boucleInfo, textInfo, audioInfo, itemName, itemTime);
	}
}

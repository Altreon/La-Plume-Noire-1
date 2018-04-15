package Scene;

public class Celui_qui_se_cache_dans_la_lumiere_part2 extends Scene{
	private static String sceneName = "Celui qui se cache dans la lumiere part2";
	private static boolean asBoucle = false;
	private static boolean asEnd = false;
	private static boolean asItem = false;
	private static int[] boucleInfo = {0, 0};
	private static int[][] textInfo = {{1110, 196, 90, 26, 0, 20}};
	private static float[] audioInfo = {0};
	private static String itemName = null;
	private static float itemTime =  0;
	
	public Celui_qui_se_cache_dans_la_lumiere_part2 () {
		super(sceneName, asBoucle, asEnd, asItem, boucleInfo, textInfo, audioInfo, itemName, itemTime);
	}
}

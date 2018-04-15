package Scene;

public class Les_lumieres_de_la_voix_eteinte extends Scene{
	private static String sceneName = "Les lumieres de la voix eteinte";
	private static boolean asBoucle = true;
	private static boolean asEnd = false;
	private static boolean asItem = false;
	private static int[] boucleInfo = {0, 300};
	private static int[][] textInfo = {{1110, 98, 90, 124, 6, 120+9}, {1110, 98, 90, 26, 2, 120+14}};
	private static float[] audioInfo = {1.8F, 120+24.6F};
	private static String itemName = null;
	private static float itemTime =  0;
	
	public Les_lumieres_de_la_voix_eteinte () {
		super(sceneName, asBoucle, asEnd, asItem, boucleInfo, textInfo, audioInfo, itemName, itemTime);
	}
}

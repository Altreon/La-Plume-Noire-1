package Scene;

public class Le_dernier_acte_de_l_eternite extends Scene{
	private static String sceneName = "Le dernier acte de l eternite";
	private static boolean asBoucle = false;
	private static boolean asEnd = false;
	private static boolean asItem = false;
	private static int[] boucleInfo = {0, 0};
	private static int[][] textInfo = {{1110, 98, 90, 124, 14, 60+35}, {1110, 98, 90, 26, 1, 60+40}};
	private static float[] audioInfo = {1.9F, 60+45F};
	private static String itemName = null;
	private static float itemTime =  0;
	
	public Le_dernier_acte_de_l_eternite () {
		super(sceneName, asBoucle, asEnd, asItem, boucleInfo, textInfo, audioInfo, itemName, itemTime);
	}
}

package Scene;

public class Le_dilemme_de_l_alternative extends Scene{
	private static String sceneName = "Le dilemme de l alternative";
	private static boolean asBoucle = false;
	private static boolean asEnd = true;
	private static boolean asItem = false;
	private static int[] boucleInfo = {0, 300};
	private static int[][] textInfo = {{1110, 65, 90, 157, 11, 43}, {1110, 65, 90, 92, 12, 53}, {1110, 66, 90, 26, 6, 60+5}};
	private static float[] audioInfo = {1.6F, 60+17.8F, 60+25F};
	private static String itemName = null;
	private static float itemTime =  0;
	
	public Le_dilemme_de_l_alternative () {
		super(sceneName, asBoucle, asEnd, asItem, boucleInfo, textInfo, audioInfo, itemName, itemTime);
	}
}

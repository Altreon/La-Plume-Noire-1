package Scene;

public class Ou_l_on_cherche_ce_qu_on_ne_veut_pas_voir extends Scene{
	private static String sceneName = "Ou l on cherche ce qu on ne veut pas voir";
	private static boolean asBoucle = true;
	private static boolean asEnd = true;
	private static boolean asItem = false;
	private static int[] boucleInfo = {0, 300};
	private static int[][] textInfo = {{1110, 98, 90, 124, 1, 60+50}, {1110, 98, 90, 26, 4, 60+58}};
	private static float[] audioInfo = {1.5F, 120+5.8F, 120+16F};
	private static String itemName = null;
	private static float itemTime =  0;
	
	public Ou_l_on_cherche_ce_qu_on_ne_veut_pas_voir () {
		super(sceneName, asBoucle, asEnd, asItem, boucleInfo, textInfo, audioInfo, itemName, itemTime);
	}
}

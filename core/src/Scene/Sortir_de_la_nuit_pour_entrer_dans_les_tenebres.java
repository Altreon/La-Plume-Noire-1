package Scene;

public class Sortir_de_la_nuit_pour_entrer_dans_les_tenebres extends Scene{
	private static String sceneName = "Sortir de la nuit pour entrer dans les tenebres";
	private static boolean asBoucle = true;
	private static boolean asEnd = false;
	private static boolean asItem = false;
	private static int[] boucleInfo = {0, 300};
	private static int[][] textInfo = {{1110, 65, 90, 157, 7, 60+11}, {1110, 65, 90, 92, 10, 60+18}, {1110, 66, 90, 26, 1, 60+24}};
	private static float[] audioInfo = {1.5F, 60+29F};
	private static String itemName = null;
	private static float itemTime =  0;
	
	public Sortir_de_la_nuit_pour_entrer_dans_les_tenebres () {
		super(sceneName, asBoucle, asEnd, asItem, boucleInfo, textInfo, audioInfo, itemName, itemTime);
	}
}

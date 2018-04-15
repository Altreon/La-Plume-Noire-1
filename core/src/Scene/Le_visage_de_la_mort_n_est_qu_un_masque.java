package Scene;

public class Le_visage_de_la_mort_n_est_qu_un_masque extends Scene{
	private static String sceneName = "Le visage de la mort n est qu un masque";
	private static boolean asBoucle = false;
	private static boolean asEnd = false;
	private static boolean asItem = false;
	private static int[] boucleInfo = {0, 0};
	private static int[][] textInfo = {{1110, 98, 90, 124, 14, 120+10}, {1110, 98, 90, 26, 1, 120+18}};
	private static float[] audioInfo = {1.7F, 120+23.2F};
	private static String itemName = null;
	private static float itemTime =  0;
	
	public Le_visage_de_la_mort_n_est_qu_un_masque () {
		super(sceneName, asBoucle, asEnd, asItem, boucleInfo, textInfo, audioInfo, itemName, itemTime);
	}
}

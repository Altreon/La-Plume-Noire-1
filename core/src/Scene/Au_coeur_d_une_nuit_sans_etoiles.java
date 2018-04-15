package Scene;

public class Au_coeur_d_une_nuit_sans_etoiles extends Scene{
	private static String sceneName = "Au coeur d une nuit sans etoiles";
	private static boolean asBoucle = true;
	private static boolean asEnd = false;
	private static boolean asItem = false;
	private static int[] boucleInfo = {0, 300};
	private static int[][] textInfo = {{1110, 65, 90, 157, 6, 60+32}, {1110, 65, 90, 92, 2, 60+42}, {1110, 66, 90, 26, 5, 60+48}};
	private static float[] audioInfo = {1.35F, 60+53F};
	private static String itemName = null;
	private static float itemTime =  0;
	
	public Au_coeur_d_une_nuit_sans_etoiles () {
		super(sceneName, asBoucle, asEnd, asItem, boucleInfo, textInfo, audioInfo, itemName, itemTime);
	}
}

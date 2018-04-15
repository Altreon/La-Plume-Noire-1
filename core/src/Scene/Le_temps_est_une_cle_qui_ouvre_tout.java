package Scene;

public class Le_temps_est_une_cle_qui_ouvre_tout extends Scene{
	private static String sceneName = "Le temps est une cle qui ouvre tout";
	private static boolean asBoucle = false;
	private static boolean asEnd = false;
	private static boolean asItem = true;
	private static int[] boucleInfo = {0, 0};
	private static int[][] textInfo = {{1110, 98, 90, 124, 10, 60+41}, {1110, 98, 90, 26, 6, 60+46}};
	private static float[] audioInfo = {1.3F, 60+52F};
	private static String itemName = "Anneau";
	private static float itemTime =  60+37.3F;
	
	public Le_temps_est_une_cle_qui_ouvre_tout () {
		super(sceneName, asBoucle, asEnd, asItem, boucleInfo, textInfo, audioInfo, itemName, itemTime);
	}
}

package Scene;

public class La_ou_la_lumiere_ne_vient_plus extends Scene{
	private static String sceneName = "La ou la lumiere ne vient plus";
	private static boolean asBoucle = false;
	private static boolean asEnd = false;
	private static boolean asItem = false;
	private static int[] boucleInfo = {0, 0};
	private static int[][] textInfo = {{1110, 98, 90, 124, 10, 48}, {1110, 98, 90, 26, 8, 54}};
	private static float[] audioInfo = {1.6F, 60+1.3F};
	private static String itemName = null;
	private static float itemTime =  0;
	
	public La_ou_la_lumiere_ne_vient_plus () {
		super(sceneName, asBoucle, asEnd, asItem, boucleInfo, textInfo, audioInfo, itemName, itemTime);
	}
}

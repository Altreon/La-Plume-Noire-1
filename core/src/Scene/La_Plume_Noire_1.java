package Scene;

public class La_Plume_Noire_1 extends Scene{
	private static String sceneName = "La Plume Noire 1";
	private static boolean asBoucle = true;
	private static boolean asEnd = false;
	private static boolean asItem = false;
	private static int[] boucleInfo = {65, 365};
	private static int[][] textInfo = {{1110, 196, 90, 26, 1, 19}};
	private static float[] audioInfo = {2.65F, 36.5F};
	private static String itemName = null;
	private static float itemTime =  0;
	
	public La_Plume_Noire_1 () {
		super(sceneName, asBoucle, asEnd, asItem, boucleInfo, textInfo, audioInfo, itemName, itemTime);
	}
}

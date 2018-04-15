package Scene;

public class La_lumiere_au_service_des_tenebres extends Scene{
	private static String sceneName = "La lumiere au service des tenebres";
	private static boolean asBoucle = true;
	private static boolean asEnd = false;
	private static boolean asItem = false;
	private static int[] boucleInfo = {0, 300};
	private static int[][] textInfo = {{1110, 98, 90, 124, 19, 120}, {1110, 98, 90, 26, 20, 120+10}};
	private static float[] audioInfo = {1.85F, 120+17.2F};
	private static String itemName = null;
	private static float itemTime =  0;
	
	public La_lumiere_au_service_des_tenebres () {
		super(sceneName, asBoucle, asEnd, asItem, boucleInfo, textInfo, audioInfo, itemName, itemTime);
	}
}

package Scene;

public class Celle_qui_separe_l_ombre_de_la_lumiere extends Scene{
	private static String sceneName = "Celle qui separe l ombre de la lumiere";
	private static boolean asBoucle = true;
	private static boolean asEnd = false;
	private static boolean asItem = false;
	private static int[] boucleInfo = {0, 300};
	private static int[][] textInfo = {{1110, 65, 90, 157, 3, 54}, {1110, 65, 90, 92, 6, 60+9}, {1110, 66, 90, 26, 5, 60+14}};
	private static float[] audioInfo = {1.5F, 60+19F};
	private static String itemName = null;
	private static float itemTime =  0;
	
	public Celle_qui_separe_l_ombre_de_la_lumiere () {
		super(sceneName, asBoucle, asEnd, asItem, boucleInfo, textInfo, audioInfo, itemName, itemTime);
	}
}

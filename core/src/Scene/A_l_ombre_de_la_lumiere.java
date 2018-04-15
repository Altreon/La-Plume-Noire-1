package Scene;

public class A_l_ombre_de_la_lumiere extends Scene{
	private static String sceneName = "A l ombre de la lumiere";
	private static boolean asBoucle = false;
	private static boolean asEnd = false;
	private static boolean asItem = false;
	private static int[] boucleInfo = {0, 0};
	private static int[][] textInfo = {{1110, 98, 90, 124, 21, 60+45}, {1110, 98, 90, 26, 22, 60+53}};
	private static float[] audioInfo = {1.7F, 120+5.2F};
	private static String itemName = null;
	private static float itemTime =  0;
	
	public A_l_ombre_de_la_lumiere () {
		super(sceneName, asBoucle, asEnd, asItem, boucleInfo, textInfo, audioInfo, itemName, itemTime);
	}
}

package Scene;

public class SHEOL extends Scene{
	private static String sceneName = "SHEOL";
	private static boolean asBoucle = false;
	private static boolean asEnd = false;
	private static boolean asItem = false;
	private static int[] boucleInfo = {0, 0};
	private static int[][] textInfo = {{1110, 98, 90, 124, 13, 60+21}, {1110, 98, 90, 26, 6, 60+29}};
	private static float[] audioInfo = {1.5F, 60+34.6F};
	private static String itemName = null;
	private static float itemTime =  0;
	
	public SHEOL () {
		super(sceneName, asBoucle, asEnd, asItem, boucleInfo, textInfo, audioInfo, itemName, itemTime);
	}
}

package Scene;

public class Aller_de_l_avant_ou_assurer_ses_arrieres extends Scene{
	private static String sceneName = "Aller de l avant ou assurer ses arrieres";
	private static boolean asBoucle = true;
	private static boolean asEnd = false;
	private static boolean asItem = false;
	private static int[] boucleInfo = {0, 300};
	private static int[][] textInfo = {{1110, 98, 90, 124, 16, 60+21}, {1110, 98, 90, 26, 18, 60+30}};
	private static float[] audioInfo = {1.5F, 60+40.8F};
	private static String itemName = null;
	private static float itemTime =  0;
	
	public Aller_de_l_avant_ou_assurer_ses_arrieres () {
		super(sceneName, asBoucle, asEnd, asItem, boucleInfo, textInfo, audioInfo, itemName, itemTime);
	}
}

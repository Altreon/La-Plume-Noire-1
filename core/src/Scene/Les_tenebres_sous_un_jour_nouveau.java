package Scene;

public class Les_tenebres_sous_un_jour_nouveau extends Scene{
	private static String sceneName = "Les tenebres sous un jour nouveau";
	private static boolean asBoucle = false;
	private static boolean asEnd = false;
	private static boolean asItem = false;
	private static int[] boucleInfo = {0, 0};
	private static int[][] textInfo = {{1110, 98, 90, 124, 9, 60+9}, {1110, 98, 90, 26, 6, 60+15}};
	private static float[] audioInfo = {1.6F, 60+21F};
	private static String itemName = null;
	private static float itemTime =  0;
	
	public Les_tenebres_sous_un_jour_nouveau () {
		super(sceneName, asBoucle, asEnd, asItem, boucleInfo, textInfo, audioInfo, itemName, itemTime);
	}
}

package Scene;

import com.badlogic.gdx.Gdx;
//Import for DebugMode :
//import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;

import la.plume.noire1.LaPlumeNoire1;

public class Scene{
	private FileHandle[] boucle;
	private FileHandle introBeginFile;
	private FileHandle introEndFile;
	private FileHandle textBeginFile;
	private FileHandle textEndFile;
	private FileHandle boucleEndFile;
	private FileHandle endBeginFile;
	private FileHandle endEndFile;
	
	private Texture img;
	private Pixmap introBegin;
	private Pixmap introEnd;
	
	private Texture imgText;
	private Pixmap textBegin;
	private Pixmap textEnd;
	
	private FileHandle audioFile;
	private Music audio;
	private boolean asBoucle;
	private boolean asEnd;
	private boolean asItem;
	private boolean isEnd = false;
	private boolean isItem = false;
	
	private int[] boucleInfo;
	private int[][] textInfo;
	private float[] audioInfo;
	private String itemName;
	private float itemTime;
	
	private int[] preS = new int[25];
	private int[] preTextS = new int[85];
	
	private int frame = 0;
	private long time;
	
	private boolean singleDraw = false;
	
	private int[] frameText;
	private float[] startTimeText;
	private long timeText;
	private boolean[] startText;
	private boolean[] endText;
	
	private boolean firstScene = false;
	private boolean passAudio = false;
	private int stat = 0;
	
	private boolean asText = true;
	private boolean isLastScene = false;
	
	private static boolean isPause = false;
	private static boolean isAudioFinish = false;
		
	public Scene (String sceneName, boolean asBoucle, boolean asEnd, boolean asItem, int[] boucleInfo, int[][] textInfo, float[] audioInfo, String itemName, float itemTime) {
		this.asBoucle = asBoucle;
		this.asEnd = asEnd;
		this.asItem = asItem;
		
		if(Gdx.app.getType().name().equals("Desktop")) {
			loadFile("bin/" + sceneName + "/boucle");
			introBeginFile = Gdx.files.internal("bin/introBegin.jpg");
			textBeginFile = Gdx.files.internal("bin/textBegin.jpg");
			if(asBoucle){
				introEndFile = Gdx.files.internal("bin/" + sceneName + "/boucle/img-0001.jpg");
			}else{
				introEndFile = Gdx.files.internal("bin/" + sceneName + "/introEnd.jpg");
			}
			if(!sceneName.equals("Celui qui se cache dans la lumiere part1")){
				textEndFile = Gdx.files.internal("bin/" + sceneName + "/textEnd.jpg");
			}else{
				asText = false;
			}
			if(asEnd){
				boucleEndFile = Gdx.files.internal("bin/" + sceneName + "/boucleEnd.jpg");
				endBeginFile = Gdx.files.internal("bin/endBegin.jpg");
				endEndFile = Gdx.files.internal("bin/" + sceneName + "/endEnd.jpg");
			}
			this.audioFile = Gdx.files.internal("bin/" + sceneName + "/audio.ogg");
		}else if(Gdx.app.getType().name().equals("Android")) {
			loadFile(LaPlumeNoire1.data + "/" + sceneName + "/boucle");
			introBeginFile = Gdx.files.internal("introBegin.jpg");
			textBeginFile = Gdx.files.internal("textBegin.jpg");
			if(asBoucle){
				introEndFile = Gdx.files.absolute(LaPlumeNoire1.data + "/" + sceneName + "/boucle/img-0001.jpg");
			}else{
				introEndFile = Gdx.files.absolute(LaPlumeNoire1.data + "/" + sceneName + "/introEnd.jpg");
			}
			if(!sceneName.equals("Celui qui se cache dans la lumiere part1")){
				textEndFile = Gdx.files.absolute(LaPlumeNoire1.data + "/" + sceneName + "/textEnd.jpg");
			}else{
				asText = false;
			}
			if(asEnd){
				boucleEndFile = Gdx.files.absolute(LaPlumeNoire1.data + "/" + sceneName + "/boucleEnd.jpg");
				endBeginFile = Gdx.files.internal("endBegin.jpg");
				endEndFile = Gdx.files.absolute(LaPlumeNoire1.data + "/" + sceneName + "/endEnd.jpg");
			}
			this.audioFile = Gdx.files.absolute(LaPlumeNoire1.data + "/" + sceneName + "/audio.ogg");
		}
		
		if(sceneName.equals("Celui qui se cache dans la lumiere part2")){
			isLastScene = true;
		}

		this.boucleInfo = boucleInfo;
		this.textInfo = textInfo;
		this.audioInfo = audioInfo;
		this.itemName = itemName;
		this.itemTime = itemTime;
	}
	
	public void pause(){
		preS = new int[25];
		preTextS = new int[85];
		
		frame = 0;
		
		stat = 0;
		if(!LaPlumeNoire1.isLastScenes() && canRepeat()){
			isAudioFinish = true;
		}
		audio.stop();
		unload();
		isPause = true;
	}
	
	public void unPause () {
		load();
		if(isEnd){
			audio.play();
			audio.setPosition(audioInfo[2]);
		}
		isPause = false;
		time = System.currentTimeMillis();
	}
	
	public void load () {
		audio = Gdx.audio.newMusic(audioFile);
		introBegin = new Pixmap(introBeginFile);
		textBegin = new Pixmap(textBeginFile);
		introEnd = new Pixmap(introEndFile);
		if(asText){
			textEnd = new Pixmap(textEndFile);
		}
		if(!isPause){
			img = new Texture(introBegin);
		}else{
			if(asBoucle){
				Pixmap pixmap = new Pixmap(boucle[0]);
				img = new Texture(pixmap);
				pixmap.dispose();
			}else{
				img = new Texture(introEndFile);
			}
		}
		imgText = new Texture(textBegin);
		singleDraw = false;
	}
	
	public void unload () {
		introBegin.dispose();
		if(asText){
			textBegin.dispose();
			textEnd.dispose();
		}
		introEnd.dispose();
		img.dispose();
		imgText.dispose();
		audio.dispose();
	}
	
	private void loadFile (String folderPath) {
		FileHandle folder = Gdx.files.absolute(folderPath);
		boucle = new FileHandle[folder.list().length];
		int i=0;
		for (FileHandle file : folder.list()) {
			boucle[i] = file;
			i++;
		}
	}
	
	public FileHandle[] getBoucle () {
		return boucle;
	}
	
	public void update () {
		if(isPause){
			unPause();
		}
		
		if(stat == 0){
			if(LaPlumeNoire1.getLanch().isPlaying()){
				if(firstScene){
					if(System.currentTimeMillis() - time >= 60){
						imgApear(true);
						time += 60;
					}
				}else{
					if(System.currentTimeMillis() - time >= 40){
						imgApear(false);
						time += 40;
					}
				}
			}else{
				introBegin.dispose();
				introBegin = new Pixmap(introEndFile);
				introEnd.dispose();
				introEnd = new Pixmap(introBeginFile);
				
				stat++;
				LaPlumeNoire1.getLanch().stop();
				preS = new int[25];
				frameText = new int[textInfo.length];
				startTimeText = new float[textInfo.length];
				timeText = System.currentTimeMillis();
				startText = new boolean[textInfo.length];
				endText = new boolean[textInfo.length];
				for(int i=0; i < textInfo.length; i++){
					startText[i] = true;
					endText[i] = false;
				}
				audio.play();
				if((passAudio || isAudioFinish) && !LaPlumeNoire1.isLastScenes()){
					audio.setPosition(audioInfo[1]);
				}
			}
			
		}else if(stat == 1){
			if(!audio.isPlaying() && !LaPlumeNoire1.isLastScenes()){
				audio.play();
				audio.setPosition(audioInfo[1]);
			}
			
			//DebugMode :
			/*if(Gdx.input.isKeyJustPressed(Keys.ENTER)){
				if(LaPlumeNoire1.isLastScenes()){
					audio.stop();
				}else if(audioInfo[1] > audio.getPosition()){
					audio.setPosition(audioInfo[1]);
					if(asText){
						textBegin.drawPixmap(textEnd, 0, 0);
						for(int i=0; i < endText.length; i++){
							endText[0] = true;
						}
				
						imgText.dispose();
						imgText = new Texture(textBegin);
					}
					if(!passAudio && asItem && !isItem && itemTime <= audio.getPosition()){
						LaPlumeNoire1.addItem(itemName);
						isItem = true;
					}
				}
			}*/
			
			if(asBoucle){
				if(System.currentTimeMillis() - time > 33){
					if(frame < boucleInfo[1]-1){
						frame++;
					}else{
						frame = boucleInfo[0];
					}
					img.dispose();
					Pixmap pixmap = new Pixmap(boucle[frame]);
					img = new Texture(pixmap);
					pixmap.dispose();
					time += 33;
				}
				
			}else{
				if(!singleDraw){
					img = new Texture(introEndFile);
					singleDraw = true;
				}
			}
			
			if(asText && !isLastScene){
				if(System.currentTimeMillis() - timeText >= 40){
					for(int i=0; i < textInfo.length; i++){
						if(isText(i)){
							textApear(i, false);
						}
					}
					timeText += 40;
				}
			}else{
				if(!isLastScene){
					if(!audio.isPlaying()){
						close(false);
						System.out.println("changeScene : " + 24);
						LaPlumeNoire1.changeScene(24);
					}
				}else{
					if(System.currentTimeMillis() - timeText >= 40){
						for(int i=0; i < textInfo.length; i++){
							if(isText(i)){
								textApear(i, false);
							}
						}
						timeText += 40;
					}
					if(!audio.isPlaying()){
						close(false);
						unload();
						stat = 0;
						LaPlumeNoire1.ending();
					}
				}
			}
			if(!passAudio && asItem && !isItem && itemTime <= audio.getPosition()){
				LaPlumeNoire1.addItem(itemName);
				isItem = true;
			}
			
			if(asEnd && audioInfo[2] <= audio.getPosition()){
				LaPlumeNoire1.startEnd();
				isEnd = true;
				preS = new int[55];
				introEnd.dispose();
				introEnd = new Pixmap(endBeginFile);
				textBegin.dispose();
				if(asText){
					textBegin = new Pixmap(textEndFile);
				}
				textEnd.dispose();
				textEnd = new Pixmap(textBeginFile);
				for(int i=0; i < textInfo.length; i++){
					startText[i] = true;
				}
				stat++;
			}
			
		}else if(stat == 2){
			if(audioInfo[2] + 4 > audio.getPosition()){
				if(System.currentTimeMillis() - time >= 40){
					imgEndApear(true);
					if(asText){
						for(int i=0; i < textInfo.length; i++){
							textEndApear(true);
						}
					}
					time += 40;
				}
			}else{
				img.dispose();
				img = new Texture(introEnd);
				imgText.dispose();
				imgText = new Texture(textEnd);
				introBegin.dispose();
				introBegin = new Pixmap(endBeginFile);
				textBegin.dispose();
				textBegin = new Pixmap(textBeginFile);
				introEnd.dispose();
				introEnd = new Pixmap(boucleEndFile);
				textEnd.dispose();
				textEnd = new Pixmap(endEndFile);
				stat++;
			}
			
		}else if(stat == 3){
			if(audioInfo[2] + 8 > audio.getPosition()){
				if(System.currentTimeMillis() - time >= 40){
					imgEndApear(false);
					for(int i=0; i < textInfo.length; i++){
						textEndApear(false);
					}
					time += 40;
				}
			}else{
				preS = new int[25];
				preTextS = new int[85];
				img.dispose();
				img = new Texture(introEnd);
				imgText.dispose();
				imgText = new Texture(textEnd);
				introBegin.dispose();
				introBegin = new Pixmap(boucleEndFile);
				introEnd.dispose();
				introEnd = new Pixmap(introBeginFile);
				if(asText){
					textEnd.dispose();
					textEnd = new Pixmap(textBeginFile);
				}
				LaPlumeNoire1.endEnd();
				stat = -1;
			}
			
		}else if(stat == 4){
			if(LaPlumeNoire1.getLanch().getPosition() < 5){
				if(System.currentTimeMillis() - time >= 40){
					imgApear(false);
					if(asText){
						for(int i=0; i < textInfo.length; i++){
							textApear(i, true);
						}
					}
					time += 40;
				}
			}else{
				if(asText){
					textBegin.drawPixmap(textEnd, 0, 0);
					imgText.dispose();
					imgText = new Texture(textBegin);
				}
				stat = 0;
				preS = new int[25];
				preTextS = new int[85];
				firstScene = false;
				if(!LaPlumeNoire1.isLastScenes()){
					passAudio = true;
				}
				isEnd = false;
				LaPlumeNoire1.change();
			}
		}
	}
	
	public void imgApear (boolean intro) {
		int s = 0;
		if(intro){
			s = 28 - (int)(((10f - LaPlumeNoire1.getLanch().getPosition())/10f) * 28);
		}else{
			s = 29 - (int)(((5f - LaPlumeNoire1.getLanch().getPosition())/5f) * 29);
			if(stat == 0){
				s = s-29;
			}
		}
		if(frame < 6-1){
			frame ++;
		}else{
			frame = 0;
		}
		for(int y=24-frame; y >= 0; y-=6){
			for(int f=0; f < s-preS[y]; f++){
				if(y%2 == 0){
					for(int x=0; x <= 742/2; x+=8){
						for(int a=x; a < x+8; a++){
							introBegin.drawPixel(x+a, (y*14)-s+f, introEnd.getPixel(x+a, (y*14)-s+f));
						}
					}
				}else{
					for(int x=4; x <= 742/2; x+=8){
						for(int a=x; a < x+8; a++){
							introBegin.drawPixel(x+a, (y*14)-s+f, introEnd.getPixel(x+a, (y*14)-s+f));
						}
					}
				}
			}
			preS[y] = s;
		}
		img.dispose();
		img = new Texture(introBegin);
	}
	
	public void imgEndApear (boolean intro) {
		int s = 0;
		if(intro){
			s = 28 - (int)(((4f - (audio.getPosition() - audioInfo[2]))/4f) * 28);
		}else{
			s = 29 - (int)(((4f - (audio.getPosition() - audioInfo[2] - 4))/4f) * 29);
		}
		if(frame < 6-1){
			frame ++;
		}else{
			frame = 0;
		}
		for(int x=frame; x <= 54; x+=6){
			for(int f=0; f < s-preS[x]; f++){
				if(x%2 == 0){
					for(int y=0; y <= 318/2; y+=8){
						for(int a=y; a < y+8; a++){
							introBegin.drawPixel(((x*14)+s-f) - 14, y+a, introEnd.getPixel(((x*14)+s-f) - 14, y+a));
						}
					}
				}else{
					for(int y=4; y <= 318/2; y+=8){
						for(int a=y; a < y+8; a++){
							introBegin.drawPixel(((x*14)+s-f) - 14, y+a, introEnd.getPixel(((x*14)+s-f) - 14, y+a));
						}
					}
				}
			}
			preS[x] = s;
		}
		img.dispose();
		img = new Texture(introBegin);
	}
	
	public void textApear(int i, boolean end){
		int s = 0;
		int startY = 0;
		int endY =0;
		if(!end){
			s = 28 - (int)(((4F - (audio.getPosition()-startTimeText[i]))/4F) * 28);
			if(textInfo.length == 1 || passAudio){
				startY = 0;
				endY = 98;
			}else if(textInfo.length == 2){
				startY = (98/2)*i;
				endY = (98/2)*(i+1);
			}else{
				if(i == 2){
					startY = 66;
					endY = 98;
				}else{
					startY = (65/2)*i;
					endY = (65/2)*(i+1);
				}
			}
		}else{
			s = 29 - (int)(((5f - LaPlumeNoire1.getLanch().getPosition())/5f) * 29);
			startY = 0;
			endY = 98;
		}
		if(frameText[i] < 6-1){
			frameText[i] ++;
		}else{
			frameText[i] = 0;
		}
		for(int x=frameText[i]; x <= 84; x+=6){
			for(int f=0; f < s-preTextS[x]; f++){
				if(x%2 == 0){
					for(int y=startY; y <= endY; y+=8){
						for(int a=y; a < y+8; a++){
							textBegin.drawPixel(((x*14)+s-f) - 14, y+a, textEnd.getPixel(((x*14)+s-f) - 14, y+a));
						}
					}
				}else{
					for(int y=startY+4; y <= endY; y+=8){
						for(int a=y; a < y+8; a++){
							textBegin.drawPixel(((x*14)+s-f) - 14, y+a, textEnd.getPixel(((x*14)+s-f) - 14, y+a));
						}
					}
				}
			}
			preTextS[x] = s;
		}
		
		if(s > 30){
			if(textInfo.length == 1){
				textBegin.drawPixmap(textEnd, 0, 0);
			}else if(textInfo.length == 2){
				textBegin.drawPixmap(textEnd, 0, (98/2)*i, 0, (98/2)*i, 1110, 98);
			}else{
				if(i == 2){
					textBegin.drawPixmap(textEnd, 0, 66, 0, 66, 1110, 66);
				}else{
					textBegin.drawPixmap(textEnd, 0, (65/2)*i, 0, (65/2)*i, 1110, 65);
				}
			}
			endText[i] = true;
		}
		
		imgText.dispose();
		imgText = new Texture(textBegin);
	}
	
	public void textEndApear(boolean intro){
		int s = 0;
		int startY = 0;
		int endY = 98;
		if(intro){
			s = 28 - (int)(((4f - (audio.getPosition() - audioInfo[2]))/4f) * 28);
		}else{
			s = 29 - (int)(((4f - (audio.getPosition() - audioInfo[2] - 4))/4f) * 29);
		}
		if(frameText[0] < 6-1){
			frameText[0] ++;
		}else{
			frameText[0] = 0;
		}
		for(int x=frameText[0]; x <= 84; x+=6){
			for(int f=0; f < s-preTextS[x]; f++){
				if(x%2 == 0){
					for(int y=startY; y <= endY; y+=8){
						for(int a=y; a < y+8; a++){
							textBegin.drawPixel(((x*14)+s-f) - 14, y+a, textEnd.getPixel(((x*14)+s-f) - 14, y+a));
						}
					}
				}else{
					for(int y=startY+4; y <= endY; y+=8){
						for(int a=y; a < y+8; a++){
							textBegin.drawPixel(((x*14)+s-f) - 14, y+a, textEnd.getPixel(((x*14)+s-f) - 14, y+a));
						}
					}
				}
			}
			preTextS[x] = s;
		}
		
		if(s > 30){
			textBegin.drawPixmap(textEnd, 0, 0);
			endText[0] = true;
		}
		
		imgText.dispose();
		imgText = new Texture(textBegin);
	}
	
	public Texture getImg() {
		return img;
	}
	
	public Texture getImgText() {
		return imgText;
	}
	
	public Music getAudio () {
		return audio;
	}
	
	public int[] getBoucleInfo() {
		return boucleInfo;
	}
	
	public int[][] getTextInfo() {
		return textInfo;
	}
	
	public float[] getAudioInfo() {
		return audioInfo;
	}
	
	public void passAudio(){
		passAudio = true;
	}
	
	public void reniPassAudio(){
		passAudio = false;
	}
	
	public boolean getPassAudio () {
		return passAudio;
	}
	
	public boolean canClick (int i) {
		if(stat == 1){
			return endText[i];
		}else{
			return false;
		}
	}

	public boolean isText(int i) {
		if(audio.getPosition() >= textInfo[i][5] || passAudio){
			if(startText[i]){
				startTimeText[i] = audio.getPosition();
				startText[i] = false;
			}
			if(!endText[i]){
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
	}
	
	public void setFirstScene() {
		firstScene = true;
	}
	
	public void start() {
		time = System.currentTimeMillis();
	}

	public void close(boolean back) {
		audio.stop();
		isAudioFinish = false;
		textBegin.dispose();
		if(!isEnd && asText){
			textBegin = new Pixmap(textEndFile);
		}else if(asText){
			textBegin = new Pixmap(endEndFile);
		}
		if(asText){
			textEnd.dispose();
			textEnd = new Pixmap(textBeginFile);
		}
		if(back){
			stat = 0;
		}else{
			stat = 4;
			if(!LaPlumeNoire1.isLastScenes()){
				passAudio = true;
			}
		}
		time = System.currentTimeMillis();
	}

	public boolean canRepeat() {
		if(audioInfo[1] <= audio.getPosition() && endText[0] && !asEnd){
			return true;
		}else{
			return false;
		}
	}
}

package la.plume.noire1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Base64Coder;

import Scene.*;
import Item.*;
import End.*;

public class LaPlumeNoire1 extends ApplicationAdapter {
	private static String versionText = "V1.0.1";
	
	public static String data;
	
	private static float ratio;
	private static float ratio2;
	
	private static Batch batch;
	private static Texture cadre;
	private static Texture repeat;
	private static Music lanch;
	private static Sound take;
	private static Sound start;
	
	private static Scene[] scene = new Scene[25];
	private static int preScene = -1;
	private static int currentScene = 0;
	private static int nextScene;
	
	private static boolean sceneLoad = false;
	
	private static Stage stage;
	private static Button[] button;
	
	private static Button repeatButton;
	
	private static List<Item> items = new ArrayList<Item>();
	
	private static File save;
	
	//Install
	private static String packageName;
	private static ZipExtract zipExtract;
	private static Thread zipExtractThread;
	private static boolean isInstall;
	private static boolean isDestination;
	
	private static Texture installBar;
	private static Texture installText;
	private static boolean atInstall;
	//fin Install
	
	//Menu
	private static Texture aienkei;
	private static Texture aienkei2;
	private static Texture imgText;
	private static Texture heip;
	private static Texture clp;
	private static Texture introBegin;
	private static Texture[] rs = new Texture[5];
	private static Button[] rsButton = new Button[5];
	private static BitmapFont versionFont;
	private static Label version;
	private static boolean isBegin = true;
	private static boolean quitBegin = false;
	private static boolean isNew = false;
	private static boolean menuEnd = true;
	private static long beginTime;
	private static long creditsTime;
	private static Sprite img;
	private static int beginStat = 0;
	private static int frame = 65;
	private static long timeB;
	private static boolean isCredits = false;
	private static boolean isEnd = false;
	
	private static Button nouveau;
	private static Button continuer;
	private static Button credits;
	private static Button quitter;
	private static Button retour;
	private static Texture nouveauImg;
	private static Texture continuerImg;
	private static Texture creditsImg;
	private static Texture quitterImg;
	private static Texture retourImg;
	private static Texture whiteBack;
	
	private static Music rain;
	//fin Menu
	
	private static End end;
	
	private boolean returnM = false;
	
	public LaPlumeNoire1 (String name) {
		packageName = name;
	}
					
	@Override
	public void create () {
		data = Gdx.files.getLocalStoragePath() + "/Scene";
		
		Gdx.input.setCatchBackKey(true);
		
		ratio = Gdx.graphics.getWidth()/1280F;
		ratio2 = (float)((16D/9D)-((float)Gdx.graphics.getWidth()/(float)Gdx.graphics.getHeight()));
		ratio2 = (float)(ratio2*(9D/16D));
		
		batch = new SpriteBatch();
		batch.getProjectionMatrix().setToOrtho2D(0, 720/2-(720+(720*ratio2))/2, 1280, 720+(720*ratio2));
		if(Gdx.app.getType().name().equals("Desktop")) {
			repeat = new Texture(Gdx.files.internal("bin/repeat.png"));
			cadre = new Texture(Gdx.files.internal("bin/cadre.png"));
			versionFont = new BitmapFont(Gdx.files.internal("bin/versionFont/version.fnt"), false);
		
			lanch = Gdx.audio.newMusic(Gdx.files.internal("bin/lanch.ogg"));
			take = Gdx.audio.newSound(Gdx.files.internal("bin/take.ogg"));
			start = Gdx.audio.newSound(Gdx.files.internal("bin/start.ogg"));
			rain = Gdx.audio.newMusic(Gdx.files.internal("bin/menu/rain.ogg"));
		
			Pixmap pm = new Pixmap(Gdx.files.internal("bin/blackfeather.png"));
			Gdx.graphics.setCursor(Gdx.graphics.newCursor(pm, 0, 0));
			pm.dispose();
		}else if(Gdx.app.getType().name().equals("Android")) {
			repeat = new Texture(Gdx.files.internal("repeat.png"));
			cadre = new Texture(Gdx.files.internal("cadre.png"));
			versionFont = new BitmapFont(Gdx.files.internal("versionFont/version.fnt"), false);
		
			lanch = Gdx.audio.newMusic(Gdx.files.internal("lanch.ogg"));
			take = Gdx.audio.newSound(Gdx.files.internal("take.ogg"));
			start = Gdx.audio.newSound(Gdx.files.internal("start.ogg"));
			rain = Gdx.audio.newMusic(Gdx.files.internal("menu/rain.ogg"));
		
			Pixmap pm = new Pixmap(Gdx.files.internal("blackfeather.png"));
			Gdx.graphics.setCursor(Gdx.graphics.newCursor(pm, 0, 0));
			pm.dispose();
		}
				
		save = new File(Gdx.files.getLocalStoragePath() + "/save.txt");
		
		end = new End();
		
		stage = new Stage();
		
		if(Gdx.app.getType().name().equals("Android")) {
			zipExtract = new ZipExtract(packageName);
			zipExtractThread = new Thread(zipExtract);
			isInstall = zipExtract.isInstall();
			isDestination = zipExtract.obbDestinationExist();
			if(isInstall){
				installBar = new Texture(Gdx.files.internal("install/installBar.png"));
				installText = new Texture(Gdx.files.internal("install/installText.png"));
				zipExtractThread.start();
				atInstall = true;
			}else if(!zipExtract.obbDestinationExist()){
				installText = new Texture(Gdx.files.internal("install/installNotFound.png"));
				Gdx.input.setCatchBackKey(false);
			}else{
				scene[0] = new La_Plume_Noire_1();
				loadBegin();
				atInstall = false;
			}
		}else{
			scene[0] = new La_Plume_Noire_1();
			loadBegin();
			isInstall = false;
			isDestination = true;
			atInstall = false;
		}
	}
	
	@Override
	public void pause () {
		if(Gdx.app.getType().name().equals("Android")) {
			if(isBegin){
				beginStat = 0;
				frame = 65;
				isCredits = false;
				if(beginStat >=3){
					unloadBegin2();
					deleteButton();
				}
			}else if(isEnd){
				end.pause();
			}else{
				scene[currentScene].pause();
			}
		}
	}
	
	@Override
	public void resume () {
		if(Gdx.app.getType().name().equals("Android")) {
			if(isBegin){
				loadBegin();
				if(beginStat >=3){
					loadBegin2();
				}
				beginTime = System.currentTimeMillis();
			}else if(isEnd){
				end.unPause();
			}else{
				scene[currentScene].unPause();
			}
		}
	}

	
	private static void loadScene(){
		scene[1] = new Au_coeur_d_une_nuit_sans_etoiles();
		scene[2] = new Celle_qui_separe_l_ombre_de_la_lumiere();
		scene[3] = new Ou_l_on_cherche_ce_qu_on_ne_veut_pas_voir();
		scene[4] = new Libre_de_s_echapper();
		scene[5] = new Les_lumieres_de_la_voix_eteinte();
		scene[6] = new Sortir_de_la_nuit_pour_entrer_dans_les_tenebres();
		scene[7] = new La_ou_la_lumiere_ne_vient_plus();
		scene[8] = new Les_tenebres_sous_un_jour_nouveau();
		scene[9] = new Le_temps_est_une_cle_qui_ouvre_tout();
		scene[10] = new Le_dilemme_de_l_alternative();
		scene[11] = new Cache_a_la_vue_de_tous();
		scene[12] = new SHEOL();
		scene[13] = new Comme_un_ver_dans_la_pomme();
		scene[14] = new Un_doute_decisif();
		scene[15] = new Aller_de_l_avant_ou_assurer_ses_arrieres();
		scene[16] = new Ecouter_ou_ecourter();
		scene[17] = new Le_dernier_acte_de_l_eternite();
		scene[18] = new La_lumiere_au_service_des_tenebres();
		scene[19] = new Le_visage_de_la_mort_n_est_qu_un_masque();
		scene[20] = new A_l_ombre_de_la_lumiere();
		scene[21] = new Ce_visage_sur_son_masque();
		scene[22] = new Trouve_les_trois_pour_n_en_faire_qu_une();
		scene[23] = new Celui_qui_se_cache_dans_la_lumiere_part1();
		scene[24] = new Celui_qui_se_cache_dans_la_lumiere_part2();
	}
	
	private static void loadBegin(){
		if(Gdx.app.getType().name().equals("Desktop")) {
			aienkei = new Texture(Gdx.files.internal("bin/menu/Aienkei.png"));
		}else if(Gdx.app.getType().name().equals("Android")) {
			aienkei = new Texture(Gdx.files.internal("menu/Aienkei.png"));
		}
				
		beginTime = System.currentTimeMillis();
	}
	
	private static void loadBegin2(){
		if(Gdx.app.getType().name().equals("Desktop")) {
			aienkei2 = new Texture(Gdx.files.internal("bin/menu/Aienkei2.png"));
			heip = new Texture(Gdx.files.internal("bin/menu/heip.png"));
			clp = new Texture(Gdx.files.internal("bin/menu/clp.png"));
			introBegin = new Texture(Gdx.files.internal("bin/introBegin.jpg"));
			rs[0] = new Texture(Gdx.files.internal("bin/menu/patreon.png"));
			rs[1] = new Texture(Gdx.files.internal("bin/menu/youtube.png"));
			rs[2] = new Texture(Gdx.files.internal("bin/menu/twitter.png"));
			rs[3] = new Texture(Gdx.files.internal("bin/menu/facebook.png"));
			rs[4] = new Texture(Gdx.files.internal("bin/menu/google+.png"));
			nouveauImg = new Texture(Gdx.files.internal("bin/menu/Nouveau.png"));
			continuerImg = new Texture(Gdx.files.internal("bin/menu/Continuer.png"));
			creditsImg = new Texture(Gdx.files.internal("bin/menu/Credits.png"));
			quitterImg = new Texture(Gdx.files.internal("bin/menu/Quitter.png"));
			retourImg = new Texture(Gdx.files.internal("bin/menu/Retour.png"));
			imgText = new Texture(Gdx.files.internal("bin/textBegin.jpg"));
		}else if(Gdx.app.getType().name().equals("Android")) {
			aienkei2 = new Texture(Gdx.files.internal("menu/Aienkei2.png"));
			heip = new Texture(Gdx.files.internal("menu/heip.png"));
			clp = new Texture(Gdx.files.internal("menu/clp.png"));
			introBegin = new Texture(Gdx.files.internal("introBegin.jpg"));
			rs[0] = new Texture(Gdx.files.internal("menu/patreon.png"));
			rs[1] = new Texture(Gdx.files.internal("menu/youtube.png"));
			rs[2] = new Texture(Gdx.files.internal("menu/twitter.png"));
			rs[3] = new Texture(Gdx.files.internal("menu/facebook.png"));
			rs[4] = new Texture(Gdx.files.internal("menu/google+.png"));
			nouveauImg = new Texture(Gdx.files.internal("menu/Nouveau.png"));
			continuerImg = new Texture(Gdx.files.internal("menu/Continuer.png"));
			creditsImg = new Texture(Gdx.files.internal("menu/Credits.png"));
			quitterImg = new Texture(Gdx.files.internal("menu/Quitter.png"));
			retourImg = new Texture(Gdx.files.internal("menu/Retour.png"));
			imgText = new Texture(Gdx.files.internal("textBegin.jpg"));
		}
		menuEnd = true;
		System.out.println("The data location : " + data);
		img = new Sprite(new Texture(scene[0].getBoucle()[65]));
		img.setPosition(273, 321);
		
		LabelStyle versionLabelStyle = new LabelStyle();
		versionLabelStyle.font = versionFont;
		version = new Label(versionText, versionLabelStyle);
		version.setPosition(0, 720-16-1);
		
		rsButton[0] = new Button();
		rsButton[0].setSize(rs[0].getWidth()*ratio, rs[0].getHeight()*ratio);
		rsButton[0].setPosition(Gdx.graphics.getWidth()/2-(rs[0].getWidth()*ratio/2) + (-32*ratio*9) + (100*ratio)*0, Gdx.graphics.getHeight()/2-(rs[0].getHeight()*ratio/2) + 100*ratio);
		rsButton[0].addListener(new ClickListener() {
			@Override
			public void clicked (InputEvent e, float x, float y) {
				Gdx.net.openURI("https://www.patreon.com/Aienkei");
			}
		});
		rsButton[1] = new Button();
		rsButton[1].setSize(rs[1].getWidth()*ratio, rs[1].getHeight()*ratio);
		rsButton[1].setPosition(Gdx.graphics.getWidth()/2-(rs[1].getWidth()*ratio/2) + (-32*ratio*9) + (100*ratio)*1, Gdx.graphics.getHeight()/2-(rs[1].getHeight()*ratio/2) + 100*ratio);
		rsButton[1].addListener(new ClickListener() {
			@Override
			public void clicked (InputEvent e, float x, float y) {
				Gdx.net.openURI("https://www.youtube.com/user/Aienkei");
			}
		});
		rsButton[2] = new Button();
		rsButton[2].setSize(rs[2].getWidth()*ratio, rs[2].getHeight()*ratio);
		rsButton[2].setPosition(Gdx.graphics.getWidth()/2-(rs[2].getWidth()*ratio/2) + (-32*ratio*0.2F) + (80*ratio)*2, Gdx.graphics.getHeight()/2-(rs[2].getHeight()*ratio/2) + 100*ratio);
		rsButton[2].addListener(new ClickListener() {
			@Override
			public void clicked (InputEvent e, float x, float y) {
				Gdx.net.openURI("https://twitter.com/Aienkei");
			}
		});
		rsButton[3] = new Button();
		rsButton[3].setSize(rs[3].getWidth()*ratio, rs[3].getHeight()*ratio);
		rsButton[3].setPosition(Gdx.graphics.getWidth()/2-(rs[3].getWidth()*ratio/2) + (-32*ratio*0.2F) + (80*ratio)*3, Gdx.graphics.getHeight()/2-(rs[3].getHeight()*ratio/2) + 100*ratio);
		rsButton[3].addListener(new ClickListener() {
			@Override
			public void clicked (InputEvent e, float x, float y) {
				Gdx.net.openURI("https://www.facebook.com/Aienkei");
			}
		});
		rsButton[4] = new Button();
		rsButton[4].setSize(rs[4].getWidth()*ratio, rs[4].getHeight()*ratio);
		rsButton[4].setPosition(Gdx.graphics.getWidth()/2-(rs[4].getWidth()*ratio/2) + (-32*ratio*0.2F) + (80*ratio)*4, Gdx.graphics.getHeight()/2-(rs[4].getHeight()*ratio/2) + 100*ratio);
		rsButton[4].addListener(new ClickListener() {
			@Override
			public void clicked (InputEvent e, float x, float y) {
				Gdx.net.openURI("https://plus.google.com/+AienkeiFreedom");
			}
		});
		
		Pixmap pixmap = new Pixmap(742, 318, Format.RGB565);
		pixmap.setColor(1, 1, 1, 1);
		pixmap.fillRectangle(0, 0, 742, 318);
		whiteBack = new Texture(pixmap);
		pixmap.dispose();
		
		rain.play();
		rain.setVolume(0);
		rain.setLooping(true);
				
		beginTime = System.currentTimeMillis();
		timeB = System.currentTimeMillis();
	}
	
	private static void loadBeginButton(){
		stage.dispose();
		stage = new Stage();
		nouveau = new Button();
		nouveau.setSize(nouveauImg.getWidth()*ratio, nouveauImg.getHeight()*ratio);
		nouveau.setPosition(Gdx.graphics.getWidth()/2-(nouveauImg.getWidth()*ratio/2) + -300*ratio, Gdx.graphics.getHeight()/2-(nouveauImg.getHeight()*ratio/2) + -190*ratio);
		nouveau.addListener(new ClickListener() {
			@Override
			public void clicked (InputEvent e, float x, float y) {
				quitBegin = true;
				deleteButton();
				isNew = true;
				creditsTime = System.currentTimeMillis();
			}
		});
		continuer = new Button();
		continuer.setSize(continuerImg.getWidth()*ratio, continuerImg.getHeight()*ratio);
		continuer.setPosition(Gdx.graphics.getWidth()/2-(continuerImg.getWidth()*ratio/2) + -300*ratio, Gdx.graphics.getHeight()/2-(continuerImg.getHeight()*ratio/2) + -280*ratio);
		continuer.addListener(new ClickListener() {
			@Override
			public void clicked (InputEvent e, float x, float y) {
				quitBegin = true;
				deleteButton();
				isNew = false;
				creditsTime = System.currentTimeMillis();
			}
		});
		credits = new Button();
		credits.setSize(creditsImg.getWidth()*ratio, creditsImg.getHeight()*ratio);
		credits.setPosition(Gdx.graphics.getWidth()/2-(creditsImg.getWidth()*ratio/2) + 300*ratio, Gdx.graphics.getHeight()/2-(creditsImg.getHeight()*ratio/2) + -190*ratio);
		credits.addListener(new ClickListener() {
			@Override
			public void clicked (InputEvent e, float x, float y) {
				batch.setColor(1, 1, 1, 1);
				stage.dispose();
				stage = new Stage();
				for(Button button : rsButton){
					stage.addActor(button);
				}
				stage.addActor(retour);
				Gdx.input.setInputProcessor(stage);
				isCredits = true;
				creditsTime = System.currentTimeMillis();
			}
		});
		quitter = new Button();
		quitter.setSize(quitterImg.getWidth()*ratio, quitterImg.getHeight()*ratio);
		quitter.setPosition(Gdx.graphics.getWidth()/2-(quitterImg.getWidth()*ratio/2) + 300*ratio, Gdx.graphics.getHeight()/2-(quitterImg.getHeight()*ratio/2) + -280*ratio);
		quitter.addListener(new ClickListener() {
			@Override
			public void clicked (InputEvent e, float x, float y) {
				Gdx.app.exit();
			}
		});
		stage.addActor(nouveau);
		stage.addActor(continuer);
		stage.addActor(credits);
		stage.addActor(quitter);
		
		retour = new Button();
		retour.setSize(retourImg.getWidth()*ratio, retourImg.getHeight()*ratio);
		retour.setPosition(Gdx.graphics.getWidth()/2-(retourImg.getWidth()*ratio/2), Gdx.graphics.getHeight()/2-(retourImg.getHeight()*ratio/2) + -240*ratio);
		retour.addListener(new ClickListener() {
			@Override
			public void clicked (InputEvent e, float x, float y) {
				stage.dispose();
				stage = new Stage();
				stage.addActor(nouveau);
				stage.addActor(continuer);
				stage.addActor(credits);
				stage.addActor(quitter);
				Gdx.input.setInputProcessor(stage);
				isCredits = false;
				creditsTime = System.currentTimeMillis();
			}
		});
		
		Gdx.input.setInputProcessor(stage);
	}
	
	private static void deleteButton(){
		stage.dispose();
		stage = new Stage();
		Gdx.input.setInputProcessor(null);
	}
	
	private static void unloadBegin () {
		rain.stop();
		aienkei.dispose();
		aienkei2.dispose();
		heip.dispose();
		clp.dispose();
		rs[0].dispose();
		rs[1].dispose();
		rs[2].dispose();
		rs[3].dispose();
		rs[4].dispose();
		nouveauImg.dispose();
		continuerImg.dispose();
		creditsImg.dispose();
		quitterImg.dispose();
		retourImg.dispose();
		whiteBack.dispose();
		img.getTexture().dispose();
		System.gc();
		
		stage.dispose();
		stage = new Stage();
	}
	
	public void unloadBegin2 () {
		introBegin.dispose();
		imgText.dispose();
	}
	
	public static void endInstall () {
		installBar.dispose();
		installText.dispose();
		isInstall = false;
		isDestination = true;
	}

	@Override
	public void render () {
		if(isInstall || !isDestination){
			Gdx.gl.glClearColor(0, 0, 0, 0);
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
			batch.begin();
			batch.draw(installText, 0, 0);
			if(isInstall){
				batch.draw(installBar, 0, 510, zipExtract.getProgress(), installBar.getHeight());
			}
			batch.end();
		}else{
			
		if(Gdx.input.isKeyJustPressed(Keys.ESCAPE) || Gdx.input.isKeyJustPressed(Keys.BACK)){
			returnMenu();
		}
		
		if(returnM){
			float time = 1 - ((System.currentTimeMillis() - beginTime)/1000F);
			batch.setColor(1, 1, 1, time);
			if(scene[currentScene].getAudio().isPlaying()){
				scene[currentScene].getAudio().setVolume(time);
			}else if(lanch.isPlaying()){
				lanch.setVolume(time);
			}
			if(time <= 0){
				batch.setColor(1, 1, 1, 0);
				if(lanch.isPlaying()){
					lanch.stop();
				}
				lanch.setVolume(1);
				scene[currentScene].close(true);
				scene[currentScene].unload();
				loadBegin2();
				rain.play();
				isBegin = true;
				returnM = false;
				currentScene = 0;
				beginTime = System.currentTimeMillis();
			}
		}
		if(isBegin){
			Gdx.gl.glClearColor(0, 0, 0, 0);
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
			
			if(atInstall){
				scene[0] = new La_Plume_Noire_1();
				loadBegin();
				atInstall = false;
			}
			
			float time = 0;
			if(beginStat == 0){
				time = (System.currentTimeMillis() - beginTime)/1000F;
				batch.setColor(1, 1, 1, time);
				if(time >= 1){
					start.play();
					batch.setColor(1, 1, 1, 1);
					beginStat ++;
					beginTime = System.currentTimeMillis();
				}
			}
			if(beginStat == 1){
				time = (System.currentTimeMillis() - beginTime)/1000F;
				if(time >= 1){
					beginStat ++;
					beginTime = System.currentTimeMillis();
				}
			}
			if(beginStat == 2){
				time = 1-((System.currentTimeMillis() - beginTime)/1000F);
				batch.setColor(1, 1, 1, time);
				if(time <= 0){
					batch.setColor(1, 1, 1, 0);
					beginStat ++;
					loadBegin2();
					beginTime = System.currentTimeMillis();
				}
			}
			if(beginStat < 3){
				Pixmap pixmap = new Pixmap(1280, 720, Pixmap.Format.RGBA4444);
				pixmap.setColor(1, 1, 1, 1);
				pixmap.fill();
				Texture texture = new Texture(pixmap);
				pixmap.dispose();
				
				batch.begin();
				batch.draw(texture, 0, 0);
				batch.draw(aienkei, (int)(1280/2-(aienkei.getWidth()/2)), (int)(720/2-(aienkei.getHeight()/2)));
				batch.end();
				texture.dispose();
			}else{
				
				float alpha = 1;
				if(beginStat == 3){
					time = (System.currentTimeMillis() - beginTime)/1000F;
					batch.setColor(1, 1, 1, time);
					alpha = time;
					if(time >= 1){
						batch.setColor(1, 1, 1, 1);
						rain.setVolume(1);
						alpha = 1;
						loadBeginButton();
						beginStat ++;
					}else{
						rain.setVolume(time);
					}
				}else{
					if(isCredits || quitBegin){
						alpha = 1-((System.currentTimeMillis() - creditsTime)/1000F);
						if(alpha <= 0){
							rain.setVolume(0);
							alpha = 0;
							if(quitBegin){
								batch.setColor(1, 1, 1, 1);
								img.setAlpha(alpha);
								if(!sceneLoad){
									loadScene();
									sceneLoad = true;
								}else{
									for(Scene s : scene){
										s.reniPassAudio();
									}
								}
								if(isNew){
									nouveau();
								}else{
									continuer();
								}
							}
						}else{
							rain.setVolume(alpha);
						}
					}else{
						alpha = (System.currentTimeMillis() - creditsTime)/1000F;
						if(alpha >= 1){
							rain.setVolume(1);
							alpha = 1;
						}else{
							rain.setVolume(alpha);
						}
					}
				}
				img.setAlpha(alpha);
				
				if(System.currentTimeMillis() - timeB > 33){
					if(frame < scene[0].getBoucle().length-1){
						frame++;
					}else{
						frame = 65;
					}
					Pixmap pixmap = new Pixmap(scene[0].getBoucle()[frame]);
					img.getTexture().dispose();
					img.setTexture(new Texture(pixmap));
					pixmap.dispose();
					timeB += 33;
				}
				
				batch.begin();
				if(beginStat == 4 && !quitBegin){
					batch.draw(whiteBack, 273, 321);
					batch.draw(heip, (int)(1280/2-(heip.getWidth()/2)), (int)(720/2-(heip.getHeight()/2) + 230));
					batch.draw(aienkei2, (int)(1280/2-(aienkei2.getWidth()/2)), (int)(720/2-(aienkei2.getHeight()/2) + 115));
					for(int i=0; i < rs.length; i++){
						if(i < 2){
							batch.draw(rs[i], (int)(1280/2-(rs[i].getWidth()/2) + (-32*9) + 100*i), (int)(720/2-(rs[i].getHeight()/2) + 100));
						}else{
							batch.draw(rs[i], (int)(1280/2-(rs[i].getWidth()/2) + (-32*0.2F) + 80*i), (int)(720/2-(rs[i].getHeight()/2) + 100));
						}
					}
					batch.draw(clp, (int)(1280/2-(clp.getWidth()/2)), (int)(720/2-(clp.getHeight()/2) + -20));
				}else if(quitBegin){
					batch.draw(introBegin, 273, 321);
				}
				img.draw(batch);
				batch.draw(imgText, 90, 26);
				if(isCredits){
					batch.draw(retourImg, (int)(1280/2-(retourImg.getWidth()/2)), (int)(720/2-(retourImg.getHeight()/2) + -230));
				}else if(!quitBegin){
					batch.draw(nouveauImg, (int)(1280/2-(nouveauImg.getWidth()/2) + -300), (int)(720/2-(nouveauImg.getHeight()/2) + -190));
					batch.draw(continuerImg, (int)(1280/2-(continuerImg.getWidth()/2) + -300), (int)(720/2-(continuerImg.getHeight()/2) + -280));
					batch.draw(creditsImg, (int)(1280/2-(creditsImg.getWidth()/2) + 300), (int)(720/2-(creditsImg.getHeight()/2) + -190));
					batch.draw(quitterImg, (int)(1280/2-(quitterImg.getWidth()/2) + 300), (int)(720/2-(quitterImg.getHeight()/2) + -280));
				}
				
				batch.draw(cadre, 0, 0);
				if(beginStat == 4 && !quitBegin){
					version.draw(batch, 1);
				}
				batch.end();
			}
		}else if(isEnd){
			end.update();
			batch.begin();
			batch.draw(end.getImg(), 0, 0);
			batch.end();
		}else{	
			Gdx.gl.glClearColor(0, 0, 0, 0);
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
			scene[currentScene].update();
			if(menuEnd){
				unloadBegin2();
				quitBegin = false;
				menuEnd = false;
			}
			if(preScene != -1){
				scene[preScene].unload();
				preScene = -1;
			}
			
			batch.begin();
			batch.draw(scene[currentScene].getImg(), 273, 321);
			batch.draw(scene[currentScene].getImgText(), 90, 26);
			batch.draw(cadre, 0, 0);
			if(!isLastScenes() && preScene == -1 && scene[currentScene].canRepeat()){
				batch.draw(repeat, 1168, 190);
			}
			for(Item item : items){
				batch.draw(item.getTexture(), (int)(item.getX()), (int)(item.getY()+(720-720)/2), (int)(item.getTexture().getWidth()), (int)(item.getTexture().getHeight()));
			}
			batch.end();
		}
		}
	}
	
	private void returnMenu() {
		if(isBegin){
			if(isCredits){
				stage.dispose();
				stage = new Stage();
				stage.addActor(nouveau);
				stage.addActor(continuer);
				stage.addActor(credits);
				stage.addActor(quitter);
				Gdx.input.setInputProcessor(stage);
				isCredits = false;
				creditsTime = System.currentTimeMillis();
			}
		}else if(!isEnd){
			returnM = true;
			beginStat = 3;
			beginTime = System.currentTimeMillis();
		}
	}

	public static void changeScene (int nextScene) {
		if(nextScene == 22 && haveObject()){
			nextScene = 23;
		}
		scene[nextScene].load();
		LaPlumeNoire1.nextScene = nextScene;
			
		stage.dispose();
		stage = new Stage();
		if(nextScene != 23){
			if(nextScene != 24){
				button = new Button[scene[nextScene].getTextInfo().length];
				for(int i=0; i < button.length; i++){
					button[i] = new Button();
					button[i].setSize(scene[nextScene].getTextInfo()[i][0]*ratio, scene[nextScene].getTextInfo()[i][1]*ratio);
					button[i].setPosition(scene[nextScene].getTextInfo()[i][2]*ratio, scene[nextScene].getTextInfo()[i][3]*ratio+(Gdx.graphics.getHeight()-720*ratio)/2);
					final int i2 = i;
					final int nextScene2 = nextScene;
					button[i].addListener(new ClickListener() {
						@Override
						public void clicked (InputEvent e, float x, float y) {
							if(scene[currentScene].canClick(i2)){
								scene[currentScene].close(false);
								System.out.println("changeScene : " + scene[nextScene2].getTextInfo()[i2][4]);
								changeScene(scene[nextScene2].getTextInfo()[i2][4]);
							}
						}
					});
					stage.addActor(button[i]);
				}
		
				repeatButton = new Button();
				repeatButton.setSize(32*ratio, 32*ratio);
				repeatButton.setPosition(1168*ratio, 190*ratio+(Gdx.graphics.getHeight()-720*ratio)/2);
				repeatButton.addListener(new ClickListener() {
					@Override
					public void clicked (InputEvent e, float x, float y) {
						if(!isLastScenes() && preScene == -1 && scene[currentScene].canRepeat()){
							scene[currentScene].getAudio().setPosition(scene[currentScene].getAudioInfo()[0]);
						}
					}
				});
		
				stage.addActor(repeatButton);
			}else{
				button = new Button[scene[nextScene].getTextInfo().length];
				button[0] = new Button();
				button[0].setSize(scene[nextScene].getTextInfo()[0][0]*ratio, scene[nextScene].getTextInfo()[0][1]*ratio);
				button[0].setPosition(scene[nextScene].getTextInfo()[0][2]*ratio, scene[nextScene].getTextInfo()[0][3]*ratio+(Gdx.graphics.getHeight()-720*ratio)/2);
				button[0].addListener(new ClickListener() {
					@Override
					public void clicked (InputEvent e, float x, float y) {
						if(scene[currentScene].canClick(0)){
							Gdx.net.openURI("https://www.youtube.com/watch?v=TMRjbN1Aotw");
						}
					}
				});
				stage.addActor(button[0]);
			}
		}
		Gdx.input.setInputProcessor(stage);
		
		LaPlumeNoire1.nextScene = nextScene;
		lanch.play();
	}
	 
	private static boolean haveObject() {
		boolean have[] = new boolean[3];
		for(Item item : items){
			if(item.getName().equals("Manche")){
				have[0] = true;
			}
			if(item.getName().equals("Anneau")){
				have[1] = true;
			}
			if(item.getName().equals("Lame")){
				have[2] = true;
			}
		}
		if(have[0] && have[1] && have[2]){
			return true;
		}else{
			return false;
		}
	}

	public static Music getLanch () {
		return lanch;
	}
	
	public static boolean isLastScenes(){
		if(currentScene == 23 || currentScene == 24){
			return true;
		}else{
			return false;
		}
	}

	public static void change() {
		preScene = currentScene;
		currentScene = nextScene;
		save();
		scene[currentScene].start();
		System.gc();
	}
	
	public static void startEnd() {
		stage.dispose();
		stage = new Stage();
		button = new Button[0];
		repeatButton = new Button();
	}
	
	public static void endEnd() {
		stage.dispose();
		stage = new Stage();
		button = new Button[1];
		button[0] = new Button();
		button[0].setSize(1110*ratio, 196*ratio);
		button[0].setPosition(90*ratio, 26*ratio+(Gdx.graphics.getHeight()-720*ratio)/2);
		button[0].addListener(new ClickListener() {
			@Override
			public void clicked (InputEvent e, float x, float y) {
				scene[currentScene].close(false);
				System.out.println("changeScene : " + "1");
				changeScene(1);
			}
		});
		stage.addActor(button[0]);
		Gdx.input.setInputProcessor(stage);
	}
	
	public static void addItem (String name) {
		try {
			items.add((Item)Class.forName("Item." + name).getConstructor().newInstance());
			take.play();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void nouveau () {
		unloadBegin();
		try {
			PrintWriter writer = new PrintWriter(save, "UTF-8");
			String write = "";
			write += "0:";
			write += "false:false:false:";
			for(int i=0; i < scene.length; i++){
				write += "false:";
			}
			writer.print(Base64Coder.encodeString(write));
			writer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		scene[currentScene].setFirstScene();
		changeScene(currentScene);
		scene[currentScene].start();
		isBegin = false;
	}
	
	public static void continuer () {
		if(save.exists() && !save.isDirectory()){
			unloadBegin();
			try {
				Scanner sc = new Scanner(save);
				StringTokenizer info = new StringTokenizer(Base64Coder.decodeString(sc.nextLine()), ":");
				currentScene = Integer.parseInt(info.nextToken());
				if(Boolean.parseBoolean(info.nextToken())){
					items.add(new Manche());
				}
				if(Boolean.parseBoolean(info.nextToken())){
					items.add(new Anneau());
				}
				if(Boolean.parseBoolean(info.nextToken())){
					items.add(new Lame());
				}
				for(int i=0; i < scene.length; i++){
					if(Boolean.parseBoolean(info.nextToken())){
						scene[i].passAudio();
					}
				}
				sc.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		
			scene[currentScene].setFirstScene();
			lanch.play();
			changeScene(currentScene);
			scene[currentScene].start();
			isBegin = false;
		}else{
			nouveau();
		}
	}
	
	public static void ending () {
		end.load();
		isEnd = true;
		System.gc();
	}
	
	public static void reboot () {
		isEnd = false;
		isBegin = true;
		beginStat = 0;
		loadBegin();
		System.gc();
	}
	
	public static void save () {
		try {
			PrintWriter writer = new PrintWriter(save, "UTF-8");
			String write = "";
			write += currentScene + ":";
			boolean have[] = new boolean[3];
			for(Item item : items){
				if(item.getName().equals("Manche")){
					have[0] = true;
				}
				if(item.getName().equals("Anneau")){
					have[1] = true;
				}
				if(item.getName().equals("Lame")){
					have[2] = true;
				}
			}
			for(boolean b : have){
				if(b){
					write += "true" + ":";
				}else{
					write += "false" + ":";
				}
			}
			for(int i=0; i < scene.length; i++){
				write += scene[i].getPassAudio() + ":";
			}
			writer.print(Base64Coder.encodeString(write));
			writer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

package Item;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Item {
	private String name;
	private int[] coords;
	private Texture texture;
	
	public Item (String name, int[] coords) {
		this.name = name;
		this.coords = coords;
		if(Gdx.app.getType().name().equals("Desktop")){
			texture = new Texture("bin/item/" + name + ".png");
		}else if(Gdx.app.getType().name().equals("Android")){
			texture = new Texture("item/" + name + ".png");
		}
	}
	
	public String getName () {
		return name;
	}
	
	public int getX () {
		return coords[0];
	}
	
	public int getY () {
		return coords[1];
	}
	
	public Texture getTexture () {
		return texture;
	}
}

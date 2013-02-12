package org.snowink.tka;
import java.awt.Color;


public class Block implements Cloneable {

	private boolean block = false;

	private Color color = Color.BLACK;
	private Color color_light = Color.BLACK;
	private Color color_dark = Color.BLACK;
	
	private Color shadow_color = Color.GRAY;
	private Color shadow_color_light = Color.GRAY;
	private Color shadow_color_dark = Color.GRAY;

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
	
	public void createBlock() {
		block = true;
	}
	
	public Color getColor() {
		return color;
	}

	public Color getColorDark() {
		return color_dark;
	}

	public Color getColorLight() {
		return color_light;
	}

	public Color getShadowColor() {
		return shadow_color;
	}

	public Color getShadowColorDark() {
		return shadow_color_dark;
	}

	public Color getShadowColorLight() {
		return shadow_color_light;
	}
	
	public boolean isBlock() {
		return block;
	}
	
	public void removeBlock() {
		block = false;
	}
	
	public void setColors(Color color, Color color_light, Color color_dark) {
		this.color = color;
		this.color_light = color_light;
		this.color_dark = color_dark;
	}
	
	public void setShadowColors(Color shadow_color, Color shadow_color_light, Color shadow_color_dark) {
		this.shadow_color = shadow_color;
		this.shadow_color_light = shadow_color_light;
		this.shadow_color_dark = shadow_color_dark;
	}

}

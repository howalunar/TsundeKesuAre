package jp.snowink.tka;

import java.awt.Point;

public class Move {
	
	private Field field;
	private Point point;
	private int rotate;
	
	public Move(Field field, Point point, int rotate) {
		this.field = field;
		this.point = point;
		this.rotate = rotate;
	}

	public Field getField() {
		return field;
	}

	public void setField(Field field) {
		this.field = field;
	}

	public Point getPoint() {
		return point;
	}

	public void setPoint(Point point) {
		this.point = point;
	}

	public int getRotate() {
		return rotate;
	}

	public void setRotate(int rotate) {
		this.rotate = rotate;
	}
	
	
	
}

package org.snowink.tka;

import java.awt.Point;

import org.snowink.tka.mino.Mino;


public class Move {
	
	private Controller c;

	private Mino mino;
	private Point point;
	private int rotate;
	private boolean hold = false;
	
	public Move(boolean hold) {
		this.hold = hold;
	}
	
	public Move(Controller c, Mino mino, Point point, int rotate) {
		this.c = c;
		this.mino = mino;
		this.point = point;
		this.rotate = rotate;
		this.hold = false;
	}
	
	public Move(Field field, Mino mino, Point point, int rotate) {
		this.c = new Controller(field);
		this.mino = mino;
		this.point = point;
		this.rotate = rotate;
		this.hold = false;
	}

	public Controller getController() {
		return c;
	}

	public void setController(Controller c) {
		this.c = c;
	}
	
	public Field getField() {
		return c.getField();
	}
	
	public Mino getMino() {
		return mino;
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
	
	public String toString() {
		if (hold) {
			return "HOLD";
		}
		else {
			return mino.getName() + "(" + rotate + ") [" + point.x + ", " + point.y + "]";
		}
	}
	
}

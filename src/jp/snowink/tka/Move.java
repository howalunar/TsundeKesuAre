package jp.snowink.tka;

import java.awt.Point;

import jp.snowink.tka.mino.Mino;

public class Move {
	
	private Field field;
	private Mino mino;
	private Point point;
	private int rotate;
	private boolean hold = false;
	
	public Move(boolean hold) {
		this.hold = hold;
	}
	
	public Move(Field field, Mino mino, Point point, int rotate) {
		this.field = field;
		this.mino = mino;
		this.point = point;
		this.rotate = rotate;
		this.hold = false;
	}

	public Field getField() {
		return field;
	}

	public void setField(Field field) {
		this.field = field;
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

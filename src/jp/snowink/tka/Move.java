package jp.snowink.tka;

import java.awt.Point;

import jp.snowink.tka.mino.Mino;

public class Move {
	
	private Field field;
	private Mino mino;
	private Point point;
	private int rotate;
	
	public Move(Field field, Mino mino, Point point, int rotate) {
		this.field = field;
		this.mino = mino;
		this.point = point;
		this.rotate = rotate;
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
		return mino.getName() + "(" + rotate + ") [" + point.x + ", " + point.y + "]";
	}
	
}

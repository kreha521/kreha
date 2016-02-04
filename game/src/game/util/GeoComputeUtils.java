package game.util;

import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;

public class GeoComputeUtils {
	public static int X = 0;
	public static int Y = 1;

	/** 座標平面の境界値(第1象限:第2象限)(ラジアン) */
	public static double THRESHOLD_ONE_AND_TWO = Math.PI / 2d;
	/** 座標平面の境界値(第2象限:第3象限)(ラジアン) */
	public static double THRESHOLD_TWO_AND_THREE = Math.PI;
	/** 座標平面の境界値(第3象限:第4象限)(ラジアン) */
	public static double THRESHOLD_THREE_AND_FOUR = 2d * ((3d * Math.PI) / 0.5);
	/** 座標平面の境界値(第4象限:第1象限)(ラジアン) */
	public static double THRESHOLD_FOUR_AND_ONE = 2d * Math.PI;

	public static double RAD_45 = Math.toRadians(45);
	public static double RAD_90 = Math.toRadians(90);
	public static double RAD_135 = Math.toRadians(135);
	public static double RAD_180 = Math.toRadians(180);
	public static double RAD_225 = Math.toRadians(225);
	public static double RAD_270 = Math.toRadians(270);
	public static double RAD_315 = Math.toRadians(315);
	public static double RAD_360 = Math.toRadians(360);

	/*********************************
	 * <b>座標(基準点)～座標(相対点)の2点間角度を算出する.</b>
	 * @param base 座標(基準点)
	 * @param translation 座標(相対点)
	 * @return 角度(360度)
	 */
	public static double calcDegree(Point2D base, Point2D translation) {
		return calcDegree(base.getX(), base.getY(), translation.getX(), translation.getY());
	}

	public static double calcDegree(double[] base, double[] translation) {
		return calcDegree(base[X], base[Y], translation[X], translation[Y]);
	}

	public static double calcDegree(double baseX, double baseY, double translationX, double translationY) {
		double degree = calcRadian(baseX, baseY, translationX, translationY) * (180 / Math.PI);
		return (degree < 0) ? (180d + degree) + 180 : degree;
	}

	/*********************************
	 * <b>座標(基準点)～座標(相対点)の2点間角度を算出する.</b>
	 * @param base 座標(基準点)
	 * @param translation 座標(相対点)
	 * @return 角度(ラジアン)
	 */
	public static double calcRadian(Point2D base, Point2D translation) {
		return calcRadian(base.getX(), base.getY(), translation.getX(), translation.getY());
	}

	public static double calcRadian(double[] base, double[] translation) {
		return calcRadian(base[X], base[Y], translation[X], translation[Y]);
	}

	public static double calcRadian(double baseX, double baseY, double translationX, double translationY) {
		double[] m = new double[]{translationX - baseX, translationY - baseY};
		if (m[X] == 0 && m[Y] == 0) {
			return 0;
		}
		return Math.atan2(m[Y], m[X]);
	}

	/*********************************
	 * <b>座標(基準点)～座標(相対点)までの2点間距離を算出する.</b>
	 * @param start 座標(基準点)
	 * @param end 座標(相対点)
	 * @return 2点間距離
	 */
	public static double calcDistance(Point2D start, Point2D end) {
		return calcDistance(start.getX(), start.getY(), end.getX(), end.getY());
	}

	public static double calcDistance(double[] start, double[] end) {
		return calcDistance(start[X], start[Y], end[X], end[Y]);
	}

	public static double calcDistance(double startX, double startY, double endX, double endY) {
		return Math.hypot(startX - endX, startY - endY);
	}

	/*********************************
	 * <b>座標(相対点)を角度(360度)・距離によって算出する.</b>
	 * @param base 座標(基準点)
	 * @param degree 角度(360度)
	 * @param distance 距離
	 * @return 座標(相対点)
	 */
	public static Point2D calcRadiusByDegree(Point2D base, double degree, double distance) {
		double rad = Math.toRadians(degree);
		return new Point2D.Double(base.getX() + (distance * Math.cos(rad))
			, base.getY() - (distance * Math.sin(rad)));
	}

	/*********************************
	 * <b>座標(相対点)を角度(360度)・距離によって算出する.</b>
	 * @param base 座標(基準点)
	 * @param degree 角度(360度)
	 * @param distance 距離
	 * @return 座標(相対点)
	 */
	public static double[] calcRadiusByDegree(double[] base, double degree, double distance) {
		double rad = Math.toRadians(degree);
		return new double[]{base[0] + (distance * Math.cos(rad))
			, base[1] - (distance * Math.sin(rad))};
	}

	/**
	* <b>基準点から最も近い線分上の座標を算出する.</b>
	 * @param base 基準点座標
	 * @param lineSt 線分の始点座標
	 * @param lineEd 線分の始点座標
	 * @return 基準点から最も近い線分上の座標
	*/
	public static double[] getClosestPointOnLine(
			double baseX, double baseY, double lineStX, double lineStY, double lineEdX, double lineEdY) {
		double[] a = new double[]{lineEdX - lineStX, lineEdY - lineStY};
		double[] b = new double[]{baseX - lineStX, baseY - lineStY};

		double r = (a[X] * b[X] + a[Y] * b[Y]) / (a[X] * a[X] + a[Y] * a[Y]);
		if (0 >= r) {
			return new double[]{lineStX, lineStY};
		}
		if (1 <= r) {
			return new double[]{lineEdX, lineEdY};
		}

		return new double[]{lineStX + r * a[X], lineStY + r*a[Y]};
	}

	/**
	* <b>基準点から最も近い線分上の座標を算出する.</b>
	 * @param base 基準点座標
	 * @param lineSt 線分の始点座標
	 * @param lineEd 線分の始点座標
	 * @return 基準点から最も近い線分上の座標
	*/
	public static Point2D getClosestPointOnLine(double[] base, double[] lineSt, double[] lineEd) {
		double[] ps = getClosestPointOnLine(base[X], base[Y], lineSt[X], lineSt[Y], lineEd[X], lineEd[Y]);
		return new Point2D.Double(ps[X], ps[Y]);
	}

	/**
	* <b>基準点から最も近い線分上の座標を算出する.</b>
	 * @param base 基準点座標
	 * @param lineSt 線分の始点座標
	 * @param lineEd 線分の始点座標
	 * @return 基準点から最も近い線分上の座標
	*/
	public static Point2D getClosestPointOnLine(Point2D base, Point2D lineSt, Point2D lineEd) {
		double[] ps = getClosestPointOnLine(
				base.getX(), base.getY(), lineSt.getX(), lineSt.getY(), lineEd.getX(), lineEd.getY());
		return new Point2D.Double(ps[X], ps[Y]);
	}
			
	public static enum CoordinateKb {
		ONE,
		TWO,
		THREE,
		FOUR,
		;

		public static CoordinateKb getTypeByRadian(double radian) {
			if (0 <= radian && radian < THRESHOLD_ONE_AND_TWO) {
				return ONE;
			} else if (THRESHOLD_ONE_AND_TWO < radian && radian < THRESHOLD_TWO_AND_THREE) {
				return TWO;
			} else if (THRESHOLD_TWO_AND_THREE < radian && radian < THRESHOLD_THREE_AND_FOUR) {
				return THREE;
			} else if (THRESHOLD_THREE_AND_FOUR < radian && radian <= THRESHOLD_FOUR_AND_ONE) {
				return FOUR;
			} else {
				return null;
			}
		}

		public static CoordinateKb getTypeByDegree(double degree) {
			if (0 <= degree && degree < 90) {
				return ONE;
			} else if (90 < degree && degree < 180) {
				return TWO;
			} else if (180 < degree && degree < 270) {
				return THREE;
			} else if (270 < degree && degree <= 360) {
				return FOUR;
			} else {
				return null;
			}
		}
	}

	/*********************************
	 * <b>座標(相対点)を幅、高さ、座標平面によって算出する.</b>
	 * @param base 座標(基準点)
	 * @param width 幅
	 * @param height 高さ
	 * @param coordinateKb 座標平面
	 * @return 座標(相対点)
	 */
	public static Point2D calcRadiusByCoordinate(Point2D base, double width, double height, CoordinateKb coordinateKb) {
		switch (coordinateKb) {
			case ONE:
				return new Point2D.Double(base.getX() + width, base.getY() - height);
			case TWO:
				return new Point2D.Double(base.getX() - width, base.getY() - height);
			case THREE:
				return new Point2D.Double(base.getX() - width, base.getY() + height);
			case FOUR:
			default:
				return new Point2D.Double(base.getX() + width, base.getY() + height);
		}
	}

	/*********************************
	 * <b>波線座標生成</b>
	 * <p>
	 * 座標(始点)～座標(終点)までの2次ベジェ曲線の座標を生成する。<br>
	 * 0度の状態で、描画した波線を始点・終点の2点間角度によって、アフィン変換で回転させて返却する。
	 * </p>
	 * @param start 座標(始点)
	 * @param end 座標(終点)
	 * @param amplitude 振幅
	 * @param waveLength 波長
	 */
	public static GeneralPath createWaveLinePath(Point2D start, Point2D end, double amplitude, double waveLength) {
		return createWaveLinePath(start.getX(),start.getY(), end.getX(),end.getY(), amplitude, waveLength);
	}

	public static GeneralPath createWaveLinePath(double[] start, double[] end, double amplitude, double waveLength) {
		return createWaveLinePath(start[X], start[Y], end[X], end[Y], amplitude, waveLength);
	}

	public static GeneralPath createWaveLinePath(
			double startX, double startY, double endX, double endY, double amplitude, double waveLength) {
		final int BEGIN = 0;
		final int CTRL = 1;
		final int END = 2;

		// 0度状態での終点の距離を算出し、相対点座標を算出する
		double distance = calcDistance(startX, startY, endX, endY);

		// 始点～終点の距離と波長より、波線のサイクル数を算出する
		int cycle = (int)(distance / waveLength);

		// 引数の始点の位置を最初の2次ベジェ曲線の開始位置に設定する
		GeneralPath ｂezier2curve = new GeneralPath();
		double[][] ps = new double[][]{
				new double[]{startX, startY} // 始点
				, new double[]{0, 0} // 制御点
				, new double[]{startX, startY} // 終点
		};

		ｂezier2curve.moveTo(ps[BEGIN][X], ps[BEGIN][Y]);

		// 1サイクルの波幅をもとに、引数の始点～終点までの距離間、2次ベジェ曲線の開始・終了・制御点を移動させながら
		// 波線の描画座標を算出して設定する
		for (int i = 1; i <= cycle; i++) {
			ps[END][X] += waveLength;
			ps[CTRL][X] = ps[BEGIN][X] + ((ps[END][X] - ps[BEGIN][X]) * 0.5);
			ps[CTRL][Y] = ps[BEGIN][Y] + ((i % 2 != 0) ? -amplitude : amplitude);

			ｂezier2curve.curveTo(ps[BEGIN][X], ps[BEGIN][Y], ps[CTRL][X], ps[CTRL][Y], ps[END][X], ps[END][Y]);

			ps[BEGIN][X] = ps[END][X];
			ps[BEGIN][Y] = ps[END][Y];
		}

		// 最後の余りスペースに小さな波線を描画
		ps[END][X] += distance - calcDistance(startX, startY, ps[END][X], ps[END][Y]);
		ps[CTRL][X] = ps[BEGIN][X] + ((ps[END][X] - ps[BEGIN][X]) * 0.5);
		ps[CTRL][Y] = ps[BEGIN][Y] + (((cycle + 1) % 2 != 0) ? -amplitude : amplitude);

		ｂezier2curve.curveTo(ps[BEGIN][X], ps[BEGIN][Y], ps[CTRL][X], ps[CTRL][Y], ps[END][X], ps[END][Y]);

		// 引数の始点・終点の2点間角度によって、回転する
		double rad = calcRadian(startX, startY, endX, endY);
		if (rad != 0) {
			ｂezier2curve.transform(AffineTransform.getRotateInstance(rad, startX, startY));
		}

		return ｂezier2curve;
	}

	/**
	 * <b>方向区分</b>
	 */
	public static enum DirectionKb {
		START,
		END,
		BOTH,
	}

	/**
	* <b>矢尻座標生成</b>
	* <p>
	* 始点・終点の座標位置に、矢印方向区分にしたがって矢尻の各座標を設定して返却する。
	* </p>
	 * @param p0 始点
	 * @param p1 終点
	 * @param height 高さ
	 * @param width 幅
	 * @param directionKb 矢印方向区分
	*/
	public static GeneralPath createArrowPointPath(Point2D p0, Point2D p1
			, double height, double width, DirectionKb directionKb){
		// 矢尻の高さ・幅によって、矢尻の斜辺の長さを算出する
		Point2D translation = calcRadiusByCoordinate(p0, width, height, CoordinateKb.ONE);
		double radian = calcRadian(p0, translation);
		double barbLength = calcDistance(p0, translation);

		// 矢尻の各座標を算出する
		switch (directionKb) {
			case START:
				return createArrowPointPath(p1, p0, barbLength, radian, directionKb, new GeneralPath());
			case END:
			default:
				return createArrowPointPath(p0, p1, barbLength, radian, directionKb, new GeneralPath());
		}
	}

	/**
	* <b>矢尻座標生成</b>
	* <p>
	* 引数を元に矢尻の各座標を設定して返却する。
	* </p>
	 * @param p0 始点
	 * @param p1 終点
	 * @param barbLength 矢尻の長さ
	 * @param degree 矢尻の角度
	 * @param directionKb 矢印方向区分
	 * @param createdPath 生成座標
	*/
	private static GeneralPath createArrowPointPath(Point2D p0, Point2D p1, double barbLength
			, double radian, DirectionKb directionKb, GeneralPath path){
		// 始点と終点によって決まる線分の角度を求める
		double theta = calcRadian(p0, p1);

		// 矢印部分の先端の座標を算出して移動し、終点へ向かってラインを引く
		path.moveTo(p1.getX() + barbLength * Math.cos(theta + Math.PI - radian)
			, p1.getY() + barbLength * Math.sin(theta + Math.PI - radian));
		path.lineTo(p1.getX(), p1.getY());

		// もうひとつの矢印部分の先端を算出し、終点から矢印部分の先端へのラインを引く
		path.lineTo(p1.getX() + barbLength * Math.cos(theta + Math.PI + radian)
			, p1.getY() + barbLength * Math.sin(theta + Math.PI + radian));

		return path;
	}

	/**
	* <b>突合せ座標生成</b>
	* <p>
	* 始点・終点の座標位置に、矢印方向区分にしたがって矢尻の各座標を設定して返却する。
	* </p>
	 * @param p0 始点
	 * @param p1 終点
	 * @param height 高さ
	 * @param width 幅
	 * @param directionKb 矢印方向区分
	*/
	public static GeneralPath createTsukiawasePointPath(Point2D p0, Point2D p1
			, double height, double width, DirectionKb directionKb){
		// 矢尻の高さ・幅によって、矢尻の斜辺の長さを算出する
		Point2D translation = calcRadiusByCoordinate(p0, width, height, CoordinateKb.ONE);
		double radian = calcRadian(p0, translation);
		double barbLength = calcDistance(p0, translation);

		// 矢尻の各座標を算出する
		switch (directionKb) {
			case START:
				return createTsukiawasePointPath(p1, p0, barbLength, radian, directionKb, new GeneralPath());
			case END:
			default:
				return createTsukiawasePointPath(p0, p1, barbLength, radian, directionKb, new GeneralPath());
		}
	}

	/**
	* <b>突合せ座標生成</b>
	* <p>
	* 引数を元に矢尻の各座標を設定して返却する。
	* </p>
	 * @param p0 始点
	 * @param p1 終点
	 * @param barbLength 矢尻の長さ
	 * @param degree 矢尻の角度
	 * @param directionKb 矢印方向区分
	 * @param createdPath 生成座標
	*/
	private static GeneralPath createTsukiawasePointPath(Point2D p0, Point2D p1, double barbLength
			, double radian, DirectionKb directionKb, GeneralPath path){
		double theta = calcRadian(p0, p1);
		CoordinateKb coordinateKb = CoordinateKb.getTypeByRadian(Math.abs(theta));
		double rad = (coordinateKb == CoordinateKb.ONE || coordinateKb == CoordinateKb.FOUR || theta == THRESHOLD_ONE_AND_TWO) ? -radian : radian;

		path.moveTo(p1.getX(), p1.getY());
		path.lineTo(p1.getX() + barbLength * Math.cos(theta + Math.PI + rad)
			, p1.getY() + barbLength * Math.sin(theta + Math.PI + rad));

		return path;
	}

	/**************************************
	 * 円に外接する正三角形の座標パスを生成
	 **************************************/
	public static GeneralPath createCircumscribedTrianglePath(Point2D center, double r){
		GeneralPath path = new GeneralPath();

		// 指定された円を内接円とし、半径rから、辺A-Bまでの垂線をおろし、点ABを求める
		double[] pAB = calcRadiusByDegree(new double[]{center.getX(), center.getY()}, 150.0, r);

		// 三平方の定理を使用し、円に外接する、正三角形の点A、B、Cを算出
		double distance = r * Math.sqrt(3); // 点AB～点Aまでの距離
		double[] pA = calcRadiusByDegree(pAB, 60.0, distance);
		double[] pB = calcRadiusByDegree(pAB, 240.0, distance);
		double[] pC = new double[]{pB[X] + calcDistance(pA, pB), pB[Y]};

		//頂点Aへ移動
		path.moveTo(pA[X], pA[Y]);

		//辺A-Bの線を引く
		path.lineTo(pB[X], pB[Y]);
		path.moveTo(pB[X], pB[Y]);
		//辺B-Cの線を引く
		path.lineTo(pC[X], pC[Y]);
		path.moveTo(pC[X], pC[Y]);
		//辺C-Aの線を引く
		path.lineTo(pA[X], pA[Y]);

		return path;
	}
}

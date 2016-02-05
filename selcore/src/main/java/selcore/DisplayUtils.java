package selcore;

import java.awt.Dimension;

public class DisplayUtils {
	private static Dimension resolution() {
		return java.awt.Toolkit.getDefaultToolkit().getScreenSize();
	}

	public static void main(String[] args) {
		Dimension res = resolution();
		print(res.getWidth() + "");
		print(res.getHeight() + "");

	}

	private static void print(CharSequence m) {
		System.out.println(m);
	}

	public static Dimension locateRight(Dimension boxDimension, Dimension defPosition) {

		Dimension scr = resolution();
		int newX = scr.width - boxDimension.width;
		if (newX <= 0)
			newX = defPosition.width;

		return new Dimension(newX, defPosition.height);
	}

	public static Dimension locateBottom(Dimension boxDimension, Dimension defPosition) {

		Dimension scr = resolution();

		int newY = scr.height - boxDimension.height;
		if (newY <= 0)
			newY = defPosition.height;

		return new Dimension(defPosition.width, newY);
	}

}

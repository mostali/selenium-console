package selcore;

import java.io.File;
import java.io.IOException;
import java.util.Random;

import org.apache.commons.io.FileUtils;

public class Utils {
	private static final Random RAND = new Random();

	public static long timeRandom(Long timeMini, Long timeMax) {
		return timeRandom(timeMini.intValue(), timeMax.intValue());
	};

	public static int timeRandom(int min, int max) {
		if (min == max)
			return RAND.nextInt(min);

		else if (min > max)
			return RAND.nextInt(min - max) + max;

		return RAND.nextInt(max - min) + min;
	}

	public static void sleepRandom(Long min, Long max) throws InterruptedException {
		sleepRandom(min.intValue(), max.intValue());
	}

	public static void sleepRandom(int min, int max) throws InterruptedException {
		Thread.sleep(timeRandom(min, max));
	}

	public static void sleepRandom(int min, int max, boolean trueException) {
		try {
			sleepRandom(min, max);
		} catch (InterruptedException ex) {
			if (trueException)
				new RuntimeException(ex);
			else
				WARN(ex.getMessage());
		}
	}

	public static Integer getInteger(String string, boolean throwsError) {
		Integer stringInteger = getInteger(string);
		if (stringInteger == null && throwsError)
			throw new NumberFormatException(string);
		return stringInteger;

	}

	public static Integer getInteger(String string) {
		if (string == null)
			return null;
		try {
			return Integer.valueOf(string);
		} catch (NumberFormatException ex) {
			try {
				return Double.valueOf(string).intValue();
			} catch (NumberFormatException ex2) {
				return null;
			}
		}
	}

	public static int getInteger(String string, Integer defInteger) {
		Integer stringInteger = getInteger(string);
		return (stringInteger == null) ? defInteger : stringInteger;

	}

	public static void MESSAGE(String infoMessage) {
		System.out.println(infoMessage);

	}

	public static void WARN(String warnMessage) {
		System.err.println("WARNING:" + warnMessage);
	}

	public static void ERR(String errorMessage) {
		System.err.println("ERROR:" + errorMessage);

	}

	public static String getCurrentPath() {
		return Utils.class.getProtectionDomain().getCodeSource().getLocation().getFile();
	}

	public static void writeString2projectLocation(String fileName, String data, boolean append) {
		File file = new File(getCurrentPath(), fileName);
		try {
			FileUtils.writeStringToFile(file, data, append);
			Utils.MESSAGE("write - " + file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
	}

	public static int randomNumber(int size) {
		return RAND.nextInt(size);
	}

}

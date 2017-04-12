package synapticloop.nanohttpd.module.example;

import java.util.Random;

public class Util {
	private static final String LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

	public static final String getRandomLetter() {
		Random random = new Random(System.currentTimeMillis());
		int nextInt = random.nextInt(LETTERS.length());
		return(LETTERS.substring(nextInt, nextInt + 1));
	}
}

package lombax5832.DarkSouls.util;

public class RandomRange {
	public static int randomRange(int min, int max){
		return (min + (int)(Math.random() * ((max - min) + 1)));
	}
}

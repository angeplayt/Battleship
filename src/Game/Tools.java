package Game;


public class Tools {

	public static int[] eingaben(int x, int y, int tileSize) {
		int[] rückgabe = new int[2];
		rückgabe[0] = y / tileSize;
		rückgabe[1] = x / tileSize;
		return rückgabe;
	}

}

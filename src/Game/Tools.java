package Game;


public class Tools {

	public static int[] eingaben(int x, int y, int tileSize) {
		int[] r�ckgabe = new int[2];
		r�ckgabe[0] = y / tileSize;
		r�ckgabe[1] = x / tileSize;
		return r�ckgabe;
	}

}

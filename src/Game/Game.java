package Game;

public class Game {
	// 0 = empty, >0 = SIZE of the ship, -1 = not available for a ship
	private int[][] field;
	private int[][] hit;
	private final int SIZE;
	private int tries;
	private int shipHits;
	private final int SHIP_FIELDS;
	private final int MAX_SHIP_SIZE;

	public Game(int size) {
		// TODO Auto-generated constructor stub
		field = new int[size][size];
		hit = new int[size][size];
		this.SIZE = size;
		initializeField(4);
		tries = 0;
		shipHits = 0;
		this.MAX_SHIP_SIZE = 4;
		SHIP_FIELDS = shipFields(4);
	}

	public Game(int SIZE, int MAX_SHIP_SIZE) {
		field = new int[SIZE][SIZE];
		hit = new int[SIZE][SIZE];
		this.SIZE = SIZE;
		initializeField(MAX_SHIP_SIZE);
		tries = 0;
		shipHits = 0;
		this.MAX_SHIP_SIZE = MAX_SHIP_SIZE;
		SHIP_FIELDS = shipFields(MAX_SHIP_SIZE);
	}

	// place the ships on the field
	private void initializeField(int MAX_SHIP_SIZE) {
		for (int shipSIZE = MAX_SHIP_SIZE; shipSIZE >= 1; shipSIZE--) {
			for (int j = 0; j < MAX_SHIP_SIZE + 1 - shipSIZE; j++) {
				int x;
				int y;
				boolean horizontal;
				do {
					x = (int) (Math.random() * SIZE);
					y = (int) (Math.random() * SIZE);
					horizontal = (int) (Math.random() * 2) == 0;
				} while (!isPossible(x, y, shipSIZE, horizontal));
				setField(shipSIZE, x, y, horizontal);
			}
		}
	}

	// place the ships
	private void setField(int shipSIZE, int x, int y, boolean horizontal) {
		if (horizontal) {
			for (int i = 0; i < shipSIZE; i++) {
				field[x][i + y] = shipSIZE;
			}
			setHorizontal(x - 1, y, shipSIZE + 2);
			setHorizontal(x + 1, y, shipSIZE + 2);
			setLeftRight(x, y, shipSIZE);
		} else {
			for (int i = 0; i < shipSIZE; i++) {
				field[i + x][y] = shipSIZE;
			}
			setVertical(x, y - 1, shipSIZE + 2);
			setVertical(x, y + 1, shipSIZE + 2);
			setUpDown(x, y, shipSIZE);

		}
	}

	private void setVertical(int x, int y, int shipSIZE) {
		for (int i = 0; i < shipSIZE; i++) {
			if (x - 1 + i >= 0 && x - 1 + i < SIZE && y >= 0 && y < SIZE) {
				field[x - 1 + i][y] = -1;
			}
		}
	}

	private void setHorizontal(int x, int y, int shipSIZE) {
		for (int i = 0; i < shipSIZE; i++) {
			if (x >= 0 && x < SIZE && y - 1 + i >= 0 && y - 1 + i < SIZE) {
				field[x][y - 1 + i] = -1;
			}
		}
	}

	private void setUpDown(int x, int y, int shipSIZE) {
		if (x - 1 >= 0 && x - 1 < SIZE && y >= 0 && y < SIZE) {
			field[x - 1][y] = -1;
		}
		if (x + shipSIZE >= 0 && x + shipSIZE < SIZE && y >= 0 && y < SIZE) {
			field[x + shipSIZE][y] = -1;
		}
	}

	private void setLeftRight(int x, int y, int shipSIZE) {
		if (x >= 0 && x < SIZE && y - 1 >= 0 && y - 1 < SIZE) {
			field[x][y - 1] = -1;
		}
		if (x >= 0 && x < SIZE && y + shipSIZE >= 0 && y + shipSIZE < SIZE) {
			field[x][y + shipSIZE] = -1;
		}
	}

	// is it possible to set a ship at this position
	private boolean isPossible(int x, int y, int shipSIZE, boolean horizontal) {
		if (horizontal) {
			if (y + shipSIZE >= SIZE) {
				return false;
			}
			for (int i = 0; i < shipSIZE; i++) {
				if (field[x][i + y] != 0) {
					return false;
				}
			}
		} else {
			if (x + shipSIZE >= SIZE) {
				return false;
			}
			for (int i = 0; i < shipSIZE; i++) {
				if (field[i + x][y] != 0) {
					return false;
				}

			}
		}
		return true;
	}

	// true if a legal draw was made, false if a field is already chosen
	public boolean draw(int x, int y) {
		if (x >= 0 && x < SIZE && y >= 0 && y < SIZE) {
			if (hit[x][y] != 0) {
				return false;
			} else if (field[x][y] > 0) {
				hit[x][y] = 1;
				tries++;
				shipHits++;
				return true;
			} else {
				hit[x][y] = -1;
				tries++;
				return false;
			}
		}
		return false;
	}

	public boolean finish() {
		for (int i = 0; i < field.length; i++) {
			for (int j = 0; j < field.length; j++) {
				if (field[i][j] > 0 && hit[i][j] <= 0) {
					return false;
				}
			}
		}
		return true;
	}
	
	private int shipFields(int shipSize){
		int sum = 0;
		for(int i = 1; i <= shipSize; i++) {
			sum = sum + shipSize* i + i - i*i;
		}
		return sum;
	}

	public int[][] getHit() {
		return hit;
	}

	public int getSIZE() {
		return SIZE;
	}

	public int getTries() {
		return tries;
	}

	public int getShipHits() {
		return shipHits;
	}

	public int getSHIP_FIELDS() {
		return SHIP_FIELDS;
	}

	public int getMAX_SHIP_SIZE() {
		return MAX_SHIP_SIZE;
	}
	
	
}

package direction;

public enum Direction {
	UP(1), DOWN(-1), SUPER_UP(10), SUPER_DOWN(-10);

	private final int directionRow;

	private Direction(int _directionRow) {
		this.directionRow = _directionRow;
	}

	public int getDRow() {
		return directionRow;
	}

	public String toString() {
		switch (directionRow) {
		case 1: {
			return "UP";
		}
		case -1: {
			return "DOWN";
		}
		case 10: {
			return "SUPER_UP";
		}
		case -10: {
			return "SUPER_DOWN";
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + directionRow);
		}
	}
}

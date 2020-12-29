package view.constant;

public enum Rad {
	LITTLE(3.0), MEDIUM(4.0), HIGH(5.0);

	private double radius;

	private Rad(double radius) {
		assert (radius > 0); // use CornerRadii EMPTY instead of = 0
		this.radius = radius;
	}

	public double getRadius() {
		return this.radius;
	}
}
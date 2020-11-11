package app;

import factory.DrawPileFactory;

public class App {

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		DrawPileFactory df = new DrawPileFactory();

		/* POINT */
		// https://stackoverflow.com/questions/15020850/copy-constructors-and-defensive-copying
		// A simple point.
		Point p1 = new Point(3, 42);
		// A new point at the same place as p1 but a completely different object.
		Point p2 = new Point(p1);
		p2.x = 5;

		System.out.println(p1.toString());

		System.out.println(p1.x);
		System.out.println(p2.toString());
		System.out.println(p2.x);

		/* DUMMY */
		// https://stackoverflow.com/questions/869033/how-do-i-copy-an-object-in-java
		DummyBean dum = new DummyBean();
		dum.setDummy("foo");
		System.out.println(dum.getDummy()); // prints 'foo'

		// DummyBean dumtwo = dum; // prints 'bar' but it should print 'foo'
		DummyBean dumtwo = new DummyBean(dum); // prints 'bar'
		System.out.println(dumtwo.getDummy()); // prints 'foo'

		dum.setDummy("bar");
		System.out.println(dumtwo.getDummy()); // prints 'bar' but it should print 'foo'

	}

}

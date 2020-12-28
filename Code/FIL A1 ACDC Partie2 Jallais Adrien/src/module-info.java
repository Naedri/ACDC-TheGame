/**
 * @author Adrien Jallais
 *
 */
module acdc_partie2 {
	requires org.junit.jupiter.api;
	requires javafx.graphics;
	requires javafx.controls;
	requires javafx.media;
	requires javafx.base;
	requires java.base;
	requires javafx.web;

	// https://stackoverflow.com/questions/53035454/javafx-module-javafx-graphics
	// exports application;
	opens application to javafx.graphics;
}
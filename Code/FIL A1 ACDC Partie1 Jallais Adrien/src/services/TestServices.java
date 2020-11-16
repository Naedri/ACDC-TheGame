package services;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class TestServices {

	@Test
	void test_SerivcesUser() {
		String path1 = "C:\\Users\\Adrien Jallais\\Documents\\IMT\\Cours\\ACDC\\PROJET\\Jeu_essai\\game1.txt"; // not ok
		assertTrue(ServiceUser.isPathValid(path1));
	}

}

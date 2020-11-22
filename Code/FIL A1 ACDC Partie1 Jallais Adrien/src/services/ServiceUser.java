package services;

import java.util.Scanner;

public final class ServiceUser {

	/**
	 * check if the given string match with a path
	 * 
	 * @Deprecated do not know if it will be used in Unix or Windows computer
	 * @param filePath of the file to be evaluated
	 * @return
	 */
	public static boolean isPathValid(String filePath) {
		// return filePath.matches("([a-zA-Z]:)?(\\\\[a-zA-Z0-9_.-]+)+\\\\?");
		return true;
	}

	/**
	 * Secure the choice of a number by the client
	 * 
	 * @param borneMin choixMinimale allowed
	 * @param borneMax choixMaximum allowed
	 */
	public static int setChoice(int borneMin, int borneMax) {
		boolean saisieCorrecte = false;
		int nombreChoisie = borneMin;
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		while (!saisieCorrecte) {
			System.out.println("Please choose a number between " + borneMin + " and " + borneMax
					+ " included.\nThen press \'Enter\'.\n");

			String saisie = sc.nextLine();
			System.out.println("You have choosen : " + saisie + ".\n");

			try {
				if (saisie.matches("\\s*\\d+\\s*")) {
					nombreChoisie = Integer.parseInt(saisie);
					if (nombreChoisie >= borneMin && nombreChoisie <= borneMax) {
						// break the loop
						saisieCorrecte = true;
					} else
						System.out.println("The input is incorect, retry.\n");
				} else
					System.out.println("The input is incorect, retry.\n");
			}

			catch (java.lang.NumberFormatException e1) {
				System.out.println("The input format is incorrect, retry.\n");
			}
		}
		// sc.close(); // Exception in thread "main" java.util.NoSuchElementException:
		// No line found
		return nombreChoisie;
	}

}

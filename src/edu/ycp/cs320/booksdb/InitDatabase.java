package edu.ycp.cs320.booksdb;

import java.util.Scanner;

import edu.ycp.cs320.booksdb.persist.DatabaseProvider;

import edu.ycp.cs320.booksdb.persist.DerbyDatabase;

public class InitDatabase {
	public static void init(Scanner keyboard) {
		System.out.print("Which database (1=derby): ");
		int which = Integer.parseInt(keyboard.nextLine());
		if (which == 1) {
			DatabaseProvider.setInstance(new DerbyDatabase());
		} else {
			throw new IllegalArgumentException("Invalid choice: " + which);
		}
	}
}

package org.mathpi;

import java.util.Scanner;

public class Grammar {
	/*
	 * grammaire : S -> exp F exp -> terme resteExp terme -> i | expParenthesee
	 * expParenthesee -> ( exp ) resteExp -> + exp | epsilon
	 */
	public static int idx = 0; // indice de parcours dans la chaîne en entrée
	public static char lexeme; // symbole courant
	public static String entree; // chaîne en entrée

	public static void lexemeSuivant() {
		System.out.println("Lexeme suivant");
		if (idx < entree.length()) {
			lexeme = entree.charAt(idx);
			idx++;
		}
	}

	public static boolean lexeme(char lex) {
		boolean rep;
		if (lex == lexeme) {
			System.out.println("" + lex + " reconnu. On avance");
			rep = true;
		} else {
			System.out.println("" + lex + " attendu");
			rep = false;
		}
		lexemeSuivant();
		return (rep);
	}

	public static boolean s() {
		System.out.println("On cherche a reconnaitre un S");
		return (exp() && lexeme('f'));
	}

	public static boolean exp() {
		System.out.println("On cherche a reconnaitre un exp");
		return (terme() && resteExp());
	}

	public static boolean terme() {
		System.out.println("On cherche a reconnaitre un terme");
		return (lexeme('i') || expParenthesee());
	}

	public static boolean expParenthesee() {
		System.out.println("On cherche a reconnaitre un expParenthesee");
		return (lexeme('(') && exp() && lexeme(')'));
	}

	public static boolean resteExp() {
		System.out.println("On cherche a reconnaitre un resteExp");
		return ((lexeme('+') && exp()) || true);
	}

	public static void main(String[] args) {
		System.out.print("Entrez une chaine a reconnaitre :");
		Scanner s = new Scanner(System.in);
		entree = s.nextLine();
		lexemeSuivant();
		if (s())
			System.out.println("OK");
		else
			System.out.println("NON RECONNU\n");
	}
}

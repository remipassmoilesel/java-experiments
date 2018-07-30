package org.prog2.utils;

public class EqualsHashcode {

	public static class Etudiant {

		private String ine;

		public Etudiant(String ine) {
			this.ine = ine;
		}

		@Override
		public int hashCode() {
			// permet d'avoir un meme hashcode pour deux entités étudiant avec
			// le meme numero etudiant
			return ine.hashCode();
		}

		@Override
		public boolean equals(Object obj) {
			if (obj instanceof Etudiant) {
				return ((Etudiant) obj).ine.equals(ine);
			}
			return false;
		}
	}

}

package org.interactionstats;

/**
 * Element de mesure d'interactions. Un element de mesure possède un identifiant
 * et un compteur numérique.
 * 
 * @author remipassmoilesel
 *
 */
public class InteractionStatUnit {

	private static final String SEPARATOR = ":";
	private int interactions;
	private String id;

	InteractionStatUnit(String id) {

		if (id.matches("^[0-9a-zA-Z]+$") == false) {
			throw new IllegalArgumentException("Invalid id: " + id);
		}

		this.id = id;
		this.interactions = 0;
	}

	/**
	 * Comptabiliser une nouvelle interaction.
	 */
	public void incrementInteractions() {
		interactions++;
	}

	/**
	 * Retourne l'identifiant de l'element
	 * 
	 * @return
	 */
	public String getId() {
		return id;
	}

	/**
	 * Retourne le nombre d'interactions.
	 * 
	 * @return
	 */
	public int getInteractions() {
		return interactions;
	}

	/**
	 * Conversion de l'element en une chaine de caractere au format 'id:nbr'
	 */
	@Override
	public String toString() {
		return id + SEPARATOR + interactions;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + interactions;
		return result;
	}

	/**
	 * Retourne vrai si l'element passé en paramètre possède le même identifiant
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		InteractionStatUnit other = (InteractionStatUnit) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;

		return true;
	}

}

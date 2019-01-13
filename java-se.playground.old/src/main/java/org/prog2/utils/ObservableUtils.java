package org.prog2.utils;

import java.util.Observable;
import java.util.Observer;

public class ObservableUtils {

	public static void main(String[] args) {

		// créer un objet observable
		DataObservable obs1 = new DataObservable();

		// ajouter des observateurs
		obs1.addObserver(new ObserverObject());
		obs1.addObserver(new ObserverObject());
		obs1.addObserver(new ObserverObject());

		// modifier les données
		obs1.add(8);

		// notifier les observateurs, peut se faire directement dans la méthode
		// de modification
		obs1.notifyObservers();

	}

	private static class DataObservable extends Observable {

		private int value;

		public DataObservable() {
			this.value = 0;
		}

		public void add(int nmr) {

			value += nmr;

			// activer le flag de changement, sinon les notifications ne partent
			// pas
			setChanged();

		}

		public int getValue() {
			return value;
		}

	}

	private static class ObserverObject implements Observer {

		private static int counter = 0;
		private String id;

		public ObserverObject() {
			counter++;
			this.id = this.getClass().getSimpleName() + "_" + counter;
		}

		@Override
		public void update(Observable o, Object arg) {
			System.out.println(id + " updated by " + o + ", arguments: " + arg);
		}

	}

}

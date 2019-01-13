package org.prog2.streams;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class SerializeObjects {

	public static void main(String[] args) throws IOException {

		// les données à sérialiser
		ArrayList<SerializablePerson> persons = new ArrayList<SerializablePerson>();
		persons.add(new SerializablePerson("John Doe", 15));
		persons.add(new SerializablePerson("Jean Claude", 35));
		persons.add(new SerializablePerson("Jackie Chan", 62));

		// sérialiser les données
		Path file = Paths.get("personnes.dat");
		ObjectOutputStream out = null;
		try {
			out = new ObjectOutputStream(new BufferedOutputStream(
					Files.newOutputStream(file)));

			for (SerializablePerson p : persons) {
				out.writeObject(p);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.close();
			}
		}

		// afficher les données
		ObjectInputStream in = null;
		try {
			in = new ObjectInputStream(new BufferedInputStream(
					Files.newInputStream(file)));

			SerializablePerson p = null;
			while (true) {
				try {
					p = (SerializablePerson) in.readObject();
					System.out.println(p);
				} catch (EOFException e) {
					break;
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.close();
			}
		}

	}
}

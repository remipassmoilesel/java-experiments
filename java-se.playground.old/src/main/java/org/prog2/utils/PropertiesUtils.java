package org.prog2.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Properties;

public class PropertiesUtils {

	public static final String PROPERTIE_1 = "PROPERTIE_1";
	public static final String PROPERTIE_2 = "PROPERTIE_2";

	public static void main(String[] args) throws IOException {

		// créer un objet depropriétés
		Properties pp = new Properties();
		pp.put(PROPERTIE_1, "value 1");
		pp.put(PROPERTIE_2, "value 2");

		File file = Paths.get("./properties.xml").toFile();

		// sérialization
		FileOutputStream os = new FileOutputStream(file);
		pp.storeToXML(os, "comment");

		os.close();

		// désérialization

		Properties pp2 = new Properties();
		FileInputStream fis = new FileInputStream(file);
		pp2.loadFromXML(fis);

		System.out.println(pp2);

	}
}

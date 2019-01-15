package org.xstream;

import com.thoughtworks.xstream.XStream;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Small trial to test XStream reaction front of class changes without serialize number
 * <p>
 * When deserializing:
 * <p>
 * If a field is in serialized form, but not in original class an error is thrown
 * If a field is in original class but not in serialized form, no errors are raised
 */
public class XStreamLab {

    private static final Path serializedPath = Paths.get("xstream_serialized.xml");
    private static final XStream xmlSerializer = new XStream();

    public static void main(String[] args) throws IOException {
        //serialize();
        deserialize();
    }

    public static void serialize() throws IOException {

        Files.deleteIfExists(serializedPath);

        ConfigurationContainer toSerialize = new ConfigurationContainer();

        try (BufferedWriter writer = Files.newBufferedWriter(serializedPath, Charset.forName("utf-8"))) {
            writer.write(xmlSerializer.toXML(toSerialize));
            writer.flush();
            writer.close();
        }
    }

    public static void deserialize() throws IOException {

        try (BufferedReader reader = Files.newBufferedReader(serializedPath, Charset.forName("utf-8"))) {
            ConfigurationContainer container = (ConfigurationContainer) xmlSerializer.fromXML(reader);
            System.out.println(container);
        }
    }


}

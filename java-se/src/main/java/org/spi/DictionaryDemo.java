package org.spi;

/**
 * Simple Service Provider Interface example, from Oracle tutorials
 *
 * To add a service provider definition with Maven, create file:
 * MY_APP/src/main/resources/META-INF/services/org.fullname.of.spi.Interface
 *
 * And add into the full name of the implementation
 * org.fullname.of.spi.Implementation
 *
 * Then:
 * $ mvn clean compile
 *
 * Note: Intellij do not use maven as default make command, this can be fix in
 * "Run Configurations", change "Make" by "Maven Goal > compile"
 *
 * See also: https://maven.apache.org/guides/getting-started/
 */
public class DictionaryDemo {

    public static void main(String[] args) {
        DictionaryService dictionary = DictionaryService.getInstance();

        System.out.println(DictionaryDemo.lookup(dictionary, "book"));
        System.out.println(DictionaryDemo.lookup(dictionary, "editor"));
        System.out.println(DictionaryDemo.lookup(dictionary, "xml"));
        System.out.println(DictionaryDemo.lookup(dictionary, "REST"));
    }

    public static String lookup(DictionaryService dictionary, String word) {
        String outputString = word + ": ";
        String definition = dictionary.getDefinition(word);
        if (definition == null) {
            return outputString + "Cannot find definition for this word.";
        } else {
            return outputString + definition;
        }
    }
}
package org.optionnal;

import java.util.Optional;

public class OptionnalExperiments {

    public static void main(String[] args) {
        optionalOfNullableAcceptNullArgument();
        optionalOfRequireNonNull();
        optionalThrowOnGetIfValueIsNotPresent();

        System.out.println("hey");
    }

    private static void optionalThrowOnGetIfValueIsNotPresent() {
        try {
            Optional.ofNullable(null).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void optionalOfNullableAcceptNullArgument() {
        Optional.ofNullable(null);
    }

    private static void optionalOfRequireNonNull() {
        try {
            Optional.of(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

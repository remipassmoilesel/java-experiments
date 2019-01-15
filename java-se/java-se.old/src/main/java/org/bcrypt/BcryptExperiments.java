package org.bcrypt;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BcryptExperiments {

    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encoded = encoder.encode("azerty");
        System.out.println(encoded);
    }

}

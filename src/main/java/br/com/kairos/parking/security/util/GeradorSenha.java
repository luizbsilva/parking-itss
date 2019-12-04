package br.com.kairos.parking.security.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class GeradorSenha {
    
    public static void main(final String[] args) {
        final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println(encoder.encode("PARKING"));
    }
    
}

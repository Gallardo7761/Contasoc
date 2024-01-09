package dev.galliard.contasoc.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class DNIValidator {
	public static boolean validarDNI(String dni) {
        if (dni.length() != 9) {
            return false;
        }
        // Diccionario con los valores de las letras del DNI
        Map<Character, Integer> valoresLetras = new HashMap<>();
        valoresLetras.put('T', 0);
        valoresLetras.put('R', 1);
        valoresLetras.put('W', 2);
        valoresLetras.put('A', 3);
        valoresLetras.put('G', 4);
        valoresLetras.put('M', 5);
        valoresLetras.put('Y', 6);
        valoresLetras.put('F', 7);
        valoresLetras.put('P', 8);
        valoresLetras.put('D', 9);
        valoresLetras.put('X', 10);
        valoresLetras.put('B', 11);
        valoresLetras.put('N', 12);
        valoresLetras.put('J', 13);
        valoresLetras.put('Z', 14);
        valoresLetras.put('S', 15);
        valoresLetras.put('Q', 16);
        valoresLetras.put('V', 17);
        valoresLetras.put('H', 18);
        valoresLetras.put('L', 19);
        valoresLetras.put('C', 20);
        valoresLetras.put('K', 21);
        valoresLetras.put('E', 22);

        char letra = Character.toUpperCase(dni.charAt(8));
        char[] digitos = dni.substring(0, 8).toCharArray();
        int numero = 0;
        try {
        	numero = Integer.parseInt(new String(digitos));
        } catch(Exception e) {
        	return false;
        }

        int resto = numero % 23;
        if(!valoresLetras.containsKey(letra)) {
        	return false;
        }
        int valorLetra = valoresLetras.get(letra);

        return resto == valorLetra;
    }
	
	public static boolean validarNIE(String nie) {
        if (nie.length() != 9) {
            return false;
        }
        
        Map<Character, Integer> valoresLetras = new HashMap<>();
        valoresLetras.put('T', 0);
        valoresLetras.put('R', 1);
        valoresLetras.put('W', 2);
        valoresLetras.put('A', 3);
        valoresLetras.put('G', 4);
        valoresLetras.put('M', 5);
        valoresLetras.put('Y', 6);
        valoresLetras.put('F', 7);
        valoresLetras.put('P', 8);
        valoresLetras.put('D', 9);
        valoresLetras.put('X', 10);
        valoresLetras.put('B', 11);
        valoresLetras.put('N', 12);
        valoresLetras.put('J', 13);
        valoresLetras.put('Z', 14);
        valoresLetras.put('S', 15);
        valoresLetras.put('Q', 16);
        valoresLetras.put('V', 17);
        valoresLetras.put('H', 18);
        valoresLetras.put('L', 19);
        valoresLetras.put('C', 20);
        valoresLetras.put('K', 21);
        valoresLetras.put('E', 22);

        // Diccionario con los valores de las letras del NIE
        Map<Character, Character> valoresPrimerasLetras = new HashMap<>();
        valoresPrimerasLetras.put('X', '0');
        valoresPrimerasLetras.put('Y', '1');
        valoresPrimerasLetras.put('Z', '2');
        
        char primeraLetra = Character.toUpperCase(nie.charAt(0));
        char letra = Character.toUpperCase(nie.charAt(8));
        char[] digitos = nie.substring(1, 8).toCharArray();
        if(!valoresPrimerasLetras.containsKey(primeraLetra)) {
        	return false;
        }
        int numero = Integer.parseInt(String.join("", List.of(valoresPrimerasLetras.get(primeraLetra).toString(),new String(digitos))));


        int resto = numero % 23;
        int valorLetra = valoresLetras.get(letra);

        return resto == valorLetra;
    }
	
	public static String generarDNI() {
        // Generar número aleatorio de 8 dígitos
        Random random = new Random();
        int numero = random.nextInt(99999999);

        // Diccionario con los valores de las letras del DNI
        char[] letras = {
            'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z', 'S', 'Q', 'V', 'H', 'L', 'C', 'K', 'E'
        };

        // Calcular resto del número dividido por 23
        int resto = numero % 23;

        // Obtener letra correspondiente al resto
        char letra = letras[resto];

        // Combinar número y letra para formar el DNI
        String dni = String.format("%08d", numero) + letra;

        return dni;
    }
}

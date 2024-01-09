package dev.galliard.contasoc.ui;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.galliard.contasoc.Contasoc;
import dev.galliard.contasoc.util.ContasocLogger;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class UpdateChecker implements Runnable{
    public UpdateChecker() {
        super();
    }

    @Override
    public void run() {
        try {
            String API_URL = "https://api.github.com/repos/GalliardDev/Contasoc/releases/latest";
            if(compareVersions(getLatestRelease(API_URL), Contasoc.VERSION) > 0) {
                int answer = JOptionPane.showConfirmDialog(null, "Hay una nueva versión disponible. ¿Quieres descargarla?", "Actualización disponible", JOptionPane.OK_CANCEL_OPTION);
                if(answer == JOptionPane.OK_OPTION) {
                    new Thread(new UpdateInstaller()).start();
                } else if (answer == JOptionPane.CANCEL_OPTION) {
                    ContasocLogger.info("Actualización cancelada.");
                }
            } else {
                ContasocLogger.info("No hay actualizaciones disponibles.");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static String getLatestRelease(String apiUrl) throws IOException {
        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/vnd.github.v3+json");

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            try (BufferedReader reader = new BufferedReader(new java.io.InputStreamReader(connection.getInputStream()))) {
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }

                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode jsonNode = objectMapper.readTree(response.toString());


                // Obtén el valor de 'tag_name' que contiene la versión.
                String version = jsonNode.get("tag_name").asText();
                return version.replace("v","");
            }
        } else {
            throw new IOException("Error en la solicitud HTTP: " + responseCode);
        }
    }

    private static int compareVersions(String version1, String version2) {
        String[] v1 = version1.split("\\.");
        String[] v2 = version2.split("\\.");

        int length = Math.max(v1.length, v2.length);

        for (int i = 0; i < length; i++) {
            int num1 = (i < v1.length) ? Integer.parseInt(v1[i]) : 0;
            int num2 = (i < v2.length) ? Integer.parseInt(v2[i]) : 0;

            if (num1 < num2) {
                return -1;
            } else if (num1 > num2) {
                return 1;
            }
        }

        return 0;
    }


}

package dev.galliard.contasoc.ui;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class UpdateInstaller implements Runnable {
    public static final String DOWNLOADS_FOLDER = System.getProperty("user.home") + "\\Downloads";

    public static void downloadFile(String url, String targetDirectory) throws IOException {
        @SuppressWarnings("deprecation")
		URL fileUrl = new URL(url);
        try (InputStream in = fileUrl.openStream()) {
            String fileName = url.substring(url.lastIndexOf("/") + 1);
            Path target = new File(targetDirectory + File.separator + fileName).toPath();
            Files.copy(in, target, StandardCopyOption.REPLACE_EXISTING);
        }
    }

    public static void executeDownloadedExe(String directory, String fileName) throws IOException {
        String exePath = directory + File.separator + fileName;
        ProcessBuilder processBuilder = new ProcessBuilder(exePath);
        processBuilder.start();
    }

    @Override
    public void run() {
        try {
            String LATEST_VERSION = UpdateChecker.getLatestRelease("https://api.github.com/repos/GalliardDev/Contasoc/releases/latest");
            String DOWNLOAD_URL = "https://github.com/GalliardDev/Contasoc/releases/download/v" + LATEST_VERSION + "/ContasocSetup-" + LATEST_VERSION + ".exe";
            downloadFile(DOWNLOAD_URL, DOWNLOADS_FOLDER);
            executeDownloadedExe(DOWNLOADS_FOLDER, "ContasocSetup-" + LATEST_VERSION + ".exe");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

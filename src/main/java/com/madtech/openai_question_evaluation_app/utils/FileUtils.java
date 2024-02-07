package com.madtech.openai_question_evaluation_app.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {
    public static final String TEXT_RESOURCES_PATH = "resources/text/";
    public static final String VIDEO_RESOURCES_PATH = "resources/video/";
    public static final String AUDIO_RESOURCES_PATH = "resources/audio/";

//    static {
//        try {
//            Files.createDirectories(Paths.get(TEXT_RESOURCES_PATH));
//        } catch (IOException e) {
//            throw new ExceptionInInitializerError("Error creating directory");
//        }
//    }

    public static void writeTextToFile(String textData, String fileName) {
        Path directory = Paths.get(TEXT_RESOURCES_PATH);
        Path filePath = directory.resolve(fileName);
        try {
            Files.deleteIfExists(filePath);
            Files.writeString(filePath, textData, StandardOpenOption.CREATE_NEW);
        } catch (IOException e) {
            throw new UncheckedIOException("Error writing text to file", e);
        }
    }

    public static String getFileName(MultipartFile multipartFile) {
        String fileExtension = "";
        String originalFilename = multipartFile.getOriginalFilename();
        assert originalFilename != null;
        int lastDotIndex = originalFilename.lastIndexOf('.');
        if (lastDotIndex > 0) {
            fileExtension = originalFilename.substring(lastDotIndex + 1);
        }
        return originalFilename.substring(0, originalFilename.length() - fileExtension.length() - 1);
    }

    public static String saveFile(MultipartFile file) throws IOException {
        String dir = System.getProperty("user.dir") + VIDEO_RESOURCES_PATH;
        file.transferTo(new File(dir + file.getOriginalFilename()));
        System.out.println(dir + file.getOriginalFilename());
        return dir + file.getOriginalFilename();
    }

    public static List<File> splitMp3File(File sourceMp3File, int segmentDurationMinutes) {
        List<File> chunks = new ArrayList<>();
        int chunkCounter = 1;

        if (!sourceMp3File.exists()) {
            throw new IllegalArgumentException("Source file not found at: " + sourceMp3File.getAbsolutePath());
        }

        try {
            String ffmpegCommand = String.format("ffmpeg -i %s -f segment -segment_time %d -c copy -segment_start_number 1 %s",
                    sourceMp3File.getAbsolutePath(),
                    segmentDurationMinutes * 60,
                    sourceMp3File.getAbsolutePath().replace(".mp3", "-%d.mp3"));

            Process process = Runtime.getRuntime().exec(ffmpegCommand);
            process.waitFor();

            if (process.exitValue() == 0) {
                while (true) {
                    File chunkFile = new File(sourceMp3File.getAbsolutePath().replace(".mp3", "-" + chunkCounter + ".mp3"));
                    if (chunkFile.exists()) {
                        chunks.add(chunkFile);
                        chunkCounter++;
                    } else {
                        break;
                    }
                }
            } else {
                throw new IOException("FFmpeg command failed");
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        return chunks;
    }

    public static String convertToMp3(MultipartFile file) {
        try {
            String inputDirectory = System.getProperty("user.dir") + VIDEO_RESOURCES_PATH;
            String outputDirectory = System.getProperty("user.dir") + AUDIO_RESOURCES_PATH;
            String fileName = getFileName(file);
            String inputFileName = fileName + ".mp4";
            String inputFilePath = inputDirectory + inputFileName;
            String outputFileName = fileName + ".mp3";
            String outputFilePath = outputDirectory + outputFileName;
            System.out.println("Convert processes started");
//            String ffmpegCommand = "ffmpeg -i " + inputFilePath + " -q:a 0 -map a " + outputFilePath;
            String[] ffmpegCommand = {
                    "ffmpeg", "-i", inputFilePath, "-q:a", "0", "-map", "a", outputFilePath
            };

            ProcessBuilder processBuilder = new ProcessBuilder(ffmpegCommand);
            processBuilder.directory(new File(outputDirectory));
            processBuilder.redirectErrorStream(true);
            Process process = processBuilder.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            int exitCode = process.waitFor();
            if (exitCode == 0) {
                return outputFileName;
            } else {
                System.out.println("Exit Code convertToMp3 func: " + exitCode);
                return "Could not convert to mp3 format.";
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return "Failed convert process";
        }
    }



}

package com.madtech.openai_question_evaluation_app.service;

import com.madtech.openai_question_evaluation_app.utils.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileServiceImpl implements FileService{
    @Override
    public String saveFile(MultipartFile file) throws IOException {
        return FileUtils.saveFile(file);
    }
    @Override
    public String convertToMp3(MultipartFile file) throws IOException {
        return FileUtils.convertToMp3(file);
    }

    @Override
    public void deleteFile(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        try{
            Files.delete(path);
            System.out.println("File removed from "+filePath);
        }catch (IOException e){
            System.out.println("File could not removed from "+filePath);
        }
    }
}

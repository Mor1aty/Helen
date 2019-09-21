package com.moriaty.controller;

import com.moriaty.entity.File;
import com.moriaty.entity.Filetype;
import com.moriaty.entity.Folder;
import com.moriaty.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired(required = false)
    private FileRepository fileRepository;

    @GetMapping("/getAllFolder")
    public List<Folder> getAllFolder() {
        return fileRepository.getAllFolder();
    }

    @GetMapping("/getFileByFolder/{id}")
    public List<File> getFileByFolder(@PathVariable("id") long id) {
        return fileRepository.getFileByFolder(id);
    }

    @PostMapping("/createFolder")
    public void createFolder(@RequestBody Folder folder) {
        fileRepository.createFolder(folder);
    }

    @PostMapping("/createFile")
    public void createFile(@RequestBody File file) {
        fileRepository.createFile(file);
    }

    @DeleteMapping("/deleteFile/{id}")
    public void deleteFile(@PathVariable("id") long id) {
        fileRepository.deleteFile(id);
    }

    @DeleteMapping("/deleteFolder/{id}")
    public void deleteFolder(@PathVariable("id") long id) {
        fileRepository.deleteFileByFolder(id);
        fileRepository.deleteFolder(id);
    }

    @GetMapping("/getFileByIdAndUp/{id}/{username}")
    public File getFileByIdAndUp(@PathVariable("id") long id, @PathVariable("username") String username) {
        return fileRepository.getFileByIdAndUp(id, username);
    }

    @GetMapping("/getFolderByIdAndCreator/{id}/{username}")
    public Folder getFolderByIdAndCreator(@PathVariable("id") long id, @PathVariable("username") String username) {
        return fileRepository.getFolderByIdAndCreator(id, username);
    }

    @GetMapping("/getAllFiletype")
    public List<Filetype> getAllFiletype() {
        return fileRepository.getAllFiletype();
    }

    @GetMapping("/getFiletypeBySuffix/{suffix}")
    public Filetype getFiletypeBySuffix(@PathVariable("suffix") String suffix) {
        return fileRepository.getFiletypeBySuffix(suffix);
    }

    @GetMapping("/getFolderById/{id}")
    public Folder getFolderById(@PathVariable("id") long id) {
        return fileRepository.getFolderById(id);
    }
}

package com.moriaty.feign;

import com.moriaty.entity.File;
import com.moriaty.entity.Filetype;
import com.moriaty.entity.Folder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 16计算机 Moriaty
 * @version 1.0
 * @copyright ：Moriaty 版权所有 © 2019
 * @date 2019/8/30 16:18
 * @Description TODO
 * File 服务请求 Feign
 */
@FeignClient("file")
public interface FileFeign {

    @GetMapping("/file/getAllFolder")
    public List<Folder> getAllFolder();

    @GetMapping("/file/getFileByFolder/{id}")
    public List<File> getFileByFolder(@PathVariable("id") long id);

    @PostMapping("/file/createFolder")
    public void createFolder(@RequestBody Folder folder);

    @PostMapping("/file/createFile")
    public void createFile(@RequestBody File file);

    @DeleteMapping("/file/deleteFile/{id}")
    public void deleteFile(@PathVariable("id") long id);

    @DeleteMapping("/file/deleteFolder/{id}")
    public void deleteFolder(@PathVariable("id") long id);

    @GetMapping("/file/getFileByIdAndUp/{id}/{username}")
    public File getFileByIdAndUp(@PathVariable("id") long id, @PathVariable("username") String username);

    @GetMapping("/file/getFolderByIdAndCreator/{id}/{username}")
    public Folder getFolderByIdAndCreator(@PathVariable("id") long id, @PathVariable("username") String username);

    @GetMapping("/file/getAllFiletype")
    public List<Filetype> getAllFiletype();

    @GetMapping("/file/getFiletypeBySuffix/{suffix}")
    public Filetype getFiletypeBySuffix(@PathVariable("suffix") String suffix);

    @GetMapping("/file/getFolderById/{id}")
    public Folder getFolderById(@PathVariable("id") long id);
}

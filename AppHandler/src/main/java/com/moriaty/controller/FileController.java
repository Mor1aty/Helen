package com.moriaty.controller;

import com.alibaba.fastjson.JSON;
import com.moriaty.entity.File;
import com.moriaty.entity.Filetype;
import com.moriaty.entity.Folder;
import com.moriaty.entity.User;
import com.moriaty.feign.FileFeign;
import com.moriaty.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * @author 16计算机 Moriaty
 * @version 1.0
 * @copyright ：Moriaty 版权所有 © 2019
 * @date 2019/8/30 16:21
 * @Description TODO
 * File 服务请求处理
 */
@RestController
@RequestMapping("/appFile")
public class FileController {

    @Autowired
    private FileFeign fileFeign;

    @GetMapping("/getAllFolder")
    public String getAllFolder() {
        return JsonUtil.jsonResponse(GlobalConstant.CODE_SUCCESS, fileFeign.getAllFolder(), "获取成功");
    }

    @GetMapping("/getFileByFolder/{id}")
    public String getFileByFolder(@PathVariable("id") long id) {
        return JsonUtil.jsonResponse(GlobalConstant.CODE_SUCCESS, fileFeign.getFileByFolder(id), "获取成功");
    }

    @PostMapping("/createFolder")
    public String createFolder(@RequestParam("name") String name, HttpServletRequest request) {
        Folder folder = new Folder();
        folder.setName(name);
        folder.setCreateTime(DateUtil.dateToString(new Date()));
        folder.setCreator(JSON.parseObject(JWTUtil.getPlaintextJson(request), User.class));
        fileFeign.createFolder(folder);
        return JsonUtil.jsonResponse(GlobalConstant.CODE_SUCCESS, null, "创建成功");
    }

    @PostMapping("/createFile")
    public String createFile(@RequestParam("file") MultipartFile multipartFile, @RequestParam("folder") long folderId, HttpServletRequest request) {
        try {
            Folder folder = fileFeign.getFolderById(folderId);
            if (folder == null) {
                return JsonUtil.jsonResponse(GlobalConstant.CODE_FAILURE, null, "文件夹不存在");
            }
            String filename = multipartFile.getOriginalFilename();
            String suffix = filename.substring(filename.lastIndexOf("."));
            System.out.println(suffix);
            Filetype filetype = fileFeign.getFiletypeBySuffix(suffix);
            if (filetype == null) {
                return JsonUtil.jsonResponse(GlobalConstant.CODE_FAILURE, null, "上传文件类型不允许");
            }
            String storageName = UUIDUtil.getUUID() + suffix;
            String path = ResourceUtils.getURL("classpath:").getPath();

            File file = new File();
            file.setLocation(path + storageName);
            file.setName(filename);
            file.setType(filetype);
            file.setUp(JSON.parseObject(JWTUtil.getPlaintextJson(request), User.class));
            file.setUpTime(DateUtil.dateToString(new Date()));
            file.setFolder(folder);
            fileFeign.createFile(file);
            multipartFile.transferTo(new java.io.File(file.getLocation()));
            return JsonUtil.jsonResponse(GlobalConstant.CODE_SUCCESS, null, "上传成功");
        } catch (IOException e) {
            e.printStackTrace();
            return JsonUtil.jsonResponse(GlobalConstant.CODE_FAILURE, null, "服务器发生异常");
        }
    }

    @GetMapping("/deleteFile/{id}")
    public String deleteFile(@PathVariable("id") long id, HttpServletRequest request) {
        File file = fileFeign.getFileByIdAndUp(id, JSON.parseObject(JWTUtil.getPlaintextJson(request), User.class).getUsername());
        if (file == null) {
            return JsonUtil.jsonResponse(GlobalConstant.CODE_FAILURE, null, "不能删除非您上传的文件");
        }
        fileFeign.deleteFile(id);
        return JsonUtil.jsonResponse(GlobalConstant.CODE_SUCCESS, null, "删除成功");
    }

    @GetMapping("/deleteFolder/{id}")
    public String deleteFolder(@PathVariable("id") long id, HttpServletRequest request) {
        Folder folder = fileFeign.getFolderByIdAndCreator(id, JSON.parseObject(JWTUtil.getPlaintextJson(request), User.class).getUsername());
        if (folder == null) {
            return JsonUtil.jsonResponse(GlobalConstant.CODE_FAILURE, null, "不能删除非您创建的文件夹");
        }
        fileFeign.deleteFolder(id);
        return JsonUtil.jsonResponse(GlobalConstant.CODE_SUCCESS, null, "删除成功");
    }

    @GetMapping("/getAllFiltype")
    public String getAllFiltype() {
        return JsonUtil.jsonResponse(GlobalConstant.CODE_SUCCESS, fileFeign.getAllFiletype(), "获取成功");
    }
}

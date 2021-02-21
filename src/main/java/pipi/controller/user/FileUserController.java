package pipi.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pipi.model.ResponseStmt;
import pipi.model.article.ArticleFile;
import pipi.model.file.FileManagement;
import pipi.service.FileService;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Controller
public class FileUserController {
    @Autowired
    FileService fileService;


    //上传文件
    @ResponseBody
    @RequestMapping(value ="/post-file",produces = MediaType.APPLICATION_JSON_VALUE,method = RequestMethod.POST)
    public ResponseStmt postFile(
            @RequestParam("file") MultipartFile file,
            @RequestParam("userId") Long userId
    ){
        //获取文件名
        String originalFilename = file.getOriginalFilename();

        String[] split = originalFilename.split("\\.");
        FileManagement fileManagement =fileService.findNewestOne();
        FileManagement fileManagement1 = new FileManagement();
        if (fileManagement == null){
            split[0] = split[0] + 1+"";
        originalFilename = split[0]+"."+split[1];
        fileManagement1.setFileName(originalFilename);
    }else {
        Long num = fileManagement.getId() + 1;
        originalFilename = split[0] = split[0] + num +"."+split[1];
        fileManagement1.setFileName(originalFilename);
    }
        fileManagement1.setCreateTime(LocalDateTime.now());
        fileManagement1.setState("undisposed");
        fileManagement1.setUserId(userId);

        //定义上传文件保存路径
        String beginPath = "E:\\file\\userfile";
        //新建文件
        assert originalFilename != null;
        File filePath = new File(beginPath,originalFilename);

        if (!filePath.getParentFile().exists()){
            filePath.getParentFile().mkdirs();
        }
        try {
            file.transferTo(new File(beginPath + File.separator + originalFilename));
        }catch (IOException e){
            e.printStackTrace();
        }
        fileManagement1.setFilePath(beginPath + File.separator + originalFilename);
        fileService.save(fileManagement1);
        return ResponseStmt.ADD_OK;
    }
    @ResponseBody
    @RequestMapping(value ="/update-file",produces = MediaType.APPLICATION_JSON_VALUE,method = RequestMethod.POST)
    public ResponseStmt finishProcessFile(
            @RequestParam("id")Long id
    ){
        fileService.update(id);


        return ResponseStmt.PROCESS_OK;
    }
}

package pipi.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pipi.model.ResponseStmt;
import pipi.model.article.AddArticleData;
import pipi.model.file.FileManagementData;
import pipi.service.FileService;

@Controller
public class FileController {
    @Autowired
    FileService fileService;

    @ResponseBody
    @RequestMapping(value = "/add_file",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseStmt addArticle(
            @RequestBody FileManagementData fileManagementData
    ){
        fileService.addfile(fileManagementData);
        return ResponseStmt.ADD_OK;
    }

}

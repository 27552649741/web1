package com.cyh.controller;


import com.cyh.bean.msg;
import com.cyh.util.DateUtils;
import org.apache.commons.io.FileUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@RestController
@CrossOrigin
public class uploadController {
    @PostMapping("/upload")
    public msg upload(MultipartFile file, @RequestParam(required = false) String kind)
    {
        String fileRealName = file.getOriginalFilename();//获得原始文件名;
        int pointIndex =  fileRealName.lastIndexOf(".");//点号的位置
        String fileSuffix = fileRealName.substring(pointIndex);//截取文件后缀
        String fileNewName = DateUtils.getNowTimeForUpload();//新文件名,时间戳形式yyyyMMddHHmmssSSS
        String saveFileName = fileNewName.concat(fileSuffix);//新文件完整名（含后缀）
        String filepath="D:\\Ideaprojects\\selfInfo\\target\\selfInfo\\img";
        if(file!=null)
        {
            try {

                File path=new File(filepath);
                if(!path.exists())
                {
                    path.mkdirs();
                }
                File saveFile=new File(filepath);
                saveFile=new File(filepath,saveFileName);
                FileUtils.copyInputStreamToFile(file.getInputStream(),saveFile);
            }
           catch (Exception e)
           {

           }
        }
        return msg.success().add("src",filepath+"\\"+saveFileName).add("info","上传成功");
    }
}

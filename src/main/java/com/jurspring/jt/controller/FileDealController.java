package com.jurspring.jt.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.SocketException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class FileDealController {
    @RequestMapping(value = "/file/upload",method = RequestMethod.POST)
    public String upload(MultipartFile file) throws SocketException, IOException {
        log.info("调取中");
        // 获取文件名
        String fileName = file.getOriginalFilename();
        // 在file文件夹中创建名为fileName的文件
        OutputStreamWriter op = new OutputStreamWriter(new FileOutputStream("D:/workspace/file/" + fileName), "UTF-8");
        // 获取文件输入流
        InputStreamReader inputStreamReader = new InputStreamReader(file.getInputStream());
        char[] bytes = new char[12];
        // 如果这里的bytes不是数组，则每次只会读取一个字节，例如test会变成 t   e     s    t
        while (inputStreamReader.read(bytes) != -1){
            op.write(bytes);
        }
        // 关闭输出流
        op.close();
        // 关闭输入流
        inputStreamReader.close();
        return "上传成功";
    }
    /**
     * 处理上传文件方法请求
     * @param file 前台上传的文件对象
     * @return
     */
//    @RequestMapping(value = "/file/upload",method = RequestMethod.POST)
//    @ResponseBody
//    public Map<String,Object> uploadOne(HttpServletRequest request, @RequestParam("file")MultipartFile file)
//    {
//        Map map=new HashMap();
//        try {
//            //上传目录地址
////            String uploadDir = request.getSession().getServletContext().getRealPath("upload");
//            String uploadDir = request.getSession().getServletContext().getRealPath("/") +"upload/";
//            //如果目录不存在，自动创建文件夹
//            File dir = new File(uploadDir);
//            if(!dir.exists())
//            {
//                dir.mkdir();
//            }
//            //调用上传方法
//            String fileName=upload.executeUpload(uploadDir,file);
//            uploadDir=uploadDir.substring(0,uploadDir.length()-1);
//            map.put("fileName",fileName);
//            map.put("dir",uploadDir);
//        }catch (Exception e)
//        {
//            //打印错误堆栈信息
//            e.printStackTrace();
//            return api.returnJson(2,"上传失败",map);
//        }
//
//        return api.returnJson(1,"上传成功",map);
//    }
}

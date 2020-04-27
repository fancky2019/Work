package zd.ocg.loganalysis.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.ClassUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.UUID;

@Controller
@RequestMapping("/fileupload")
public class FileUpLoadController {

    private static final Logger logger = LoggerFactory.getLogger(FileUpLoadController.class);


    @RequestMapping("")
    public String index() {
//
//        //D:\fancky\Git\Java\Work\loganalysis
//        String path = System.getProperty("user.dir");
//
//        //  输出目录: /D:/fancky/Git/Java/Work/loganalysis/target/classes/
//
//        //获取classes目录绝对路径
//
//        String path1 = ClassUtils.getDefaultClassLoader().getResource("").getPath();
//
//        try {
//            String path2 = ResourceUtils.getURL("classpath:").getPath();
//            int m=0;
//        }
//        catch ( Exception ex)
//        {
//
//        }
        return "upload/uploadFile";
    }


    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public String upload(@RequestParam(value = "file") MultipartFile file) {
        try {
            LocalDateTime localDateTime=LocalDateTime.now();
            String  dateStr=   localDateTime.format( DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            String rootPath = System.getProperty("user.dir");
//            String rootPath =   ResourceUtils.getURL("classpath:").getPath();
            String directory=rootPath+"/uploadfiles"+"/"+dateStr+"/";
            File destFile = new File(directory);
            //判断路径是否存在,和C#不一样。她判断路径和文件是否存在
            if (!destFile.exists()) {
                destFile.mkdirs();
            }

            //获取文件名称
            String sourceFileName = file.getOriginalFilename();

            //写入目的文件
            String fileFullName =directory + sourceFileName;
            file.transferTo(new File(fileFullName));

            return fileFullName;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    @RequestMapping(value = "/uploadStatus")
    @ResponseBody
    public Integer uploadStatus(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Object percent = session.getAttribute("upload_percent");
        return null != percent ? (Integer) percent : 0;
    }

}

package zd.fancky.webconsole.controller;


import org.apache.tomcat.jni.FileInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import zd.fancky.webconsole.model.entity.newClasses.FeedBack;
import zd.fancky.webconsole.model.viewmodel.TPSVM;
import zd.fancky.webconsole.model.vo.MessageResult;
import zd.fancky.webconsole.service.NewClassesService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 上传文件不需要添加额外依赖
 * 长传进度：
 * 添加依赖：commons-fileupload
 * 添加：uploadFile文件夹类
 * 添加：MultipartResolverConfig配置
 */
@Controller
@RequestMapping("/FileUpLoad")
public class FileUpLoadController {

    private static final Logger logger = LoggerFactory.getLogger(FileUpLoadController.class);
    @Autowired    //spring mvc 的IOC
    private NewClassesService newClassesService;

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
        return "fileupload/index";
    }

    //  PicUploadResult uploadManyImg(MultipartFile[] uploadFile, HttpServletRequest request);
    //单文件上传，多个文件上传，参数是个数组
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public String upload(@RequestParam(value = "file") MultipartFile file, HttpServletRequest request) {
        try {
            LocalDateTime localDateTime = LocalDateTime.now();
            String dateStr = localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            String rootPath = System.getProperty("user.dir");
//            String rootPath =   ResourceUtils.getURL("classpath:").getPath();
            String directory = rootPath + "/uploadfiles" + "/" + dateStr + "/";
            File destFile = new File(directory);
            //判断路径是否存在,和C#不一样。她判断路径和文件是否存在
            if (!destFile.exists()) {
                destFile.mkdirs();
            }

            //获取body中的参数
//            String value = (String)request.getAttribute("paramName");
            //获取文件名称
            String sourceFileName = file.getOriginalFilename();
            //写入目的文件
            String fileFullName = directory + sourceFileName;


            //region  自己计算上传进度
//            InputStream inputStream = file.getInputStream();
//            byte[] buffer = new byte[1024];
//            FileOutputStream fileOutputStream = new FileOutputStream(fileFullName);
//            long fileSize=file.getSize();
//            long readTotalSize = 0;
//            int readSize = 0;
//            while ((readSize = inputStream.read(buffer)) != -1) {
//                readTotalSize += readSize;
//                //计算进度
//                long progress=readTotalSize/fileSize;
//                //写入文件
//                fileOutputStream.write(buffer,0,readSize);
//
//            }
//            fileOutputStream.close();
//            inputStream.close();
            //endregion


            file.transferTo(new File(fileFullName));

            return fileFullName;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    //注：前端传过来的参数名称files要对应
    //  PicUploadResult uploadManyImg(MultipartFile[] uploadFile, HttpServletRequest request);
    //单文件上传，多个文件上传，参数是个数组
    @RequestMapping(value = "/uploadFileAndForm", method = RequestMethod.POST)
    @ResponseBody
    public MessageResult<Void> uploadFileAndForm(@RequestParam(value = "files") MultipartFile[] files, HttpServletRequest request) {
        MessageResult<Void> messageResult = new MessageResult<>();
        logger.info("uploadFileAndForm");
        try {
            String suggestion = request.getParameter("suggestion");//取出form-data中a的值
            String phone = request.getParameter("phone");//取出form-data中a的值

            LocalDateTime localDateTime = LocalDateTime.now();
            String dateStr = localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            String rootPath = System.getProperty("user.dir");
//            String rootPath =   ResourceUtils.getURL("classpath:").getPath();
            String directory = rootPath + "\\uploadfiles" + "\\" + dateStr + "\\";
            File destFile = new File(directory);
            //判断路径是否存在,和C#不一样。她判断路径和文件是否存在
            if (!destFile.exists()) {
                destFile.mkdirs();
            }

            List<String> fileNameList = new ArrayList<>();
            for (MultipartFile file : files) {


                //获取body中的参数
//            String value = (String)request.getAttribute("paramName");
                //获取文件名称
                String sourceFileName = file.getOriginalFilename();
                //写入目的文件
                String fileFullName = directory + sourceFileName;


                //region  自己计算上传进度
//            InputStream inputStream = file.getInputStream();
//            byte[] buffer = new byte[1024];
//            FileOutputStream fileOutputStream = new FileOutputStream(fileFullName);
//            long fileSize=file.getSize();
//            long readTotalSize = 0;
//            int readSize = 0;
//            while ((readSize = inputStream.read(buffer)) != -1) {
//                readTotalSize += readSize;
//                //计算进度
//                long progress=readTotalSize/fileSize;
//                //写入文件
//                fileOutputStream.write(buffer,0,readSize);
//
//            }
//            fileOutputStream.close();
//            inputStream.close();
                //endregion


                file.transferTo(new File(fileFullName));
                fileNameList.add(fileFullName);
            }
            String fileNames = String.join(";", fileNameList);

            FeedBack feedBack = new FeedBack();
            feedBack.setImagepath(fileNames);
            feedBack.setPhone(phone);
            feedBack.setSuggestion(suggestion);
            messageResult = newClassesService.insert(feedBack);


            return messageResult;
//            return fileFullName;
        } catch (Exception e) {
            e.printStackTrace();
            messageResult.setSuccess(false);
            return messageResult;
        }
    }


    @RequestMapping(value = "/uploadStatus")
    @ResponseBody
    public Integer uploadStatus(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Object percent = session.getAttribute("upload_percent");
        return null != percent ? (Integer) percent : 0;
    }

    // SpringMVC的自动装箱（实体类接收参数）
    //post提交 data:{}是一个对象，要用对象接收，类的访问级别是共有，否则MVC反射找不到报。
    //  @ResponseBody  返回业务对象，不要返回字符串，不然前台无法转换JSON而报错，还要Json 序列化操作。
    @RequestMapping(value = "/deleteFile")
    @ResponseBody
    public MessageResult<Void> deleteFile(@RequestBody String filePath) {
        File file = new File(filePath);
        String returnMsg = "";
        MessageResult<Void> message = new MessageResult<>();
        //判断路径是否存在,和C#不一样。她判断路径和文件是否存在
        if (file.exists()) {
            if (file.delete()) {
                returnMsg = "删除成功!";
                message.setSuccess(true);
            } else {
                returnMsg = "删除失败!";
                message.setSuccess(false);
            }
        } else {
            returnMsg = "文件不存在!";
            message.setSuccess(false);
        }


        message.setMessage(returnMsg);
        return message;

    }


    //格式错误：无法请求到后端
//    @RequestMapping(value = "/deleteFile")
//    @ResponseBody
//    public MessageResult<Void> deleteFile(@RequestParam(value = "filePath") String filePath) {
//        File file = new File(filePath);
//        String returnMsg = "";
//        MessageResult<Void> message = new MessageResult<>();
//        //判断路径是否存在,和C#不一样。她判断路径和文件是否存在
//        if (file.exists()) {
//            if (file.delete()) {
//                returnMsg = "删除成功!";
//                message.setSuccess(true);
//            } else {
//                returnMsg = "删除失败!";
//                message.setSuccess(false);
//            }
//        } else {
//            returnMsg = "文件不存在!";
//            message.setSuccess(false);
//        }
//
//
//        message.setMessage(returnMsg);
//        return message;
//
//    }


    @GetMapping("/getQuery")
    @ResponseBody
    public LinkedList<TPSVM> getQuery(TPSVM tpsvm) {
        LinkedList<TPSVM> list = new LinkedList<>();
        TPSVM tpsvm1 = new TPSVM();
        tpsvm1.setCustomerNo("fancky123");
        tpsvm1.settPSQueueCount(22);
        String timeStr = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));
        tpsvm1.setDequeueTime(timeStr);
        list.add(tpsvm1);
        return list;
    }
}


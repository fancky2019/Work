package zd.fancky.webconsole.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import zd.fancky.webconsole.model.ClientInLog;
import zd.fancky.webconsole.service.LogAnalysisService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Controller
//@RequestMapping("")
//@RequestMapping("")
public class LogAnalysisController {

    private static final Logger logger = LoggerFactory.getLogger(LogAnalysisController.class);

    @Autowired
    LogAnalysisService logAnalysisService;


    @RequestMapping("/loganalysis")
    public String index() {
        return "index";
    }

    @RequestMapping("/ClientIn")
    public String clientIn() {
//        //每毫秒可以打印15条log.
//        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
//        for (int i = 0; i < 9999; i++) {
//            logger.info(MessageFormat.format("{0} : i - {1}", LocalDateTime.now().format(dateTimeFormatter), i));
//        }
        return "clientin/index";
    }

    @RequestMapping("/getData")
    @ResponseBody
    public List<ClientInLog> getData() {
//       // String p = "20200113 07:16:52:239,3469952817305 [3] - 192.168.80.22:63130 {(len=19)TEST0001@@@@@@@@@@&}";
//       // String p="20200113 06:44:08:067,3466308395631 [2] - 30.0.0.40:64102 connected!";
//       String p ="20200113 11:38:52:214,3499120543282 [3] - 192.168.80.22:63130 {(len=167)CancStHK@00000@00011150LC004646@8205775@@CO020990001@HKEX@@@@0009861&HKEX_1@@CO020990001@123456@@00011150LC004646@13029242@HKEX@68361.HK@1@300000@0.100000@0@@7@@P@@@@@}";
//        int nineteenIndex = p.indexOf("len=");
//        if (nineteenIndex != -1) {
//
//        }
//
//
//        int rightParenthesesIndex = p.indexOf(")");
//        String lenStr = p.substring(nineteenIndex+4, rightParenthesesIndex);
//        Integer len=Integer.parseInt(lenStr);
//        if(len<100)
//        {
//
//        }
//        int m0 = 0;

        List<ClientInLog> datas = logAnalysisService.getData();
        return datas;

    }

    @RequestMapping("/statisticsAnalysis")
    @ResponseBody
    private HashMap<String, Integer> statisticsAnalysis(String fileName, String unitStr) {
        TimeUnit unit = TimeUnit.SECONDS;
        switch (unitStr) {
            case "second":
                unit = TimeUnit.SECONDS;
                break;
            case "minute":
                unit = TimeUnit.MINUTES;
                break;
            case "hour":
                unit = TimeUnit.HOURS;
                break;
        }
        HashMap<String, Integer> statisticsAnalysisResult = logAnalysisService.statisticsAnalysis(fileName, unit);
        return statisticsAnalysisResult;
    }


    /*
    @RequestParam(value="file", required=false).要设置required=false不然报下面错误
    Required request part 'file' is not present
     */
    @PostMapping("/upload")
    @ResponseBody
    public String fileUpload(@RequestParam(value = "file") MultipartFile srcFile, RedirectAttributes redirectAttributes) {
        //前端没有选择文件，srcFile为空
        if (srcFile.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "请选择一个文件");
//            return "redirect:upload_status";
            return "请选择文件！";
        }
        //选择了文件，开始进行上传操作
        try {
            //构建上传目标路径
            File destFile = new File(ResourceUtils.getURL("classpath:").getPath());
            if (!destFile.exists()) {
                destFile = new File("");
            }
            //输出目标文件的绝对路径:D:\fancky\Git\Java\Work\loganalysis\target\classes
//            System.out.println("file path:"+destFile.getAbsolutePath());
            File upload = new File(destFile.getAbsolutePath(), LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + "/");
            //拼接static目录
//            String fileName= srcFile.getOriginalFilename();
//            String suffixName = fileName.substring(fileName.lastIndexOf("."));
//            fileName= UUID.randomUUID() +suffixName;
            //若目标文件夹不存在，则创建一个
            if (!upload.exists()) {
                upload.mkdirs();
            }
            System.out.println("完整的上传路径:" + upload.getAbsolutePath() + "/" + srcFile.getOriginalFilename());
            //根据srcFile的大小，准备一个字节数组
            byte[] bytes = srcFile.getBytes();
            //拼接上传路径
            /*Path path= Paths.get(UPLOAD_FOLDER+srcFile.getOriginalFilename());*/
            //通过项目路径，拼接上传路径
            String filePath = upload.getAbsolutePath() + "/" + srcFile.getOriginalFilename();
            Path path = Paths.get(filePath);

            //最重要的一步，将源文件写入目标地址
            Files.write(path, bytes);
            //将文件上传成功的信息写入messages
            //  redirectAttributes.addFlashAttribute("message","文件上传成功"+srcFile.getOriginalFilename());
            return filePath;
        } catch (IOException e) {
            e.printStackTrace();
            return "上传失败！";
        }
//        return "redirect:upload_status";
    }

}

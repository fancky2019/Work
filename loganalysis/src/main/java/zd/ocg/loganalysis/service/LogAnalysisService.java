package zd.ocg.loganalysis.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import zd.ocg.loganalysis.model.ClientInLog;
import zd.ocg.loganalysis.utilities.TXTFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class LogAnalysisService {

    Logger logger = LoggerFactory.getLogger(LogAnalysisService.class);

    public List<ClientInLog> getData() {
        String fileName = "C:\\Users\\Administrator\\Desktop\\ClientIn_20200115.log";
        List<ClientInLog> datas = readData(fileName);
        return datas;
    }


    private List<ClientInLog> readData(String fileName) {
        // String fileName = "C:\\Users\\Administrator\\Desktop\\ClientIn_20200115.log";
        List<ClientInLog> orderContent = new ArrayList<>();
        try {
            List<String> allContent = TXTFile.readTXT(fileName);


            for (String p : allContent) {
                try {
//                    int nineteenIndex = p.indexOf("len=");
//                    if (nineteenIndex == -1) {
//                        continue;
//                    }
//                    int rightParenthesesIndex = p.indexOf(")");
//                    String lenStr = p.substring(nineteenIndex + 4, rightParenthesesIndex);
//                    Integer len = Integer.parseInt(lenStr);
//                    //不是下单指令，可能是心跳
//                    if (len < 100) {
//                        continue;
//                    }
//                    String logTimeStr = p.substring(0, 21);
//                    LocalDateTime logTime = LocalDateTime.parse(logTimeStr, DateTimeFormatter.ofPattern("yyyyMMdd HH:mm:ss:SSS"));
//                    int rightParenthesisIndex = p.indexOf(")");
//                    String content = p.substring(rightParenthesisIndex, p.length() - 1);


                    String logContent = p.substring(62);
                    int logContentLength = logContent.length();
                    List<String> listString = new ArrayList<>();
                    //不是下单指令，可能是心跳、登录指令
                    if (logContentLength < 100) {
                        continue;
                    } else {

                        int firstIndex = logContent.indexOf('}');
                        logContentLength = logContent.length();

                        if (firstIndex == logContentLength - 1) {
                            listString.add(logContent);
                        } else {
                            String currentContent = "";
                            while (firstIndex != logContentLength - 1) {
                                currentContent = logContent.substring(0, firstIndex + 1);
                                listString.add(currentContent);

                                logContent = logContent.substring(firstIndex + 1);
                                logContentLength = logContent.length();
                                firstIndex = logContent.indexOf('}');
                            }
                            listString.add(logContent);
                        }

                        String logTimeStr = p.substring(0, 21);
                        LocalDateTime logTime = LocalDateTime.parse(logTimeStr, DateTimeFormatter.ofPattern("yyyyMMdd HH:mm:ss:SSS"));

                        listString.forEach(str->
                        {
                            ClientInLog clientInLog = new ClientInLog();

                            clientInLog.setLogTime(logTime)
                                    .setContent(str);
                            orderContent.add(clientInLog);
                        });

//                        int m = 0;
                    }
                } catch (Exception ex) {
                    logger.error(p);
                    logger.error(ex.getMessage());
                }
            }

        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
        return orderContent;
    }

    public LinkedHashMap<String, Integer> statisticsAnalysis(String fileName, TimeUnit unit) {

        List<ClientInLog> datas = readData(fileName);
        //排序
        // datas.stream().sorted(Comparator.comparing(ClientInLog::getLogTime));
        // LinkedHashMap<String, List<Brand>> brandMap = brandList.stream().collect(Collectors.groupingBy(Brand::getFirstLetter, LinkedHashMap::new, Collectors.toList()));
        //LinkedHashMap取键值对时，是按照你放入的顺序来取的,避免hashmap造成顺序是乱的
        LinkedHashMap<String, List<ClientInLog>> groupByResult = null;
        switch (unit) {
            case SECONDS:
                groupByResult = datas.stream().collect(Collectors.groupingBy(ClientInLog::getLogTimeSecondStr, LinkedHashMap::new, Collectors.toList()));
                break;
            case MINUTES:
                groupByResult = datas.stream().collect(Collectors.groupingBy(ClientInLog::getLogTimeMinuteStr, LinkedHashMap::new, Collectors.toList()));
                break;
            case HOURS:
                groupByResult = datas.stream().collect(Collectors.groupingBy(ClientInLog::getLogTimeHourStr, LinkedHashMap::new, Collectors.toList()));
                break;
        }
        //用HashMap会造成顺序乱。HashMap是无序的。
        LinkedHashMap<String, Integer> statisticsAnalysis = new LinkedHashMap<>();
        groupByResult.forEach((key, val) ->
        {
            statisticsAnalysis.put(key, val.size());
        });

        return statisticsAnalysis;
    }
}

package zd.ocg.loganalysis.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import zd.ocg.loganalysis.model.viewmodel.TPSQueueVM;
import zd.ocg.loganalysis.utilities.TXTFile;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class ClientInTPSService {
    Logger logger = LoggerFactory.getLogger(LogAnalysisService.class);

    public LinkedHashMap<String, Integer> getData() {
        String fileName = "C:\\Users\\Administrator\\Desktop\\20200113\\TPSQueue_20200113.log";
        List<TPSQueueVM> datas = readData(fileName);
        LinkedHashMap<String, Integer> statisticsAnalysis = statisticsAnalysis(fileName, TimeUnit.SECONDS);
        return statisticsAnalysis;
    }

    private List<TPSQueueVM> readData(String fileName) {
        // String fileName = "C:\\Users\\Administrator\\Desktop\\ClientIn_20200115.log";
        List<TPSQueueVM> tPSQueueData = new ArrayList<>();
        try {
            List<String> allContent = TXTFile.readTXT(fileName);

            int currentLineCout = 0;
            int id = 0;
            for (String p : allContent) {
                try {
                    int nineteenIndex = p.indexOf("Sleep");
                    if (nineteenIndex != -1) {
                        continue;
                    }

                    String logTimeStr = p.substring(0, 21);
                    LocalDateTime logTime = LocalDateTime.parse(logTimeStr, DateTimeFormatter.ofPattern("yyyyMMdd HH:mm:ss:SSS"));
                    //66
                    int colonIndex = p.indexOf(":", 66);
                    String enqueueOrDequeue = p.substring(66, colonIndex);

                    //   String content = p.substring(colonIndex, p.length() - 1);


                    switch (enqueueOrDequeue) {
                        case "Enqueue":
                            currentLineCout++;
                            break;
                        case "Dequeue":
                            currentLineCout--;
                            break;
                    }

                    TPSQueueVM tPSQueueVM = new TPSQueueVM();
                    tPSQueueVM.setId(++id);
                    tPSQueueVM.setLogTime(logTime);
                    tPSQueueVM.setEnqueueOrDequeue(enqueueOrDequeue);
                    tPSQueueVM.setLineCout(currentLineCout);
                    tPSQueueData.add(tPSQueueVM);
                } catch (Exception ex) {
                    logger.error(p);
                    logger.error(ex.getMessage());
                }
            }

        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
        TPSQueueVM last = tPSQueueData.get(tPSQueueData.size() - 1);
        return tPSQueueData;
    }

    public LinkedHashMap<String, Integer> statisticsAnalysis(String fileName, TimeUnit unit) {
        List<TPSQueueVM> datas = readData(fileName);
//        LocalDate tomorrow = LocalDate.parse("2020-01-14", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
//        LocalDateTime localDateTime=  LocalDateTime.of(tomorrow, LocalTime.of(0,0,0));
        LinkedHashMap<String, List<TPSQueueVM>> groupByResult = null;
        switch (unit) {
            case SECONDS:
                groupByResult = datas.stream().collect(Collectors.groupingBy(TPSQueueVM::getLogTimeSecondStr, LinkedHashMap::new, Collectors.toList()));
                break;
            case MINUTES:
                groupByResult = datas.stream().collect(Collectors.groupingBy(TPSQueueVM::getLogTimeMinuteStr, LinkedHashMap::new, Collectors.toList()));
                break;
            case HOURS:
                groupByResult = datas.stream().collect(Collectors.groupingBy(TPSQueueVM::getLogTimeHourStr, LinkedHashMap::new, Collectors.toList()));
                break;
        }

        //用HashMap会造成顺序乱。HashMap是无序的。
        LinkedHashMap<String, Integer> statisticsAnalysis = new LinkedHashMap<>();
        groupByResult.forEach((key, val) ->
        {
            IntSummaryStatistics intSummaryStatistics = val.stream().mapToInt(p -> p.getId()).summaryStatistics();
            int maxId = intSummaryStatistics.getMax();
            int currentLineCount = val.stream().filter(p -> p.getId() == maxId).collect(Collectors.toList()).get(0).getLineCout();
            statisticsAnalysis.put(key, currentLineCount);
        });


        int m = 0;

        return statisticsAnalysis;
    }


}

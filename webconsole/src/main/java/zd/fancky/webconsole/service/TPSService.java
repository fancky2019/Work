package zd.fancky.webconsole.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import zd.fancky.webconsole.model.viewmodel.NetInfo;
import zd.fancky.webconsole.model.viewmodel.TPSQueueVM;
import zd.fancky.webconsole.model.viewmodel.TPSVM;
import zd.fancky.webconsole.utilities.TXTFile;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TPSService {
    Logger logger = LoggerFactory.getLogger(TPSService.class);

    public List<TPSQueueVM> getData() {
        String fileName = "C:\\Users\\Administrator\\Desktop\\TPSQueue.log";
        List<TPSQueueVM> datas = readData(fileName);

        List<TPSVM> statisticsAnalysisData = statisticsAnalysis(datas);
        return datas;
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
                    String enqueueOrDequeue = p.substring(66 + 1, colonIndex);


                    switch (enqueueOrDequeue) {
                        case "Enqueue":
                            currentLineCout++;
                            break;
                        case "Dequeue":
                            currentLineCout--;
                            break;
                    }
                    String netInfoStr = p.substring(colonIndex + 1, p.length() - 1);
                    String[] netInfoArray = netInfoStr.split("@");
                    NetInfo netInfo = new NetInfo();
                    netInfo.setOrderType(netInfoArray[0]);
                    netInfo.setCustomerNo(netInfoArray[2]);

                    TPSQueueVM tPSQueueVM = new TPSQueueVM();
                    tPSQueueVM.setId(++id);
                    tPSQueueVM.setLogTime(logTime);
                    tPSQueueVM.setEnqueueOrDequeue(enqueueOrDequeue);
                    tPSQueueVM.setLineCount(currentLineCout);
                    tPSQueueVM.setNetInfo(netInfo);
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

    public List<TPSVM> statisticsAnalysis(String fileName) {
        List<TPSQueueVM> datas = readData(fileName);

        List<TPSVM> statisticsAnalysisData = statisticsAnalysis(datas);

        return statisticsAnalysisData;
    }

    private List<TPSVM> statisticsAnalysis(List<TPSQueueVM> listTPSQueueVM) {
        List<TPSVM> tpsvmList = new ArrayList<>();

        List<TPSQueueVM> enQueueList = listTPSQueueVM.stream().filter(p -> p.getEnqueueOrDequeue().equals("Enqueue")).collect(Collectors.toList());
        List<TPSQueueVM> deQueueList = listTPSQueueVM.stream().filter(p -> p.getEnqueueOrDequeue().equals("Dequeue")).collect(Collectors.toList());
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd HH:mm:ss:SSS");
        //ageList.contains(s.getAge())
        enQueueList.forEach(p ->
        {
            TPSVM tpsvm = new TPSVM();
            tpsvm.setLogTime(p.getLogTime().format(dateTimeFormatter));
            tpsvm.settPSQueueCount(p.getLineCount());
            tpsvm.setEnqueueTime(p.getLogTime().format(dateTimeFormatter));
            tpsvm.setOrderType(p.getNetInfo().getOrderType());
            tpsvm.setCustomerNo(p.getNetInfo().getCustomerNo());
            tpsvmList.add(tpsvm);

            deQueueList.forEach(d ->
            {
                if (p.getNetInfo().getOrderType().equals(d.getNetInfo().getOrderType()) && p.getNetInfo().getCustomerNo().equals(d.getNetInfo().getCustomerNo())) {
                    tpsvm.setDequeueTime(d.getLogTime().format(dateTimeFormatter));
                }
            });
        });
        return tpsvmList;
    }
}

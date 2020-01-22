package zd.ocg.loganalysis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import zd.ocg.loganalysis.model.ClientInLog;
import zd.ocg.loganalysis.model.ClientInTPS;
import zd.ocg.loganalysis.model.TPSQueueVM;
import zd.ocg.loganalysis.service.ClientInTPSService;
import zd.ocg.loganalysis.service.LogAnalysisService;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * vali:github:https://github.com/pratikborsadiya/vali-admin
 */
@Controller
//@RequestMapping("/ClientInTPS")
public class ClientInTPSController {


    @Autowired
    LogAnalysisService logAnalysisService;

    @Autowired
    ClientInTPSService clientInTPSService;

    @RequestMapping("/clientInTPS")
    public String clientInTPS() {
        return "clientInTPS";
    }

    @RequestMapping("/clientInTPSStatisticsAnalysis")
    @ResponseBody
    public List<ClientInTPS> clientInTPSStatisticsAnalysis(String clientFileName, String tpsQueueFileName, String unitStr) {

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
        List<ClientInTPS> list = new ArrayList<>();
        LinkedHashMap<String, Integer> clintInStatisticsAnalysis = logAnalysisService.statisticsAnalysis(clientFileName, unit);
        LinkedHashMap<String, Integer> tpsStatisticsAnalysis = clientInTPSService.statisticsAnalysis(tpsQueueFileName, unit);


        LinkedHashSet<String> dateSet = new LinkedHashSet<>();
        dateSet.addAll(clintInStatisticsAnalysis.keySet());
        dateSet.addAll(tpsStatisticsAnalysis.keySet());

        dateSet.forEach(p ->
        {
            ClientInTPS clientInTPS = new ClientInTPS();
            clientInTPS.setLogTime(p);
            if (clintInStatisticsAnalysis.containsKey(p)) {
                clientInTPS.setClientCount(clintInStatisticsAnalysis.get(p));
            }
            if (tpsStatisticsAnalysis.containsKey(p)) {
                clientInTPS.settPSQueueCount(tpsStatisticsAnalysis.get(p));
            }
            list.add(clientInTPS);
        });
        return list;

    }
}

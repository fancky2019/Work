package zd.fancky.webconsole.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import zd.fancky.webconsole.model.ClientInTPS;
import zd.fancky.webconsole.service.ClientInTPSService;
import zd.fancky.webconsole.service.LogAnalysisService;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * vali:github:https://github.com/pratikborsadiya/vali-admin
 */
@Controller
@RequestMapping("")
public class ClientInTPSController {


    @Autowired
    LogAnalysisService logAnalysisService;

    @Autowired
    ClientInTPSService clientInTPSService;

    @RequestMapping("/ClientInTPS")
    public String clientInTPS() {
        return "clientintps/index";
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

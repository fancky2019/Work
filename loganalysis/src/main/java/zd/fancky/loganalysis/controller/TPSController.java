package zd.fancky.loganalysis.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import zd.fancky.loganalysis.model.PageData;
import zd.fancky.loganalysis.model.viewmodel.TPSVM;
import zd.fancky.loganalysis.model.querymodel.TPSVMQueryModel;
import zd.fancky.loganalysis.service.TPSService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@Controller
//@RequestMapping("/ClientInTPS")
public class TPSController {

    Logger logger = LoggerFactory.getLogger(TPSController.class);

    @Autowired
    private TPSService tPSService;

    @RequestMapping("/tps")
    public String clientInTPS() {
        return "tps";
    }

    @RequestMapping("/tpsData1")
    @ResponseBody
    public PageData<TPSVM> tpsData(TPSVMQueryModel queryModel) {
        PageData<TPSVM> pageData = new PageData<>();
//        List<TPSVM> list = new ArrayList<>();
//        for (int i = 1; i <= 37; i++) {
//            TPSVM tpsvm = new TPSVM();
//            tpsvm.setLogTime("2020-02-11 12:23:44");
//            tpsvm.setEnqueueTime("2020-02-11 12:23:44");
//            tpsvm.setDequeueTime("2020-02-11 12:23:44");
//            tpsvm.settPSQueueCount(i);
//            list.add(tpsvm);
//        }


        String fileName = "C:\\Users\\Administrator\\Desktop\\TPSQueue.log";
//         tPSService.getData();
        List<TPSVM> list = tPSService.statisticsAnalysis(fileName);

        if (queryModel.gettPSQueueCount() != null) {
            list = list.stream().filter(p -> p.gettPSQueueCount() > queryModel.gettPSQueueCount()).collect(Collectors.toList());
        }


        // 从第几条数据开始
        int fromIndex = queryModel.getSkip();
        // 到第几条数据结束
        int toIndex = queryModel.getSkip() + queryModel.getTake();
        toIndex = toIndex > list.size() ? list.size() : toIndex;
        int total = list.size();
//
        list = list.subList(fromIndex, toIndex);
        pageData.setRows(list);
        pageData.setTotal(total);
        return pageData;
    }


    @RequestMapping("/tpsData")
    @ResponseBody
    public PageData<TPSVM> tpsDataSession(TPSVMQueryModel queryModel, HttpServletRequest request, HttpSession session) {
        PageData<TPSVM> pageData = new PageData<>();
//        List<TPSVM> list = new ArrayList<>();
//        for (int i = 1; i <= 37; i++) {
//            TPSVM tpsvm = new TPSVM();
//            tpsvm.setLogTime("2020-02-11 12:23:44");
//            tpsvm.setEnqueueTime("2020-02-11 12:23:44");
//            tpsvm.setDequeueTime("2020-02-11 12:23:44");
//            tpsvm.settPSQueueCount(i);
//            list.add(tpsvm);
//        }
        if (queryModel.getFilePath() == null || queryModel.getFilePath().isEmpty()) {
            return pageData;
        }

//        String fileName = "C:\\Users\\Administrator\\Desktop\\TPSQueue.log";
//         tPSService.getData();
        List<TPSVM> list = tPSService.statisticsAnalysis(queryModel.getFilePath());


        //采用session
//        List<TPSVM> list = null;
//        Object tpsData = session.getAttribute("tpsData");
//        if (tpsData == null) {
//            //   String fileName = "C:\\Users\\Administrator\\Desktop\\TPSQueue.log";
//            list = tPSService.statisticsAnalysis(queryModel.getFilePath();
//            session.setAttribute("tpsData", list);
//        } else {
//            list = (List<TPSVM>) tpsData;
//        }

        if (queryModel.gettPSQueueCount() != null) {
            list = list.stream().filter(p -> p.gettPSQueueCount() > queryModel.gettPSQueueCount()).collect(Collectors.toList());
        }
        if (queryModel.getCustomerNo() != null && !queryModel.getCustomerNo().isEmpty()) {
            list = list.stream().filter(p -> p.getCustomerNo().contains(queryModel.getCustomerNo())).collect(Collectors.toList());
        }


        // 从第几条数据开始
        int fromIndex = queryModel.getSkip();
        // 到第几条数据结束
        int toIndex = queryModel.getSkip() + queryModel.getTake();
        toIndex = toIndex > list.size() ? list.size() : toIndex;
        int total = list.size();
//
        list = list.subList(fromIndex, toIndex);
        pageData.setRows(list);
        pageData.setTotal(total);
        return pageData;
    }


    @RequestMapping("/tpsTable")
    @ResponseBody
    public PageData<TPSVM> tpsData() {
        String fileName = "C:\\Users\\Administrator\\Desktop\\TPSQueue.log";
//         tPSService.getData();
        tPSService.statisticsAnalysis(fileName);
        return null;
    }
}

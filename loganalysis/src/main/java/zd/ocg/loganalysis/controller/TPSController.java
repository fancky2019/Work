package zd.ocg.loganalysis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import zd.ocg.loganalysis.model.PageData;
import zd.ocg.loganalysis.model.querymodel.QueryModelBase;
import zd.ocg.loganalysis.model.TPSVM;
import zd.ocg.loganalysis.model.querymodel.TPSVMQueryModel;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
//@RequestMapping("/ClientInTPS")
public class TPSController {
    @RequestMapping("/tps")
    public String clientInTPS() {
        return "tps";
    }

    @RequestMapping("/tpsData")
    @ResponseBody
    public PageData<TPSVM> tpsData(TPSVMQueryModel queryModel) {
        PageData<TPSVM> pageData = new PageData<>();
        List<TPSVM> list = new ArrayList<>();
        for (int i = 1; i <= 37; i++) {
            TPSVM tpsvm = new TPSVM();
            tpsvm.setLogTime("2020-02-11 12:23:44");
            tpsvm.setEnqueueTime("2020-02-11 12:23:44");
            tpsvm.setDequeueTime("2020-02-11 12:23:44");
            tpsvm.settPSQueueCount(i);
            list.add(tpsvm);
        }


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
}

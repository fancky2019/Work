package zd.ocg.loganalysis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
//@RequestMapping("/ClientInTPS")
public class TPSController {
    @RequestMapping("/tps")
    public String clientInTPS() {
        return "tps";
    }
}

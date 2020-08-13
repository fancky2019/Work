package zd.fancky.webconsole.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/TPS")
public class TPCController {
    @GetMapping("")
    public String tps() {
        return "tps/index";
    }
}

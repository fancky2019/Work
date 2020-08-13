package zd.fancky.webconsole.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/UserManager")
public class UserManagerController {
    @GetMapping("")
    public String index() {
        return "usermanager/index";
    }
}

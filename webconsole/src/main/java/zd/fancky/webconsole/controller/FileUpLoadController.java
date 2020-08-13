package zd.fancky.webconsole.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/FileUpLoad")
public class FileUpLoadController {
    @GetMapping("")
    public String fileUpLoad() {
        return "fileupload/index";
    }
}

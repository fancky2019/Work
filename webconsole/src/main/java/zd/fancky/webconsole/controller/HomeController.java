package zd.fancky.webconsole.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.ResponseBody;
import zd.fancky.webconsole.model.ClientInTPS;
import zd.fancky.webconsole.model.po.Menus;
import zd.fancky.webconsole.model.po.User;

@Controller
@RequestMapping("")
public class HomeController {
    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);


    @RequestMapping("")
    public String index() {

        logger.info("info");
        logger.debug("debug");
        logger.error("error");
//
//        //D:\fancky\Git\Java\Work\loganalysis
//        String path = System.getProperty("user.dir");
//
//        //  输出目录: /D:/fancky/Git/Java/Work/loganalysis/target/classes/
//
//        //获取classes目录绝对路径
//
//        String path1 = ClassUtils.getDefaultClassLoader().getResource("").getPath();
//
//        try {
//            String path2 = ResourceUtils.getURL("classpath:").getPath();
//            int m=0;
//        }
//        catch ( Exception ex)
//        {
//
//        }
        return "home/index";
    }

    @RequestMapping("/getMenus")
    @ResponseBody
    public List<Menus> getMenus(User user) {
        List<Menus> list = new ArrayList<Menus>();
        Menus menu = new Menus();
        menu.setId(1);
        menu.setParentID(0);
        menu.setMenuName("OCG");
        menu.setDisplayName("OCG日志分析");
        menu.setUrl("#");
        menu.setIcoName("");
        list.add(menu);


        // /clientIn
        menu = new Menus();
        menu.setId(2);
        menu.setParentID(1);
        menu.setMenuName("ClientIn");
        menu.setDisplayName("ClientIn");
        menu.setUrl("/ClientIn");
        menu.setIcoName("");
        list.add(menu);

        menu = new Menus();
        menu.setId(3);
        menu.setParentID(1);
        menu.setMenuName("TPS");
        menu.setDisplayName("TPS");
        menu.setUrl("/TPS");
        menu.setIcoName("");
        list.add(menu);

        // /clientIn
        menu = new Menus();
        menu.setId(4);
        menu.setParentID(1);
        menu.setMenuName("ClientInTPS");
        menu.setDisplayName("ClientInTPS");
        menu.setUrl("/ClientInTPS");
        menu.setIcoName("");
        list.add(menu);


        menu = new Menus();
        menu.setId(5);
        menu.setParentID(0);
        menu.setMenuName("TT");
        menu.setDisplayName("TT");
        menu.setUrl("/tt");
        menu.setIcoName("");
        list.add(menu);

        menu = new Menus();
        menu.setId(6);
        menu.setParentID(0);
        menu.setMenuName("PSHK");
        menu.setDisplayName("PSHK");
        menu.setUrl("/pshk");
        menu.setIcoName("");
        list.add(menu);


        return list;
    }


}

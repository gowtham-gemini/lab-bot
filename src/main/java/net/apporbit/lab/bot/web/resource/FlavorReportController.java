package net.apporbit.lab.bot.web.resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FlavorReportController {
    @RequestMapping("/flavorReports")
    public ModelAndView greeting() {

    	ModelAndView model = new ModelAndView("flavorReport");
        return model;

    }
}

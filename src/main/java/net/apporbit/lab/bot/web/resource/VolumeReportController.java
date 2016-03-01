package net.apporbit.lab.bot.web.resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class VolumeReportController {
    @RequestMapping("/volumeReports")
    public ModelAndView greeting() {

    	ModelAndView model = new ModelAndView("volumeReport");
        return model;

    }
}

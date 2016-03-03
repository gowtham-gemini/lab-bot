package net.apporbit.lab.bot.web.resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FlavorReportController {
    @RequestMapping("/flavorReports")
    public String greeting() {
        return "flavorReport";

    }
}

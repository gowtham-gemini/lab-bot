package net.apporbit.lab.bot.web.resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AccountReportController {
    @RequestMapping("/accountReports")
    public String greeting() {
        return "accountReport";

    }
}

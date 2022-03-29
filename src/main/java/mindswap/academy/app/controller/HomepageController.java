package mindswap.academy.app.controller;

import mindswap.academy.app.utils.HTMLUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomepageController {

    @GetMapping("/homepage")
    public String home() {
        return HTMLUtil.getHTML();
    }
}

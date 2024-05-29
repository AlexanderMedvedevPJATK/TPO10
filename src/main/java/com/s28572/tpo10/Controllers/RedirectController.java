package com.s28572.tpo10.Controllers;

import com.s28572.tpo10.Entities.Link;
import com.s28572.tpo10.Services.LinkService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class RedirectController {

    private final LinkService linkService;

    public RedirectController(LinkService linkService) {
        this.linkService = linkService;
    }

    @GetMapping("/red/{id}")
    public RedirectView redirect(@PathVariable String id) {
        Link link = linkService.getLink(id);
        linkService.incrementCounter(link);
        return new RedirectView(link.getTargetUrl(), true, false);
    }
}

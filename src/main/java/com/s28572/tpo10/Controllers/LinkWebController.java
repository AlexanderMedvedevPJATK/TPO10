package com.s28572.tpo10.Controllers;

import com.s28572.tpo10.DTOs.LinkDTO;
import com.s28572.tpo10.Entities.Link;
import com.s28572.tpo10.Services.LinkService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.servlet.view.RedirectView;

import java.net.URI;
import java.util.Optional;

@Controller
public class LinkWebController {

    private final LinkService linkService;

    public LinkWebController(LinkService linkService) {
        this.linkService = linkService;
    }

    @GetMapping("/")
    public String test(Model model) {
        if (!model.containsAttribute("link")) {
            model.addAttribute("link", new Link());
            model.addAttribute("creating", true);
        }
        return "link";
    }

    @PostMapping("/links")
    public RedirectView addLink(@ModelAttribute LinkDTO linkDTO) {
        Link savedLink = linkService.addLink(linkDTO);
        return new RedirectView("/links/" + savedLink.getId(), true, false);
    }

    @GetMapping("/links/{id}")
    public String getLink(@PathVariable String id, Model model) {
        Link link = linkService.getLink(id);
        model.addAttribute("link", link);
        return "link";
    }

    @PatchMapping("/links/{id}")
    public ResponseEntity<Void> updateLink(@PathVariable String id, @RequestBody LinkDTO linkDTO) {
        linkService.updateLink(id, linkDTO);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/links/{id}")
    public ResponseEntity<Void> deleteLink(@PathVariable String id, @RequestBody Optional<String> password) {
        linkService.deleteLink(id, password);
        return ResponseEntity.noContent().build();
    }
}

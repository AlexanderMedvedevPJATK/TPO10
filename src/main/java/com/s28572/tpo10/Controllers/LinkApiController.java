package com.s28572.tpo10.Controllers;

import com.s28572.tpo10.Entities.Link;
import com.s28572.tpo10.DTOs.LinkDTO;
import com.s28572.tpo10.Services.LinkService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/api/links")
public class LinkApiController {

    private final LinkService linkService;

    public LinkApiController(LinkService linkService) {
        this.linkService = linkService;
    }

    @PostMapping
    public ResponseEntity<Link> addLink(@RequestBody LinkDTO linkDTO) {
        Link savedLink = linkService.addLink(linkDTO);

        URI savedLinkLocation = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedLink.getId())
                .toUri();

        return ResponseEntity.created(savedLinkLocation).body(savedLink);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Link> getLink(@PathVariable String id) {
        Link link = linkService.getLink(id);
        return ResponseEntity.ok(link);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateLink(@PathVariable String id, @RequestBody LinkDTO linkDTO) {
        linkService.updateLink(id, linkDTO);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLink(@PathVariable String id, @RequestBody Optional<String> password) {
        linkService.deleteLink(id, password);
        return ResponseEntity.noContent().build();
    }
}

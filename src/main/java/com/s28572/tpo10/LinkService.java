package com.s28572.tpo10;

import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class LinkService {

    private final LinkRepository linkRepository;

    public LinkService(LinkRepository linkRepository) {
        this.linkRepository = linkRepository;
    }

    public Link addLink(LinkDTO linkDTO) {
        Link link = new Link(linkDTO.getName(), linkDTO.getTargetUrl());
        if (linkDTO.getPassword() != null) {
            link.setPassword(linkDTO.getPassword());
        }
        System.out.println(link);
        return linkRepository.save(link);
    }

    public Link getLink(String id) {
        return linkRepository.findById(id).orElseThrow(() -> new NoSuchElementException(id));
    }

    public void updateLink(String id, LinkDTO linkDTO) {
        Link link = linkRepository.findById(id).orElseThrow(() -> new NoSuchElementException(id));
        System.out.println(link.getPassword() + " " + linkDTO.getPassword());
        if (linkDTO.getPassword() != null && link.getPassword().equals(linkDTO.getPassword())) {
            if (linkDTO.getName() != null) {
                link.setName(linkDTO.getName());
            }
            if (linkDTO.getTargetUrl() != null) {
                link.setTargetUrl(linkDTO.getTargetUrl());
            }
            linkRepository.save(link);
        } else {
            throw new IllegalArgumentException("wrong password");
        }
    }

    public void deleteLink(String id, Optional<String> password) {
        Optional<Link> link = linkRepository.findById(id);
        if (link.isEmpty()) {
            return;
        }
        if (password.isPresent() && link.get().getPassword().equals(password.get())) {
            linkRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("wrong password");
        }
    }

    public void incrementCounter(Link link) {
        link.setVisits(link.getVisits() + 1);
        linkRepository.save(link);
    }
}

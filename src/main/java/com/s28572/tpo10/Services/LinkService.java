package com.s28572.tpo10.Services;

import com.s28572.tpo10.DTOs.LinkDTO;
import com.s28572.tpo10.Entities.Link;
import com.s28572.tpo10.Repositories.LinkRepository;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

@Service
public class LinkService {

    private final LinkRepository linkRepository;
    private final Validator validator;

    public LinkService(LinkRepository linkRepository, Validator validator) {
        this.linkRepository = linkRepository;
        this.validator = validator;
    }

    public Link addLink(LinkDTO linkDTO) {
        Link link = new Link(linkDTO.getName(), linkDTO.getTargetUrl());
        Set<ConstraintViolation<Link>> violations = validator.validate(link);
        if (!violations.isEmpty()) {
            violations.forEach(err -> System.out.println(
                    "> " + err.getPropertyPath() + " " + err.getMessage() + " (" + err.getInvalidValue() + ")"
            ));
        }
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
        if (linkDTO.getPassword() != null && linkDTO.getPassword().equals(link.getPassword())) {
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
        if (password.isPresent() && password.get().equals(link.get().getPassword())) {
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

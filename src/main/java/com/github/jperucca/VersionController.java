package com.github.jperucca;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/version")
public class VersionController {

    private Version version;

    @PostConstruct
    public void init() {
        this.version = Version.builder().number("1.0").build();
    }

    @PostMapping("/{number}")
    public ResponseEntity post(@PathVariable("number") Double number) throws URISyntaxException {
        this.version = Version.builder().number(number.toString()).build();

        final String uri = "/version/" + this.version.getNumber();
        return ResponseEntity.created(new URI(uri)).build();
    }

    @GetMapping
    public Version get() {
        return version;
    }

    @Builder
    @Data
    static class Version {
        private String number;
    }
}

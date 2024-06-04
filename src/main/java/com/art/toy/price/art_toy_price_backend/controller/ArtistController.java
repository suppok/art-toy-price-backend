package com.art.toy.price.art_toy_price_backend.controller;

import com.art.toy.price.art_toy_price_backend.dto.ArtistDto;
import com.art.toy.price.art_toy_price_backend.dto.CreateArtistDto;
import com.art.toy.price.art_toy_price_backend.dto.SeriesDto;
import com.art.toy.price.art_toy_price_backend.service.ArtistService;
import com.art.toy.price.art_toy_price_backend.service.SeriesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/artists")
@Slf4j
public class ArtistController {

    private ArtistService artistService;
    private SeriesService seriesService;

    public ArtistController(ArtistService artistService) {
        this.artistService = artistService;
    }

    @GetMapping
    public Collection<ArtistDto> list() {
        Collection<ArtistDto> artists = artistService.findAll();
        log.info("Total artist: {}", artists.size());
        return artists;
    }

    @GetMapping("/{id}")
    public ArtistDto get(@PathVariable UUID id) {
        ArtistDto artistDto = artistService.findById(id);
        log.info("Get artist {} completed", id);
        return artistDto;
    }

    @GetMapping("/{id}/series")
    public Collection<SeriesDto> getSeries(@PathVariable UUID id) {
        Collection<SeriesDto> seriesList = artistService.getSeriesByArtist(id);
        log.info("Total series: {}", seriesList.size());
        return seriesList;
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody CreateArtistDto artistDto) {
        log.info("Create artist: {}", artistDto);
        artistService.create(artistDto);
        return ResponseEntity.ok().build();
    }
}

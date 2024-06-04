package com.art.toy.price.art_toy_price_backend.controller;

import com.art.toy.price.art_toy_price_backend.dto.CreateSeriesDto;
import com.art.toy.price.art_toy_price_backend.dto.SeriesDto;
import com.art.toy.price.art_toy_price_backend.entity.Series;
import com.art.toy.price.art_toy_price_backend.service.ArtistService;
import com.art.toy.price.art_toy_price_backend.service.SeriesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/series")
@Slf4j
public class SeriesController {

    private SeriesService seriesService;
    private ArtistService artistService;

    public SeriesController(SeriesService seriesService, ArtistService artistService) {
        this.seriesService = seriesService;
        this.artistService = artistService;
    }

    @GetMapping
    public Collection<SeriesDto> list() {
        Collection<SeriesDto> seriesList = seriesService.findAll();
        log.info("Total series: {}", seriesList.size());
        return seriesList;
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody CreateSeriesDto seriesDto) {
        log.info("Create series: {}", seriesDto);
        Series series = seriesService.create(seriesDto);
        if (series == null) {
            return ResponseEntity.badRequest().build();
        }
        artistService.addSeriesToArtist(series);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public SeriesDto get(@PathVariable UUID id) {
        SeriesDto seriesDto = seriesService.findById(id);
        log.info("Get series {} completed", id);
        return seriesDto;
    }
}

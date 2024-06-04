package com.art.toy.price.art_toy_price_backend.service;

import com.art.toy.price.art_toy_price_backend.dto.ArtistDto;
import com.art.toy.price.art_toy_price_backend.dto.CreateArtistDto;
import com.art.toy.price.art_toy_price_backend.dto.SeriesDto;
import com.art.toy.price.art_toy_price_backend.entity.Artist;
import com.art.toy.price.art_toy_price_backend.entity.Series;
import com.art.toy.price.art_toy_price_backend.mapper.ArtistMapper;
import com.art.toy.price.art_toy_price_backend.mapper.SeriesMapper;
import com.art.toy.price.art_toy_price_backend.repository.ArtistRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Service
public class ArtistService {

    private ArtistRepository artistRepository;
    private ArtistMapper artistMapper;
    private SeriesMapper seriesMapper;

    public ArtistService(ArtistRepository artistRepository, ArtistMapper artistMapper, SeriesMapper seriesMapper) {
        this.artistRepository = artistRepository;
        this.artistMapper = artistMapper;
        this.seriesMapper = seriesMapper;
    }

    public Collection<ArtistDto> findAll() {
        return artistMapper.toDtoList(artistRepository.findAll());
    }

    public ArtistDto findById(UUID id) {
        return artistMapper.map(artistRepository.findById(id).orElse(null));
    }

    public Collection<SeriesDto> getSeriesByArtist(UUID id) {
        Artist artist = artistRepository.findById(id).orElse(null);
        if (artist == null) {
            return null;
        }
        return seriesMapper.toDtoList(artist.getSeriesList());
    }

    public void create(CreateArtistDto artistDto) {
        Artist artist = artistMapper.map(artistDto);
        artist.setCreateAt(LocalDateTime.now());
        artist.setUpdateAt(LocalDateTime.now());
        artistRepository.save(artist);
    }

    public void addSeriesToArtist(Series series) {
        Artist artist = artistRepository.findById(series.getArtist().getId()).orElse(null);
        if (artist == null) {
            return;
        }
        List<Series> seriesList = artist.getSeriesList();
        seriesList.add(series);
        artist.setSeriesList(seriesList);
        artist.setUpdateAt(LocalDateTime.now());
        artistRepository.save(artist);
    }
}

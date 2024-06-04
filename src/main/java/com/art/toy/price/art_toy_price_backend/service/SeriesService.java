package com.art.toy.price.art_toy_price_backend.service;

import com.art.toy.price.art_toy_price_backend.dto.CreateSeriesDto;
import com.art.toy.price.art_toy_price_backend.dto.SeriesDto;
import com.art.toy.price.art_toy_price_backend.entity.Artist;
import com.art.toy.price.art_toy_price_backend.entity.Series;
import com.art.toy.price.art_toy_price_backend.mapper.SeriesMapper;
import com.art.toy.price.art_toy_price_backend.repository.ArtistRepository;
import com.art.toy.price.art_toy_price_backend.repository.SeriesRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.UUID;

@Service
public class SeriesService {

    private SeriesRepository seriesRepository;
    private ArtistRepository artistRepository;
    private SeriesMapper seriesMapper;

    public SeriesService(SeriesRepository seriesRepository, SeriesMapper seriesMapper, ArtistRepository artistRepository) {
        this.seriesRepository = seriesRepository;
        this.seriesMapper = seriesMapper;
        this.artistRepository = artistRepository;
    }

    public Collection<SeriesDto> findAll() {
        return seriesMapper.toDtoList(seriesRepository.findAll());
    }

    public SeriesDto findById(UUID id) {
        return seriesMapper.map(seriesRepository.findById(id).orElse(null));
    }

    public Series create(CreateSeriesDto seriesDto) {

        Series series = seriesMapper.map(seriesDto);
        series.setCreateAt(LocalDateTime.now());
        series.setUpdateAt(LocalDateTime.now());
        Artist artist = artistRepository.findById(seriesDto.getArtist()).orElse(null);
        if (artist == null) {
            return null;
        }
        series.setArtist(artist);
        seriesRepository.save(series);
        return series;
    }
}

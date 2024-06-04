package com.art.toy.price.art_toy_price_backend.mapper;

import com.art.toy.price.art_toy_price_backend.dto.ArtistDto;
import com.art.toy.price.art_toy_price_backend.dto.CreateArtistDto;
import com.art.toy.price.art_toy_price_backend.entity.Artist;
import com.art.toy.price.art_toy_price_backend.entity.Series;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ArtistMapper {

    public Collection<ArtistDto> toDtoList(List<Artist> list) {
        if (list == null || list.isEmpty()) {
            Collections.emptyList();
        }

        return list.stream().map(e -> ArtistDto.builder()
                        .id(e.getId())
                        .name(e.getName())
                        .seriesList(e.getSeriesList().stream().map(Series::getId).toList())
                        .createAt(e.getCreateAt())
                        .updateAt(e.getUpdateAt())
                        .build())
                .collect(Collectors.toList());
    }

    public Artist map(CreateArtistDto artistDto) {
        return Artist.builder()
                .name(artistDto.getName())
                .build();
    }

    public ArtistDto map(Artist artist) {
        if (artist == null) {
            return null;
        }
        return ArtistDto.builder()
                .id(artist.getId())
                .name(artist.getName())
                .seriesList(artist.getSeriesList().stream().map(Series::getId).toList())
                .createAt(artist.getCreateAt())
                .updateAt(artist.getUpdateAt())
                .build();
    }
}

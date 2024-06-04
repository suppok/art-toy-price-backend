package com.art.toy.price.art_toy_price_backend.mapper;

import com.art.toy.price.art_toy_price_backend.dto.CreateSeriesDto;
import com.art.toy.price.art_toy_price_backend.dto.SeriesDto;
import com.art.toy.price.art_toy_price_backend.entity.Series;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class SeriesMapper {

    public Collection<SeriesDto> toDtoList(List<Series> list) {
        if (list == null || list.isEmpty()) {
            Collections.emptyList();
        }

        return list.stream().map(e -> SeriesDto.builder()
                        .id(e.getId())
                        .name(e.getName())
                        .artist(e.getArtist().getId())
                        .collectionList(e.getCollectionList().stream().map(com.art.toy.price.art_toy_price_backend.entity.Collection::getId).toList())
                        .createAt(e.getCreateAt())
                        .updateAt(e.getUpdateAt())
                        .build())
                .collect(Collectors.toList());
    }

    public Series map(CreateSeriesDto seriesDto) {
        return Series.builder()
                .name(seriesDto.getName())
                .build();
    }

    public SeriesDto map(Series series) {
        if (series == null) {
            return null;
        }
        return SeriesDto.builder()
                .id(series.getId())
                .name(series.getName())
                .artist(series.getArtist().getId())
                .collectionList(series.getCollectionList().stream().map(com.art.toy.price.art_toy_price_backend.entity.Collection::getId).toList())
                .createAt(series.getCreateAt())
                .updateAt(series.getUpdateAt())
                .build();
    }
}

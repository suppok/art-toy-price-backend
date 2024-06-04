package com.art.toy.price.art_toy_price_backend.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Builder
@Data
public class ArtistDto {
    private UUID id;
    private String name;
    private List<UUID> seriesList;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
}

package com.art.toy.price.art_toy_price_backend.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class CreateSeriesDto {
    private String name;
    private UUID artist;
}

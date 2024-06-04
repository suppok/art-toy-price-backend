package com.art.toy.price.art_toy_price_backend.repository;

import com.art.toy.price.art_toy_price_backend.entity.Series;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SeriesRepository extends JpaRepository<Series, UUID> {
}

package com.art.toy.price.art_toy_price_backend.repository;

import com.art.toy.price.art_toy_price_backend.entity.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ArtistRepository extends JpaRepository<Artist, UUID> {
}

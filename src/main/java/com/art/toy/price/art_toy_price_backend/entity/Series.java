package com.art.toy.price.art_toy_price_backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Series {
    @Id
    @UuidGenerator(style = UuidGenerator.Style.TIME)
    private UUID id;
    private String name;

    @Column(nullable = false)
    private LocalDateTime createAt;
    private LocalDateTime updateAt;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "artist_id", nullable = false)
    private Artist artist;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "series", cascade = CascadeType.ALL)
    private List<Collection> collectionList;
}

package com.art.toy.price.art_toy_price_backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Sale {
    @Id
    @UuidGenerator(style = UuidGenerator.Style.TIME)
    private UUID id;

    @Column(nullable = false)
    private LocalDateTime createAt;
    private LocalDateTime updateAt;

    @Column(nullable = false)
    private double price;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "character_id", nullable = false)
    private Character character;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "reseller_id", nullable = false)
    private Reseller reseller;

    private LocalDateTime postDate;
}

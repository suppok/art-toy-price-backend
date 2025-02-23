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
public class Reseller {
    @Id
    @UuidGenerator(style = UuidGenerator.Style.TIME)
    private UUID id;
    private String name;

    @Column(nullable = false)
    private LocalDateTime createAt;
    private LocalDateTime updateAt;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "reseller", cascade = CascadeType.ALL)
    private List<Sale> saleList;
}

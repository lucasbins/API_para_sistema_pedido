package com.example.test.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Entity
@Table(name = "orders")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long numberOrder;

    @ManyToOne
    @JoinColumn(name = "costumer")
    private Costumer costumer;

    private Float amount;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime dateOrder;

    @PrePersist
    protected void onCreate() {
        ZoneId zoneId = ZoneId.of("America/Sao_Paulo");
        dateOrder = LocalDateTime.now(zoneId);
    }
}

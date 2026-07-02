package com.its.gestioneordinirestclient.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.envers.AuditOverride;
import org.hibernate.envers.Audited;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "orders")
@Audited
@AuditOverride(forClass = Auditable.class)
public class Order extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String description;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDate creation;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusEnum status;

    private BigDecimal total;

    private String customerEmail;
}

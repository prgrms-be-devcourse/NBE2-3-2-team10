package org.team10.washcode.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.team10.washcode.Enum.LaundryCategory;

@Entity
@Data
public class HandledItems {
    // 가격표
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "laundryshop_id")
    private LaundryShop laundryshop;

    private String item_name;

    @Enumerated(EnumType.STRING)
    private LaundryCategory category;
    private int price;
}

package org.team10.washcode.domain.inquiry.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.team10.washcode.domain.user.entity.User;

import java.sql.Timestamp;

@Entity
@Data
public class Inquiry {
    //문의
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String inquiry_content;
    private String reply_content;
    private Timestamp created_at;

}

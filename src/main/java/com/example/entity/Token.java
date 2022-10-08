package com.example.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "campaign")
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "token_value")
    private String value;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="campaign_id")
    private Campaign campaign;
    private boolean deactivated;

}

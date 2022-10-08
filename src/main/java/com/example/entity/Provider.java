package com.example.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "campaign")
public class Provider {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private double price;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="campaign_id")
    private Campaign campaign;
    private boolean deactivated;


}

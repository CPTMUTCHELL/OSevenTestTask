package com.example.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Getter
@Setter
@Entity
@Table(name = "token")
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "token_value")
    @Size( min= 6,max = 6,message = "Toke's length must be equal to 6 chars")
    private String value;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="campaign_id")
    private Campaign campaign;
    private boolean deactivated;

}

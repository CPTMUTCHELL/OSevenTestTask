package com.example.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;
@Getter
@Setter
@Entity
@Table(name = "campaign")
@ApiModel(description = "Campaign entity", value = "Campaign")

public class Campaign {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "Campaign id ", example = "1")
    private Integer id;
    @ApiModelProperty(value = "Campaign name ", example = "campaign1")

    private String name;
    @ApiModelProperty(value = "Campaign activation status ", example = "false")
    private boolean deactivated;

}

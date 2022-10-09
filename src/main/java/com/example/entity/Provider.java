package com.example.entity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "provider")
@ApiModel(description = "Provider entity", value = "Provider")

public class Provider {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "Provider id ", example = "2")

    private Integer id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="campaign_id")
    @ApiModelProperty(value = "Campaign of the current provider")
    private Campaign campaign;
    @ApiModelProperty(value = "Provider name", example = "provider1")
    private String name;
    @ApiModelProperty(value = "Provider activation status ", example = "false")
    private boolean deactivated;
    @ApiModelProperty(value = "URL of a new provider", example = "http://xyz.com")
    private String url;
    @OneToMany(mappedBy = "provider")
    @ApiModelProperty(value = "Array of the products")
    private List<Product> products;

}


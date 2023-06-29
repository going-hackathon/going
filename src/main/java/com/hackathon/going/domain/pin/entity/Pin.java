package com.hackathon.going.domain.pin.entity;

import com.hackathon.going.domain.common.BaseEntity;
import com.hackathon.going.domain.travel.entity.Travel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Table(name = "pin")
@Entity
public class Pin extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pin_id")
    private Long id;

    @Column(name = "latitude", nullable = false)
    private Double latitude;

    @Column(name = "longitude", nullable = false)
    private Double longitude;

    @ManyToOne
    @JoinColumn(name = "travel_id")
    private Travel travel;

    @Builder
    public Pin(Double latitude, Double longitude, Travel travel) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.travel = travel;
    }

}

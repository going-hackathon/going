package com.hackathon.going.domain.pin.entity;

import com.hackathon.going.domain.common.BaseEntity;
import com.hackathon.going.domain.travel.entity.Travel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.locationtech.jts.geom.Point;

import javax.persistence.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "pin")
@Entity
public class Pin extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pin_id")
    private Long id;

    @Column(columnDefinition = "Point")
    private Point point;

    @ManyToOne
    @JoinColumn(name = "travel_id")
    private Travel travel;
}

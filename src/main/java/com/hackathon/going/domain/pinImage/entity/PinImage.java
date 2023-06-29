package com.hackathon.going.domain.pinImage.entity;

import com.hackathon.going.domain.common.BaseEntity;
import com.hackathon.going.domain.pinImage.constant.PinImgStatus;
import com.hackathon.going.domain.pin.entity.Pin;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "pin_image")
@Entity
public class PinImage extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pin_image_id")
    private Long id;

    @Column(name = "url")
    private String url;

    @ManyToOne
    @JoinColumn(name = "pin_id")
    private Pin pin;

    @Enumerated(EnumType.STRING)
    @Column(name = "pin_image_status")
    private PinImgStatus status;

    public void setPin(Pin pin) {
        this.pin = pin;
    }
}

package com.hackathon.going.domain.pin.entity;

import com.hackathon.going.domain.common.BaseEntity;
import com.hackathon.going.domain.pin.constant.PinStatus;
import com.hackathon.going.domain.pinImage.entity.PinImage;
import com.hackathon.going.domain.travel.entity.Travel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    @Column(name = "latitude", nullable = false)
    private Double latitude;

    @Column(name = "longitude", nullable = false)
    private Double longitude;

    @Column(name = "content")
    private String content;

    @Column(name = "title")
    private String title;

    @Column(name = "address")
    private String address;

    @Column(name = "start_date")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime startDate;

    @Column(name = "end_date")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime endDate;

    @ManyToOne
    @JoinColumn(name = "travel_id")
    private Travel travel;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private PinStatus status = PinStatus.PLANNED;

    @OneToMany(mappedBy = "pin",
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            orphanRemoval = true)
    @Builder.Default
    private List<PinImage> pinImages = new ArrayList<>();

    public void addImage(PinImage pinImage) {
        this.pinImages.add(pinImage);

        if (pinImage.getPin() != this)
            pinImage.setPin(this);
    }

    public void changeStatus() {
        this.status = PinStatus.ARRIVED;
    }
}

package com.hackathon.going.domain.pinImage.repository;

import com.hackathon.going.domain.pinImage.entity.PinImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PinImageRepository extends JpaRepository<PinImage, Long> {
}

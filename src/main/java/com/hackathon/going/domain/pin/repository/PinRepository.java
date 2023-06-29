package com.hackathon.going.domain.pin.repository;

import com.hackathon.going.domain.pin.entity.Pin;
import com.hackathon.going.domain.travel.entity.Travel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PinRepository extends JpaRepository<Pin, Long> {

    Optional<Pin> findByTravelOrderByModifiedAt(Travel travel);
    List<Pin> findAllByTravel(Travel travel);
}

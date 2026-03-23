package com.aydog4nn.repository;

import com.aydog4nn.model.SaledCar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaledCarRepository  extends JpaRepository<SaledCar,Long> {
}

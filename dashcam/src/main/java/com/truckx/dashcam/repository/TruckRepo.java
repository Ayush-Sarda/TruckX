package com.truckx.dashcam.repository;

import com.truckx.dashcam.entity.Truck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TruckRepo extends JpaRepository<Truck, Integer> {
}

package com.truckx.admin.repository;

import com.truckx.admin.entity.Truck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TruckRepo extends JpaRepository<Truck, Integer> {
}

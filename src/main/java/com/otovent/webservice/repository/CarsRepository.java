package com.otovent.webservice.repository;

import com.otovent.webservice.entity.Cars;
import com.otovent.webservice.entity.User;
import com.otovent.webservice.entity.enums.StatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarsRepository extends JpaRepository<Cars,Long>{
    List<Cars> findByUserAndStatus(User user, StatusEntity status);
}

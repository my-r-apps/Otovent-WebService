package com.otovent.webservice.repository;

import com.otovent.webservice.entity.Ads;
import com.otovent.webservice.entity.enums.AdStatus;
import com.otovent.webservice.entity.enums.StatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdRepository extends JpaRepository<Ads,Long>{
    List<Ads> findAllByStatusAndConfirmationStatusOrderByDonationDesc(StatusEntity status,
                                                                      AdStatus confirmationStatus);
}

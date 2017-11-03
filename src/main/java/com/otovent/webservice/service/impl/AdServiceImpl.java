package com.otovent.webservice.service.impl;

import com.otovent.webservice.entity.Ads;
import com.otovent.webservice.entity.enums.AdStatus;
import com.otovent.webservice.entity.enums.StatusEntity;
import com.otovent.webservice.entity.request.AdRequest;
import com.otovent.webservice.repository.AdRepository;
import com.otovent.webservice.service.AdService;
import com.otovent.webservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AdServiceImpl implements AdService{
    @Autowired
    AdRepository adRepository;
    @Autowired
    UserService userService;

    @Override
    public Ads getOne(Long id) {
        return adRepository.findOne(id);
    }

    @Override
    public List<Ads> getAllAdsVendor() {
        return adRepository.findAllByStatusAndConfirmationStatusOrderByDonationDesc(StatusEntity.ACTIVE,
                AdStatus.CONFIRMED);
    }

    @Override
    public Boolean addAd(AdRequest adRequest) {
        Ads ad = Ads.builder()
                .createdDate(new Date())
                .description(adRequest.getDesc())
                .donation(adRequest.getDonation())
                .confirmationStatus(AdStatus.TO_CONFIRM)
                .status(StatusEntity.ACTIVE)
                .name(adRequest.getName())
                .user(userService.getDetailOneUser(adRequest.getIdUser()))
                .build();
        adRepository.save(ad);
        return Boolean.TRUE;
    }

    @Override
    public Boolean editAd(AdRequest adRequest) {
        Ads ad = adRepository.findOne(adRequest.getId());
        ad.setDonation(adRequest.getDonation());
        ad.setDescription(adRequest.getDesc());
        ad.setName(adRequest.getName());
        adRepository.save(ad);
        return Boolean.TRUE;
    }

    @Override
    public Boolean deleteAd(Long id) {
        Ads ad = adRepository.findOne(id);
        ad.setStatus(StatusEntity.DELETED);
        adRepository.save(ad);
        return Boolean.TRUE;
    }

    @Override
    public Boolean updateLinkImage(Long id, String imgUrl) {
        Ads ad = adRepository.findOne(id);
        ad.setImageLink(imgUrl);
        adRepository.save(ad);
        return Boolean.TRUE;
    }

    @Override
    public Boolean confirmOrBlacklistAd(Long id, AdStatus adStatus) {
        Ads ad = adRepository.findOne(id);
        ad.setConfirmationStatus(adStatus);
        adRepository.save(ad);
        return Boolean.TRUE;
    }
}

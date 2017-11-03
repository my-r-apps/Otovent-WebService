package com.otovent.webservice.service;

import com.otovent.webservice.entity.Ads;
import com.otovent.webservice.entity.enums.AdStatus;
import com.otovent.webservice.entity.request.AdRequest;

import java.util.List;

public interface AdService {
    Ads getOne(Long id);
    List<Ads> getAllAdsVendor();
    Boolean addAd(AdRequest adRequest);
    Boolean editAd(AdRequest adRequest);
    Boolean deleteAd(Long id);
    Boolean updateLinkImage(Long id, String imgUrl);
    Boolean confirmOrBlacklistAd(Long id, AdStatus adStatus);
}

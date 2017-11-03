package com.otovent.webservice.controller;

import com.otovent.webservice.entity.Ads;
import com.otovent.webservice.entity.enums.AdStatus;
import com.otovent.webservice.entity.request.AdRequest;
import com.otovent.webservice.entity.response.BaseResponse;
import com.otovent.webservice.service.AdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(value = "*")
@RequestMapping(value = "ads")
public class AdController {
    @Autowired
    AdService adService;

    // TODO Get List Ads of vendors
    @RequestMapping(value = "/get/timeline/promoted", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse getAdsOfVendor(){
        List<Ads> result =  adService.getAllAdsVendor();
        if(!result.isEmpty())
            return BaseResponse.builder().httpStatus(HttpStatus.OK).message("Success")
                    .result(result).build();
        else
            return BaseResponse.builder().httpStatus(HttpStatus.OK).message("No Ads")
                    .result(null)
                    .build();
    }

    @PostMapping(value = "/add",produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse addAd(@RequestBody AdRequest adRequest){
        List<Boolean> result = new ArrayList<>();
        result.add(adService.addAd(adRequest));
        return BaseResponse.builder().httpStatus(HttpStatus.OK).message("Success")
                .result(result)
                .build();
    }

    @PostMapping(value = "/edit",produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse editAd(@RequestBody AdRequest adRequest){
        List<Boolean> result = new ArrayList<>();
        result.add(adService.editAd(adRequest));
        return BaseResponse.builder().httpStatus(HttpStatus.OK).message("Success")
                .result(result)
                .build();
    }

    @PostMapping(value = "/delete",produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse deleteAd(@RequestHeader Long id){
        List<Boolean> result = new ArrayList<>();
        result.add(adService.deleteAd(id));
        return BaseResponse.builder().httpStatus(HttpStatus.OK).message("Success")
                .result(result)
                .build();
    }

    @PostMapping(value = "/confirm",produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse confirmOrBlacklistAd(@RequestHeader Long id, AdStatus adStatus){
        List<Boolean> result = new ArrayList<>();
        result.add(adService.confirmOrBlacklistAd(id,adStatus));
        return BaseResponse.builder().httpStatus(HttpStatus.OK).message("Success")
                .result(result)
                .build();
    }
}

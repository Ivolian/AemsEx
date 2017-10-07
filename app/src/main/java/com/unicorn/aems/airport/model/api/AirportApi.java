package com.unicorn.aems.airport.model.api;

import com.unicorn.aems.airport.model.entity.AirportResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface AirportApi {

    @GET("api/v1/appService/appVersion/appDataConfig")
    Observable<AirportResponse> getAppDataConfig(@Query("lastUpdateDate") String lastUpdateDate, @Query("key") String key);

}
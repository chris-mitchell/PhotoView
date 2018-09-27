package com.cmitchell687.photoviewer.network

import com.cmitchell687.photoviewer.models.Photo
import io.reactivex.Observable
import retrofit2.http.GET

interface WebService {

    @GET("list")
    fun getPhotoList(): Observable<List<Photo>>
}
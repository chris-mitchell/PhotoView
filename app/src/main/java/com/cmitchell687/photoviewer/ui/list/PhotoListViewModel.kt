package com.cmitchell687.photoviewer.ui.list

import android.support.v4.app.FragmentActivity
import com.airbnb.mvrx.Async
import com.airbnb.mvrx.BaseMvRxViewModel
import com.airbnb.mvrx.Loading
import com.airbnb.mvrx.MvRxState
import com.airbnb.mvrx.MvRxViewModelFactory
import com.airbnb.mvrx.Uninitialized
import com.cmitchell687.photoviewer.core.MvRxViewModel
import com.cmitchell687.photoviewer.models.Photo
import com.cmitchell687.photoviewer.network.WebService
import org.koin.android.ext.android.inject

data class PhotoListState(
        val photos: Async<List<Photo>> = Uninitialized
) : MvRxState

class PhotoListViewModel(
        initialState: PhotoListState,
        private val webService: WebService
): MvRxViewModel<PhotoListState>(initialState) {

    init {
        fetch()
    }

    fun fetch() = withState { state ->
        if (state.photos is Loading) return@withState

        webService.getPhotoList().execute { copy(photos = it) }
    }

    companion object : MvRxViewModelFactory<PhotoListState> {
        @JvmStatic
        override fun create(activity: FragmentActivity, state: PhotoListState): BaseMvRxViewModel<PhotoListState> {
            val service: WebService by activity.inject()
            return PhotoListViewModel(state, service)
        }
    }

}
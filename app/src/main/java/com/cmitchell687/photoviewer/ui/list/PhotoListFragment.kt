package com.cmitchell687.photoviewer.ui.list

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.View
import com.airbnb.mvrx.fragmentViewModel
import com.cmitchell687.photoviewer.R
import com.cmitchell687.photoviewer.core.BaseFragment
import com.cmitchell687.photoviewer.core.simpleController
import com.cmitchell687.photoviewer.ui.list.views.photoRow
import com.cmitchell687.photoviewer.views.loadingRow
import com.cmitchell687.photoviewer.views.toolbarRow

class PhotoListFragment : BaseFragment() {

    private val viewModel: PhotoListViewModel by fragmentViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.asyncSubscribe(PhotoListState::photos, onFail = { error ->
            Snackbar.make(coordinatorLayout, R.string.photo_list_api_failed, Snackbar.LENGTH_INDEFINITE)
                    .show()
        })
    }

    override fun epoxyController() = simpleController(viewModel) { state ->
        toolbarRow {
            id("toolbar")
            title("Photo List")
        }

        val photos = state.photos()
        if (photos == null) {
            loadingRow {
                id("loading")
            }
        } else {
            photos.forEach { photo ->
                photoRow {
                    id(photo.id)
                    image("https://picsum.photos/200/200?image=" + photo.id)
                    title(photo.author)
                }
            }
        }
    }
}
package com.cmitchell687.photoviewer.ui.list.views

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.airbnb.epoxy.ModelView
import com.airbnb.epoxy.TextProp
import com.cmitchell687.photoviewer.R
import com.squareup.picasso.Picasso

@ModelView(autoLayout = ModelView.Size.WRAP_WIDTH_WRAP_HEIGHT)
class PhotoRow @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
        ) : RelativeLayout(context, attrs, defStyleAttr) {

    private val imageView: ImageView
    private val titleView: TextView


    init {
        inflate(context, R.layout.photo_row, this)
        imageView = findViewById(R.id.image)
        titleView = findViewById(R.id.title)
    }

    @TextProp
    fun setTitle(title: CharSequence) {
        titleView.text = title
    }

    @TextProp
    fun setImage(url: CharSequence) {
        Picasso.get().load(url.toString()).into(imageView)
    }
}
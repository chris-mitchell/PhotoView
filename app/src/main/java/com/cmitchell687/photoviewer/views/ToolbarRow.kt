package com.cmitchell687.photoviewer.views

import android.content.Context
import android.support.v7.widget.Toolbar
import android.util.AttributeSet
import android.widget.LinearLayout
import com.airbnb.epoxy.ModelView
import com.airbnb.epoxy.TextProp
import com.cmitchell687.photoviewer.R

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class ToolbarRow @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private val toolBar: Toolbar

    init {
        inflate(context, R.layout.toolbar, this)
        toolBar = findViewById(R.id.toolbar)
    }

    @TextProp
    fun setTitle(title: CharSequence) {
        toolBar.title = title
    }

}
package com.pierrepiron.customviewproject.module.customview

import com.pierrepiron.customviewproject.R
import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

class CustomView : ConstraintLayout {

    private var _defaultColor: Int = Color.DKGRAY

    private var customDrawable: String? = null
    private var customTextColor: Int
        get() = _defaultColor
        set(value) {
            _defaultColor = value
        }

    constructor(context: Context) : super(context) {
        addView(View.inflate(context, R.layout.activity_custom_view, null))
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initView(attrs)
    }


    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int): super(context, attrs, defStyleAttr) {
        initView(attrs)
    }

    private fun initView(attrs: AttributeSet) {
        // Get custom values
        val a = context.obtainStyledAttributes(
            attrs, com.pierrepiron.customviewproject.R.styleable.CustomView
        )

        customDrawable = a.getString(
            com.pierrepiron.customviewproject.R.styleable.CustomView_drawable
        )

        customTextColor = a.getColor(
            com.pierrepiron.customviewproject.R.styleable.CustomView_textColor,
            customTextColor
        )

        a.recycle()

        val view = View.inflate(context, R.layout.activity_custom_view, null)
        addView(view)

        // Display image
        val imageView: ImageView = findViewById(R.id.custom_view_image)
        Glide.with(view)
            .load(customDrawable)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(imageView)
            .waitForLayout()

        // Change editText Text Color
        val editText: EditText = findViewById(R.id.custom_view_text)
        editText.setTextColor(customTextColor)
    }
}
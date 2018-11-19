package org.shadracktaeli.networkimageview

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView

class NetworkImageView @JvmOverloads constructor(
        context: Context,
        attributeSet: AttributeSet? = null
) : AppCompatImageView(context, attributeSet) {
    // Image url to load
    private var imageUrl: String? = null
    // Placeholder drawable resource
    private var placeholderDrawableRes: Int = DEFAULT_RESOURCE_VALUE
    // Error drawable resource
    private var errorDrawableRes: Int = DEFAULT_RESOURCE_VALUE

    init {
        val attributes = context
            .obtainStyledAttributes(attributeSet, R.styleable.NetworkImageView, 0, 0)

        // Get image url
        imageUrl = attributes.getString(R.styleable.NetworkImageView_imageUrl)
        // Get placeholder drawable resource
        placeholderDrawableRes = attributes
            .getResourceId(R.styleable.NetworkImageView_placeholderDrawable, DEFAULT_RESOURCE_VALUE)
        // Get error drawable resource
        errorDrawableRes = attributes
            .getResourceId(R.styleable.NetworkImageView_errorDrawable, DEFAULT_RESOURCE_VALUE)

        // Recycle attributes
        attributes.recycle()

        // Load image
        loadImage()
    }

    /**
     * Loads an image into [NetworkImageView] with the specified [imageUrl]
     */
    private fun loadImage() {
        imageUrl?.apply {
            // TODO load image    
        }
    }

    fun loadImage(
            imageUrl: String,
            placeholderDrawableRes: Int = DEFAULT_RESOURCE_VALUE,
            errorDrawableRes: Int = DEFAULT_RESOURCE_VALUE
    ) {
        this.imageUrl = imageUrl
        this.placeholderDrawableRes = placeholderDrawableRes
        this.errorDrawableRes = errorDrawableRes
        loadImage()
    }

    companion object {
        private const val DEFAULT_RESOURCE_VALUE = -1
    }
}
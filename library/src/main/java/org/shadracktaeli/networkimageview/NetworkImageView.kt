package org.shadracktaeli.networkimageview

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.webkit.URLUtil
import androidx.annotation.DrawableRes
import androidx.appcompat.widget.AppCompatImageView
import org.shadracktaeli.networkimageview.glide.GlideApp

class NetworkImageView @JvmOverloads constructor(
        context: Context, attributeSet: AttributeSet? = null
) : AppCompatImageView(context, attributeSet) {
    // Image url to load
    private var imageUrl: String? = null
    // Placeholder drawable resource
    private var placeholderDrawableRes: Int
    // Error drawable resource
    private var errorDrawableRes: Int
    // Cache type
    private var cacheType: Int = DEFAULT_CACHE_TYPE

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
            // Check if imageUrl is a valid url
            if (this.isBlank() || !URLUtil.isNetworkUrl(this)) {
                val errorMessage = if (this.isBlank()) "Please provide" else "$this is not"
                Log.e(TAG, "$errorMessage a valid url")
                return
            }

            // @formatter:off
            GlideApp.with(context)
                .load(this)
                .placeholderDrawable(placeholderDrawableRes)
                .errorDrawable(errorDrawableRes)
                .into(this@NetworkImageView)
            // @formatter:on
        }
    }

    fun loadImage(
            imageUrl: String, @DrawableRes placeholderDrawableRes: Int = DEFAULT_RESOURCE_VALUE, @DrawableRes errorDrawableRes: Int = DEFAULT_RESOURCE_VALUE
    ) {
        this.imageUrl = imageUrl
        this.placeholderDrawableRes = placeholderDrawableRes
        this.errorDrawableRes = errorDrawableRes
        loadImage()
    }

    companion object {
        private const val TAG = "NetworkImageView"
        private const val DEFAULT_RESOURCE_VALUE = -1
        private const val DEFAULT_CACHE_TYPE = 1
    }
}
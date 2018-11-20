package org.shadracktaeli.networkimageview

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.webkit.URLUtil
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.annotation.DrawableRes
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import org.shadracktaeli.networkimageview.glide.CacheStrategy
import org.shadracktaeli.networkimageview.glide.GlideApp
import org.shadracktaeli.networkimageview.glide.ImageLoadingListener

class NetworkImageView @JvmOverloads constructor(
        context: Context, attributeSet: AttributeSet? = null
) : FrameLayout(context, attributeSet), RequestListener<Drawable> {
    // Views
    private val imageView: ImageView
    private val progressBar: ProgressBar

    // Image url to load
    private var imageUrl: String?
    // Placeholder drawable resource
    private var placeholderDrawableRes: Int
    // Error drawable resource
    private var errorDrawableRes: Int
    // Cache type
    private var cacheStrategy: CacheStrategy
    // Show progress loader
    private var showLoader: Boolean
    // Image loading listener
    private var imageLoadingListener: ImageLoadingListener? = null

    init {
        // Inflate views
        LayoutInflater.from(context).inflate(R.layout.network_image_view, this)
        imageView = findViewById(R.id.image)
        progressBar = findViewById(R.id.progress_bar)

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
        // Get cache strategy
        cacheStrategy = CacheStrategy.values()[attributes.getInt(R.styleable.NetworkImageView_cacheStrategy, DEFAULT_CACHE_STRATEGY.ordinal)]
        // Get show progress loader
        showLoader = attributes
            .getBoolean(R.styleable.NetworkImageView_showLoader, DEFAULT_SHOW_PROGRESS_LOADER)

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
            if (this.isBlank() || !URLUtil.isValidUrl(this)) {
                val errorMessageRes = if (this.isBlank()) R.string.error_blank_image_url else R.string.error_invalid_image_url
                Log.e(TAG, context.getString(errorMessageRes))
                return
            }

            // Show loading progress
            showProgressBar()

            // @formatter:off
            GlideApp.with(context)
                .load(this)
                .listener(this@NetworkImageView)
                .placeholderDrawable(placeholderDrawableRes)
                .errorDrawable(errorDrawableRes)
                .diskCacheStrategy(cacheStrategy.value)
                .into(imageView)
            // @formatter:on
        }
    }

    fun loadImage(
            imageUrl: String,
            @DrawableRes placeholderDrawableRes: Int = DEFAULT_RESOURCE_VALUE,
            @DrawableRes errorDrawableRes: Int = DEFAULT_RESOURCE_VALUE,
            showLoader: Boolean = false
    ) {
        this.imageUrl = imageUrl
        this.placeholderDrawableRes = placeholderDrawableRes
        this.errorDrawableRes = errorDrawableRes
        this.showLoader = showLoader
        loadImage()
    }

    private fun showProgressBar() {
        if (showLoader) {
            progressBar.visibility = View.VISIBLE
        }
    }

    private fun hideProgressBar() {
        progressBar.visibility = View.GONE
    }

    override fun onLoadFailed(
            e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean
    ): Boolean {
        hideProgressBar()
        imageLoadingListener?.onLoadFailed(e)
        return false
    }

    override fun onResourceReady(
            resource: Drawable?,
            model: Any?,
            target: Target<Drawable>?,
            dataSource: DataSource?,
            isFirstResource: Boolean
    ): Boolean {
        hideProgressBar()
        imageLoadingListener?.onLoaded()
        return false
    }

    companion object {
        private const val TAG = "NetworkImageView"
        private const val DEFAULT_RESOURCE_VALUE = -1
        private val DEFAULT_CACHE_STRATEGY = CacheStrategy.NONE
        private const val DEFAULT_SHOW_PROGRESS_LOADER = false
    }
}
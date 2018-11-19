package org.shadracktaeli.networkimageview.glide

import androidx.annotation.DrawableRes
import com.bumptech.glide.annotation.GlideExtension
import com.bumptech.glide.annotation.GlideOption
import com.bumptech.glide.request.RequestOptions

@GlideExtension
object Extensions {
    private const val DEFAULT_RESOURCE_VALUE = -1

    @GlideOption
    @JvmStatic
    fun placeholderDrawable(options: RequestOptions, @DrawableRes placeholderDrawableRes: Int): RequestOptions {
        if (placeholderDrawableRes != DEFAULT_RESOURCE_VALUE) {
            options.placeholder(placeholderDrawableRes)
        }
        return options
    }

    @GlideOption
    @JvmStatic
    fun errorDrawable(options: RequestOptions, @DrawableRes errorDrawableRes: Int): RequestOptions {
        if (errorDrawableRes != DEFAULT_RESOURCE_VALUE) {
            options.error(errorDrawableRes)
        }
        return options
    }
}
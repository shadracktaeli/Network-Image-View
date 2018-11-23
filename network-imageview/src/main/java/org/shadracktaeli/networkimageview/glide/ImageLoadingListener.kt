package org.shadracktaeli.networkimageview.glide

interface ImageLoadingListener {
    fun onLoading()
    fun onLoaded()
    fun onLoadFailed(exception: Exception?)
}
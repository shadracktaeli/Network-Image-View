package org.shadracktaeli.networkimageview.glide

import com.bumptech.glide.load.engine.DiskCacheStrategy

enum class CacheStrategy(val value: DiskCacheStrategy) {
    // @formatter:off
    ALL(DiskCacheStrategy.ALL),
    AUTO(DiskCacheStrategy.AUTOMATIC),
    DATA(DiskCacheStrategy.DATA),
    NONE(DiskCacheStrategy.NONE),
    RESOURCE(DiskCacheStrategy.RESOURCE)
    // @formatter:on
}
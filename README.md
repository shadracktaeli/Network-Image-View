# Network-ImageView

NetworkImageView is an Android library that simplifies the process of loading images into image views and reduces Glide's boilerplate code.

## Download

Gradle:

```gradle
repositories {
  mavenCentral()
  google()
}

dependencies {
  implementation 'org.shadracktaeli:networkimageview:1.0.0'
}
```

How do I use NetworkImageView?
-------------------

> XML

```xml
<org.shadracktaeli.networkimageview.NetworkImageView
        android:layout_width="100dp"
        android:layout_height="100dp"
        app:imageUrl="https://via.placeholder.com/100x100"
        app:cacheStrategy="all|auto|data|none|resource"
        app:showProgressLoader="true"/>
```

> Kotlin

``` kotlin
override fun onCreate (savedInstanceState: Bundle?) {
    ...
    val networkImageView: NetworkImageView = findViewById(R.id.my_image_view)
    val imageUrl = "https://via.placeholder.com/100x100"
    val placeholderDrawable = R.drawable.placeholder
    val errorDrawable = R.drawable.error
    val showLoader = true
    val cacheStrategy = CacheStrategy.ALL | AUTO | DATA | NONE | RESOURCE
    networkImageView.cacheStrategy = cacheStrategy
    // placeholderDrawable, errorDrawable and showLoader are optional in Kotlin
    networkImageView.loadImage(imageUrl, placeholderDrawable, errorDrawable, showLoader)
}
```

Compatibility
-------------

 * **Minimum Android SDK**: NetworkImageView v1 requires a minimum API level of 21.
 * **Compile Android SDK**: NetworkImageView v1 requires you to compile against API 28 or later.

## Built With

* [Glide](https://bumptech.github.io/glide/) - Image loader used

## Authors

* **Shadrack Taeli** - *Initial work* - [NetworkImageView](https://github.com/shadracktaeli/Network-ImageView)

## License

This project is licensed under the Apache License 2.0 - see the [LICENSE.md](LICENSE.md) file for details

Disclaimer
---------
This is not an official Glide product.

# Network-ImageView

## Download

Gradle:

```gradle
repositories {
  mavenCentral()
  google()
}

dependencies {
  implementation 'org.shadracktaeli.networkimageview:networkimageview:1.0.0'
}
```

ProGuard
--------
Depending on your ProGuard (DexGuard) config and usage, you may need to include the following lines in your proguard.cfg:

```pro
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public class * extends com.bumptech.glide.module.AppGlideModule
-keep public enum com.bumptech.glide.load.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}

# for DexGuard only
-keepresourcexmlelements manifest/application/meta-data@value=GlideModule
```

How do I use NetworkImageView?
-------------------

```xml
// For a simple view:
<org.shadracktaeli.networkimageview.NetworkImageView
        android:layout_width="100dp"
        android:layout_height="100dp"
        app:imageUrl="https://via.placeholder.com/100x100"
        app:cacheStrategy="none"
        app:showProgressLoader="true"/>
```
## Built With

* [Glide](https://bumptech.github.io/glide/) - Image loader used

## Authors

* **Shadrack Taeli** - *Initial work* - [NetworkImageView](https://github.com/shadracktaeli/Network-ImageView)

## License

This project is licensed under the Apache License 2.0 - see the [LICENSE.md](LICENSE.md) file for details

Disclaimer
---------
This is not an official Glide product.

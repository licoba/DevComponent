
# About

商品相关 ( 如商品详情、购物车、商品列表复用等 ) Module

# 依赖信息

```groovy
dependencies {

    // ======================
    // = libs - 模块封装复用 =
    // ======================

    // 依赖 lib_commodity
    api project(':lib_commodity')
}
```

# AndroidManifest

```xml
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    package="afkt.module.commodity">

    <application>
        <provider
            android:name="androidx.startup.InitializationProvider"
            android:authorities="${applicationId}.androidx-startup"
            android:exported="false"
            tools:node="merge">
            <meta-data
                android:name="afkt.module.commodity.CommodityInitializer"
                android:value="@string/androidx_startup" />
        </provider>

        <activity
            android:name=".activity.MainContainerActivity"
            android:configChanges="orientation|keyboardHidden"
            android:screenOrientation="portrait" />
    </application>
</manifest>
```

# main/java 目录结构

```
- java                              
   - afkt                           
      - module                      
         - commodity                
            - activity              
            - adapter               
            - base                  
            - fragment              
```


# main/res 目录结构

```
- res                               
   - layout                         
   - mipmap-xxxhdpi                 
   - values                         
   - values-zh                      
```

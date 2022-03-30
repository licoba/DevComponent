
# About

商品通用快捷工具库 ( 方便复用 - 例 )

# 依赖信息

```groovy
dependencies {

    // 核心基础依赖库 ( 编译但不参与打包 )
    compileOnly project(':core')
}
```

# AndroidManifest

```xml
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    package="afkt_replace.libs.commodity">

    <application>
        <provider
            android:name="androidx.startup.InitializationProvider"
            android:authorities="${applicationId}.androidx-startup"
            android:exported="false"
            tools:node="merge">
            <meta-data
                android:name="afkt_replace.libs.commodity.CommodityLibraryInitializer"
                android:value="@string/androidx_startup" />
        </provider>
    </application>
</manifest>
```

# main/java 目录结构

```
- java                               
   - afkt_replace                    
      - libs                         
         - commodity                 
            - utils                  
```


# main/res 目录结构

```
- res                                
   - drawable-xhdpi                  
```


# main/res-label 目录结构

```
- res-label                          
   - drawable-xhdpi                  
```

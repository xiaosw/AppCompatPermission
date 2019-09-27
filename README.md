# [AppCompatPermission](https://github.com/xiaosw/AppCompatPermission)
处理动态权限兼容问题，简化使用步骤。

## Usage
## JitPack.io
I strongly recommend https://jitpack.io

```groovy
repositories {
    // ...
    maven { url "https://jitpack.io" }
}

dependencies {
    compile 'com.github.xiaosw:AppCompatPermission:1.0.0'
}
```

## Use it in Kotlin
```kotlin
AppCompatPermission.checkAndRequestPermission(requestCode, object : OnPermissionListener {
    override fun onGranted(requestCode: Int, grantedPermissions: Array<out String>) {
        
    }
}, permissions)
```

## Use it in Java
```java
AppCompatPermission.checkAndRequestPermission(reqeustCode, new OnPermissionListener() {
            @Override
            public void onGranted(int reqeustCode, @NotNull String[] granteds) {

            }

            @Override
            public void onDenied(int reqeustCode, List<String> denieds) {
            }

            @Override
            public void onNeverDenied(int reqeustCode, List<String> derverDenieds) {

            }
        }, permissions);
```
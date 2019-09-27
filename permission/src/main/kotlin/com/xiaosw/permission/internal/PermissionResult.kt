package com.xiaosw.permission.internal

import android.content.pm.PackageManager
import com.xiaosw.permission.AppCompatPermission
import com.xiaosw.permission.OnPermissionListener
import com.xiaosw.permission.util.Logger
import java.lang.ref.WeakReference

/**
 * @ClassName [PermissionResult]
 * @Description
 *
 * @Date 2019-05-29.
 * @Author xiaosw<xiaosw0802@163.com>.
 */
internal class PermissionResult(
    val requestCode: Int,
    listener: OnPermissionListener
) {

    private var callbackRef: WeakReference<OnPermissionListener> = WeakReference(listener)

    fun onRequestPermissionsResult(permissions: Array<out String>, grantResults: IntArray) {
        callbackRef.get()?.let { listener ->
            with(mutableListOf<String>()) {
                grantResults.forEachIndexed { index, i ->
                    if (i != PackageManager.PERMISSION_GRANTED) {
                        add(permissions[index])
                    }
                }
                if (size == 0) {
                    listener.onGranted(requestCode, permissions)
                    return
                }
                filterDeniedPermission(this).also {
                    if (it.size > 0) {
                        listener.onDenied(requestCode, it)
                        return
                    }
                }
                filterNeverDeniedPermission(this).also {
                    if (size > 0) {
                        listener.onNeverDenied(requestCode, it)
                        return
                    }
                }
            }
        }
        Logger.e("onRequestPermissionsResult: handle exception!")
    }

    private inline fun filterDeniedPermission(deniedPermissions: MutableList<String>) : MutableList<String> {
        return mutableListOf<String>().also {
            deniedPermissions.forEach { permission ->
                if (AppCompatPermission.shouldShowRequestPermissionRationale(permission)) {
                    it.add(permission)
                }
            }
        }
    }

    private inline fun filterNeverDeniedPermission(deniedPermissions: MutableList<String>) : MutableList<String> {
        return mutableListOf<String>().also {
            deniedPermissions.forEach { permission ->
                if (!AppCompatPermission.shouldShowRequestPermissionRationale(permission)) {
                    it.add(permission)
                }
            }
        }
    }

}
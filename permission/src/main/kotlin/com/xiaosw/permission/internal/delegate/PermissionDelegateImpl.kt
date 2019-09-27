package com.xiaosw.permission.internal.delegate

import android.annotation.TargetApi
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import com.xiaosw.permission.AppCompatPermission
import com.xiaosw.permission.OnPermissionListener
import com.xiaosw.permission.internal.PermissionFragmentFactory
import com.xiaosw.permission.internal.PermissionResult

/**
 * @ClassName [PermissionDelegateImpl]
 * @Description
 *
 * @Date 2019-05-29.
 * @Author xiaosw<xiaosw0802@163.com>.
 */
@TargetApi(Build.VERSION_CODES.M)
internal class PermissionDelegateImpl(context: Context) : PermissionDelegate(context) {

    override fun shouldShowRequestPermissionRationale(permission: String) : Boolean {
        return AppCompatPermission.topActivity?.shouldShowRequestPermissionRationale(permission) ?: false
    }

    override fun checkPermission(permissions: Array<out String>): Boolean {
        permissions.forEach {
            if (!checkPermission(it)) {
                return false
            }
        }
        return true
    }

    override fun checkPermission(permission: String): Boolean {
        if (context.checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) {
            return false
        }
        return true
    }

    override fun checkAndRequestPermission(requestCode: Int,
                                           listener: OnPermissionListener,
                                           permissions: Array<out String>) {
        with(filterNotGrantedPermission(permissions)) {
            if (size > 0) {
                with(PermissionFragmentFactory.createIfNeeded()) {
                    requestPermissions(permissions,
                        PermissionResult(requestCode, listener)
                    )
                }
            } else {
                listener.onGranted(requestCode, permissions)
            }
        }
    }

    private inline fun filterNotGrantedPermission(permissions: Array<out String>) : MutableList<String> {
        return mutableListOf<String>().also {
            permissions.forEach { permission ->
                if (!checkPermission(permission)) {
                    it.add(permission)
                }
            }
        }
    }

}
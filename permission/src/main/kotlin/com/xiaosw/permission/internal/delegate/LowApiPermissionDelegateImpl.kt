package com.xiaosw.permission.internal.delegate

import android.content.Context
import com.xiaosw.permission.OnPermissionListener

/**
 * @ClassName [LowApiPermissionDelegateImpl]
 * @Description
 *
 * @Date 2019-05-29.
 * @Author xiaosw<xiaosw0802@163.com>.
 */
internal class LowApiPermissionDelegateImpl(context: Context) : PermissionDelegate(context) {

    override fun shouldShowRequestPermissionRationale(permission: String) = false

    override fun checkPermission(permissions: Array<out String>) = true

    override fun checkPermission(permission: String) = true

    override fun checkAndRequestPermission(requestCode: Int,
                                           listener: OnPermissionListener,
                                           permissions: Array<out String>) {
        listener.onGranted(requestCode, permissions)
    }

}
package com.xiaosw.permission.internal.fragment

import android.annotation.TargetApi
import android.app.Fragment
import android.os.Build
import android.os.Bundle
import com.xiaosw.permission.internal.PermissionRequest
import com.xiaosw.permission.internal.PermissionResult
import java.lang.ref.WeakReference

/**
 * @ClassName [PermissionAppFragment]
 * @Description
 *
 * @Date 2019-05-29.
 * @Author xiaosw<xiaosw0802@163.com>.
 */
@TargetApi(Build.VERSION_CODES.M)
internal class PermissionAppFragment : Fragment(),
    PermissionRequest {

    override lateinit var callback: WeakReference<PermissionResult>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun requestPermissions(permissions: Array<out String>, callback: PermissionResult) {
        this.callback = WeakReference(callback)
        requestPermissions(permissions, callback.requestCode)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        callback.get()?.onRequestPermissionsResult(permissions, grantResults)
    }

}
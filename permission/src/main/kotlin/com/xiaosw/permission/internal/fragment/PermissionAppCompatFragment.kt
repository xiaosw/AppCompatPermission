package com.xiaosw.permission.internal.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.xiaosw.permission.internal.PermissionRequest
import com.xiaosw.permission.internal.PermissionResult
import java.lang.ref.WeakReference

/**
 * @ClassName [PermissionAppCompatFragment]
 * @Description
 *
 * @Date 2019-05-29.
 * @Author xiaosw<xiaosw0802@163.com>.
 */
internal class PermissionAppCompatFragment : Fragment(),
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
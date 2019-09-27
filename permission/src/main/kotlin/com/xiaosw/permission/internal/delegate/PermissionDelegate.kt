package com.xiaosw.permission.internal.delegate

import android.content.Context
import android.app.Activity
import android.os.Build
import com.xiaosw.permission.OnPermissionListener

/**
 * @ClassName [PermissionDelegate]
 * @Description
 *
 * @Date 2019-05-29.
 * @Author xiaosw<xiaosw0802@163.com>.
 */
internal abstract class PermissionDelegate(val context: Context) {
    /**
     * @see [Activity.shouldShowRequestPermissionRationale]
     */
    abstract fun shouldShowRequestPermissionRationale(permission: String) : Boolean

    /**
     * @see Context.checkSelfPermission
     */
    abstract fun checkPermission(permissions: Array<out String>): Boolean

    /**
     * @see Context.checkSelfPermission
     */
    abstract fun checkPermission(permission: String): Boolean

    /**
     * may be request permission.
     */
    abstract fun checkAndRequestPermission(
        requestCode: Int,
        listener: OnPermissionListener,
        permissions: Array<out String>)

    companion object {

        fun create(context: Context) =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                PermissionDelegateImpl(context)
            else
                LowApiPermissionDelegateImpl(context)

    }

}
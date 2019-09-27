package com.xiaosw.permission.internal

import java.lang.ref.WeakReference


/**
 * @ClassName [PermissionRequest]
 * @Description
 *
 * @Date 2019-05-29.
 * @Author xiaosw<xiaosw0802@163.com>.
 */
internal interface PermissionRequest {

    var callback: WeakReference<PermissionResult>

    fun requestPermissions(permissions: Array<out String>, callback: PermissionResult)
}
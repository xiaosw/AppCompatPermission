package com.xiaosw.permission.internal

import androidx.fragment.app.FragmentActivity
import com.xiaosw.permission.AppCompatPermission
import com.xiaosw.permission.internal.fragment.PermissionAppCompatFragment
import com.xiaosw.permission.internal.fragment.PermissionAppFragment

/**
 * @ClassName [PermissionFragmentFactory]
 * @Description
 *
 * @Date 2019-05-29.
 * @Author xiaosw<xiaosw0802@163.com>.
 */
internal object PermissionFragmentFactory {

    private const val TAG_PERMISSION_REQUESTER = "com.xiaosw.permission.internal.PermissionRequest"

    fun createIfNeeded(tag: String = TAG_PERMISSION_REQUESTER) = with(
        AppCompatPermission.topActivity) {
        requireNotNull(this) { "AppCompatPermission is not initialized!" }
        if (this is FragmentActivity) {
            var fragment = supportFragmentManager.findFragmentByTag(tag)
            if (null == fragment) {
                fragment = PermissionAppCompatFragment()
                supportFragmentManager
                    .beginTransaction()
                    .add(fragment, tag)
                    .commitAllowingStateLoss()
                supportFragmentManager.executePendingTransactions()
            }
            fragment as PermissionRequest
        } else {
            var fragment = fragmentManager.findFragmentByTag(tag)
            if (null == fragment) {
                fragment = PermissionAppFragment()
                fragmentManager
                    .beginTransaction()
                    .add(fragment, tag)
                    .commitAllowingStateLoss()
                fragmentManager.executePendingTransactions()
            }
            fragment as PermissionRequest
        }
    }

}
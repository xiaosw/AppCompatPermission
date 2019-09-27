package com.xiaosw.permission

/**
 * @ClassName [OnPermissionListener]
 * @Description
 *
 * @Date 2019-05-29.
 * @Author xiaosw<xiaosw0802@163.com>.
 */
interface OnPermissionListener {

    /**
     * 授权成功
     * @param requestCode default is [AppCompatPermission.CODE_DEF_REQUEST_PERMISSION]
     */
    fun onGranted(requestCode: Int, grantedPermissions: Array<out String>)

    /**
     * 拒绝授权
     * @param requestCode default is [AppCompatPermission.CODE_DEF_REQUEST_PERMISSION]
     */
    fun onDenied(requestCode: Int, deniedPermissions: MutableList<String>) {}

    /**
     * 拒绝授权并勾选不再提示用户
     * @param requestCode default is [AppCompatPermission.CODE_DEF_REQUEST_PERMISSION]
     */
    fun onNeverDenied(requestCode: Int, neverDeniedPermissions: MutableList<String>) {}
}
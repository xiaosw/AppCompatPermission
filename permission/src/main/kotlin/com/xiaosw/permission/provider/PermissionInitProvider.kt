package com.xiaosw.permission.provider

import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.net.Uri
import com.xiaosw.permission.AppCompatPermission
import com.xiaosw.permission.util.Logger

/**
 * @ClassName [PermissionInitProvider]
 * @Description
 *
 * @Date 2019-05-30.
 * @Author xiaosw<xiaosw0802@163.com>.
 */
class PermissionInitProvider : ContentProvider() {

    override fun onCreate(): Boolean {
        Logger.d("onCreate: $context")
        context?.apply {
            AppCompatPermission.init(this)
        }
        return true
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? = null

    override fun query(
        uri: Uri,
        projection: Array<String>?,
        selection: String?,
        selectionArgs: Array<String>?,
        sortOrder: String?
    ): Cursor? = null

    override fun update(uri: Uri, values: ContentValues?, selection: String?, selectionArgs: Array<String>?): Int = -1

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int = -1

    override fun getType(uri: Uri) : String? = null
}
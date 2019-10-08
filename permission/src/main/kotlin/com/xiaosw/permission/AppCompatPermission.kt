package com.xiaosw.permission

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import androidx.annotation.Keep
import com.xiaosw.permission.internal.delegate.PermissionDelegate
import com.xiaosw.permission.util.Logger
import java.lang.ref.WeakReference
import java.util.concurrent.atomic.AtomicBoolean

/**
 * @ClassName [AppCompatPermission]
 * @Description
 *
 * @Date 2019-05-29.
 * @Author xiaosw<xiaosw0802@163.com>.
 */
@Keep
object AppCompatPermission : Application.ActivityLifecycleCallbacks {

    /** 默认权限请求码 */
    private const val CODE_DEF_REQUEST_PERMISSION = 1000

    private val delegate by lazy {
        PermissionDelegate.create(app)
    }

    private var mCurrentActivityRef: WeakReference<Activity?>? = null
    private lateinit var app: Application
    private var isInitializer = AtomicBoolean()

    internal var topActivity: Activity? = null
        get() = mCurrentActivityRef?.get()

    internal fun init(context: Context) {
        Logger.w("init: context = $context")
        if (isInitializer.get()) {
            Logger.w("init: App compat permission is initializer!")
            return
        }
        app = context as Application
        app.registerActivityLifecycleCallbacks(this)
        isInitializer.compareAndSet(false, true)
    }

    @JvmStatic
    fun checkPermission(vararg permissions: String) = delegate.checkPermission(permissions)

    @JvmStatic
    @JvmOverloads
    fun checkAndRequestPermission(
        requestCode: Int = CODE_DEF_REQUEST_PERMISSION,
        listener: OnPermissionListener,
        vararg permissions: String
    ) = delegate.checkAndRequestPermission(requestCode, listener, permissions)

    private fun updateTopActivityIfNeeded(activity: Activity?) {
        if (mCurrentActivityRef?.get() != activity) {
            mCurrentActivityRef = WeakReference(activity)
        }
    }

    @JvmStatic
    fun shouldShowRequestPermissionRationale(permission: String) =
        delegate.shouldShowRequestPermissionRationale(permission)

    override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {
        updateTopActivityIfNeeded(activity)
        Logger.v("onActivityCreated: activity = $topActivity")
    }

    override fun onActivityStarted(activity: Activity?) {
    }

    override fun onActivityResumed(activity: Activity?) {
        updateTopActivityIfNeeded(activity)
        Logger.v("onActivityResumed: activity = $topActivity")
    }

    override fun onActivityPaused(activity: Activity?) {
    }

    override fun onActivitySaveInstanceState(activity: Activity?, outState: Bundle?) {
    }

    override fun onActivityStopped(activity: Activity?) {
    }

    override fun onActivityDestroyed(activity: Activity?) {
        Logger.v("onActivityDestroyed: activity = $activity")
    }

}
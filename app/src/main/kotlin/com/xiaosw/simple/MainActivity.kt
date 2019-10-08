package com.xiaosw.simple

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.xiaosw.permission.AppCompatPermission
import com.xiaosw.permission.OnPermissionListener

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AppCompatPermission.checkAndRequestPermission(1, object : OnPermissionListener {
            override fun onGranted(requestCode: Int, grantedPermissions: Array<out String>) {

            }

        })
    }
}

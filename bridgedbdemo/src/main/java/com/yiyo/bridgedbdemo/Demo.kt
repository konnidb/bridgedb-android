package com.yiyo.bridgedbdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bridgedb_connector.BridgeDbBlocking
import com.example.bridgedb_connector.BridgeDbConnection

class Demo : AppCompatActivity() {
    val connection = BridgeDbConnection(
        "0.0.0.0", "root", "qwe", 5000, true
    )

    lateinit var db: BridgeDbBlocking

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_demo)
        db = BridgeDbBlocking(connection)
        db.initStub()

    }
}

package com.example.socketiotester


import MySocket
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import com.example.socketiotester.ui.theme.SocketIoTesterTheme

import io.socket.client.IO
import io.socket.client.Socket


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        setContent {
            SocketIoTesterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Row{
                    Greeting("Android")

                    Button(onClick = {
                        val socket = MySocket()
                        socket.connect()
                        /*
                        Log.d("websocket","connect start ......")
                        // 连接服务器
                        val socket: Socket = IO.socket("http://10.0.2.2:5000")

// 监听连接成功事件
                        socket.on(Socket.EVENT_CONNECT) {
                            println("连接成功！")
                        }

// 监听服务器发送的消息事件
                        socket.on("message") { args ->
                            val message = args[0] as String
                            println("收到消息：$message")
                        }

// 连接服务器
                        socket.connect()
                        */


                    }) {
                        Text(text = "Connect")
                    }
                    }

                   // MyWeb()
                }
            }
        }


    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Composable
fun MyWeb(){

    // Declare a string that contains a url
    val mUrl = "http://10.0.2.2:5000"

    // Adding a WebView inside AndroidView
    // with layout as full screen
    AndroidView(factory = {
        WebView(it).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            webViewClient = WebViewClient()
            loadUrl(mUrl)
        }
    }, update = {
        it.loadUrl(mUrl)
    })
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SocketIoTesterTheme {
        Greeting("SocketIo Test Utility v1.0")
    }
}
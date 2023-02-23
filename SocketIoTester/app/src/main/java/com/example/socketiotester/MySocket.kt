import io.socket.client.IO
import io.socket.client.Socket

class MySocket {
    private var socket: Socket? = null

    fun connect() {
        val options = IO.Options().apply {
            forceNew = true
            reconnection = true
            query = ""
            transports = arrayOf("websocket")
        }
        val url = "http://10.0.2.2:5000"
        socket = IO.socket(url, options)
        socket?.on(Socket.EVENT_CONNECT) {
            println("Connected")
            socket?.emit("my event", "{\"data\":\"Hello from the Kotlin client!\"}")
        }?.on("my response") { args ->
            println(args[0])
        }
        socket?.connect()
    }

    fun disconnect() {
        socket?.disconnect()
    }
}

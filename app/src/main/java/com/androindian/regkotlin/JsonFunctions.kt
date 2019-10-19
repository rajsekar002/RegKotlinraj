package com.androindian.regkotlin

import org.json.JSONObject
import java.io.*
import java.net.HttpURLConnection
import java.net.URL

object JsonFunctions {

    fun RequestPost(url: String, param: String?): JSONObject? {

        var jsonObject: JSONObject? = null

        var jurl = URL(url)
        var connection = jurl.openConnection() as HttpURLConnection

        connection.doInput = true
        connection.doOutput = true
        connection.connectTimeout = 6000
        connection.useCaches = false
        connection.requestMethod = "POST"
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded")
        connection.setRequestProperty("Accept-Charset", "UTF-8")
        connection.setRequestProperty("Accept", "application/json")
        connection.connect()

        var outputStream = BufferedOutputStream(connection.outputStream)
        outputStream.write(param!!.toByteArray())
        outputStream.flush()

        var inputStream = BufferedInputStream(connection.inputStream)
        var bufferredaer = BufferedReader(InputStreamReader(inputStream))
        val sf = StringBuffer()
        val abc: String
        abc = bufferredaer.readLine()
        inputStream.close()
        jsonObject = JSONObject(abc)
        return jsonObject


    }
}
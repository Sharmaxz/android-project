package com.infnet.battle_dual.service

import khttp.*
import java.util.*
import java.lang.Exception
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.infnet.battle_dual.model.Hash
import com.infnet.battle_dual.settings.AppPreferences


object HashService {

    private const val url = "https://battle-dual.herokuapp.com/api/hash/"

    fun get(hash : Int) : Any {
        val url = this.url + hash.toString() + "/"
        val header = mapOf("Authorization" to "Bearer ${AppPreferences.env["BEARER"]}")

        var result: MutableMap<String, Any>
        try {
            val response = get(url, headers=header)
            if(response.statusCode == 200) {
                val gson = Gson()
                return gson.fromJson(response.text, Hash::class.java)
            }
            else {
                result = mutableMapOf(
                    "response" to response.statusCode,
                    "result" to response.text
                )
            }
        }
        catch (e: Exception) {
            result = mutableMapOf(
                "response" to 0
            )
        }

        return result
    }

}

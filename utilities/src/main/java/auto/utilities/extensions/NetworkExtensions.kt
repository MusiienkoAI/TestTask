package auto.utilities.extensions

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


inline fun <reified T> fromJson(json: String?): T {
    return Gson().fromJson<T>(json, object : TypeToken<T>() {}.type)
}
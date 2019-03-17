package auto.data.entities.responses

import com.google.gson.annotations.SerializedName


data class LoginResponse(
    @SerializedName("token") val token: String
) {
    val apiToken: String
        get() = "Bearer $token"
}
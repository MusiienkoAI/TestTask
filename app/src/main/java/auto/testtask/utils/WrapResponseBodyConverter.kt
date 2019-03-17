package auto.testtask.utils

import auto.utilities.entities.Resource
import okhttp3.ResponseBody
import retrofit2.Converter


class WrapResponseBodyConverter<T>(
    private val converter: Converter<ResponseBody, T>
) : Converter<ResponseBody, Resource<T>> {
    override fun convert(value: ResponseBody): Resource<T> {
        val response = converter.convert(value)

        return Resource.fromData(response)
    }
}
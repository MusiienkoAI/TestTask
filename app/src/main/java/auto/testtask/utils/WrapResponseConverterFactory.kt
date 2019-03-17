package auto.testtask.utils

import auto.testtask.utils.WrapResponseBodyConverter
import com.google.gson.Gson
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type


class WrapResponseConverterFactory(
    gson: Gson
) : Converter.Factory() {
    private val gsonConverterFactory: GsonConverterFactory = GsonConverterFactory.create(gson)

    override fun responseBodyConverter(
        type: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): Converter<ResponseBody, *>? {
        val dataType = (type as ParameterizedType).actualTypeArguments[0]
        Timber.d("response body converter triggered, type: $type, annotations: $annotations, data type: $dataType")
        val gsonConverter: Converter<ResponseBody, *>? =
            gsonConverterFactory.responseBodyConverter(dataType, annotations, retrofit)
        return WrapResponseBodyConverter(gsonConverter as Converter<ResponseBody, *>)
    }

    override fun requestBodyConverter(
        type: Type,
        parameterAnnotations: Array<Annotation>,
        methodAnnotations: Array<Annotation>,
        retrofit: Retrofit
    ): Converter<*, RequestBody>? {
        Timber.d("request body converter triggered, type: $type, parameterAnnotation: $parameterAnnotations, methodAnnotations: $methodAnnotations")
        return gsonConverterFactory.requestBodyConverter(type, parameterAnnotations, methodAnnotations, retrofit)
    }
}
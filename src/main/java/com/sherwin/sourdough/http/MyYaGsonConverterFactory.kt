package com.sherwin.sourdough.http

import com.sherwin.sourdough.MyApiModel
import com.google.gson.TypeAdapter
import com.google.gson.reflect.TypeToken
import com.sherwin.sourdough.candy.typeWith
import okhttp3.MediaType
import okhttp3.RequestBody
import okhttp3.ResponseBody
import okio.Buffer
import retrofit2.Converter
import retrofit2.Retrofit
import java.io.OutputStreamWriter
import java.lang.reflect.Type
import java.nio.charset.Charset

/**
 * Created by Administrator on 2017/11/8.
 */
class MyYaGsonConverterFactory : Converter.Factory() {
    override fun requestBodyConverter(type: Type?, parameterAnnotations: Array<out Annotation>?, methodAnnotations: Array<out Annotation>?, retrofit: Retrofit?): Converter<Any, RequestBody> {
        @Suppress("UNCHECKED_CAST")
        val adapter = Json.gson.getAdapter(TypeToken.get(type)) as TypeAdapter<Any>
        return Converter {
            val buffer = Buffer()
            val writer = OutputStreamWriter(buffer.outputStream(), Charset.forName("UTF-8"))
            val jsonWriter = Json.gson.newJsonWriter(writer)
            adapter.write(jsonWriter, it)
            jsonWriter.close()
            RequestBody.create(MediaType.parse("application/json; charset=UTF-8"), buffer.readByteString())
        }
    }

    override fun responseBodyConverter(type: Type, annotations: Array<out Annotation>, retrofit: Retrofit): Converter<ResponseBody, Any> {
        val adapter = Json.gson.getAdapter(TypeToken.get(MyApiModel::class.typeWith(type)))
        return Converter { value ->
            value.use {
                @Suppress("UNCHECKED_CAST")
                val responseData = (adapter.fromJson(it.charStream()) as MyApiModel<Any>)
                return@Converter responseData.data
            }
        }
    }
}
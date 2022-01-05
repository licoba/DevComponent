package dev.core.router

import android.content.Context
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.facade.service.SerializationService
import dev.engine.DevEngine
import java.lang.reflect.Type

@Route(path = "/service/json_serialization")
class JSONServiceImpl : SerializationService {

    override fun init(context: Context?) {
    }

    override fun <T : Any?> json2Object(
        input: String?,
        clazz: Class<T>?
    ): T {
        return parseObject(input, clazz)
    }

    override fun object2Json(instance: Any?): String? {
        return DevEngine.getJSON()?.toJson(instance)
    }

    override fun <T : Any?> parseObject(
        input: String?,
        clazz: Type?
    ): T {
        return DevEngine.getJSON()?.fromJson<T>(input, clazz) as T
    }
}
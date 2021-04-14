package dev.core.router

import android.content.Context
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.facade.service.SerializationService
import dev.engine.json.DevJSONEngine
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
        return DevJSONEngine.getEngine()?.toJson(instance)
    }

    override fun <T : Any?> parseObject(
        input: String?,
        clazz: Type?
    ): T {
        return DevJSONEngine.getEngine()?.fromJson<T>(input, clazz) as T
    }
}
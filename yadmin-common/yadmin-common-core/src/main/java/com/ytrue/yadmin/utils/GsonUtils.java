package com.ytrue.yadmin.utils;


import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.io.*;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

/**
 * 优势：
 * 数据量低于1万的时候速度有绝对优势
 * API和注解支持较为完善，支持宽松解析
 * 支持的数据源较广泛（字符串，对象，文件、流）
 *
 * @author ytrue
 * @date 2021/6/13 14:54
 * @description GsonUtils Gson工具类
 */

public class GsonUtils {
    private static final Gson gson;

    static {
        GsonBuilder gsonBuilder = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss");
        gsonBuilder.disableHtmlEscaping();//禁止将部分特殊字符转义为unicode编码
        registerTypeAdapter(gsonBuilder);
        gson = gsonBuilder.create();
    }

    private static void registerTypeAdapter(GsonBuilder gsonBuilder) {
        gsonBuilder.registerTypeAdapter(short.class, new NumberTypeAdapter<>(short.class));
        gsonBuilder.registerTypeAdapter(Short.class, new NumberTypeAdapter<>(Short.class));
        gsonBuilder.registerTypeAdapter(int.class, new NumberTypeAdapter<>(int.class));
        gsonBuilder.registerTypeAdapter(Integer.class, new NumberTypeAdapter<>(Integer.class));
        gsonBuilder.registerTypeAdapter(long.class, new NumberTypeAdapter<>(long.class));
        gsonBuilder.registerTypeAdapter(Long.class, new NumberTypeAdapter<>(Long.class));
        gsonBuilder.registerTypeAdapter(float.class, new NumberTypeAdapter<>(float.class));
        gsonBuilder.registerTypeAdapter(Float.class, new NumberTypeAdapter<>(Float.class));
        gsonBuilder.registerTypeAdapter(double.class, new NumberTypeAdapter<>(double.class));
        gsonBuilder.registerTypeAdapter(Double.class, new NumberTypeAdapter<>(Double.class));
        gsonBuilder.registerTypeAdapter(BigDecimal.class, new NumberTypeAdapter<>(BigDecimal.class));
    }

    /**
     * JSON反序列化
     */
    public static <V> V from(InputStream inputStream, Class<V> type) {
        JsonReader reader = new JsonReader(new InputStreamReader(Objects.requireNonNull(inputStream)));
        return gson.fromJson(reader, type);
    }

    /**
     * JSON反序列化
     */
    public static <V> V from(InputStream inputStream, TypeToken<V> typeToken) {
        JsonReader reader = new JsonReader(new InputStreamReader(Objects.requireNonNull(inputStream)));
        return gson.fromJson(reader, typeToken.getType());
    }

    /**
     * JSON反序列化（List）
     */
    public static <V> List<V> fromList(InputStream inputStream, Class<V> type) {
        JsonReader reader = new JsonReader(new InputStreamReader(Objects.requireNonNull(inputStream)));
        TypeToken<List<V>> typeToken = (TypeToken<List<V>>) TypeToken.getParameterized(ArrayList.class, type);
        return gson.fromJson(reader, typeToken.getType());
    }

    /**
     * JSON反序列化
     */
    public static <V> V from(File file, Class<V> type) {
        try {
            JsonReader reader = new JsonReader(new FileReader(file));
            return gson.fromJson(reader, type);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("gson from error, file path: " + file.getPath() + ", type: " + type, e);
        }
    }

    /**
     * JSON反序列化
     */
    public static <V> V from(File file, TypeToken<V> typeToken) {
        try {
            JsonReader reader = new JsonReader(new FileReader(file));
            return gson.fromJson(reader, typeToken.getType());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e.getMessage(),e);
        }
    }

    /**
     * JSON反序列化（List）
     */
    public static <V> List<V> fromList(File file, Class<V> type) {
        try {
            JsonReader reader = new JsonReader(new FileReader(file));
            TypeToken<List<V>> typeToken = (TypeToken<List<V>>) TypeToken.getParameterized(ArrayList.class, type);
            return gson.fromJson(reader, typeToken.getType());
        } catch (FileNotFoundException e) {
           throw new RuntimeException(e.getMessage(),e);
        }
    }

    /**
     * JSON反序列化
     */
    public static <V> V from(String json, Class<V> type) {
        return gson.fromJson(json, type);
    }

    /**
     * JSON反序列化
     */
    public static <V> V from(String json, Type type) {
        return gson.fromJson(json, type);
    }

    /**
     * JSON反序列化
     */
    public static <V> V from(String json, TypeToken<V> typeToken) {
        return gson.fromJson(json, typeToken.getType());
    }

    /**
     * JSON反序列化（List）
     */
    public static <V> List<V> fromList(String json, Class<V> type) {
        TypeToken<List<V>> typeToken = (TypeToken<List<V>>) TypeToken.getParameterized(ArrayList.class, type);
        return gson.fromJson(json, typeToken.getType());
    }

    /**
     * JSON反序列化（Map）
     */
    public static Map<String, Object> fromMap(String json) {
        return gson.fromJson(json, new TypeToken<HashMap<String, Object>>() {
        }.getType());
    }

    /**
     * 宽松JSON反序列化
     */
    public static <V> V fromLenient(InputStream inputStream, Class<V> type) {
        JsonReader reader = new JsonReader(new InputStreamReader(Objects.requireNonNull(inputStream)));
        reader.setLenient(true);
        return gson.fromJson(reader, type);
    }

    /**
     * 宽松JSON反序列化（List）
     */
    public static <V> List<V> fromListLenient(InputStream inputStream, Class<V> type) {
        JsonReader reader = new JsonReader(new InputStreamReader(Objects.requireNonNull(inputStream)));
        reader.setLenient(true);
        TypeToken<List<V>> typeToken = (TypeToken<List<V>>) TypeToken.getParameterized(ArrayList.class, type);
        return gson.fromJson(reader, typeToken.getType());
    }

    /**
     * 宽松JSON反序列化
     */
    public static <V> V fromLenient(File file, Class<V> type) {
        try {
            JsonReader reader = new JsonReader(new FileReader(file));
            reader.setLenient(true);
            return gson.fromJson(reader, type);
        } catch (FileNotFoundException e) {
           throw new RuntimeException(e.getMessage(),e);
        }
    }

    /**
     * 宽松JSON反序列化（List）
     */
    public static <V> List<V> fromListLenient(File file, Class<V> type) {
        try {
            JsonReader reader = new JsonReader(new FileReader(file));
            reader.setLenient(true);
            TypeToken<List<V>> typeToken = (TypeToken<List<V>>) TypeToken.getParameterized(ArrayList.class, type);
            return gson.fromJson(reader, typeToken.getType());
        } catch (FileNotFoundException e) {
           throw new RuntimeException(e.getMessage(),e);
        }
    }

    /**
     * 宽松JSON反序列化
     */
    public static <V> V fromLenient(String json, Class<V> type) {
        if (StringUtils.isEmpty(json)) {
            return null;
        }
        JsonReader reader = new JsonReader(new StringReader(json));
        reader.setLenient(true);
        return gson.fromJson(reader, type);
    }

    /**
     * 宽松JSON反序列化
     */
    public static <V> V fromLenient(String json, Type type) {
        if (StringUtils.isEmpty(json)) {
            return null;
        }
        JsonReader reader = new JsonReader(new StringReader(json));
        reader.setLenient(true);
        return gson.fromJson(reader, type);
    }

    /**
     * 宽松JSON反序列化
     */
    public static <V> V fromLenient(String json, TypeToken<V> typeToken) {
        if (StringUtils.isEmpty(json)) {
            return null;
        }
        JsonReader reader = new JsonReader(new StringReader(json));
        reader.setLenient(true);
        return gson.fromJson(reader, typeToken.getType());
    }

    /**
     * 宽松JSON反序列化（List）
     */
    public static <V> List<V> fromListLenient(String json, Class<V> type) {
        if (StringUtils.isEmpty(json)) {
            return null;
        }
        JsonReader reader = new JsonReader(new StringReader(json));
        reader.setLenient(true);
        TypeToken<List<V>> typeToken = (TypeToken<List<V>>) TypeToken.getParameterized(ArrayList.class, type);
        return gson.fromJson(reader, typeToken.getType());
    }

    /**
     * 序列化为JSON
     */
    public static <V> String to(List<V> list) {
        return gson.toJson(list);
    }

    /**
     * 序列化为JSON
     */
    public static <V> String to(V v) {
        return gson.toJson(v);
    }

    /**
     * 序列化为JSON文件
     */
    public static <V> void toFile(String path, List<V> list) {
        try (JsonWriter jsonWriter = new JsonWriter(new FileWriter(new File(path), true));) {
            gson.toJson(list, new TypeToken<List<V>>() {
            }.getType(), jsonWriter);
            jsonWriter.flush();
        } catch (Exception e) {
           throw new RuntimeException(e.getMessage(),e);
        }
    }

    /**
     * 序列化为JSON文件
     */
    public static <V> void toFile(String path, V v) {
        try (JsonWriter jsonWriter = new JsonWriter(new FileWriter(new File(path), true));) {
            gson.toJson(v, v.getClass(), jsonWriter);
            jsonWriter.flush();
        } catch (Exception e) {
           throw new RuntimeException(e.getMessage(),e);
        }
    }

    /**
     * 从json串中获取某个字段
     *
     * @return String，默认为 null
     */
    public static String getAsString(String json, String key) {
        if (StringUtils.isEmpty(json)) {
            return null;
        }
        String propertyValue;
        JsonElement jsonByKey = getAsJsonObject(json, key);
        if (null == jsonByKey) {
            return null;
        }
        try {
            propertyValue = jsonByKey.getAsString();
        } catch (Exception e) {
            propertyValue = jsonByKey.toString();
        }
        return propertyValue;
    }

    /**
     * 从json串中获取某个字段
     *
     * @return int，默认为 0
     */
    public static int getAsInt(String json, String key) {
        if (StringUtils.isEmpty(json)) {
            return 0;
        }
        JsonElement jsonByKey = getAsJsonObject(json, key);
        if (null == jsonByKey) {
            return 0;
        }
        try {
            return jsonByKey.getAsInt();
        } catch (Exception e) {
           throw new RuntimeException(e.getMessage(),e);
        }
    }

    /**
     * 从json串中获取某个字段
     *
     * @return long，默认为 0
     */
    public static long getAsLong(String json, String key) {
        if (StringUtils.isEmpty(json)) {
            return 0L;
        }
        JsonElement jsonByKey = getAsJsonObject(json, key);
        if (null == jsonByKey) {
            return 0L;
        }
        try {
            return jsonByKey.getAsLong();
        } catch (Exception e) {
           throw new RuntimeException(e.getMessage(),e);
        }
    }

    /**
     * 从json串中获取某个字段
     *
     * @return double，默认为 0.0
     */
    public static double getAsDouble(String json, String key) {
        if (StringUtils.isEmpty(json)) {
            return 0.0;
        }
        JsonElement jsonByKey = getAsJsonObject(json, key);
        if (null == jsonByKey) {
            return 0.0;
        }
        try {
            return jsonByKey.getAsDouble();
        } catch (Exception e) {
           throw new RuntimeException(e.getMessage(),e);
        }
    }

    /**
     * 从json串中获取某个字段
     *
     * @return BigInteger，默认为 0.0
     */
    public static BigInteger getAsBigInteger(String json, String key) {
        if (StringUtils.isEmpty(json)) {
            return new BigInteger(String.valueOf(0.00));
        }
        JsonElement jsonByKey = getAsJsonObject(json, key);
        if (null == jsonByKey) {
            return new BigInteger(String.valueOf(0.00));
        }
        try {
            return jsonByKey.getAsBigInteger();
        } catch (Exception e) {
           throw new RuntimeException(e.getMessage(),e);
        }
    }

    /**
     * 从json串中获取某个字段
     *
     * @return BigDecimal，默认为 0.0
     */
    public static BigDecimal getAsBigDecimal(String json, String key) {
        if (StringUtils.isEmpty(json)) {
            return new BigDecimal("0.0");
        }
        JsonElement jsonByKey = getAsJsonObject(json, key);
        if (null == jsonByKey) {
            return new BigDecimal("0.0");
        }
        try {
            return jsonByKey.getAsBigDecimal();
        } catch (Exception e) {
           throw new RuntimeException(e.getMessage(),e);
        }
    }

    /**
     * 从json串中获取某个字段
     *
     * @return boolean, 默认为 false
     */
    public static boolean getAsBoolean(String json, String key) {
        if (StringUtils.isEmpty(json)) {
            return false;
        }
        JsonPrimitive jsonByKey = (JsonPrimitive) getAsJsonObject(json, key);
        if (null == jsonByKey) {
            return false;
        }
        try {
            if (jsonByKey.isBoolean()) {
                return jsonByKey.getAsBoolean();
            } else {
                if (jsonByKey.isString()) {
                    String string = jsonByKey.getAsString();
                    if ("1".equals(string)) {
                        return true;
                    } else {
                        return BooleanUtils.toBoolean(string);
                    }
                } else {//number
                    return BooleanUtils.toBoolean(jsonByKey.getAsInt());
                }
            }
        } catch (Exception e) {
           throw new RuntimeException(e.getMessage(),e);
        }
    }

    /**
     * 从json串中获取某个字段
     *
     * @return byte, 默认为 0
     */
    public static byte getAsByte(String json, String key) {
        if (StringUtils.isEmpty(json)) {
            return 0;
        }
        JsonElement jsonByKey = getAsJsonObject(json, key);
        if (null == jsonByKey) {
            return 0;
        }
        try {
            return jsonByKey.getAsByte();
        } catch (Exception e) {
           throw new RuntimeException(e.getMessage(),e);
        }
    }

    /**
     * 从json串中获取某个字段
     *
     * @return object, 默认为 null
     */
    public static <V> V getAsObject(String json, String key, Class<V> type) {
        if (StringUtils.isEmpty(json)) {
            return null;
        }
        JsonElement jsonByKey = getAsJsonObject(json, key);
        if (null == jsonByKey) {
            return null;
        }
        try {
            return from(jsonByKey.getAsString(), type);
        } catch (Exception e) {
           throw new RuntimeException(e.getMessage(),e);
        }
    }

    /**
     * 从json串中获取某个字段
     *
     * @return list, 默认为 null
     */
    public static <V> List<V> getAsList(String json, String key, Class<V> type) {
        if (StringUtils.isEmpty(json)) {
            return null;
        }
        JsonElement jsonByKey = getAsJsonObject(json, key);
        if (null == jsonByKey) {
            return null;
        }
        try {
            JsonArray jsonArray = jsonByKey.getAsJsonArray();
            TypeToken<List<V>> typeToken = (TypeToken<List<V>>) TypeToken.getParameterized(ArrayList.class, type);
            return from(jsonArray.toString(), typeToken);
        } catch (Exception e) {
           throw new RuntimeException(e.getMessage(),e);
        }
    }

    /**
     * 从json串中获取某个字段
     */
    public static JsonElement getAsJsonObject(String json, String key) {
        try {
            JsonElement element = JsonParser.parseString(json);
            JsonObject jsonObj = element.getAsJsonObject();
            return jsonObj.get(key);
        } catch (JsonSyntaxException e) {
           throw new RuntimeException(e.getMessage(),e);
        }
    }

    /**
     * 向json中添加属性
     *
     * @return json
     */
    public static <V> String add(String json, String key, V value) {
        JsonElement element = JsonParser.parseString(json);
        JsonObject jsonObject = element.getAsJsonObject();
        add(jsonObject, key, value);
        return jsonObject.toString();
    }

    /**
     * 向json中添加属性
     */
    private static <V> void add(JsonObject jsonObject, String key, V value) {
        if (value instanceof String) {
            jsonObject.addProperty(key, (String) value);
        } else if (value instanceof Number) {
            jsonObject.addProperty(key, (Number) value);
        } else {
            jsonObject.addProperty(key, to(value));
        }
    }

    /**
     * 除去json中的某个属性
     *
     * @return json
     */
    public static String remove(String json, String key) {
        JsonElement element = JsonParser.parseString(json);
        JsonObject jsonObj = element.getAsJsonObject();
        jsonObj.remove(key);
        return jsonObj.toString();
    }

    /**
     * 修改json中的属性
     */
    public static <V> String update(String json, String key, V value) {
        JsonElement element = JsonParser.parseString(json);
        JsonObject jsonObject = element.getAsJsonObject();
        jsonObject.remove(key);
        add(jsonObject, key, value);
        return jsonObject.toString();
    }

    /**
     * 格式化Json(美化)
     *
     * @return json
     */
    public static String format(String json) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonElement jsonElement = JsonParser.parseString(json);
        return gson.toJson(jsonElement);
    }

    /**
     * 判断字符串是否是json
     *
     * @return json
     */
    public static boolean isJson(String json) {
        try {
            return JsonParser.parseString(json).isJsonObject();
        } catch (Exception e) {
            return false;
        }
    }


    /**
     * @author ytrue
     * @date 2021/6/13 14:54
     * @description Gson解析的Number类型的字段解析适配器
     */
    private static class NumberTypeAdapter<T> extends TypeAdapter<Number> {
        private Class<T> c;

        public NumberTypeAdapter(Class<T> c) {
            this.c = c;
        }

        @Override
        public void write(JsonWriter jsonWriter, Number number) throws IOException {
            if (number != null) {
                jsonWriter.value(number);
            } else {
                jsonWriter.nullValue();
            }
        }

        @Override
        public Number read(JsonReader jsonReader) {
            try {
                if (jsonReader.peek() == null) {
                    return null;
                }
                String json = jsonReader.nextString();
                if (c == short.class) {
                    return NumberUtils.toShort(json);
                } else if (c == Short.class) {
                    if (StringUtils.isEmpty(json)) {
                        return null;
                    }
                    return Short.parseShort(json);
                } else if (c == int.class) {
                    return NumberUtils.toInt(json);
                } else if (c == Integer.class) {
                    if (StringUtils.isEmpty(json)) {
                        return null;
                    }
                    return Integer.parseInt(json);
                } else if (c == long.class) {
                    return NumberUtils.toLong(json);
                } else if (c == Long.class) {
                    if (StringUtils.isEmpty(json)) {
                        return null;
                    }
                    return Long.parseLong(json);
                } else if (c == float.class) {
                    return Float.parseFloat(json);
                } else if (c == Float.class) {
                    if (StringUtils.isEmpty(json)) {
                        return null;
                    }
                    return NumberUtils.toFloat(json);
                } else if (c == double.class) {
                    return NumberUtils.toDouble(json);
                } else if (c == Double.class) {
                    if (StringUtils.isEmpty(json)) {
                        return null;
                    }
                    return Double.parseDouble(json);
                } else if (c == BigDecimal.class) {
                    if (StringUtils.isEmpty(json)) {
                        return null;
                    }
                    return new BigDecimal(json);
                } else {
                    return Integer.parseInt(json);
                }
            } catch (Exception e) {
                return null;
            }
        }
    }


}

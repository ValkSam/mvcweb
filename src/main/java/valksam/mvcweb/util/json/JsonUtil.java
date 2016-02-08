package valksam.mvcweb.util.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectReader;
import valksam.mvcweb.LoggerWrapper;

import java.io.IOException;
import java.util.List;

/**
 * Created by Valk on 01.02.16.
 */
public class JsonUtil {
    private static final LoggerWrapper LOG = LoggerWrapper.get(JsonUtil.class);

    public static <T> List<T> readValuesListFromString(String json, Class clazz) {
        LOG.debug("reading JSON-list for " + clazz.getName());
        ObjectReader reader = UserJsonMapper.getMapper().readerFor(clazz);
        try {
            return reader.<T>readValues(json).readAll();
        } catch (IOException e) {
            throw LOG.getIllegalArgumentException("Invalid read array from JSON:\n'" + json + "'", e);
        }
    }

    public static <T> T readValueFromString(String json, Class<T> clazz) {
        LOG.debug("reading JSON for " + clazz.getName());
        try {
            return UserJsonMapper.getMapper().readValue(json, clazz);
        } catch (IOException e) {
            throw LOG.getIllegalArgumentException("Invalid read from JSON:\n'" + json + "'", e);
        }
    }

    public static <T> String writeValueAndGetJsonAsString(T obj) {
        LOG.debug("writing JSON for " + obj.getClass().getName());
        try {
            return UserJsonMapper.getMapper().writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException("Invalid write to JSON:\n'" + obj + "'", e);
        }
    }
}

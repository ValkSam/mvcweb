package valksam.mvcweb.util.json;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.module.SimpleModule;
import valksam.mvcweb.LoggerWrapper;
import valksam.mvcweb.model.Role;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Valk on 01.02.16.
 */
public class UserJsonMapper extends ObjectMapper {
    private static ObjectMapper MAPPER = new UserJsonMapper();

    public static ObjectMapper getMapper() {
        return MAPPER;
    }

    private UserJsonMapper() {
        SimpleModule customModule = new SimpleModule();
        customModule.addSerializer(Role.class, new RoleSerializer());
        customModule.addSerializer(Date.class, new DateSerializer());
        customModule.addDeserializer(Date.class, new DateDeserializer());
        registerModule(customModule);

        setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
        setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }
}

class RoleSerializer extends JsonSerializer<Role> {
    private static final LoggerWrapper LOG = LoggerWrapper.get(RoleSerializer.class);

    @Override
    public void serialize(Role value, JsonGenerator gen, SerializerProvider serializers) throws IOException, JsonProcessingException {
        LOG.debug("serialize " + value);
        gen.writeString(value.toString());
    }
}

class DateSerializer extends JsonSerializer<Date> {
    private static final LoggerWrapper LOG = LoggerWrapper.get(DateSerializer.class);

    @Override
    public void serialize(Date value, JsonGenerator gen, SerializerProvider serializers) throws IOException, JsonProcessingException {
        LOG.debug("serialize " + value);
        gen.writeString(new SimpleDateFormat("EEE MMM dd HH:mm:ss 'EET' yyyy", Locale.ENGLISH).format(value));
    }
}

class DateDeserializer extends JsonDeserializer<Date> {
    private static final LoggerWrapper LOG = LoggerWrapper.get(DateSerializer.class);

    @Override
    public Date deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        LOG.debug("deserialize " + p.getText());
        try {
            return new SimpleDateFormat("EEE MMM dd HH:mm:ss 'EET' yyyy", Locale.ENGLISH).parse(p.getText());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
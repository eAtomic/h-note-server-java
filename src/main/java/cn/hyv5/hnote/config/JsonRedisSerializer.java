package cn.hyv5.hnote.config;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.stereotype.Component;

@Component
public class JsonRedisSerializer implements RedisSerializer<Object> {

    private final ObjectMapper om;

    public JsonRedisSerializer() {
        this.om = new ObjectMapper().enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY);
        om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        om.registerModule(new JavaTimeModule());
    }

    @Override
    public byte[] serialize(Object t) throws SerializationException {
        try {
            return om.writeValueAsBytes(t);
        } catch (JsonProcessingException e) {
            throw new SerializationException(e.getMessage(), e);
        }
    }

    @Override
    public Object deserialize(byte[] bytes) throws SerializationException {

        if (bytes == null) {
            return null;
        }

        try {
            return om.readValue(bytes, Object.class);
        } catch (Exception e) {
            throw new SerializationException(e.getMessage(), e);
        }
    }
}

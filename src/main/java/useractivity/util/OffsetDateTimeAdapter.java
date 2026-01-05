package useractivity.util;

import java.lang.reflect.Type;
import java.time.OffsetDateTime;

import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializer;

public class OffsetDateTimeAdapter
        implements JsonDeserializer<OffsetDateTime>, JsonSerializer<OffsetDateTime> {

    @Override
    public OffsetDateTime deserialize(
            JsonElement json,
            Type typeOfT,
            com.google.gson.JsonDeserializationContext context
    ) throws JsonParseException {
        return OffsetDateTime.parse(json.getAsString());
    }

    @Override
    public JsonElement serialize(
            OffsetDateTime src,
            Type typeOfSrc,
            com.google.gson.JsonSerializationContext context
    ) {
        return new JsonPrimitive(src.toString());
    }
}

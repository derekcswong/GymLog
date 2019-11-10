package ui.gsonadapters;

import com.google.gson.*;
import model.Exercise;

import java.lang.reflect.Type;
//citation: https://www.baeldung.com/gson-json-to-map
// http://www.studytrails.com/java/json/java-google-json-custom-serializer-deserializer/

public class AbstractExerciseAdapter implements JsonSerializer<Exercise>, JsonDeserializer<Exercise> {
    @Override
    public JsonElement serialize(Exercise src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject result = new JsonObject();
        result.add("exercise type", new JsonPrimitive(src.getClass().getSimpleName()));
        result.add("properties", context.serialize(src, src.getClass()));

        return result;
    }

    @Override
    public Exercise deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        String type = jsonObject.get("exercise type").getAsString();
        JsonElement element = jsonObject.get("properties");
        try {
            return context.deserialize(element, Class.forName("model." + type));
        } catch (ClassNotFoundException cnfe) {
            throw new JsonParseException("Unknown element type: " + type, cnfe);
        }
    }
}
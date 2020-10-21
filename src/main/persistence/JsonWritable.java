package persistence;

import org.json.JSONObject;

public interface JsonWritable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}

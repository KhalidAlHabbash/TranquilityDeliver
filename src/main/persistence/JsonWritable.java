package persistence;

import org.json.JSONObject;

public interface JsonWritable {
    //Some methods in the persistence package have methods taken from:
    //https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}

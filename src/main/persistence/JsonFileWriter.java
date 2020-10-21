package persistence;

import model.Driver;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * Represents a JSON writer than writes driver data into a JSON file
 */
public class JsonFileWriter {
    private static final int INDENT = 2;
    private PrintWriter writer;
    private String fileDestination;

    //REQUIRES: a valid file destination
    //EFFECTS: constructs a JsonFileWriter with a destination to write data in
    public JsonFileWriter(String fileDestination) {
        this.fileDestination = fileDestination;
    }

    // MODIFIES: this
    // EFFECTS: if destination file cannot be opened for writing throws FileNotFoundException else,
    // opens writer with fileDestination
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(fileDestination));
    }

    //REQUIRES: a valid, existing Driver d
    //MODIFIES: this
    //EFFECTS: stores Driver d as a JSONObject, then writes it to file
    public void write(Driver d) {
        JSONObject writeDriver = d.toJson();
        writeToFile(writeDriver);

    }

    //MODIFIES: this
    //EFFECTS: prints JSONObject as a string to file
    private void writeToFile(JSONObject writeDriver) {
        writer.print(writeDriver.toString(INDENT));
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        writer.close();
    }

}

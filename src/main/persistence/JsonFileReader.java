package persistence;

import model.Driver;
import model.Package;
import model.PackagesList;
import org.json.JSONArray;
import org.json.JSONObject;

import java.awt.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * Represents a JSON file reader that reads driver data from JSON file
 */
public class JsonFileReader {
    private String fileSource;

    //getter
    public String getFileSource() {
        return fileSource;
    }

    //EFFECTS: constructs A JSON file reader that reads data stored in JSON file source
    public JsonFileReader(String source) {
        this.fileSource = source;
    }

    //EFFECTS: if an error occurs while reading file, throws IOException,
    //         else reads file and returns it parsed
    public Driver read() throws IOException {
        String driverData = readFile(this.fileSource);
        JSONObject driverJsonObject = new JSONObject(driverData);
        return parseDriverData(driverJsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }
        return contentBuilder.toString();
    }

    //EFFECTS: parses Driver from JSON object and returns it
    private Driver parseDriverData(JSONObject driverJsonObject) {
        String name = driverJsonObject.getString("driverName");
        String driverID = driverJsonObject.getString("driverID");
        String licensePlate = driverJsonObject.getString("licensePlate");
        Driver driver = new Driver(name, driverID, licensePlate);

        addDeliveries(driver, driverJsonObject);
        return driver;
    }

    //MODIFIES: d
    //EFFECTS: parses packages from JSON object and adds them to driversDeliveries
    public void addDeliveries(Driver d, JSONObject driverJsonObject) {
        JSONArray deliveries = driverJsonObject.getJSONArray("driversDeliveries");

        for (Object deliveriesJson : deliveries) {
            JSONObject json = (JSONObject) deliveriesJson;
            addDelivery(d, json);
        }
    }

    //MODIFIES: d
    //EFFECTS: parses package from JSON object and adds it to driversDeliveries
    public void addDelivery(Driver d, JSONObject jsonObject) {
        String name = jsonObject.getString("customerName");
        String customerPhoneNumber = jsonObject.getString("customerPhoneNumber");
        Point location = (Point) jsonObject.get("deliveryLocation");
        String dateOrdered = jsonObject.getString("dateOrdered");
        Package p = new Package(customerPhoneNumber, location, name, dateOrdered);
        d.addPackage(p);
    }
}



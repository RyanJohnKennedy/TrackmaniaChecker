import java.io.*;

public class APIKeyManager {
    private static final String API_KEY_FILE_PATH = "api_key.txt";

    public static void CheckAPIKey() throws IOException {
        String apiKey = readApiKey();
        if (apiKey == null) {
            // If API key doesn't exist, create one
            apiKey = HttpConnection.HttpInitialPOSTRequest();
            saveApiKey(apiKey);
            System.out.println("New API key created and saved.");
        } else {
            System.out.println("API key found: " + apiKey);
        }

        // Example of updating the API key
        // updateApiKey("NEW_API_KEY");
    }

    private static String readApiKey() {
        try (BufferedReader reader = new BufferedReader(new FileReader(API_KEY_FILE_PATH))) {
            return reader.readLine();
        } catch (IOException e) {
            // File doesn't exist or unable to read
            return null;
        }
    }

    private static void saveApiKey(String apiKey) {
        File file = new File(API_KEY_FILE_PATH);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(apiKey);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to update the API key
    private static void updateApiKey(String newApiKey) {
        saveApiKey(newApiKey);
        System.out.println("API key updated to: " + newApiKey);
    }
}

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class HttpConnection {
    static String token = "";

    public static String HttpInitialPOSTRequest() {
        String output = "null";

        try {
        // URL of the API endpoint
        String url = "https://api.trackmania.com/api/access_token";

        // Form data
        String formData = "grant_type=client_credentials" +
                "&client_id=e09d25200a2e430810d1" +
                "&client_secret=" + System.getenv("APISecret");

        // Convert form data to bytes
        byte[] postData = formData.getBytes(StandardCharsets.UTF_8);

        // Set up the HTTP connection
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        connection.setDoOutput(true);

        // Write the form data to the connection
        try (DataOutputStream wr = new DataOutputStream(connection.getOutputStream())) {
            wr.write(postData);
        }

        // Read the response
        try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            System.out.println(response.toString());
            output = response.toString();

            JSONObject obj = new JSONObject(response.toString());
            token = obj.getString("access_token");
            System.out.println(output);
            System.out.println("API Key: " + token);

        }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return output;
    }

    public static JSONObject HttpGETCampaign(){
        JSONObject obj = null;

        try {
            // Specify the URL
            URL url = new URL("https://meet.trackmania.nadeo.club/api/cup-of-the-day/current");

            // Open a connection
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("Authorization", "Bearer " + token);
            // Set request method to GET
            //connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            //connection.setRequestProperty("Accept", "application/json");
            connection.setRequestMethod("GET");

            // Set Bearer token
            //connection.setAuthenticator();

            // Get the response code
            int responseCode = connection.getResponseCode();
            System.out.println("Response Code: " + responseCode);

            // Read the response
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // Print the response
            System.out.println("Response:");
            System.out.println(response.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return obj;
    }
}

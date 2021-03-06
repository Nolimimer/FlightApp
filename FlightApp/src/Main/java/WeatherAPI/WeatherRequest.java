package WeatherAPI;

import org.json.JSONObject;

import java.net.URL;
import java.util.Scanner;

public class WeatherRequest {

    private String city;

    public WeatherRequest(String city) {
        this.city = city;
    }

    private String urlBuilder () throws Exception {
        String s = null;
        if (this.city.equals("München")) {
            s = "https://api.openweathermap.org/data/2.5/forecast?q=Munich&APPID=39b1bc038434e7c0b3db7e4b96a6afb9";
        } else {
            s = "https://api.openweathermap.org/data/2.5/forecast?q=" + this.city + "&APPID=39b1bc038434e7c0b3db7e4b96a6afb9";
        }

        return urlReader(new URL(s));
    }

    private String urlReader (URL url) throws Exception {
        Scanner scan = new Scanner(url.openStream());
        String output = new String();
        while (scan.hasNext())
            output += scan.nextLine();
        scan.close();

        return output;
    }

    public String[] getIcon () throws Exception {
        String url = urlBuilder();
        JSONObject obj = new JSONObject(url);

        JSONObject today = obj.getJSONArray("list").getJSONObject(0); // Weather Today
        JSONObject tomorrow = obj.getJSONArray("list").getJSONObject(8); // Weather tomorrow
        JSONObject future = obj.getJSONArray("list").getJSONObject(16); // Weather in two days

        String[] icons = new String[3];
        icons[0] = today.getJSONArray("weather").getJSONObject(0).getString("icon");
        icons[1] = tomorrow.getJSONArray("weather").getJSONObject(0).getString("icon");
        icons[2] = future.getJSONArray("weather").getJSONObject(0).getString("icon");

        for (int i = 0; i < icons.length; i++) {
            icons[i] = isDay(icons[i]);
        }

        return icons;
    }

    public int[] getTemp () throws Exception {
        String url = urlBuilder();
        JSONObject obj = new JSONObject(url);

        JSONObject today = obj.getJSONArray("list").getJSONObject(0); // Temp Today
        JSONObject tomorrow = obj.getJSONArray("list").getJSONObject(8); // Temp Tomorrow
        JSONObject future = obj.getJSONArray("list").getJSONObject(16); // Temp in two days

        int[] temperatures = new int[3];
        temperatures[0] = tempConverter(today.getJSONObject("main").getInt("temp"));
        temperatures[1] = tempConverter(tomorrow.getJSONObject("main").getInt("temp"));
        temperatures[2] = tempConverter(future.getJSONObject("main").getInt("temp"));

        return temperatures;
    }

    public String isDay (String icon) {
        return icon.substring(0,2)+'d'+icon.substring(3);
    }

    private int tempConverter(int kelvin) {
        return kelvin - 273;
    }

}
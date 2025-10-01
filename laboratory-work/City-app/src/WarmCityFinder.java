import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

public class WarmCityFinder {
    final static String API_URL = "http://api.openweathermap.org/data/2.5/weather?q=%s&appid=d6843ab8ee963f5d372296dfff62aed7";

    private static final String[] CITIES = {
            "Moscow", "Sochi", "Krasnodar", "Yalta", "Anapa",
            "Gelendzhik", "Adler", "Tuapse", "Novorossiysk", "Yeysk"
    };

    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("=== Поиск самых теплых городов ===\n");

        // Первая попытка без Иркутска
        System.out.println("ПОПЫТКА 1: Без Иркутска");
        findWarmestCities(false);

        System.out.println("\n" + "=".repeat(50) + "\n");


        System.out.println("ПОПЫТКА 2: С Иркутском");
        findWarmestCities(true);
    }

    private static void findWarmestCities(boolean includeIrkutsk) throws InterruptedException {
        List<String> citiesToCheck = new ArrayList<>(Arrays.asList(CITIES));

        if (includeIrkutsk) {
            citiesToCheck.add("Irkutsk");
            System.out.println("Добавлен Иркутск в список для проверки");
        }

        List<WeatherThread> threads = new ArrayList<>();
        List<Weather> weatherData = new ArrayList<>();

        for (String city : citiesToCheck) {
            WeatherThread thread = new WeatherThread(String.format(API_URL, city));
            thread.start();
            threads.add(thread);
        }

        for (WeatherThread thread : threads) {
            thread.join();
            if (thread.weather != null) {
                weatherData.add(thread.weather);
            }
        }

        weatherData.sort((w1, w2) -> Integer.compare(w2.getTemperatureCelsius(), w1.getTemperatureCelsius()));

        System.out.println("\nРейтинг городов по температуре (от самых теплых к холодным):");
        System.out.println("-".repeat(50));

        for (int i = 0; i < weatherData.size(); i++) {
            System.out.printf("%2d. %s\n", i + 1, weatherData.get(i));
        }

        System.out.println("\nВсего обработано городов: " + weatherData.size());
    }

    static class WeatherThread extends Thread {
        String url;
        Weather weather;

        public WeatherThread(String url) {
            this.url = url;
        }

        @Override
        public void run() {
            try {
                URL weather_url = new URL(url);
                InputStream stream = (InputStream) weather_url.getContent();
                Gson gson = new Gson();
                weather = gson.fromJson(new InputStreamReader(stream), Weather.class);
            } catch (IOException e) {
                System.out.println("Ошибка при запросе: " + url);
            }
        }
    }
}
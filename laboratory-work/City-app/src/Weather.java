import com.google.gson.annotations.SerializedName;

class MainData {
    double temp;

    public double getTemperature() {
        return temp;
    }
}

public class Weather {

    @SerializedName("name")
    private String cityName;

    @SerializedName("main")
    private MainData main;

    public String getCityName() { return cityName; }

    public double getTemperature() {
        return main != null ? main.getTemperature() : 0;
    }

    public int getTemperatureCelsius() {
        return (int)(getTemperature() - 273.15);
    }

    @Override
    public String toString() {
        return String.format("%s: %d°C", getCityName(), getTemperatureCelsius());
    }

}



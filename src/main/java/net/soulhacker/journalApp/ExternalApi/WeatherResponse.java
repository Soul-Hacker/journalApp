package net.soulhacker.journalApp.ExternalApi;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class WeatherResponse {
    private Current current;

    public class Current{
        private int temperature;
        @JsonProperty("weather_description")
        private List<String> weatherDescriptions;
        private int feelslike;


    }
}

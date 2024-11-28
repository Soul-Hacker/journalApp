package net.soulhacker.journalApp.service;

import net.soulhacker.journalApp.ExternalApi.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Component
public class WeatherService {
//    @Value("${weather.api.key}")
    private static final String apikey="b4e805d9fb9f0d7fb14d0ae4cccc2850";
     private static final String API="http://api.weatherstack.com/current?access_key=API_KEY&query=CITY";

 @Autowired
 private RestTemplate Resttemplate;
 @Autowired
 private RedisService redisService;
    public WeatherResponse getWeather(String city)
    {
        WeatherResponse weatherResponse = redisService.get("weather_of_" + city, WeatherResponse.class);
        String finalApi=API.replace("CITY",city).replace("API_KEY",apikey);
        ResponseEntity<WeatherResponse> response = Resttemplate.exchange(finalApi, HttpMethod.GET, null, WeatherResponse.class);

        WeatherResponse body=response.getBody();
        return body;
    }
}

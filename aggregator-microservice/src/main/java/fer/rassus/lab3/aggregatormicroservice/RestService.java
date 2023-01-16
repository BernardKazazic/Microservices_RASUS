package fer.rassus.lab3.aggregatormicroservice;

import fer.rassus.lab3.aggregatormicroservice.DTO.AggregationDTO;
import fer.rassus.lab3.aggregatormicroservice.DTO.HumidityDTO;
import fer.rassus.lab3.aggregatormicroservice.DTO.TemperatureDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Service
@RefreshScope
public class RestService {
    private final RestTemplate restTemplate;
    @Autowired
    private DiscoveryClient discoveryClient;
    @Value("${temperature-unit}")
    private String temperatureUnit;

    public RestService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public AggregationDTO getAggregatedData() {
        String temperatureUrl = this.discoveryClient.getInstances("TEMPERATURE").get(0).getUri().toString();
        String humidityUrl = this.discoveryClient.getInstances("HUMIDITY").get(0).getUri().toString();
        TemperatureDTO temperatureData = adjustTemperatureUnit(Objects.requireNonNull(this.restTemplate.getForObject(temperatureUrl, TemperatureDTO.class)));
        HumidityDTO humidityData = this.restTemplate.getForObject(humidityUrl, HumidityDTO.class);

        AggregationDTO aggregationData = new AggregationDTO();
        aggregationData.setTemperatureData(temperatureData);
        aggregationData.setHumidityData(humidityData);
        return aggregationData;
    }

    private TemperatureDTO adjustTemperatureUnit(TemperatureDTO temperatureData) {
        if(temperatureData.getUnit().equals(this.temperatureUnit)) {
            return temperatureData;
        }
        else if(this.temperatureUnit.equals("C")) {
            temperatureData.setValue(kelvinToCelsius(temperatureData.getValue()));
            temperatureData.setUnit("C");
            return temperatureData;
        }
        else {
            temperatureData.setValue(celsiusToKelvin(temperatureData.getValue()));
            temperatureData.setUnit("K");
            return temperatureData;
        }
    }

    private String celsiusToKelvin(String temperature) {
        double longTemperature = Double.parseDouble(temperature);
        longTemperature += 273.15;
        return Double.toString(longTemperature);
    }

    private String kelvinToCelsius(String temperature) {
        double longTemperature = Double.parseDouble(temperature);
        longTemperature -= 273.15;
        return Double.toString(longTemperature);
    }
}

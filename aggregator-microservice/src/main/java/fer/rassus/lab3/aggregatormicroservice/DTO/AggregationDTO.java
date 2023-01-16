package fer.rassus.lab3.aggregatormicroservice.DTO;

public class AggregationDTO {
    private TemperatureDTO temperatureData;
    private HumidityDTO humidityData;

    public TemperatureDTO getTemperatureData() {
        return temperatureData;
    }

    public void setTemperatureData(TemperatureDTO temperatureData) {
        this.temperatureData = temperatureData;
    }

    public HumidityDTO getHumidityData() {
        return humidityData;
    }

    public void setHumidityData(HumidityDTO humidityData) {
        this.humidityData = humidityData;
    }
}

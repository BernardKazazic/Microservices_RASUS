package fer.rassus.lab3.temperaturemicroservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TemperatureServiceImpl implements TemperatureService {

    @Autowired
    private ReadingsRepository readingsRepository;

    @Override
    public TemperatureDTO getTemperature() {
        Long time = System.currentTimeMillis() / 1000;
        Optional<Readings> optionalReading = readingsRepository.findById(time % 100 + 1);
        TemperatureDTO temperatureDTO = new TemperatureDTO();
        temperatureDTO.setName("Temperature");
        temperatureDTO.setUnit("C");

        if(optionalReading.isEmpty()) {
            return temperatureDTO;
        }

        Readings reading = optionalReading.get();
        temperatureDTO.setValue(reading.getTemperature());
        return temperatureDTO;
    }
}

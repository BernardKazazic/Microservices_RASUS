package fer.rassus.lab3.humiditymicroservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HumidityServiceImpl implements HumidityService {

    @Autowired
    private ReadingsRepository readingsRepository;

    @Override
    public HumidityDTO getHumidity() {
        Long time = System.currentTimeMillis() / 1000;
        Optional<Readings> optionalReading = readingsRepository.findById(time % 100 + 1);
        HumidityDTO humidityDTO = new HumidityDTO();
        humidityDTO.setName("Humidity");
        humidityDTO.setUnit("%");

        if(optionalReading.isEmpty()) {
            return humidityDTO;
        }

        Readings reading = optionalReading.get();
        humidityDTO.setValue(reading.getHumidity());
        return humidityDTO;
    }
}

package fer.rassus.lab3.temperaturemicroservice;

import org.springframework.stereotype.Service;

@Service
public interface TemperatureService {

    TemperatureDTO getTemperature();
}

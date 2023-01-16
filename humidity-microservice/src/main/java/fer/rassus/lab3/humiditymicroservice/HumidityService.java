package fer.rassus.lab3.humiditymicroservice;

import org.springframework.stereotype.Service;

@Service
public interface HumidityService {

    HumidityDTO getHumidity();
}

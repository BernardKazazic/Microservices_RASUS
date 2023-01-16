package fer.rassus.lab3.humiditymicroservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HumidityController {

    @Autowired
    HumidityService humidityService;

    @GetMapping("")
    public ResponseEntity<HumidityDTO> getTemperature() {
        return ResponseEntity.ok().body(humidityService.getHumidity());
    }
}

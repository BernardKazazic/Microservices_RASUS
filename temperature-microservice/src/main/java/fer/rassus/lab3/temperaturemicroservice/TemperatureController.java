package fer.rassus.lab3.temperaturemicroservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class TemperatureController {

    @Autowired
    TemperatureService temperatureService;

    @GetMapping("")
    public ResponseEntity<TemperatureDTO> getTemperature() {
        return ResponseEntity.ok().body(temperatureService.getTemperature());
    }
}

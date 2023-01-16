package fer.rassus.lab3.aggregatormicroservice;

import fer.rassus.lab3.aggregatormicroservice.DTO.AggregationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/aggregation")
public class AggregationController {
    @Autowired
    RestService restService;

    @GetMapping("")
    public ResponseEntity<AggregationDTO> getAggregatedData() {
        return ResponseEntity.ok().body(restService.getAggregatedData());
    }
}

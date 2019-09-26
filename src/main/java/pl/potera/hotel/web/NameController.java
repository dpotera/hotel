package pl.potera.hotel.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
@RequestMapping("/name")
public class NameController {

    @Value("${hotel.name:default name}")
    private String hotelName;

    @GetMapping
    public String hotelName() {
        return hotelName;
    }
}

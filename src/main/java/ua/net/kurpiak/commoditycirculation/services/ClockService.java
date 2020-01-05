package ua.net.kurpiak.commoditycirculation.services;

import java.time.LocalDateTime;
import java.time.ZoneId;

import org.springframework.stereotype.Component;

@Component
public class ClockService {

    private static final ZoneId SERVER_ZONE = ZoneId.systemDefault();

    public LocalDateTime getLocalDateTime() {
        return LocalDateTime.now(SERVER_ZONE);
    }

}

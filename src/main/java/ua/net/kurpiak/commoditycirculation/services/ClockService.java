package ua.net.kurpiak.commoditycirculation.services;

import java.time.LocalDate;
import java.time.ZoneId;

import org.springframework.stereotype.Component;

@Component
public class ClockService {

    private static final ZoneId SERVER_ZONE = ZoneId.systemDefault();

    public LocalDate getLocalDate() {
        return LocalDate.now(SERVER_ZONE);
    }

}

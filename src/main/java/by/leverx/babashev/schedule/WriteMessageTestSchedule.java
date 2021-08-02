package by.leverx.babashev.schedule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@Component
public class WriteMessageTestSchedule {

    @Scheduled(cron = "0 */1 15-16 * * 1-5", zone = "Europe/Moscow")
    public void scheduleTask() {
        log.info("Hallo! I'm Spring-Scheduling! I'm greeting you! Time is: " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
    }
}

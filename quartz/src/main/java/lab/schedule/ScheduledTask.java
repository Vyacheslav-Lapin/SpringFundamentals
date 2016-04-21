package lab.schedule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ScheduledTask {

    @Scheduled(fixedDelay = 5000)
    public void doSomething() {
        log.info("Appending log message into ScheduleLog ...");
        ScheduleLog.append("I'm printing job...\n");
    }
}

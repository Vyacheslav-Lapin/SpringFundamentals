package scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author Vyacheslav Lapin (http://vlapin.ru/)
 * @version 0.1 (22.04.2015 0:36).
 */
@Component
public class Println {
    @Scheduled(cron = "*&#47;0 * * * * *")
    public void print(){
        System.out.println("Я всё ещё жив! " + System.currentTimeMillis());
    }
}

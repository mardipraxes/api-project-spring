package mindswap.academy.app.config;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.ContextStoppedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SetupDataLoader {

    @Autowired
    private DataLoader dataLoader;


    private boolean loaded = false;

    @EventListener(classes = {ContextStartedEvent.class})
    public void applicationStartOperation() {
        System.out.println("App started...");
        dataLoader.loadUserData();
        dataLoader.loadNewsData();

    }

    @EventListener(classes = {ContextStoppedEvent.class, ContextRefreshedEvent.class})
    public void onApplicationEvent() {
        log.info("Event received");
       if (!loaded) {
            log.info("Loading data...");
          dataLoader.loadUserData();
          dataLoader.loadNewsData();
          loaded = true;
        }
    }
}

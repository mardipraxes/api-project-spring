package mindswap.academy.app.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.ContextStoppedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class SetupDataLoader {

    @Autowired
    private UserDataLoader userDataLoader;



    private boolean loaded = false;

    @EventListener(classes = {ContextStartedEvent.class})
    public void applicationStartOperation() {
        System.out.println("App started...");
        userDataLoader.loadData();

    }

    @EventListener(classes = { ContextStoppedEvent.class, ContextRefreshedEvent.class })
    public void onApplicationEvent() {
        System.out.println("ContextStoppedEvent or ContextRefreshedEvent");
//        if (!loaded) {
//            userDataLoader.loadData();
//            loaded = true;
//        }
    }
}

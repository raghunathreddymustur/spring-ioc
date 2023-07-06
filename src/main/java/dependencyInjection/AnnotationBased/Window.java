package dependencyInjection.AnnotationBased;

import org.springframework.stereotype.Component;

@Component
public class Window {
    public Window() {
        System.out.println("Window made");
    }
    public void getWindow(){
        System.out.println("Got the window");
    }
}

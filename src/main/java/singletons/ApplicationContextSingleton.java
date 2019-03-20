package singletons;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationContextSingleton {

    private static ClassPathXmlApplicationContext instance;

    public static ClassPathXmlApplicationContext getInstance() {
        if (instance == null) {
            instance = new ClassPathXmlApplicationContext("spring-config.xml");
        }
        return instance;
    }
}

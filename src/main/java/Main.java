import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import screens.LoginScreen;
import screens.RegisterScreen;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello!");

        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");

//        RegisterScreen registerScreen =  context.getBean(RegisterScreen.class);
//        registerScreen.registerUser();

//        LoginScreen loginScreen = context.getBean(LoginScreen.class);
//        loginScreen.startLogging();
    }
}

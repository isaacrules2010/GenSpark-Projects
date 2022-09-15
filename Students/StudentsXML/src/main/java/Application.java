import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Application {
    public static void main(String[] args) {
        AbstractApplicationContext context = new ClassPathXmlApplicationContext("studentsConfig.xml");

        Student student = (Student)context.getBean("Student");
        System.out.println(student);
    }
}

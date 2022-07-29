package school21.spring.service.application;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import school21.spring.service.models.User;
import school21.spring.service.repositories.UsersRepository;


public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        UsersRepository [] usersRepositorys = new UsersRepository[2];
        usersRepositorys[0] = context.getBean("usersRepositoryJdbc", UsersRepository.class);
        usersRepositorys[1] = context.getBean("usersRepositoryJdbcTemplate", UsersRepository.class);
        for (UsersRepository usersRepository : usersRepositorys) {
            System.out.println(usersRepository.findAll());
        }
        for (UsersRepository usersRepository : usersRepositorys) {
            System.out.println(usersRepository.findById(1L));
        }
        for (UsersRepository usersRepository : usersRepositorys) {
            System.out.println(usersRepository.findByEmail("darth_vader@empire.gov"));
        }
        for (UsersRepository usersRepository : usersRepositorys) {
            System.out.println(">  save/update/delete  <");
            User newUser = new User(null, "darth_maul@empire.gov");
            System.out.println(newUser);
            usersRepository.save(newUser);
            System.out.println(usersRepository.findAll());
            newUser = usersRepository.findByEmail("darth_maul@empire.gov").get();
            newUser.setEmail("kod@loh.com");
            usersRepository.update(newUser);
            System.out.println(usersRepository.findAll());
            usersRepository.delete(newUser.getIdentifier());
            System.out.println(usersRepository.findAll());
        }
    }
}

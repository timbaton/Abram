import services.UserService;

public class Main {

    public static void main(String[] args) {
        UserService userService = new UserService();
        userService.entryUser();
//        UsersDao dao = new UsersDao();
//        System.out.println(dao.findByLogin("ainaard").get(0));

        userService.entryUser();

    }
}

package demo.function.stream;

import demo.function.stream.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StreamDemo {

    private static List<User> getUserData() {
        Random random = new Random();
        List<User> users = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            User user = new User();
            user.setUserId(i);
            user.setUserName(String.format("古时的风筝 %s 号", i));
            user.setAge(random.nextInt(100));
            user.setGender(i % 2);
            user.setPhone("18812021111");
            user.setAddress("无");
            users.add(user);
        }
        return users;
    }

    public static void main(String[] args) {
        List<User> users = getUserData();
        users.stream().filter(user -> user.getAge()>50&&user.getGender()==0).forEach(user -> System.out.println(user.getUserName()));
    }
}

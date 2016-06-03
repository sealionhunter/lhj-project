package lhj.learn.resteasy;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class UserDatabase {
    
    private static Map<Integer, User> users = new HashMap<>(); 
    
    static {
        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setId(i).setName("name " + "i").setUri("uri").setLastModified(new Date());
            users.put(i, user );
        }
    }

    public static User getUserById(int id) {
        return users.get(id);
    }

    public static void updateUser(int id) {
    }

}

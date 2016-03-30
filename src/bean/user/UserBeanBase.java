package bean.user;

import javax.annotation.PreDestroy;

import model.User;

import utils.DBManager;

public class UserBeanBase implements UserCommonBusiness {

    protected User user;
    protected DBManager dbm;
    protected String message;
    protected String state;

    public UserBeanBase() {
        System.out.println("Constructing a question...");


        // Initialize db manager and other fields
        dbm = new DBManager();
        user = new User();


//        System.out.println("Id be interested to know");
//
//        user.setName("testuser");
//        user.setEmail("email@test.com");
//        user.setRole("Admin");
//        user.setPassword("password");
//
//        User result = dbm.getUserByEmail("email@test.com");
//        System.out.println("Quiz name is: " + result.getName());
//        if (result == null) { dbm.commitUser(user); }


        // Store it


    }

    public boolean commitUser() {
        return null != dbm.commitUser(user);
    }

    public boolean userLogIn(String email, String salt){
        return dbm.userLogIn(email, salt);
    }
    @Override
    public void setName(String name){
        user.setName(name);
    }

    @Override
    public void setEmail(String email){
        user.setEmail(email);
    }

    @Override
    public String getEmail(){ return user.getEmail(); }

    @Override
    public void setPassword(String password){
        user.setPassword(password);
    }

    @Override
    public void setRole(String role){
        user.setRole(role);
    }

    @Override
    public String getRole() { return user.getRole(); }

    public String getMessage() { return message; }

    public void setMessage(String error) { message = error; }

    public boolean isRegistered(String email) {
        return null != dbm.getUserByEmail(email);
    }
    @PreDestroy
    public void destroy() {
        dbm.close();
    }
}
package bean.user;

import model.User;


public interface UserCommonBusiness {

    void setName(String name);
    void setEmail(String email);
    void setRole(String role);
    void setPassword(String password);
    String getEmail();
    String getRole(String email);
    String getName();

}

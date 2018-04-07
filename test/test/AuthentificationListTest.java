package test;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import src.AuthentificationList;

public class AuthentificationListTest {

    AuthentificationList authentificationList;
    @Before public void init(){
        authentificationList = new AuthentificationList();
    }

    @Test public void addUserTest(){
        Assert.assertEquals(authentificationList.getArrayList().size(), 0);
        authentificationList.addUser("1", "2");
        Assert.assertEquals(authentificationList.getArrayList().size(), 1);

        Assert.assertEquals(authentificationList.getArrayList().get(0).getPassword(), "2");//get the user
        Assert.assertEquals(authentificationList.getArrayList().get(0).getUsername(), "1");//get the user

    }
    @Test public void removeUserTest(){
        authentificationList.addUser("1", "2");
        authentificationList.removeUser(0);
        Assert.assertEquals(authentificationList.getArrayList().size(), 0);
    }

    @After public void destroy(){
        authentificationList.getArrayList().clear(); // erase the static members

    }
}

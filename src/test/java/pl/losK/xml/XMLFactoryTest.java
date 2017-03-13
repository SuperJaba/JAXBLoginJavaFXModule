package pl.losK.xml;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pl.losK.model.User;

/**
 * Created by m.losK on 2017-03-13.
 */
public class XMLFactoryTest {

    private String login;
    private String password;
    private String xmlUser;

    public XMLFactoryTest() {
    }

    @Before
    public void setUp() {
        login = "user1234";
        password = "abcd1234";
        xmlUser = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><User><login>user1234</login><password>abcd1234</password></User>";
    }

    @Test
    public void objectToXml() {
        XMLFactory<User> xmlFactory = new XMLFactory<>(User.class);
        User user = new User(login, password);
        String xmlUserResult = xmlFactory.objectToXml(user);
        Assert.assertTrue(xmlUserResult.equals(xmlUser));
    }


    @Test
    public void xmlObject() {
        XMLFactory<User> xmlFactory = new XMLFactory<>(User.class);
        User userFromXml = xmlFactory.xmlToObject(xmlUser);
        User user = new User(login, password);
        Assert.assertTrue(user.equals(userFromXml));
    }
}

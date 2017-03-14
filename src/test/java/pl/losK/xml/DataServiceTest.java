package pl.losK.xml;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pl.losK.model.User;
import pl.losK.service.DataService;


/**
 * Created by m.losK on 2017-03-14.
 */
public class DataServiceTest {
    private DataService dataService;
    private String xmlString = "<?xml version=\"1.0\" encoding=\"UTF-8\" " +
            "standalone=\"yes\"?><User><login>user1234</login><password>password1234</password></User>";
    private User user;
    private User userXML;

    public DataServiceTest() {
    }

    @Before
    public void setUp() {
        dataService = new DataService();
        userXML = new User("user1234", "password1234");
    }

    public void loadData() {
        user = dataService.loadData();
    }

    public void saveData() {
        dataService.saveData(xmlString);
    }

    @Test
    public void saveAndLoadData() {
        saveData();
        loadData();
        Assert.assertTrue(user.equals(userXML));
    }
}
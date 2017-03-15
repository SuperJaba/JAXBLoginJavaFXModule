package pl.losK.xml;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pl.losK.model.Address;
import pl.losK.model.Company;
import pl.losK.model.User;
import pl.losK.service.DataService;

import java.util.List;


/**
 * Created by m.losK on 2017-03-14.
 */
public class DataServiceTest {
    private DataService dataService;
    private String xmlString = "<?xml version=\"1.0\" encoding=\"UTF-8\" " +
            "standalone=\"yes\"?><User><login>user1234</login><password>password1234</password></User>";
    private User user;
    private User userXML;
    private Company company;

    public DataServiceTest() {
    }

    @Before
    public void setUp() {
        dataService = new DataService();
        userXML = new User("user1234", "password1234");
        company = new Company();
        company.setName("Biedronka");
        company.setAddress(new Address(Address.StreetPrefix.STREET, "Baraniaka", "88e",
                "4", "60-600", "Poznan", "Poland"));
        company.setNip("222-22-22");
        company.setRegon("232323");
    }

    //instead of working on files use Mockito
    public void loadData() {
        user = dataService.loadData();
    }

    public void saveData() {
        dataService.saveData(xmlString);
    }

    //instead of working on files use Mockito
    @Test
    public void saveAndLoadData() {
        saveData();
        loadData();
        Assert.assertTrue(user.equals(userXML));
    }

    @Test
    public void printCompanyInf(){
        List<String> companyInfo = dataService.printCompanyInfo(company);
        boolean resultAssert = true;
        resultAssert &= companyInfo.get(0).equals("Biedronka");
        resultAssert &= companyInfo.get(1).equals("ul.Baraniaka 88e/4\n60-600 Poznan");
        resultAssert &= companyInfo.get(2).equals("222-22-22");
        resultAssert &= companyInfo.get(3).equals("232323");
        Assert.assertTrue(resultAssert);
    }
}
package pl.losK.service;

import pl.losK.model.User;

import java.io.File;

import static pl.losK.service.DataService.loadProperties;

/**
 * Created by m.losK on 2017-03-13.
 */
public class UserService {
    public UserService() {
    }

    public boolean authenticate(User user) {
        String filePath = loadProperties().getProperty("resourcesPath");
        DataService dataService = new DataService(filePath + File.separator + "dataUser.dat");
        User userFromFile = dataService.loadData();
        return user.equals(userFromFile);
    }
}

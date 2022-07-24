package manager;

import models.User;
import org.testng.annotations.DataProvider;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MyDataProvider {

    @DataProvider
    public Iterator<Object[]> dataLogin() {
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{"viktor@gmail.com", "Vviktor12345$"});
        list.add(new Object[]{"viktor@gmail.com", "Vviktor12345$"});
        list.add(new Object[]{"viktor@gmail.com", "Vviktor12345$"});
        list.add(new Object[]{"viktor@gmail.com", "Vviktor12345$"});
        return list.iterator();
    }

    @DataProvider
    public Iterator<Object []>dataRegistration(){
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{new User().setName("Doroty0").setLastName("Dor").setEmail("dory0@gmail.com").setPassword("Dd012345$")});
        list.add(new Object[]{new User().setName("Doroty1").setLastName("Dor").setEmail("dory1@gmail.com").setPassword("Dd112345$")});
        list.add(new Object[]{new User().setName("Doroty2").setLastName("Dor").setEmail("dory2@gmail.com").setPassword("Dd212345$")});
        list.add(new Object[]{new User().setName("Doroty3").setLastName("Dor").setEmail("dory3@gmail.com").setPassword("Dd312345$")});
        return list.iterator();
    }

    @DataProvider
    public Iterator<Object []>LoginCSV() throws IOException {
        List<Object[]> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(new File("src\\test\\resources\\Login - Sheet1.csv")));
        String line = reader.readLine();
        while (line!=null) {
            String[] split = line.split(",");
            list.add(new Object[]{new User().setEmail(split[0]).setPassword(split[1])});
            line=reader.readLine();
        }
        return list.iterator();
    }
}

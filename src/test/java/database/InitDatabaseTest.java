package database;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

public class InitDatabaseTest {

    /*
        Test if program is starting correctly without Tomcat
     */
    @Test
    public void getConnect() {
        InitDatabase.setPathDbProperty("");
        assertThrows(FileNotFoundException.class, InitDatabase::getConnection);

        String fake = "fakeString";
        InitDatabase.setDbConfiguration(fake,fake, fake, fake);
        try {
            assertNull(InitDatabase.getConnection());
        } catch (FileNotFoundException exception) {
            fail();
        }
    }

    /*
    Test if program is starting correctly with setting path
     */
    @Test
    public void getConnectWithoutTomcat()  {
        InitDatabase.setPathDbProperty("src/main/resources/db.properties");
        assertDoesNotThrow(InitDatabase::getConnection);
    }

    @Test
    public void gettersSetters() {
        String defaultPath = "/null/webapps/resty_com_war//WEB-INF/classes";
        assertEquals(defaultPath, InitDatabase.getPathToPropertyFolder());

        String message = "hi";
        InitDatabase.setPathToPropertyFolder("hi");
        assertEquals(message, InitDatabase.getPathToPropertyFolder());

        InitDatabase.setPathDbProperty(message);
        assertEquals(message, InitDatabase.getPathDbProperty());
    }
}

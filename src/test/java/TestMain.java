import org.junit.Assert;
import org.junit.Test;
import ru.efomenko.Main;

import java.io.IOException;

import static org.junit.Assert.assertEquals;


public class TestMain {
    @Test
    public  void test() throws IOException {

        assertEquals(Main.calc("i + iii"),"IV");
        assertEquals(Main.calc("X + IX"),"XIX");
        assertEquals(Main.calc("V * V"),"XXV");
        assertEquals(Main.calc("V * VI"),"XXX");
        assertEquals(Main.calc("V * VII"),"XXXV");
        assertEquals(Main.calc("VI / III"),"II");
        assertEquals(Main.calc("1 + 2"),"3");
        assertEquals(Main.calc("1 - 1"),"0");
        assertEquals(Main.calc("1 * 1"),"1");
        assertEquals(Main.calc("1 / 1"),"1");
        Assert.assertThrows(IOException.class,()->Main.calc("i + 1"));
        Assert.assertThrows(IOException.class,()->Main.calc("1 + i"));
        Assert.assertThrows(IOException.class,()->Main.calc("I - II"));
        Assert.assertThrows(IOException.class,()->Main.calc("I + II + III"));
        Assert.assertThrows(IOException.class,()->Main.calc("1 + 2 + 3"));
        Assert.assertThrows(IOException.class,()->Main.calc("I II"));
        Assert.assertThrows(IOException.class,()->Main.calc(" "));
        Assert.assertThrows(IOException.class,()->Main.calc(" 15 + 2"));
        Assert.assertThrows(IOException.class,()->Main.calc("1"));

    }
}

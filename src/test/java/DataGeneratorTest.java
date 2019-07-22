import java.io.File;
import org.junit.Assert;
import org.junit.Test;

public class DataGeneratorTest {
    private static final String FILENAME = "/tmp/data.json";

    @Test
    public void whenGenerateDataJson_thenFileExists() throws Exception {
        DataGenerator.generateJsonToFile(FILENAME);
        File file = new File(FILENAME);
        Assert.assertTrue(file.exists());
        Assert.assertTrue(file.delete());
    }

    @Test
    public void whenRemoveJsonFile_thenFileDoesNotExist() throws Exception {
        DataGenerator.generateJsonToFile(FILENAME);
        DataGenerator.deleteFile(FILENAME);
        File file = new File(FILENAME);
        Assert.assertFalse(file.exists());
    }
}

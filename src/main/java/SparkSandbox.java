import lombok.extern.slf4j.Slf4j;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.laser.metric.Metric;
import org.laser.pretty.Pretty;

@Slf4j
public class SparkSandbox {
    private static final String DATA_FILE_PATH = "/tmp/data.json";

    public static void main(String[] args) {
        generateJsonFile();
        long elapsed = Metric.elapsedMillis(SparkSandbox::printDataUsingSpark);
        removeJsonFile();
        printElapsed(elapsed);
    }

    private static void printDataUsingSpark() {
        SparkSession spark = SparkSession.builder()
            .appName("SparkSandbox")
            .master("local")
            .getOrCreate();
        Dataset<Row> data = spark.read()
            .option("multiline", true)
            .json(DATA_FILE_PATH);
        data.printSchema();
        data.show();
        spark.stop();
    }

    private static void generateJsonFile() {
        try {
            DataGenerator.generateJsonToFile(DATA_FILE_PATH);
        } catch (Exception e) {
            log.error("Failed to create {}", DATA_FILE_PATH);
        }
    }

    private static void removeJsonFile() {
        try {
            DataGenerator.deleteFile(DATA_FILE_PATH);
        } catch (Exception e) {
            log.error("Failed to remove {}", DATA_FILE_PATH);
        }
    }

    private static void printElapsed(long elapsed) {
        String format = "printDataUsingSpark took %s milliseconds";
        String msg = String.format(format, elapsed);
        log.info(Pretty.yellow(msg));
    }
}

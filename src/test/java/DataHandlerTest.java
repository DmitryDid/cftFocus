
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class DataHandlerTest {

    @Test
    public void writeDataToFile() throws IOException {
        ArrayList expected = new ArrayList<>(Arrays.asList("write", "data", "to", "File"));
        DataHandler.writeDataToFile(expected, "src\\test\\resources\\out.txt");
        ArrayList actual = DataHandler.readDataFromFile("src\\test\\resources\\out.txt");
        assertEquals(expected, actual);
    }

    @Test
    public void readDataFromFilePathEqualsNull() throws IOException {
        ArrayList<String> expectedNull = DataHandler.readDataFromFile(null);
        assertNull(expectedNull);
    }

    @Test
    public void readDataFromFile() throws IOException {
        ArrayList<String> actual = DataHandler.readDataFromFile("src\\test\\resources\\in1.txt");
        ArrayList<String> expected = new ArrayList<>(Arrays.asList("1", "4", "6", "8", "9", "14", "57", "89"));
        assertEquals(expected, actual);
    }

    @Test
    public void readDataFromFileLineContainsSpace() throws IOException {
        ArrayList<String> actual = DataHandler.readDataFromFile("src\\test\\resources\\inLCS.txt");
        ArrayList<String> expected = new ArrayList<>(Arrays.asList("1", "2", "3", "4", "5", "6"));
        assertEquals(expected, actual);
    }

    @Test
    public void getParameters1() throws ParameterNotFoundException {
        String[] args = new String[]{"-i", "-a", "src/test/resources/out.txt", "in.txt"};
        HashMap<String, String> parameters = DataHandler.getParameters(args);
        assertEquals("-i", parameters.get("type"));
        assertEquals("-a", parameters.get("mode"));
        assertEquals("src/test/resources/out.txt", parameters.get("out"));
        assertEquals("in.txt", parameters.get("in1"));
    }

    @Test
    public void getParameters2() throws ParameterNotFoundException {
        String[] args = new String[]{"-s", "src/test/resources/out.txt", "src/test/resources/in1.txt", "src/test/resources/in2.txt", "src/test/resources/in3.txt"};
        HashMap<String, String> parameters = DataHandler.getParameters(args);
        assertEquals("-s", parameters.get("type"));
        assertEquals("-a", parameters.get("mode"));
        assertEquals("src/test/resources/out.txt", parameters.get("out"));
        assertEquals("src/test/resources/in1.txt", parameters.get("in1"));
        assertEquals("src/test/resources/in2.txt", parameters.get("in2"));
        assertEquals("src/test/resources/in3.txt", parameters.get("in3"));
    }

    @Test
    public void getParameters3() throws ParameterNotFoundException {
        String[] args = new String[]{"-d", "-s", "src/test/resources/out.txt", "src/test/resources/in1.txt", "src/test/resources/in2.txt"};
        HashMap<String, String> parameters = DataHandler.getParameters(args);
        assertEquals("-s", parameters.get("type"));
        assertEquals("-d", parameters.get("mode"));
        assertEquals("src/test/resources/out.txt", parameters.get("out"));
        assertEquals("src/test/resources/in1.txt", parameters.get("in1"));
        assertEquals("src/test/resources/in2.txt", parameters.get("in2"));
    }

    @Test
    public void separateData() {
        ArrayList<String> dirtyData = new ArrayList<>(Arrays.asList("1", "2", " ", "3", "4", "5", "fdgfdg", "6", "7", "8", "9", "43534 4456", "10"));
        ArrayList<String> clearedData = new ArrayList<>(Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10"));
        DataHandler.separateData(dirtyData);
        assertEquals(clearedData, dirtyData);
    }

    @Test
    public void sortString() throws IOException, ParameterNotFoundException {
        String[] args = new String[]{"-s", "src/test/resources/out.txt", "src\\test\\resources\\in1str.txt", "src\\test\\resources\\in2str.txt"};
        HashMap<String, String> parameters = DataHandler.getParameters(args);
        ArrayList expected = new ArrayList<>(
                Arrays.asList("a", "a", "aa", "aa", "aaa", "aaa", "b", "bb", "bbb", "c", "cc", "ccc"));
        ArrayList actual = DataHandler.sort(parameters);
        assertEquals(expected, actual);
    }

    @Test
    public void sortStringRevers() throws IOException, ParameterNotFoundException {
        String[] args = new String[]{"-d", "-s", "src/test/resources/out.txt", "src\\test\\resources\\in1str.txt", "src\\test\\resources\\in2str.txt"};
        HashMap<String, String> parameters = DataHandler.getParameters(args);
        ArrayList expected = new ArrayList<>(
                Arrays.asList("ccc", "cc", "c", "bbb", "bb", "b", "aaa", "aaa", "aa", "aa", "a", "a"));
        ArrayList actual = DataHandler.sort(parameters);
        assertEquals(expected, actual);
    }

    @Test
    public void sortInteger() throws IOException, ParameterNotFoundException {
        String[] args = new String[]{"-i", "src/test/resources/out.txt", "src\\test\\resources\\in1.txt", "src\\test\\resources\\in2.txt"};
        HashMap<String, String> parameters = DataHandler.getParameters(args);
        ArrayList expected = new ArrayList<>(
                Arrays.asList("1", "4", "5", "6", "8", "8", "9", "9", "14", "44", "57", "77", "89"));
        ArrayList actual = DataHandler.sort(parameters);
        assertEquals(expected, actual);
    }

    @Test
    public void sortIntegerRevers() throws IOException, ParameterNotFoundException {
        String[] args = new String[]{"-d", "-i", "src/test/resources/out.txt", "src\\test\\resources\\in1.txt", "src\\test\\resources\\in2.txt"};
        HashMap<String, String> parameters = DataHandler.getParameters(args);
        ArrayList expected = new ArrayList<>(
                Arrays.asList("89", "77", "57", "44", "14", "9", "9", "8", "8", "6", "5", "4", "1"));
        ArrayList actual = DataHandler.sort(parameters);
        assertEquals(expected, actual);
    }
}
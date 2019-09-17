import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

class SortTest {

    @Test
    void integerSort() {
        ArrayList<String> list1 = new ArrayList<>(Arrays.asList("1", "3", "5", "7", "9", "11", "13", "15", "17", "19"));
        ArrayList<String> list2 = new ArrayList<>(Arrays.asList("2", "4", "6", "8", "10", "12", "14", "16", "18", "20"));
        ArrayList<String> expectedResult = new ArrayList<>(Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
                "11", "12", "13", "14", "15", "16", "17", "18", "19", "20"));
        assertEquals(expectedResult, Sort.integerSort(list1, list2));
    }

    @Test
    void stringSort1() {
        ArrayList<String> list1 = new ArrayList<>(Arrays.asList("a", "aa", "aaa", "aaab", "aaabb", "aaabbb"));
        ArrayList<String> list2 = new ArrayList<>(Arrays.asList("b", "bb", "bbb", "bbbc", "bbbcc", "bbbccc"));
        ArrayList<String> expected = new ArrayList<>(Arrays.asList("a", "aa", "aaa", "aaab", "aaabb", "aaabbb",
                "b", "bb", "bbb", "bbbc", "bbbcc", "bbbccc"));
        ArrayList result = Sort.stringSort(list1, list2);
        assertEquals(expected, result);
    }
}
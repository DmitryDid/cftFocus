import java.util.ArrayList;


class Sort {

    static ArrayList<String> integerSort(ArrayList<String> list1, ArrayList<String> list2) {
        if (list1 == null) return list2;
        if (list2 == null) return list1;

        ArrayList<String> result = new ArrayList<>();
        int size1 = list1.size();
        int size2 = list2.size();
        int index1 = 0;
        int index2 = 0;
        int size = size1 + size2;

        for (int i = 0; i < size; i++) {
            if (index2 < size2 && index1 < size1) {
                if (Integer.parseInt(list1.get(index1)) > Integer.parseInt(list2.get(index2)))
                    result.add(i, list2.get(index2++));
                else result.add(i, list1.get(index1++));
            } else if (index2 < size2) {
                result.add(i, list2.get(index2++));
            } else {
                result.add(i, list1.get(index1++));
            }
        }
        return result;
    }

    static ArrayList<String> stringSort(ArrayList<String> list1, ArrayList<String> list2) {
        if (list1 == null) return list2;
        if (list2 == null) return list1;

        ArrayList<String> result = new ArrayList<>();
        int size1 = list1.size();
        int size2 = list2.size();
        int index1 = 0;
        int index2 = 0;
        int size = size1 + size2;

        for (int i = 0; i < size; i++) {
            if (index2 < size2 && index1 < size1) {
                if (list1.get(index1).compareTo(list2.get(index2)) > 0)
                    result.add(i, list2.get(index2++));
                else result.add(i, list1.get(index1++));
            } else if (index2 < size2) {
                result.add(i, list2.get(index2++));
            } else {
                result.add(i, list1.get(index1++));
            }
        }
        return result;
    }
}
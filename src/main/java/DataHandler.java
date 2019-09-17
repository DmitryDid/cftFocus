import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

class DataHandler {

    static ArrayList<String> readDataFromFile(String path) throws IOException {
        if (path == null) return null;
        ArrayList<String> fileData = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(path));) {
            while (reader.ready()) {
                String line = reader.readLine();
                if (!line.isEmpty()) {
                    String[] strings = line.split(" ");
                    if (strings.length == 1) {
                        fileData.add(line);
                    } else {
                        Collections.addAll(fileData, strings);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Не удалось найти файл по указанному пути: " + path);
        }
        return fileData;
    }

    static HashMap<String, String> getParameters(String[] args) throws ParameterNotFoundException {
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("mode", "-a");

        for (String arg : args) {
            String argument = arg.trim();
            if (argument.equals("-a")) {
                continue;
            }
            if (argument.equals("-d")) {
                parameters.put("mode", argument);
                continue;
            }
            if (argument.equals("-s")) {
                parameters.put("type", argument);
                continue;
            }
            if (argument.equals("-i")) {
                parameters.put("type", argument);
                continue;
            }
            if (argument.matches("^.*\\.txt$") && parameters.get("out") == null) {
                parameters.put("out", argument);
            } else {
                if (parameters.get("in1") == null) {
                    parameters.put("in1", argument);
                    continue;
                }
                if (parameters.get("in2") == null) {
                    parameters.put("in2", argument);
                    continue;
                }
                parameters.putIfAbsent("in3", argument);
            }
        }
        if (parameters.get("type") == null)
            throw new ParameterNotFoundException("Не указан режим сортировки. Ожидалось: '-a' или '-d'");
        if (parameters.get("out") == null)
            throw new ParameterNotFoundException("Не указан путь к файлу для записи результата.");
        if (parameters.get("in1") == null)
            throw new ParameterNotFoundException("Не указан путь к файлу для чтения данных.");
        return parameters;
    }

    static ArrayList sort(HashMap<String, String> parameters) throws IOException {
        ArrayList<String> temporary;
        ArrayList<String> result;
        ArrayList<String> list1 = DataHandler.readDataFromFile(parameters.get("in1"));
        ArrayList<String> list2 = DataHandler.readDataFromFile(parameters.get("in2"));
        ArrayList<String> list3 = DataHandler.readDataFromFile(parameters.get("in3"));

        if (parameters.get("type").equals("-i")) {
            DataHandler.separateData(list1);
            if (list2 != null) DataHandler.separateData(list2);
            if (list3 != null) DataHandler.separateData(list3);
            temporary = Sort.integerSort(list1, list2);
            result = Sort.integerSort(list3, temporary);
        } else {
            temporary = Sort.stringSort(list1, list2);
            result = Sort.stringSort(list3, temporary);
        }

        if (parameters.get("mode").equals("-d")) {
            Collections.reverse(result);
        }
        return result;
    }

    static void separateData(ArrayList<String> list) {
        for (int i = 0; i < list.size(); i++) {
            try {
                Integer.parseInt(list.get(i));
            } catch (NumberFormatException e) {
                list.remove(i);
                i--;
            }
        }
    }

    static void writeDataToFile(List data, String path) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path));) {
            for (int i = 0; i < data.size(); i++) {
                writer.write((String) data.get(i));
                if (i != data.size() - 1) writer.write("\n");
            }
            writer.flush();
        } catch (FileNotFoundException e) {
            System.err.println("Не удалось найти файл по указанному пути: " + path);
        }
    }
}
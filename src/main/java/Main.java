import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) {
        HashMap<String, String> parameters = null;
        try {
            parameters = Data.getParameters(args);
        } catch (ParameterNotFoundException e) {
            System.out.println(e.getMessage());
        }
        try {
            ArrayList sortResult = Data.sort(parameters);
            Data.writeDataToFile(sortResult, parameters.get("out"));
        } catch (IOException e) {
            if (e instanceof FileNotFoundException) {
                System.err.println("Не найден файл по переданному пути.");
            } else {
                System.err.println("Ошибка при работе с данными файла.");
            }
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Дальнейшее выполнение программы невозможно.");
        }
    }
}
import autotests.payloads.Category;
import autotests.payloads.Order;
import autotests.payloads.Pet;
import autotests.payloads.Tag;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        //Создаем объект duckProperties класса DuckProperties

        Pet pet = new Pet().id(1).tags(List.of(new Tag().id(1).name("fffffff"), new Tag().id(2).name("aaaaaaaaa"))).name("Test").photoUrls(Arrays.asList("ffgfffgfgfg")).category(new Category().id(1).name("dffdfd")).status("ffgfggffg");

        Order order = new Order().id(11).petId(121).quantity(12).shipDate("fjfjfjf").status("test").complete(true);


        //Передаем объект в метод преобразования объекта класса в Json
        convertAndPrintToJson(order);
    }

    //метод для преобразования объекта класса в Json
    public static void convertAndPrintToJson(Object jsonBody) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();

            ObjectWriter objectWriter = objectMapper.writerWithDefaultPrettyPrinter();
            String json = objectWriter.writeValueAsString(jsonBody);

            System.out.println(json);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

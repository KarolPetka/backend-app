package task7;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Main {
    public static void main(String[] args) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        Employee employeeObject = new Employee("Elon", "CEO");
        String employeeToJson = objectMapper.writeValueAsString(employeeObject);

        System.out.println(employeeToJson);
    }
}

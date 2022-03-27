package task8;

import com.google.gson.Gson;

public class Main {
    public static void main(String[] args) {
        String json = "{\"name\":\"Daisy\",\"age\":4}";

        Animal animalToObject = new Gson().fromJson(json, Animal.class);

        System.out.println(animalToObject);
    }
}

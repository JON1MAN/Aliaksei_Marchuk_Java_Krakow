import com.ocado.basket.BasketSplitter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {

        /*Set<String> deliveryMethodsForAllItems = new HashSet<>();
        List<String> items = new ArrayList<>(List.of("Cookies Oatmeal Raisin", "Cheese Cloth", "English Muffin"));
        Map<String , List<String>> item_delivery = new HashMap<>();
        for(String item : items){
            item_delivery.put(item, getDeliveriesForItem(item));
            deliveryMethodsForAllItems.addAll(item_delivery.get(item));
        }
        System.out.println(item_delivery);
        System.out.println(deliveryMethodsForAllItems);*/

        BasketSplitter splitter = new BasketSplitter(
                "/home/jon1man/GitProjects/Aliaksei_Marchuk_Java_Krakow/config.json");

        List<String> items = new ArrayList<>(List.of("Fond - Chocolate", "Chocolate - Unsweetened", "Nut - Almond, Blanched, Whole", "Haggis", "Mushroom - Porcini Frozen", "Cake - Miini Cheesecake Cherry", "Sauce - Mint", "Longan", "Bag Clear 10 Lb", "Nantucket - Pomegranate Pear", "Puree - Strawberry", "Numi - Assorted Teas", "Apples - Spartan", "Garlic - Peeled", "Cabbage - Nappa", "Bagel - Whole White Sesame", "Tea - Apple Green Tea"));
        Map<String, List<String>> map = splitter.split(items);
        for(Map.Entry<String, List<String>> entry : map.entrySet()){
            String delivery = entry.getKey();
            System.out.println("-".repeat(48));
            System.out.println("=".repeat(24 - delivery.length() / 2) + delivery + "=".repeat(24 - delivery.length() / 2));
            System.out.println("Products: " + entry.getValue().size());
            for(String string : entry.getValue()){
                System.out.println(string);
            }

        }
    }

    /*private static List<String> getDeliveriesForItem(String item){
        //Declaration and Initialization List that has deliveries of item
        List<String> deliveries = new ArrayList<>();

        //Declaration and init. of Gson class to make operations on JSON file
        Gson gson = new Gson();

        //Reading JSON file
        try(Reader reader = new FileReader("/home/jon1man/GitProjects/Aliaksei_Marchuk_Java_Krakow/config.json")) {

            //Parsing JSON file's data to JSON object
            //Our JSON file is big JSON object that has:
            //"name_of_item": [list of delivery strings]
            JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);

            //If our item is in JsonObject
            if(jsonObject.has(item)){
                //Then we pass an array of strings of delivery methods to deliveryArray
                String[] deliveryArray = gson.fromJson(jsonObject.get(item), String[].class);
                //Then we pass it to List
                deliveries.addAll(Arrays.asList(deliveryArray));
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return deliveries;
    }*/
}

package com.ocado.basket;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.*;

public class BasketSplitter {

    //Your absolute Path to Config file
    private final String absolutePathToConfigFile;

    //Constructor, you need to pass absolute path
    public BasketSplitter(String absolutePathToConfigFile) { //you need to add
        this.absolutePathToConfigFile = absolutePathToConfigFile;

    }

    //Main method to split your list of items on groups
    public Map<String, List<String>> split(List<String> items){

        //This will the result of split method
        Map<String ,List<String>> result = new HashMap<>();

        //Here we need to do operations while list of items is > 0
        while (!items.isEmpty()){
            //creating a map
            //key - String (Item)
            //value - List<String> (list of possible deliveries for item)
            Map<String , List<String>> map_item_delivery = new HashMap<>();

            //iterating over items and putting them to the map that we created before
            for(String item : items){
                //here as value to put we use getDeliveriesForItem
                map_item_delivery.put(item, getDeliveriesForItem(item));
            }

            //Then we need to transform our map to:
            //key - delivery method
            //value - List<String> (List of items, that can be delivered by this delivery
            Map<String , List<String>> map_delivery_item = getMapDeliveryItems(map_item_delivery);

            //Next we need to store key, of delivery type, that can deliver most items
            String key = keyWithMaxItems(map_delivery_item);

            //Storing list of items with delivery key for most items in map
            List<String> value = map_delivery_item.get(key);
            //Put this key and value to result map
            result.put(key, value);
            //Remove all items that were added to result map and remove
            //them from list of items that we passed to split method
            items.removeAll(value);
        }

        return result;
    }

    //This method get an Item and returns List<String>
    //this is a List of possible deliveries for Item
    private List<String> getDeliveriesForItem(String item){
        //Declaration and Initialization List that has deliveries of item
        List<String> deliveries = new ArrayList<>();

        //Declaration and init. of Gson class to make operations on JSON file
        Gson gson = new Gson();

        //Reading JSON file
        try(Reader reader = new FileReader(absolutePathToConfigFile)) {

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
    }


    //This method is used to transform map from:
    //key - item, value - List of deliveries for this item
    //to
    //key - delivery method, value - List of items for that delivery
    private Map<String, List<String>> getMapDeliveryItems(Map<String, List<String>> map_item_delivery) {
        Map<String, List<String>> transformedMap = new HashMap<>();

        //Iterating over a map
        for(Map.Entry<String, List<String>> entry : map_item_delivery.entrySet()){
            //Need to store key and value
            String wayOfDelivery = entry.getKey();
            List<String> items = entry.getValue();

            //Iterating over a List of items for that key
            for(String item : items){
                //Using of computeIfAbsent method to transform to new map
                transformedMap.computeIfAbsent(item, k -> new ArrayList<>()).add(wayOfDelivery);
            }
        }
        return transformedMap;
    }

    //Method to return delivery option with the longest list of items
    private String keyWithMaxItems(Map<String, List<String>> map_delivery_item){
        //max - to store max length  of list of items
        //maxKey - to store key with max length of list of items
        long max = 0;
        String maxKey = "";
        //Iterating over a mp to get key and max count
        for(Map.Entry<String, List<String>> entry : map_delivery_item.entrySet()){
            String key = entry.getKey();
            //Using stream.count to simply count length of list
            long count = entry.getValue().stream().count();
            if(count >= max){
                max = count;
                maxKey = key;
            }
        }
        return maxKey;
    }

}

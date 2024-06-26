# Aliaksei_Marchuk_Java_Krakow README file for the task
Here is a README file for given problem.
The main goal is to write an algorithm that would determine the smallest number of delivery groups and optimiz
So here is an example of algorithm with explanation step by step:
Example (I will took an example from your pdf):

**1. We need to get list of our Items:**

   ![image](https://github.com/JON1MAN/Aliaksei_Marchuk_Java_Krakow/assets/117488279/735b37af-dea4-4f8c-97ec-799478b604a5)
   
**2. Next we need to iterate through config.json to find an item and list with delivery options for that item**
   ![image](https://github.com/JON1MAN/Aliaksei_Marchuk_Java_Krakow/assets/117488279/1569ca1e-3f49-4aff-9576-b5d63ecad467)
   
**3. We need to take every delivery option and assign it a value - List of items, that can be delivered by
this delivery option**

      **Express Delivery**   :Carrots (1kg), Cold Beer (330ml), Steak (300g), AA Battery (4 Pcs.)
      
      **Click&Collect**      :Carrots (1kg), Steak (300g), Espresso Machine
      
      **Courier**            :AA Battery (4 Pcs.), Espresso Machine, Garden Chair

**4. Next we need to check, what way of deliveries has more items to deliver:
For our case it is Express Delivery.
Adding Express Deliveries to result and delete all of the items that are in
Express Delivery in every other delivery way.**

**Result:**

Express Delivery :Carrots (1kg), Cold Beer (330ml), Steak (300g), AA Battery (4 Pcs.)

**Remove Express delivery Items from list of items and repeat:**

**Express Delivery** :-
**Click&Collect**    :Espresso Machine
**Courier**          :Espresso Machine, Garden Chair

**Courier has more elements, repeat 3,4.**
**Result:**

**Express Delivery** :Carrots (1kg), Cold Beer (330ml), Steak (300g), AA Battery (4 Pcs.)

**Courier**          :Espresso Machine, Garden Chair


# What about code?
**Also, to iterate over config.json I'm using Gson libarary, dependency was added to
Maven pom.xml file:**
![image](https://github.com/JON1MAN/Aliaksei_Marchuk_Java_Krakow/assets/117488279/f6f18b9d-2a2a-4910-9fbc-ad44427b6acb)

**Class BacketSplitter has:**

**fields:**

private final String absolutePathToConfigFile; - which will save our absolute path to config file

**constructors:**

one constructor that takes String absolutePathToConfigFile and assignes it to the field

**methods:**

public Map<String, List<String>> split(List<String> items) - this method is need to split items over groups of deliveries

private List<String> getDeliveriesForItem(String item) - this method for getting list of deliveries for passed item

private Map<String, List<String>> getMapDeliveryItems(Map<String, List<String>> map_item_delivery)  - this method returns transformed map,

we passing parameters: Map<String, List<String>> that has:

key - Item

value - List of deliveries for this item

method returns Map<String, List<String>> with reversed logic:

key - Delivery way

value - List of items from our basket for that delivery

private String keyWithMaxItems(Map<String, List<String>> map_delivery_item) - method that will return Delivery way with the longest list of items

# Codes are in **src -> main -> java** folder

**src -> main -> java -> com -> ocado -> basket** -> has a class BasketSplitter

**src -> main -> java** has Main class

# How to use

in your main class:

BacketSplitter splitter = new BacketSplitter(String your_Absolute_Path_to_config_file); //pass your absolute path to config file as String
Map<String, List<String>> map = splitter.split(List<String> items) //pass as a parameter your List of items

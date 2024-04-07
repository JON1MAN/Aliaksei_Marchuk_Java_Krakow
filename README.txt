# Aliaksei_Marchuk_Java_Krakow
Here is a README file for given problem.
The main goal is to write an algorithm that would determine the smallest number of delivery groups and optimiz
So here is an example of algorithm with explanation step by step:
Example (I will took an example from your pdf):

1. We need to get list of our Items:
   ![image](https://github.com/JON1MAN/Aliaksei_Marchuk_Java_Krakow/assets/117488279/735b37af-dea4-4f8c-97ec-799478b604a5)
   
2. Next we need to iterate through config.json to find an item and list with delivery options for that item
   ![image](https://github.com/JON1MAN/Aliaksei_Marchuk_Java_Krakow/assets/117488279/1569ca1e-3f49-4aff-9576-b5d63ecad467)
   
3. We need to take every delivery option and assign it a value - List of items, that can be delivered by
this delivery option

Express Delivery :Carrots (1kg), Cold Beer (330ml), Steak (300g), AA Battery (4 Pcs.)
Click&Collect    :Carrots (1kg), Steak (300g), Espresso Machine
Courier          :AA Battery (4 Pcs.), Espresso Machine, Garden Chair

4. Next we need to check, what way of deliveries has more items to deliver:
For our case it is Express Delivery.
Adding Express Deliveries to result and delete all of the items that are in
Express Delivery in every other delivery way.

Result:
Express Delivery :Carrots (1kg), Cold Beer (330ml), Steak (300g), AA Battery (4 Pcs.)

Remove Express delivery Items from list of items and repeat:

Express Delivery :-
Click&Collect    :Espresso Machine
Courier          :Espresso Machine, Garden Chair

Courier has more elements, repeat 3,4.
Result:
Express Delivery :Carrots (1kg), Cold Beer (330ml), Steak (300g), AA Battery (4 Pcs.)
Courier          :Espresso Machine, Garden Chair



Also, to iterate over config.json I used Gson libarary, dependency was added to
Maven pom.xml file:
<dependencies>
        <!-- https://mvnrepository.com/artifact/com.google.code.gson/gson -->
        <dependency>
            <groupId>com.google.code.gson</groupId>
            <artifactId>gson</artifactId>
            <version>2.10.1</version>
        </dependency>
    </dependencies>

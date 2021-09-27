package CharacterItems;


public class ItemsFactory {
    Items item;
    //for items with single attribute
    public Items getSingleItems(String itemName, String itemType,int quantity, String firstAttribute, String firstStat, int firstValue, int turn){
        if(itemType.equalsIgnoreCase("consumable")){
            item = new Items(itemName, Items.Type.valueOf(itemType), quantity, new Attributes(new Attribute(Attribute.Type.valueOf(firstAttribute), new ItemStat(ItemStat.Type.valueOf(firstStat), firstValue), turn)));
        }
        else {
            item = new Items(itemName, Items.Type.valueOf(itemType), quantity, new Attributes(new Attribute(Attribute.Type.valueOf(firstAttribute), new ItemStat(ItemStat.Type.valueOf(firstStat), firstValue))));
        }
        return item;
    }
    //for items with double attributes
    public Items getDoubleItems(String itemName, String itemType, int quantity, String firstAttribute, String firstStat, int firstValue, String secondAttribute, String secondStat, int secondValue){
        item = new Items(itemName, Items.Type.valueOf(itemType), quantity, new Attributes(new Attribute(Attribute.Type.valueOf(firstAttribute), new ItemStat(ItemStat.Type.valueOf(firstStat), firstValue)), new Attribute(Attribute.Type.valueOf(secondAttribute), new ItemStat(ItemStat.Type.valueOf(secondStat), secondValue))));
        return item;
    }
    //for items with triple attributes
    public Items getDoubleItems(String itemName, String itemType, int quantity, String firstAttribute, String firstStat, int firstValue, String secondAttribute, String secondStat, int secondValue, String thirdAttribute, String thirdStat, int thirdValue){
        item = new Items(itemName, Items.Type.valueOf(itemType), quantity, new Attributes(new Attribute(Attribute.Type.valueOf(firstAttribute), new ItemStat(ItemStat.Type.valueOf(firstStat), firstValue)), new Attribute(Attribute.Type.valueOf(secondAttribute), new ItemStat(ItemStat.Type.valueOf(secondStat), secondValue)),new Attribute(Attribute.Type.valueOf(thirdAttribute), new ItemStat(ItemStat.Type.valueOf(thirdStat), thirdValue))));
        return item;
    }
    //for items with quadruple attributes
    public Items getDoubleItems(String itemName, String itemType, int quantity, String firstAttribute, String firstStat, int firstValue, String secondAttribute, String secondStat, int secondValue, String thirdAttribute, String thirdStat, int thirdValue, String fourthAttribute, String fourthStat, int fourthValue){
        item = new Items(itemName, Items.Type.valueOf(itemType), quantity, new Attributes(new Attribute(Attribute.Type.valueOf(firstAttribute), new ItemStat(ItemStat.Type.valueOf(firstStat), firstValue)), new Attribute(Attribute.Type.valueOf(secondAttribute), new ItemStat(ItemStat.Type.valueOf(secondStat), secondValue)),new Attribute(Attribute.Type.valueOf(thirdAttribute), new ItemStat(ItemStat.Type.valueOf(thirdStat), thirdValue)),new Attribute(Attribute.Type.valueOf(fourthAttribute), new ItemStat(ItemStat.Type.valueOf(fourthStat), fourthValue))));
        return item;
    }
}

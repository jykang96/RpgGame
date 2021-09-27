package CharacterItems;



public class Items {
    private final String name;
    //private final Attribute attribute;
    private final String type;
    private final Attributes attributes;
    private int quantity;

    public enum Type {
        WEAPON,
        OFFHAND,
        HELM,
        ARMOR,
        LEGGINGS,
        BOOTS,
        CONSUMABLE
    }

    //public Items(String name, Type type, Attribute attribute){
    //    this.name = name;
    //    this.type = type.name();
    //    this.attribute = attribute;
    //}

    public Items(String name, Type type, int quantity, Attributes attributes){
        this.name = name;
        this.type = type.name();
        this.attributes = attributes;
        this.quantity = quantity;
    }
    public String getName(){
        return this.name;
    }
    public String getType(){
        return this.type;
    }

    public Attributes getAttributes(){
        return this.attributes;
    }


    public int getQuantity(){return this.quantity;}
    public void setQuantity(int quantity){this.quantity = quantity;}

}

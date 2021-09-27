package CharacterItems;

import java.util.ArrayList;

public class Attributes {
    //test out final later
    private final ArrayList<Attribute> attributes = new ArrayList<Attribute>();
    //single attribute item
    public Attributes(Attribute attribute){
        attributes.add(attribute);
    }
    //double attribute item
    public Attributes(Attribute attributeOne, Attribute attributeTwo){
        attributes.add(attributeOne);
        attributes.add(attributeTwo);
    }
    // triple attribute item
    public Attributes(Attribute attributeOne, Attribute attributeTwo, Attribute attributeThree){
        attributes.add(attributeOne);
        attributes.add(attributeTwo);
        attributes.add(attributeThree);
    }
    // quadruple attribute item
    public Attributes(Attribute attributeOne, Attribute attributeTwo, Attribute attributeThree, Attribute attributeFour){
        attributes.add(attributeOne);
        attributes.add(attributeTwo);
        attributes.add(attributeThree);
        attributes.add(attributeFour);
    }

    public ArrayList<Attribute> getAttributeList(){
        return attributes;
    }

}

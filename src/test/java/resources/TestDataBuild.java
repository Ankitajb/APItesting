package resources;

import pojo.AddPlace;
import pojo.Location;

import java.util.ArrayList;
import java.util.List;

public class TestDataBuild {

    public AddPlace addPlacePayLoad(String name, String language, String address) {
        AddPlace p = new AddPlace();
        p.setAccuracy(50);
        p.setName(name);
        p.setAddress(address);
        p.setLanguage(language);
        p.setPhone_number("(+64) 223 915 770");
        p.setWebsite("http://google.com");
        List<String> myList = new ArrayList<String>();
        myList.add("shoe park");
        myList.add("shop");
        p.setTypes(myList);
        Location l = new Location();
        l.setLat(-38.383494);
        l.setLng(33.427362);
        p.setLocation(l);
        return p;
    }
   public String deletePlacePayload(String place_id)
   {
       return "{\r\n    \"place_id\":\""+place_id+"\"\r\n}";
   }

}

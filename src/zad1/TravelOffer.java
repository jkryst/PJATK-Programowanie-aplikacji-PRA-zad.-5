package zad1;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

public class TravelOffer {
	
	
	public Locale l;
	public String country;
	public Date dateOfDeparture;
	public Date dateOfArrival;
	public String place;
	public Number price;
	public String currency;
	
	
	
     public TravelOffer(String[] offer)
     {
	 
    	 l = getLocale(offer[0]);
    	 country = offer[1];
    	 
    	 try {
			dateOfDeparture = new SimpleDateFormat("yyyy-MM-dd").parse(offer[2]);
			dateOfArrival = new SimpleDateFormat("yyyy-MM-dd").parse(offer[3]);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	 
    	 place = offer[4];
    	   	 
    	 try {
			price = NumberFormat.getNumberInstance(l).parse(offer[5]);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	 
    	 currency = offer[6];

     } 
     
     
     public static Locale getLocale(String s) {
    	 
    	 Locale[] loc = Locale.getAvailableLocales();
    	 Map<String, Locale> map = new HashMap<String, Locale>();
    	 for (int i=0; i<loc.length; i++) {
    	      String countryCode = loc[i].toString();
    	      map.put(countryCode, loc[i]);
    	    }
    	 
    	 return map.get(s);
    	 
    	 
     }
     
     public String getCountry(Locale outLoc) {
		
    	 Locale inLoc = l;
    	 Locale[] loc = Locale.getAvailableLocales();
    	 String res = null;

    	 
    	 for (int i=0; i<loc.length; i++) 
    	 {
    		 if(loc[i].getDisplayCountry(inLoc).equals(country))
    		 {
    			 res = loc[i].getDisplayCountry(outLoc);
    			 break;
    		 }
   	     }
    	 return res;
     }
     
     
     
     public String getPlace(Locale outLoc) 
     {
    	 
    	 Locale.setDefault(l);
    	 ResourceBundle places = ResourceBundle.getBundle("Places");
    	 
    	 List<String> bundleKeys = Collections.list(places.getKeys());   	 
    	 
    	 String res = null;
    	
    	 for(int i=0; i<bundleKeys.size(); i++) 
    	 {
    		 String key = (String)bundleKeys.get(i);    		
    		
    		 if (places.getString(key).equals(place)) 
    		 {	 
    			 res = key;
    			 break; 
    		 }
    	 }
    	
    	 Locale.setDefault(outLoc); 
		 ResourceBundle placesTranslated = ResourceBundle.getBundle("Places"); 
		 return placesTranslated.getString(res); 				
     }
}

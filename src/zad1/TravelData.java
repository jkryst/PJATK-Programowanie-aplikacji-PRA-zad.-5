package zad1;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class TravelData {
	
	public static List<TravelOffer> offers = new ArrayList<TravelOffer>();

	
	public TravelData(File f) throws FileNotFoundException
	{
		File[] files = f.listFiles();
	
		for(int i = 0; i < files.length; i++) 
		{
			
		Scanner s = new Scanner(files[i]);
		
		while(s.hasNextLine()) 
		{
			String offer = s.nextLine();
			String[] offerElem = offer.split("\t");
			TravelOffer t = new TravelOffer(offerElem);
			offers.add(t);

		}
		
		s.close();

		}

		
	}
    
	public List<String> getOffersDescriptionsList(String loc, String dp) {
		
		Locale l = TravelOffer.getLocale(loc);
		
		SimpleDateFormat df = (SimpleDateFormat) DateFormat.getDateInstance();
		df.applyPattern(dp);
		
		List<String> listOfOffers = new ArrayList<>();
		
		
		for(int i=0; i<offers.size(); i++) 
		{
			TravelOffer t = offers.get(i);
			
			
			String country = t.getCountry(l);
			
			String dateDep = df.format(t.dateOfDeparture);
			
			String dateArr = df.format(t.dateOfArrival);
			
			String place = t.getPlace(l);	
			
			String price = NumberFormat.getNumberInstance(l).format(t.price);
			
			String currency = t.currency;
		
			String offer = country+" "+dateDep+" "+dateArr+" "+place+" "+price+" "+currency;
			listOfOffers.add(offer);
			
		}
		
		return listOfOffers;		
	}	
}

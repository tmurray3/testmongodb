package test;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;


import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;

/**
 * Generates documents with valid random values 
 * and inserts into mongodb collection.
 * Set createNumberOfRecords value to generate x number of documents.
 * 
 */
public class TestMongoPojoInsert {
	
	
	
	static private Random random = new Random();
	private List<Integer> zipcodes = null;

	private String[] charset = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","z"};

	public TestMongoPojoInsert() {
		
		buildZipCodes();
		StringBuilder user =null;
		Mongo mongo=null;

		Date starttime=new Date();
		
		try {
			mongo = new Mongo();
			DB db = mongo.getDB(Constants.DB_NAME);
			DBCollection dbcollection = db.getCollection(Constants.COLLECTION_NAME);
		
			//Create unique aliases
			dbcollection.ensureIndex(new BasicDBObject(PersonFields.ALIAS,1).append("unique",true));
			dbcollection.ensureIndex(new BasicDBObject(PersonFields.EMAIL,1).append("unique",true));
			
			//Create search index.
			dbcollection.ensureIndex(new BasicDBObject(PersonFields.ZIPCODE,1)
								.append(PersonFields.DS, 1)
								.append(PersonFields.DOB, 1)
								.append(PersonFields.GENDER, 1)
								.append(PersonFields.ETHNICITY, 1)
								.append(PersonFields.HEIGHT, 1)
								.append(PersonFields.RELIGION, 1)
								.append(PersonFields.BC, 1)
								.append(PersonFields.LF_STATUS, 1)
								.append(PersonFields.HASKIDS, 1)
								.append(PersonFields.SMOKE, 1)
								.append(PersonFields.DRINK, 1)
								.append(PersonFields.ACCOUNT_STATUS, -1)
								.append(PersonFields.ACCOUNT_TYPE, 1)
								.append(PersonFields.LAST_ACTIVITY, -1),"personAttributes_index");
			
			
			PersonPojo person = null;
			
			for( int i=0; i<Constants.createNumberOfRecords;i++) {
				person=new PersonPojo();
				
				user = new StringBuilder("user");
				for (int q=0;q<5;q++) {
					user.append(charset[getRandomValue(1,charset.length)]);
				}
				user.append(getRandomValue(1,99999));
				
				person.setAlias(user.toString());
				person.setDob(randomBirthDate() );
				person.setGender((short)getRandomValue(1,7));
				person.setZipcode(zipcodes.get(getRandomValue(1,zipcodes.size())));
				person.setAccountType( (short)getRandomValue(1,6));
				person.setEthnicity( (short)getRandomValue(1,6));
				person.setHaskids( (short)getRandomValue(1,4));
				person.setBC( (short)getRandomValue(1,6));
				person.setHeight( (short)getRandomValue(48,96));
				person.setReligion((short)getRandomValue(1,6));
				person.setAccountStatus((short)10);
				person.setEmail(user+"@user.com");
				person.setDS((short)getRandomValue(1,6));
				person.setSmokes((short)getRandomValue(1, 3));
				person.setDrinks((short)getRandomValue(1, 3));
				
				List<Short> lookingForList = new ArrayList<Short>(3);
				lookingForList.add((short)1);
				lookingForList.add((short)2);
				lookingForList.add((short)getRandomValue(3,6));
				person.setLFStatus(lookingForList);
				person.setLastActivity(new Date());
				person.generateId();
				person.setPassword(user.toString());
				person.setPhoto1Location("/photos/1/"+person.getId());
				person.setPhoto2Location("/photos/2/"+person.getId());
				person.setPhoto3Location("/photos/3/"+person.getId());

				dbcollection.insert(person.bsonFromPojo());
				System.out.println("Inserted ["+i+"] "+person.getId());
				
				
			}
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Date endtime=new Date();
		System.out.println("Start Time: "+starttime.toString());
		System.out.println("End Time: "+endtime.toString());
		mongo.close();
	}
	

	/**
	 * Generate a list of random zipcodes to be 
	 * used in document creation.
	 */
	public void buildZipCodes() {
		zipcodes=new ArrayList<Integer>();
		zipcodes.add(28031);
		zipcodes.add(28036);
		zipcodes.add(28123);
		zipcodes.add(28205);
		zipcodes.add(28214);
		zipcodes.add(28216);
		zipcodes.add(28233);
		zipcodes.add(28262);
		zipcodes.add(28266);
		zipcodes.add(28269);
		zipcodes.add(28078);
		zipcodes.add(28687);
		zipcodes.add(28688);
		

		
	}
	/**
	 * Generate a random birth day
	 * @return Date - dob
	 */
	public Date randomBirthDate() {
        GregorianCalendar gc = new GregorianCalendar();

        int year = randBetween(1930, 1997);

        gc.set(gc.YEAR, year);

        int dayOfYear = randBetween(1, gc.getActualMaximum(gc.DAY_OF_YEAR));

        gc.set(gc.DAY_OF_YEAR, dayOfYear);

        
        System.out.println(gc.get(gc.YEAR) + "-" + gc.get(gc.MONTH) + "-" + gc.get(gc.DAY_OF_MONTH));

        return gc.getTime();
        
        
    }

    public static int randBetween(int start, int end) {
        return start + (int)Math.round(Math.random() * (end - start));
	}
	
	static public int getRandomValue(int low, int high){
		return random.nextInt(high-low)+low;
	}


	
	public static void main(String[] args) {
		if (args!=null && args.length>0) {
			Constants.createNumberOfRecords=Integer.parseInt(args[0]);
		}
		new TestMongoPojoInsert();

}


}

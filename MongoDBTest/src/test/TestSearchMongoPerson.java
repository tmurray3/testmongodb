package test;

import java.net.UnknownHostException;

import org.apache.log4j.Logger;
import org.joda.time.DateMidnight;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.QueryBuilder;

public class TestSearchMongoPerson {
	private Mongo mongo =null;
	private static final Logger logger = Logger.getLogger(TestSearchMongoPerson.class);

	public TestSearchMongoPerson() {
		
		try {
	
			mongo = new Mongo();
			DB db = mongo.getDB(Constants.DB_NAME);
			DBCollection persons = db.getCollection(Constants.COLLECTION_NAME);
			
			DBCursor cursor = null;

			
			//Build a query
			BasicDBObject document = new BasicDBObject();
					
			BasicDBList bodylist = new BasicDBList();
			bodylist.add(1);
			bodylist.add(2);
			bodylist.add(3);
			bodylist.add(5);
			
			BasicDBList ethnicityList  = new BasicDBList();
			ethnicityList.add(1);
			ethnicityList.add(2);
			ethnicityList.add(5);
			ethnicityList.add(3);
			
			BasicDBList lookingfor  = new BasicDBList();
			lookingfor.add(1);
			lookingfor.add(5);
			
			BasicDBList genderList  = new BasicDBList();
			genderList.add(1);
			genderList.add(2);
			genderList.add(3);
			
			BasicDBList zipcodes = new BasicDBList();
			zipcodes.add(28214);
			zipcodes.add(28036);
			zipcodes.add(28269);
			zipcodes.add(28233);
			
			
			QueryBuilder qb = new QueryBuilder();
			
			qb.and(new QueryBuilder().put(PersonFields.ZIPCODE).in(zipcodes).get());

			DateMidnight dt = new DateMidnight();
			DateMidnight latest = dt.minusYears(20);
			DateMidnight earliest = dt.minusYears(40);
		
			qb.and(new QueryBuilder().put(PersonFields.DOB).greaterThanEquals(earliest.toDate()).get());
			qb.and(new QueryBuilder().put(PersonFields.DOB).lessThanEquals(latest.toDate()).get());
			qb.and(new QueryBuilder().put(PersonFields.GENDER).in(genderList).get());
			qb.and(new QueryBuilder().put(PersonFields.ETHNICITY).in(ethnicityList).get());
			qb.and(new QueryBuilder().put(PersonFields.LF_STATUS).in(lookingfor).get());
			qb.and(new QueryBuilder().put(PersonFields.DRINK).is(1).get());
			qb.and(new QueryBuilder().put(PersonFields.SMOKE).is(1).get());
			
			logger.info("Query:"+qb.toString());
			
			document.putAll(qb.get());
			
			logger.info(document.toString());
			
			long startquery=System.currentTimeMillis();

			logger.info("START FIND QUERY");
			
			 cursor = persons.find(document)
					 .sort(new BasicDBObject()
					 	.append(PersonFields.ACCOUNT_STATUS, 1)
					 	.append(PersonFields.LAST_ACTIVITY, -1))
					 .limit(10);
			 
			 long endquery = System.currentTimeMillis();
			 logger.info("END FIND QUERY...Response Time (ms): "+ (endquery - startquery));

			 DBObject obj=null;
			 
			 startquery=System.currentTimeMillis();
			 
			 while(cursor.hasNext()) {
				 obj = cursor.next();
				 logger.info("Result: "+ obj);
			}
			 endquery = System.currentTimeMillis();
			 
			 logger.info("Count:"+cursor.count() +"  Cursor Iterate Time (ms): "+ (endquery - startquery));

			 
			 
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mongo.close();
	}
	
	
	public static void main (String[] args) {
		new TestSearchMongoPerson();
	}

}

package test;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;

import test.PersonFields;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;


public class PersonPojo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private short age 						= -1;
	private int zipcode						= -1;
	private short gender					= -1;
	private short ethnicity					= -1;
	private short haskids					= -1;
	private short height					= -1;
	private short religion					= -1;
	private List<Short> lfstatus;
	private short bc						= -1;
	private String photo1Location			= null;
	private String photo2Location			= null;
	private String photo3Location			= null;
	private String email					= null;
	private String alias					= null;
	private String password					= null;
	private Date	lastActivity			= null;
	private short accountStatus				= -1;
	private short accountType				= -1;
	private short dstatus					= -1;
	private boolean emailVerified			= false;
	private ObjectId _id					= null;
	private Date	dob						= null;
	private short smokes					= -1;
	private short drinks					= -1;
	
	
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public short getAge() {
		return age;
	}
	public void setAge(short age) {
		this.age = age;
	}
	public int getZipcode() {
		return zipcode;
	}
	public void setZipcode(int zipcode) {
		this.zipcode = zipcode;
	}
	public short getGender() {
		return gender;
	}
	public void setGender(short gender) {
		this.gender = gender;
	}
	public short getEthnicity() {
		return ethnicity;
	}
	public void setEthnicity(short ethnicity) {
		this.ethnicity = ethnicity;
	}
	public short getHaskids() {
		return haskids;
	}
	public void setHaskids(short haskids) {
		this.haskids = haskids;
	}
	public short getHeight() {
		return height;
	}
	public void setHeight(short height) {
		this.height = height;
	}
	public short getReligion() {
		return religion;
	}
	public void setReligion(short religion) {
		this.religion = religion;
	}
	public short getBC() {
		return bc;
	}
	public void setBC(short bc) {
		this.bc = bc;
	}
	public String getPhoto1Location() {
		return photo1Location;
	}
	public void setPhoto1Location(String photo1Location) {
		this.photo1Location = photo1Location;
	}
	public String getPhoto2Location() {
		return photo2Location;
	}
	public void setPhoto2Location(String photo2Location) {
		this.photo2Location = photo2Location;
	}
	public String getPhoto3Location() {
		return photo3Location;
	}
	public void setPhoto3Location(String photo3Location) {
		this.photo3Location = photo3Location;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getLastActivity() {
		return lastActivity;
	}
	public void setLastActivity(Date lastActivity) {
		this.lastActivity = lastActivity;
	}
	public short getAccountStatus() {
		return accountStatus;
	}
	public void setAccountStatus(short accountStatus) {
		this.accountStatus = accountStatus;
	}
	public short getAccountType() {
		return accountType;
	}
	public void setAccountType(short accountType) {
		this.accountType = accountType;
	}
	public short getDS() {
		return dstatus;
	}
	public void setDS(short ds) {
		this.dstatus = ds;
	}
	public boolean isEmailVerified() {
		return emailVerified;
	}
	public void setEmailVerified(boolean emailVerified) {
		this.emailVerified = emailVerified;
	}
	public List<Short> getLFStatus() {
		return lfstatus;
	}
	public void setLFStatus(List<Short> lfstatus) {
		this.lfstatus = lfstatus;
	}
	
	
	
	public short getSmokes() {
		return smokes;
	}
	public void setSmokes(short smokes) {
		this.smokes = smokes;
	}
	public short getDrinks() {
		return drinks;
	}
	public void setDrinks(short drinks) {
		this.drinks = drinks;
	}
	public ObjectId getId() {
		return _id;
	}
	public void setId(ObjectId _id) {
		this._id = _id;
	}
	public void generateId() {
		if (this._id==null) {
			this._id=new ObjectId();
		}
	}
	
	public DBObject bsonFromPojo() {
		BasicDBObject document = new BasicDBObject();
		document.put(PersonFields.ID, getId());
		document.put(PersonFields.DOB,getDob());
		document.put(PersonFields.ZIPCODE,getZipcode());
		document.put(PersonFields.GENDER,getGender());
		document.put(PersonFields.ETHNICITY,getEthnicity());
		document.put(PersonFields.HASKIDS,getHaskids());
		document.put(PersonFields.HEIGHT,getHeight());
		document.put(PersonFields.RELIGION,getReligion());
		document.put(PersonFields.LF_STATUS,getLFStatus());
		document.put(PersonFields.BC,getBC());
		document.put(PersonFields.PHOTO1_LOCATION,getPhoto1Location());
		document.put(PersonFields.PHOTO2_LOCATION,getPhoto2Location());
		document.put(PersonFields.PHOTO3_LOCATION,getPhoto3Location());
		document.put(PersonFields.EMAIL,getEmail());
		document.put(PersonFields.ALIAS,getAlias());
		document.put(PersonFields.PASSWORD,getPassword());
		document.put(PersonFields.LAST_ACTIVITY,getLastActivity());
		document.put(PersonFields.ACCOUNT_STATUS,getAccountStatus());
		document.put(PersonFields.ACCOUNT_TYPE,getAccountType());
		document.put(PersonFields.DS,getDS());
		document.put(PersonFields.SMOKE, getSmokes());
		document.put(PersonFields.DRINK, getDrinks());
		
		
		return document;
	}
	
	public void makePojoFromBson(DBObject bson) {
		BasicDBObject b = (BasicDBObject) bson;
		setId((ObjectId)b.get(PersonFields.ID));
		setDob( (Date)b.getDate(PersonFields.DOB));
		setZipcode(b.getInt(PersonFields.ZIPCODE));
		setGender( (short) b.getInt(PersonFields.GENDER));
		setEthnicity( (short)b.getInt(PersonFields.ETHNICITY));
		setHaskids( (short)b.getInt(PersonFields.HASKIDS));
		setHeight((short)b.getInt(PersonFields.HEIGHT));
		setReligion((short)b.getInt(PersonFields.RELIGION));
		setLFStatus((List<Short>)b.get(PersonFields.LF_STATUS));
		setBC((short)b.getInt(PersonFields.BC));
		setPhoto1Location(b.getString(PersonFields.PHOTO1_LOCATION));
		setPhoto2Location(b.getString(PersonFields.PHOTO2_LOCATION));
		setPhoto3Location(b.getString(PersonFields.PHOTO3_LOCATION));
		setEmail(b.getString(PersonFields.EMAIL));
		setAlias(b.getString(PersonFields.ALIAS));
		setPassword(b.getString(PersonFields.PASSWORD));
		setLastActivity(b.getDate(PersonFields.LAST_ACTIVITY ));
		setAccountStatus((short)b.getInt(PersonFields.ACCOUNT_STATUS));
		setAccountType((short)b.getInt(PersonFields.ACCOUNT_TYPE));
		setDS((short)b.getInt(PersonFields.DS));
		setSmokes((short)b.getInt(PersonFields.SMOKE));
		setDrinks((short)b.getInt(PersonFields.DRINK));
	}
	
	public void makeSearchPojoFromBson(DBObject bson) {
		BasicDBObject b = (BasicDBObject) bson;
		setId((ObjectId)b.get(PersonFields.ID));
		setDob( (Date)b.getDate(PersonFields.DOB));
		setZipcode(b.getInt(PersonFields.ZIPCODE));
		setGender( (short) b.getInt(PersonFields.GENDER));
		setEthnicity( (short)b.getInt(PersonFields.ETHNICITY));
		setHaskids( (short)b.getInt(PersonFields.HASKIDS));
		setHeight((short)b.getInt(PersonFields.HEIGHT));
		setReligion((short)b.getInt(PersonFields.RELIGION));
		setLFStatus((List<Short>)b.get(PersonFields.LF_STATUS));
		setBC((short)b.getInt(PersonFields.BC));
		setPhoto1Location(b.getString(PersonFields.PHOTO1_LOCATION));
		setPhoto2Location(b.getString(PersonFields.PHOTO2_LOCATION));
		setPhoto3Location(b.getString(PersonFields.PHOTO3_LOCATION));
		setAlias(b.getString(PersonFields.ALIAS));
		setDS((short)b.getInt(PersonFields.DS));
		setSmokes((short)b.getInt(PersonFields.SMOKE));
		setDrinks((short)b.getInt(PersonFields.DRINK));
				
	}
	public void makeRegistrationPojoFromBson(DBObject bson) {
		BasicDBObject b = (BasicDBObject) bson;
		setZipcode(b.getInt(PersonFields.ZIPCODE));
		setAlias(b.getString(PersonFields.ALIAS));
		setPassword(b.getString(PersonFields.PASSWORD));
		setEmail(b.getString(PersonFields.EMAIL));
		setId(b.getObjectId(PersonFields.ID));
	}
	/**
	 * Constructs a <code>String</code> with all attributes
	 * in name = value format.
	 *
	 * @return a <code>String</code> representation 
	 * of this object.
	 */
	public String toString()
	{
	    final String TAB = "\n";
	    
	    String retValue = "";
	    
	    retValue = "PersonPojo ( "
	        + super.toString() + TAB
	        + "Dob = " + this.dob.toString() + TAB
	        + "zipcode = " + this.zipcode + TAB
	        + "gender = " + this.gender + TAB
	        + "ethnicity = " + this.ethnicity + TAB
	        + "haskids = " + this.haskids + TAB
	        + "height = " + this.height + TAB
	        + "religion = " + this.religion + TAB
	        + "lookingForStatus = " + this.lfstatus + TAB
	        + "bodyComposition = " + this.bc + TAB
	        + "photo1Location = " + this.photo1Location + TAB
	        + "photo2Location = " + this.photo2Location + TAB
	        + "photo3Location = " + this.photo3Location + TAB
	        + "email = " + this.email + TAB
	        + "alias = " + this.alias + TAB
	        + "password = " + this.password + TAB
	        + "lastActivity = " + this.lastActivity + TAB
	        + "accountStatus = " + this.accountStatus + TAB
	        + "accountType = " + this.accountType + TAB
	        + "datingStatus = " + this.dstatus + TAB
	        + "emailVerified = " + this.emailVerified + TAB
	        + "_id = " + this._id + TAB
	        + " )";
	
	    return retValue;
	}
	
	
	
	
}

package cn.edu.pku.telephonebook.contact;
/**
 * 
 * @author wangjie
 *
 */
public class Contact implements Comparable<Contact>{
	private int raw_contact_id = -1;
    private Adress adress = new Adress();
    private Email email = new Email();
    private Event event = new Event();
    private Group group = new Group();
    private Im im = new Im();
    private Nickname nickname = new Nickname();
    private Note note = new Note();
    private Organization organization = new Organization();
    private Phone phone = new Phone();
    private Photo photo = new Photo();
    private StructuredName structured_name = new StructuredName();
    private Website website = new Website();
	
    public Contact(){
    	
    }
    
	public Contact(int _raw_contact_id, Adress _adress, Email _email, 
			       Event _event, Group _group, Im _im, Nickname _nickname,
			       Note _note, Organization _organization, Phone _phone, Photo _photo, 
			        StructuredName _structured_name, Website _website){
		raw_contact_id = _raw_contact_id;
		adress = _adress;
		email = _email;
		event = _event;
		group = _group;
		im = _im;
		nickname = _nickname;
		note = _note;
		organization = _organization;
		phone = _phone;
		photo = _photo;
		structured_name = _structured_name;
		website = _website;	
	}
	
	public void setRawContactId(int _raw_contact_id){
		raw_contact_id = _raw_contact_id;
	}

	public void setAdress(Adress _adress){
		adress = _adress;
	}
	
	public void setEmail(Email _email){
		email = _email;
	}
	
	public void setEvent(Event  _event){
		event = _event;
	}
	
	public void setGroup(Group _group){
		group = _group;
	}
	
	public void setIm(Im _im){
		im = _im;
	}
	
	public void setNickname(Nickname _nickname){
		nickname = _nickname;
	}
	
	public void setNote(Note _note){
		note = _note;
	}
	
	public void setOrganization(Organization _organization){
		organization = _organization;
	}
	
	public void setPhone(Phone _phone){
		phone = _phone;
	}
	
	public void setPhoto(Photo _photo){
		photo = _photo;
	}
	
	public void setStructuredName(StructuredName _structured_name){
		structured_name = _structured_name;
	}
	
	public void setWebsite(Website _website){
		website = _website;
	}
	
	public int getRawContactId(){
		return raw_contact_id;
	}

	public Adress getAdress(){
		return adress;
	}
	
	public Email getEmail(){
		return email;
	}
	
	public Event getEvent(){
		return event;
	}
	
	public Group getGroup(){
		return group;
	}
	
	public Im getIm(){
		return im;
	}
	
	public Nickname getNickname(){
		return nickname;
	}
	
	public Note getNote(){
		return note;
	}
	
	public Organization getOrganization(){
		return organization;
	}
	
	public Phone getPhone(){
		return phone;
	}
	
	public Photo getPhoto(){
		return photo;
	}
	
	public StructuredName getStructuredName(){
		return structured_name;
	}
	
	public Website getWebsite(){
		return website;
	}
	
	
	public int compareTo(Contact other){
		return (structured_name.getLetters()).compareToIgnoreCase(other.getStructuredName().getLetters());
	}
}

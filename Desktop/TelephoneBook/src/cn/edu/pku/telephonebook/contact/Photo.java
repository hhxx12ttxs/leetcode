package cn.edu.pku.telephonebook.contact;

/**
 * Í·Ïñ
 * @author wangjie
 *
 */
public class Photo {
	private int photo_id;
	private byte[] photo;//(Alias)PHOTO
	
	public Photo(){
		
	}
	
	public void setId(int _photo_id){
		photo_id = _photo_id;
	}
	
	public void setPhoto(byte[] _photo){
		photo = _photo;
	}
	
	public int getPhotoId(){
		return photo_id;
	}
	
	public byte[] getPhoto(){
		return photo;
	}
}

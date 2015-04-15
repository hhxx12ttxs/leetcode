package cn.edu.pku.telephonebook.contact;

import java.util.HashMap;

public class Im {
	private HashMap<String, String> qq;//PROTOCOL_QQ 
	private HashMap<String, String> msn;//PROTOCOL_MSN
	private HashMap<String, String> google_talk;//PROTOCOL_GOOGLE_TALK
	private HashMap<String, String> skype;//PROTOCOL_SKYPE
	private HashMap<String, String> yahoo;//TYPE_YAHOO
	private HashMap<String, String> icq;//PROTOCOL_ICQ;
	private HashMap<String, String> jabber;//PROTOCOL_JABBER;
	private HashMap<String, String> netmeeting;//PROTOCOL_NETMEETING
	private HashMap<String, String> wechat;//PROTOCOL_AIM  н╒пе
	
	public Im(){
		
	}
	
	public Im(HashMap<String, String> _qq, HashMap<String, String> _msn, HashMap<String, String> _google_talk,
			HashMap<String, String> _skype, HashMap<String, String> _yahoo, HashMap<String, String> _icq,
			HashMap<String, String> _jabber, HashMap<String, String> _netmeeting, HashMap<String, String> _wechat){
		qq = new HashMap<String, String>(_qq);
		msn = new HashMap<String, String>(_msn);
		google_talk = new HashMap<String, String>(_google_talk);
		skype = new HashMap<String, String>(_skype);
		yahoo = new HashMap<String, String>(_yahoo);
		icq = new HashMap<String, String>(_icq);
		jabber = new HashMap<String, String>(_jabber);
		netmeeting = new HashMap<String, String>(_netmeeting);
		wechat = new HashMap<String, String>(_wechat);
	}
	
	public void setQQ(HashMap<String, String> _qq){
		qq = new HashMap<String, String>(_qq);
	}
	
	public void setMSN(HashMap<String, String> _msn){
		msn = new HashMap<String, String>(_msn);
	}
	
	public void setGoogleTalk(HashMap<String, String> _google_talk){
		google_talk = new HashMap<String, String>(_google_talk);
	}
	
	public void setSkype(HashMap<String, String> _skype){
		skype = new HashMap<String, String>(_skype);
	}
	
	public void setYahoo(HashMap<String, String> _yahoo){
		yahoo = new HashMap<String, String>(_yahoo);
	}
	
	public void setICQ(HashMap<String, String> _icq){
		icq = new HashMap<String, String>(_icq);
	}
	
	public void setJabber(HashMap<String, String> _jabber){
		jabber = new HashMap<String, String>(_jabber);
	}
	
	public void setNetMeeting(HashMap<String, String> _netmeeting){
		netmeeting = new HashMap<String, String>(_netmeeting);
	}
	
	public void setWeChat(HashMap<String, String> _wechat){
		wechat = new HashMap<String, String>(_wechat);
	}
	
	public HashMap<String, String> getQQ(){
		return qq;
	}
	
	public HashMap<String, String> getMSN(){
		return msn;
	}
	
	public HashMap<String, String> getGoogleTalk(){
		return google_talk;
	}
	
	public HashMap<String, String> getSkype(){
		return skype;
	}
	
	public HashMap<String, String> getYahoo(){
		return yahoo;
	}
	
	public HashMap<String, String> getICQ(){
		return icq;
	}
	
	public HashMap<String, String> getJabber(){
		return jabber;
	}
	
	public HashMap<String, String> getNetMeeting(){
		return netmeeting;
	}
	
	public HashMap<String, String> getWeChat(){
		return wechat;
	}
}

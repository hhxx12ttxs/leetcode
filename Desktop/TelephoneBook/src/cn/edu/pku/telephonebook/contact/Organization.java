package cn.edu.pku.telephonebook.contact;

public class Organization {
    private String company;
    
    public Organization(){
    	
    }
    
    public Organization(String _company){
    	company = _company;
    }
    
    public void setCompany(String _company){
    	company = _company;
    }
    
    public String getCompany(){
    	return company;
    }
}

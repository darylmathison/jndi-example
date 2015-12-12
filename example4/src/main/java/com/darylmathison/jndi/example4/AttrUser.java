package com.darylmathison.jndi.example4;

import javax.naming.Name;
import javax.naming.directory.BasicAttributes;

import javax.naming.directory.Attributes;
import javax.naming.directory.Attribute;
import javax.naming.directory.BasicAttribute;
import javax.naming.NameNotFoundException;

import javax.naming.NamingException;

public class AttrUser extends DirContextAdapter {
	
	public static final String INIT_VALUE = "none";
	public static final String NAME_ATTR = "cn";
	public static final String UID_ATTR  = "uid";
	public static final String EMAIL_ATTR = "mail";
	public static final String SN_ATTR = "sn";
	
	private Attributes attrs;
	
	public AttrUser() {
		attrs = new BasicAttributes(true);
		Attribute oc = new BasicAttribute("objectclass");
		oc.add("inetOrgPerson");
		oc.add("top");
		
		attrs.put(oc);
		attrs.put(NAME_ATTR, INIT_VALUE);
		attrs.put(UID_ATTR, INIT_VALUE);
		attrs.put(EMAIL_ATTR, INIT_VALUE);
		attrs.put(SN_ATTR, INIT_VALUE);

	}
	public String getName() {
		String name = null;
		try {
			Attribute a = attrs.get(NAME_ATTR);
			name =  a.get().toString();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return name;
	}
	
	public void setName(String name) {
		Attribute a = attrs.get(NAME_ATTR);
		a.clear();
		a.add(name);
	}
	
	public String getUserid() {
		String userid = null;
		try {
			Attribute a = attrs.get(UID_ATTR);
			userid = a.get().toString();
		} catch(NamingException ne) {
			ne.printStackTrace();
		}
		return userid;
	}
	
	public void setUserid(String userid) {
		Attribute a = attrs.get(UID_ATTR);
		a.clear();
		a.add(userid);
	}
	
	public String getEmail() {
		String email = null;
		try {
			Attribute a = attrs.get(EMAIL_ATTR);
			email = a.get().toString();
		} catch(NamingException ne) {
			ne.printStackTrace();
		}
		return email;
	}
	public void setEmail(String email) {
		Attribute a = attrs.get(EMAIL_ATTR);
		a.clear();
		a.add(email);
	}
	
	public void setLastname(String lastname) {
		Attribute a = attrs.get(SN_ATTR);
		a.clear();
		a.add(lastname);
	}
	
	public String getLastname() {
		String lastname = null;
		try {
			Attribute a = attrs.get(SN_ATTR);
			lastname = a.get().toString();
		} catch(NamingException ne) {
			ne.printStackTrace();
		}
		return lastname;
		
	}
	public Attributes getAttributes(Name arg0) throws NamingException {
		return getAttributes(arg0.toString());
	}
	
	public Attributes getAttributes(String arg0) throws NamingException {
		if(arg0 != null && arg0.length() != 0) {
			throw new NameNotFoundException();
		}
		return (Attributes)attrs.clone();
	}
	
	@Override
	public Attributes getAttributes(Name arg0, String[] arg1)
			throws NamingException {
		return getAttributes(arg0.toString(), arg1);
	}
	
	@Override
	public Attributes getAttributes(String arg0, String[] arg1)
			throws NamingException {
		if (arg0 != null && arg0.length() > 0) {
			throw new NameNotFoundException();
		}
		
		Attributes ret = new BasicAttributes(true);
		
		for (String id: arg1) {
			ret.put(attrs.get(id));
		}
		
		return ret;
	}
}

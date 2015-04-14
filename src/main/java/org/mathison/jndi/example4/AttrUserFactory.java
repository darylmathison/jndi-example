package org.mathison.jndi.example4;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.Name;
import javax.naming.directory.Attributes;
import javax.naming.directory.Attribute;
import javax.naming.spi.DirObjectFactory;
import javax.naming.directory.DirContext;

public class AttrUserFactory implements DirObjectFactory {

	@Override
	public Object getObjectInstance(Object arg0, Name arg1, Context arg2,
			Hashtable<?, ?> arg3) throws Exception {
		// because this is for an attributes based object, this factory method simply returns null.
		return null;
	}

	@Override
	public Object getObjectInstance(Object arg0, Name arg1, Context arg2,
			Hashtable<?, ?> arg3, Attributes arg4) throws Exception {
		AttrUser u = null;
		if (checkObject(arg0, arg4)) {
		    u = new AttrUser();
			Attribute a = arg4.get(AttrUser.NAME_ATTR);
			u.setName(a.get().toString());
			
			a = arg4.get(AttrUser.UID_ATTR);
			u.setUserid(a.get().toString());
			
			a = arg4.get(AttrUser.EMAIL_ATTR);
			u.setEmail(a.get().toString());
			
			a = arg4.get(AttrUser.SN_ATTR);
			u.setLastname(a.get().toString());
		}

		return u;
	}
	
	private boolean checkObject(Object o, Attributes att) {
		boolean isAttrUser = false;
		String[] ids = { AttrUser.NAME_ATTR,
				         AttrUser.SN_ATTR,
				         AttrUser.EMAIL_ATTR,
				         AttrUser.UID_ATTR };
				         
		
		if (o instanceof DirContext) {
			if(att != null && att.size() >= 4) {
				boolean areAttrThere = true;
				areAttrThere &= (att.get(AttrUser.NAME_ATTR) != null);
				areAttrThere &= (att.get(AttrUser.SN_ATTR) != null);
				areAttrThere &= (att.get(AttrUser.EMAIL_ATTR) != null);
				areAttrThere &= (att.get(AttrUser.UID_ATTR) != null);
				isAttrUser = areAttrThere;
			}
			
		}
	    return isAttrUser;
	}

}

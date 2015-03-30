package org.mathison.jndi.example3;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.Name;
import javax.naming.Reference;
import javax.naming.RefAddr;
import javax.naming.directory.Attributes;
import javax.naming.spi.DirObjectFactory;

public class ReferUserFactory implements DirObjectFactory {

	@Override
	public Object getObjectInstance(Object arg0, Name arg1, Context arg2,
			Hashtable<?, ?> arg3, Attributes arg4) throws Exception {
		return getObjectInstance(arg0, arg1, arg2, arg3);
	}

	@Override
	public Object getObjectInstance(Object arg0, Name arg1, Context arg2,
			Hashtable<?, ?> arg3) throws Exception {
		ReferUser user = null;
		if (arg0 instanceof Reference){
			Reference ref = (Reference)arg0;
			if(ref.getClassName().equals(ReferUser.class.getName())) {
				RefAddr name = ref.get("name");
				RefAddr uid  = ref.get("uid");
				RefAddr email = ref.get("mail");
				
				user = new ReferUser();
				user.setName(name.getContent().toString());
				user.setEmail(email.getContent().toString());
				user.setUserid(uid.getContent().toString());
			}
		}
		
		return user;
	}

}

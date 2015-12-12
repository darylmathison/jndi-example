package com.darylmathison.jndi.example3;

import javax.naming.NamingException;
import javax.naming.Reference;
import javax.naming.Referenceable;
import javax.naming.StringRefAddr;

import com.darylmathison.jndi.example2.User;

public class ReferUser extends User implements Referenceable {

	public Reference getReference() throws NamingException {
		Reference ref = new Reference(this.getClass().getName(),
									  ReferUserFactory.class.getName(),
									  null);
		ref.add(new StringRefAddr("name", getName()));
		ref.add(new StringRefAddr("mail", getEmail()));
		ref.add(new StringRefAddr("uid", getUserid()));
		
		return ref;
	}

}

package org.mathison.jndi.example4;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;


public class AttributeLookup {
	
	static Hashtable<String, String> getEnv() {
		Hashtable<String, String> env = new Hashtable<String, String>();
		
		env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
		env.put(Context.PROVIDER_URL, "ldap://localhost:10389/ou=Users,dc=example,dc=com");
		env.put(Context.OBJECT_FACTORIES, AttrUserFactory.class.getName());
		
		return env;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DirContext ctx = null;
		try {
			ctx = new InitialDirContext(getEnv());
			
			AttrUser user = new AttrUser();
			user.setEmail("joey@example.com");
			user.setLastname("Bourne");
			user.setName("Joey");
			user.setUserid("joey");
			
			ctx.rebind("uid=" + user.getUserid(), user);
			
			
			// read 
			AttrUser newUser = (AttrUser)ctx.lookup("uid=" + user.getUserid());
			System.out.println("email is " + newUser.getEmail());
			
		} catch (NamingException e) {
			e.printStackTrace();
		}
		finally{
			try {
				ctx.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
	}

}

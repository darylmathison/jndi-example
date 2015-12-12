package com.darylmathison.jndi.example2;

import javax.naming.Context;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.DirContext;
import javax.naming.NamingException;


import java.util.Hashtable;


public class JavaObjectLookup {

	static Hashtable<String, String> getEnv() {
		Hashtable<String, String> env = new Hashtable<String, String>();
		
		env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
		env.put(Context.PROVIDER_URL, "ldap://localhost:10389/ou=java,dc=example,dc=com");
		
		return env;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DirContext ctx = null;
		try {
			ctx = new InitialDirContext(getEnv());
			
			// first bind an object to the Directory
			User user = new User();
			user.setName("Joey");
			user.setUserid("joey");
			user.setEmail("joey@example.com");
			
			ctx.rebind("cn=joey", user);
			
			User u = (User)ctx.lookup("cn=joey");
			
			System.out.println( "User's email is " + u.getEmail());
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

package com.darylmathison.jndi.example3;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;

public class JavaObjectRefLookup {
	
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
			ReferUser user = new ReferUser();
			user.setName("Joey");
			user.setUserid("joey");
			user.setEmail("joey@example.com");
			
			ctx.rebind("cn=joey", user);
			
			ReferUser u = (ReferUser)ctx.lookup("cn=joey");
			
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

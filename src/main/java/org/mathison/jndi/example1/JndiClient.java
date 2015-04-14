package org.mathison.jndi.example1;

import javax.naming.Context;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.DirContext;
import javax.naming.directory.Attributes;
import javax.naming.NamingException;

import java.util.Hashtable;


public class JndiClient {
	
	static Hashtable<String, String> getEnv() {
		Hashtable<String, String> env = new Hashtable<String, String>();
		
		env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
		env.put(Context.PROVIDER_URL, "ldap://localhost:10389/dc=example,dc=com");
		
		return env;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			DirContext ctx = new InitialDirContext(getEnv());
			Attributes attr = ctx.getAttributes("uid=mborn,ou=Users");
			System.out.println("Mborn's name is " + attr.get("cn").get());
			ctx.close();
		} catch (NamingException e) {
			e.printStackTrace();
		}
		
		
	}

}

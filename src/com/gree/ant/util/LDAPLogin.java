package com.gree.ant.util;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;
import java.util.HashMap;
import java.util.Properties;

public class LDAPLogin {
    
	private final static String LDAP_URL = "LDAP://10.1.1.1";
	private final static String LDAP_PATH = "OU=格力电器,DC=it2004,DC=gree,DC=com,DC=cn";
	private final static String UID_PREFIX = "it2004\\";


public static HashMap authenticate(String uid,String psw){
    Properties env = new Properties();
    env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
//  set security credentials, note using simple cleartext authentication
    env.put(Context.SECURITY_AUTHENTICATION, "simple");
    env.put(Context.SECURITY_PRINCIPAL, UID_PREFIX+uid);
    env.put(Context.SECURITY_CREDENTIALS, psw);
//  connect to my domain controller
    
    env.put(Context.PROVIDER_URL, LDAP_URL);
    
    HashMap ret = new HashMap();
    
    try {
        //Create the initial directory context
        LdapContext ctx = new InitialLdapContext(env, null);

        //Create the search controls
        SearchControls searchCtls = new SearchControls();

        //Specify the search scope
        searchCtls.setSearchScope(SearchControls.SUBTREE_SCOPE);
        //specify the LDAP search filter
        //String searchFilter = "(&(objectCategory=person)(objectClass=user)(name=180025))";
        String searchFilter = "(&(objectCategory=person)(objectClass=user)(name="+uid+"))";

//        Specify the Base for the search
            //String searchBase = "OU=USISH,DC=usish,DC=com,DC=cn";
//        initialize counter to total the group members
//        Specify the attributes to return
            //membersof
            String returnedAtts[] = {"displayname","department","company","mail"};
            searchCtls.setReturningAttributes(returnedAtts);

//        Search for objects using the filter
            NamingEnumeration answer = ctx.search(LDAP_PATH, searchFilter, searchCtls);

            
//        Loop through the search results
            if (answer.hasMoreElements()) {
                SearchResult sr = (SearchResult) answer.next();
                //System.out.println(">>>" + sr.getName());

                Attributes attrs = sr.getAttributes();
                if (attrs != null) {
                    try {
                      for (int i = 0; i < returnedAtts.length; i++) {
                          ret.put(attrs.get(returnedAtts[i]).getID(), attrs.get(returnedAtts[i]).get());
                          //System.out.println(attrs.get(returnedAtts[i]).getID()+": " + attrs.get(returnedAtts[i]).get());
                      }

                    } catch (Exception e) {
                        e.printStackTrace();
                        System.err.println("Problem listing membership: " + e);
                    }
                }
            }
            //System.out.println("Total groups: " + totalResults);
            ctx.close();
        }catch (NamingException e) {
        	 System.out.print(":( 没有此用户");
            return null;
            //e.printStackTrace();
            //System.err.println("Problem searching directory: " + e);
        }
    return ret;
      
}
}

package api.endpoints;

/*
 Swagger URI---> https://petstore.swagger.io/
 createUser(Post)---> https://petstore.swagger.io/v2/user
 getUser(Get)--->https://petstore.swagger.io/v2/user/{username}
 updateUser(Put)--->https://petstore.swagger.io/v2/user/{username}
 deleteUser(Delete)--->https://petstore.swagger.io/v2/user/{username}
 */

public class Routes {
	
	public static String base_url="https://petstore.swagger.io/v2";
	
	//User Module
	
	public static String post_url=base_url+"/user";
	public static String get_url=base_url+"/user/{username}";
	public static String update_url=base_url+"/user/{username}";
	public static String delete_url=base_url+"/user/{username}";
	
	//Store Module
	
	 
	
	//Pet Module

}

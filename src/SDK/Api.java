package SDK;

/**
 * Created by Nicolaj on 18/11/2015.
 */

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

public class Api {

    Client client = Client.create();

    //You use the WebResource object to build requests to send to the web resource and to process responses
    //returned from the web resource.
    //For example, you can use the WebResource object for HTTP GET, PUT, POST, and DELETE requests.
    WebResource webResource = client.resource("http://localhost:8888/api");

    //GET Request: Use the get() method in the WebResource class to submit an HTTP GET request to the web resource:
    String s = webResource.get(String.class);


}

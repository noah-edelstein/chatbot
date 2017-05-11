/**
 * 
 */
package example.jbot.spoonacular;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.p.spoonacularrecipefoodnutritionv1.SpoonacularAPIClient;

/**
 * @author noah.edelstein
 *
 */
public class SpoonacularClientFactory {

	// Initializing Object Mapper for Serialization and Deserialization purposes
	public static ObjectMapper mapper = new ObjectMapper()
	{
		private static final long serialVersionUID = -174113593500315394L;
		{
			configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			setSerializationInclusion(JsonInclude.Include.NON_NULL);
		}
	};

	// Configuration parameters
	private static String xMashapeKey = "aDFavnQoytmshh0XVqB9ujdXmClFp1webq5jsnRXqBhZmQmyiW"; // The Mashape application you want to use for this session.

	public static SpoonacularAPIClient newClient() {
		return new SpoonacularAPIClient(xMashapeKey);
	}
	
}

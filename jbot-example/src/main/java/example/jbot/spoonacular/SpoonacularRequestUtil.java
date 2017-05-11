/**
 * 
 */
package example.jbot.spoonacular;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.ObjectMapper;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

/**
 * @author noah.edelstein
 *
 */
public class SpoonacularRequestUtil {

	private SpoonacularRequestUtil() {}
	private static final String MASHAPEKEY = "aDFavnQoytmshh0XVqB9ujdXmClFp1webq5jsnRXqBhZmQmyiW";
	private static final Logger LOGGER = LoggerFactory.getLogger(SpoonacularRequestUtil.class);
	private static final String UTF8 = "UTF-8";
	
	static {
		// Only one time
		Unirest.setObjectMapper(new ObjectMapper() {
		    private com.fasterxml.jackson.databind.ObjectMapper jacksonObjectMapper
		                = new com.fasterxml.jackson.databind.ObjectMapper();

		    public <T> T readValue(String value, Class<T> valueType) {
		        try {
		            return jacksonObjectMapper.readValue(value, valueType);
		        } catch (IOException e) {
		            throw new RuntimeException(e);
		        }
		    }

		    public String writeValue(Object value) {
		        try {
		            return jacksonObjectMapper.writeValueAsString(value);
		        } catch (JsonProcessingException e) {
		            throw new RuntimeException(e);
		        }
		    }
		});
	}
	
	public static List<Result> complexRecipeSearch(String cuisine, String excludeIngredients,
			String query, String type) {
		List<Result> resultList = null;
		try {
			String queryString = "addRecipeInformation=true&cuisine=" + URLEncoder.encode(cuisine.trim(), UTF8) + "&excludeIngredients=" + 
					URLEncoder.encode(excludeIngredients.trim(), UTF8) + 
					"&fillIngredients=false&instructionsRequired=true&limitLicense=false&maxCalories=1500&maxCarbs=45&maxFat=100&maxProtein=100&minCalories=150&minCarbs=5&minFat=5&minProtein=5&number=10&offset=0&query=" + 
					URLEncoder.encode(query, UTF8) + 
					"&ranking=2&type=" + URLEncoder.encode(type.trim(), UTF8);		
			HttpResponse<ComplexRecipes> complexRecipeList = Unirest.get("https://spoonacular-recipe-food-nutrition-v1.p.mashape.com/recipes/searchComplex?" + queryString)
					.header("X-Mashape-Key", MASHAPEKEY)
					.header("Accept", "application/json").asObject(ComplexRecipes.class);
			LOGGER.debug("status: " + complexRecipeList.getStatusText());
			LOGGER.debug(Integer.valueOf(complexRecipeList.getStatus()).toString());
			InputStream in = complexRecipeList.getRawBody();
			Reader reader = new InputStreamReader(in);
			//reader.

			ComplexRecipes list = complexRecipeList.getBody();
			List<Result> results = list.getResults();
			for (Result result : results) {
				LOGGER.info(result.getTitle());
			}

		} catch (UnirestException e) {
			LOGGER.error(e.getMessage(), e);
		} catch (UnsupportedEncodingException e) {
			LOGGER.error(e.getMessage(), e);
		}
		return resultList;
	}
	
}

/**
 * 
 */
package example.jbot.spoonacular;

import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.List;

import org.junit.Test;
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
public class SpoonacularClientTest {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SpoonacularClientTest.class);
	
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
	
	
	@Test
	public void searchRecipesComplexTest() {
		
		 try {
			HttpResponse<ComplexRecipes> complexRecipeList = Unirest.get("https://spoonacular-recipe-food-nutrition-v1.p.mashape.com/recipes/searchComplex?addRecipeInformation=false&cuisine=american&excludeIngredients=coconut%2C+mango&fillIngredients=false&includeIngredients=onions%2C+lettuce%2C+tomato&instructionsRequired=true&intolerances=peanut%2C+shellfish&limitLicense=false&maxCalories=1500&maxCarbs=100&maxFat=100&maxProtein=100&minCalories=150&minCarbs=5&minFat=5&minProtein=5&number=10&offset=0&query=burger&ranking=2&type=main+course")
					.header("X-Mashape-Key", "aDFavnQoytmshh0XVqB9ujdXmClFp1webq5jsnRXqBhZmQmyiW")
					.header("Accept", "application/json").asObject(ComplexRecipes.class);
			ComplexRecipes list = complexRecipeList.getBody();
			List<Result> results = list.getResults();
			for (Result result : results) {
				LOGGER.info(result.getTitle());
			}

		} catch (UnirestException e) {
			fail(e.getMessage());
		}
				
	}
	
}

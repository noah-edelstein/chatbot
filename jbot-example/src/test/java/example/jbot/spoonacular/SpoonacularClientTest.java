/**
 * 
 */
package example.jbot.spoonacular;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.mashape.p.spoonacularrecipefoodnutritionv1.SpoonacularAPIClient;

/**
 * @author noah.edelstein
 *
 */
public class SpoonacularClientTest {

	
	@Test
	public void createClientTest() {
		SpoonacularAPIClient client = SpoonacularClientFactory.newClient();
		assertNotNull(client);
	}
	
}

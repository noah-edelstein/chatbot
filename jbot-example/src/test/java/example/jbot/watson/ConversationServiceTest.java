/**
 * 
 */
package example.jbot.watson;

import org.junit.Test;

import com.ibm.watson.developer_cloud.conversation.v1.model.MessageRequest;

/**
 * @author noah.edelstein
 *
 */
public class ConversationServiceTest {

	@Test
	public void createServiceTest() {
		ConversationClient client = new ConversationClient();
		MessageRequest request = new MessageRequest.Builder()
				  .inputText("what is a good food?")
				  // Replace with the context obtained from the initial request
				  //.context(...)
				  .build();
		client.sendMessage(request);
	}
	
}

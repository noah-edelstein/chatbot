/**
 * 
 */
package example.jbot.watson;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ibm.watson.developer_cloud.conversation.v1.ConversationService;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageRequest;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageResponse;
import com.ibm.watson.developer_cloud.http.ServiceCall;


/**
 * @author noah.edelstein
 *
 */
public class ConversationClient {
	
	public ConversationClient() {}
	
	private final String VERSIONDATE = "2017-05-09";
	private final String USERNAME = "006ae213-b4ec-4114-82d5-5358b17abd7b";
	private final String PASSWORD = "BIder6eiHlbF";
	private final String WORKSPACEID = "d24fb147-18e1-480c-a1b6-d80a45cca0dc";
	
	private ConversationService service = new ConversationService(VERSIONDATE, USERNAME, PASSWORD);
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ConversationClient.class); 
	
	public String sendMessage(String message) {
		MessageRequest request = new MessageRequest.Builder()
				  .inputText(message)
				  // Replace with the context obtained from the initial request
				  //.context(...)
				  .build();
		ServiceCall<MessageResponse> serviceCall = service.message(WORKSPACEID, request);
		MessageResponse response =  serviceCall.execute();
		List<String> responseList = response.getText();
		StringBuilder sb = new StringBuilder();
		for (String r : responseList) {
			sb.append(r);
		}
		LOGGER.info(sb.toString());
		return sb.toString();
	}
}
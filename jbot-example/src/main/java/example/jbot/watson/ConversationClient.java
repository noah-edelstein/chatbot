/**
 * 
 */
package example.jbot.watson;

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
	
	public boolean sendMessage(MessageRequest request) {
		boolean status = false;
		ServiceCall<MessageResponse> serviceCall = service.message(WORKSPACEID, request);
		MessageResponse response =  serviceCall.execute();
		LOGGER.info(response.getInputText());
		System.out.println(response.getText());
		return true;
	}
}
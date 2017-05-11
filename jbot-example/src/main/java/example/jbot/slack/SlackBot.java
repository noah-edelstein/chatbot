
package example.jbot.slack;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

import example.jbot.spoonacular.Result;
import example.jbot.spoonacular.SpoonacularRequestUtil;
import example.jbot.watson.ConversationClient;
import me.ramswaroop.jbot.core.slack.Bot;
import me.ramswaroop.jbot.core.slack.Controller;
import me.ramswaroop.jbot.core.slack.EventType;
import me.ramswaroop.jbot.core.slack.models.Event;
import me.ramswaroop.jbot.core.slack.models.Message;

/**
 * A Slack Bot sample. You can create multiple bots by just extending {@link Bot} class like this
 * one.
 *
 * @author ramswaroop
 * @version 1.0.0, 05/06/2016
 */
@Component
public class SlackBot extends Bot {

	private static final Logger logger = LoggerFactory.getLogger(SlackBot.class);
	private ConversationClient client = new ConversationClient();

	/**
	 * Slack token from application.properties file. You can get your slack token next
	 * <a href="https://my.slack.com/services/new/bot">creating a new bot</a>.
	 */
	@Value("${slackBotToken}")
	private String slackToken;

	@Override
	public String getSlackToken() {
		return slackToken;
	}

	@Override
	public Bot getSlackBot() {
		return this;
	}

	/**
	 * Invoked when the bot receives a direct mention (@botname: message) or a direct message. NOTE:
	 * These two event types are added by jbot to make your task easier, Slack doesn't have any
	 * direct way to determine these type of events.
	 *
	 * @param session
	 * @param event
	 */
/*	@Controller(events = { EventType.CHANNEL_JOINED, EventType.HELLO, EventType.DIRECT_MENTION,
			EventType.DIRECT_MESSAGE }, next = "sendMessage")
	public void onReceiveDM(WebSocketSession session, Event event) {
		startConversation(event, "sendMessage");
		reply(session, event, new Message(
				"Welcome to the Diabetics Education Chat. I am DEdA, your Diabetics Education Assistant. I can help you find foods and recipes that meet your dietary needs.  If at any point you want to start over, let me know.Would you like help with food options or recipes?"));
	}*/

	/**
	 * method to send message to ConversationService
	 */
	/*@Controller
	public void sendMessage(WebSocketSession session, Event event) {
		String message = event.getText();
		String response = client.sendMessage(message);
		reply(session, event, new Message(response));
	}*/

	/**
	 * Invoked when bot receives an event of type message with text satisfying the pattern
	 * {@code ([a-z ]{2})(\d+)([a-z ]{2})}. For example, messages like "ab12xy" or "ab2bc" etc will
	 * invoke this method.
	 *
	 * @param session
	 * @param event
	 */
	/*
	 * @Controller(events = EventType.MESSAGE, pattern = "^([a-z ]{2})(\\d+)([a-z ]{2})$") public
	 * void onReceiveMessage(WebSocketSession session, Event event, Matcher matcher) {
	 * reply(session, event, new Message("First group: " + matcher.group(0) + "\n" +
	 * "Second group: " + matcher.group(1) + "\n" + "Third group: " + matcher.group(2) + "\n" +
	 * "Fourth group: " + matcher.group(3))); }
	 */

	/**
	 * Conversation feature of JBot. This method is the starting point of the conversation (as it
	 * calls {@link Bot#startConversation(Event, String)} within it. You can chain methods which
	 * will be invoked one after the other leading to a conversation. You can chain methods with
	 * {@link Controller#next()} by specifying the method name to chain with.
	 *
	 * @param session
	 * @param event
	 */
	/*
	 * @Controller(pattern = "(setup meeting)", next = "confirmTiming") public void
	 * setupMeeting(WebSocketSession session, Event event) { startConversation(event,
	 * "confirmTiming"); // start conversation reply(session, event, new
	 * Message("Cool! At what time (ex. 15:30) do you want me to set up the meeting?")); }
	 */

	/**
	 * This method is chained with {@link SlackBot#setupMeeting(WebSocketSession, Event)}.
	 *
	 * @param session
	 * @param event
	 */
	/*
	 * @Controller(next = "askTimeForMeeting") public void confirmTiming(WebSocketSession session,
	 * Event event) { reply(session, event, new Message("Your meeting is set at " + event.getText()
	 * + ". Would you like to repeat it tomorrow?")); nextConversation(event); // jump to next
	 * question in conversation }
	 */

	/**
	 * This method is chained with {@link SlackBot#confirmTiming(WebSocketSession, Event)}.
	 *
	 * @param session
	 * @param event
	 */
	/*
	 * @Controller(next = "askWhetherToRepeat") public void askTimeForMeeting(WebSocketSession
	 * session, Event event) { if (event.getText().contains("yes")) { reply(session, event, new
	 * Message("Okay. Would you like me to set a reminder for you?")); nextConversation(event); //
	 * jump to next question in conversation } else { reply(session, event, new
	 * Message("No problem. You can always schedule one with 'setup meeting' command."));
	 * stopConversation(event); // stop conversation only if user says no } }
	 */

	/**
	 * This method is chained with {@link SlackBot#askTimeForMeeting(WebSocketSession, Event)}.
	 *
	 * @param session
	 * @param event
	 */
	/*
	 * @Controller public void askWhetherToRepeat(WebSocketSession session, Event event) { if
	 * (event.getText().contains("yes")) { reply(session, event, new
	 * Message("Great! I will remind you tomorrow before the meeting.")); } else { reply(session,
	 * event, new Message("Oh! my boss is smart enough to remind himself :)")); }
	 * stopConversation(event); // stop conversation }
	 */

	@Controller(events = { EventType.DIRECT_MENTION,
			EventType.DIRECT_MESSAGE }, pattern = "(test connection)")
	public void checkConnection(WebSocketSession session, Event event) {
		String message = "hello";
		String response = client.sendMessage(message);
		reply(session, event, new Message(response));
	}

	@Controller(events = { EventType.DIRECT_MENTION,
			EventType.DIRECT_MESSAGE }, pattern = "(test recipe)")
	public void checkRecipe(WebSocketSession session, Event event) {
		List<Result> resultList = SpoonacularRequestUtil.complexRecipeSearch(
				"American, Italian, Polynesian", "Coconut, Lentil",
				 "salad", "Lunch");
		if (resultList == null) {
			reply(session, event,
					new Message("No recipes matched your request"));
		} else {
			reply(session, event,
					new Message("Here are some recipes which match your meal parameters:"));
			int i = 1;
			for ( Result r : resultList) {
				reply(session, event, new Message(i + ": " + r.getTitle()));
			}
		}

	}

}

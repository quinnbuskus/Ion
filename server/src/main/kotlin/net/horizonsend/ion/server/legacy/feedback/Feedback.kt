package net.horizonsend.ion.server.legacy.feedback

import net.kyori.adventure.audience.Audience
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.minimessage.MiniMessage.miniMessage

/**
 * @param type The type of feedback
 * @param message The feedback message, use "{index}" to insert variables into the message
 * @param parameters Variables to insert into the message
 * @see FeedbackType
 */
internal fun Audience.sendFeedbackAction(type: FeedbackType, message: String, vararg parameters: Any): Unit =
	sendActionBar(parseFeedback(type, message, parameters.toList()))

/**
 * @param type The type of feedback
 * @param message The feedback message, use "{index}" to insert variables into the message
 * @param parameters Variables to insert into the message
 * @see FeedbackType
 */
internal fun Audience.sendFeedbackMessage(type: FeedbackType, message: String, vararg parameters: Any): Unit =
	sendMessage(parseFeedback(type, message, parameters.toList()))

/**
 * @param type The type of feedback
 * @param message The feedback message, use "{index}" to insert variables into the message
 * @param parameters Variables to insert into the message
 * @see FeedbackType
 */
internal fun Audience.sendFeedbackActionMessage(type: FeedbackType, message: String, vararg parameters: Any) {
	parseFeedback(type, message, parameters.toList()).also { feedback ->
		sendActionBar(feedback)
		sendMessage(feedback)
	}
}

private fun parseFeedback(type: FeedbackType, message: String, parameters: Collection<Any>): Component {
	var newMessage = "<${type.colour}>$message"

	parameters.forEachIndexed { index, parameter ->
		newMessage = newMessage.replace(
			"{$index}",
			"<reset>${
			when (parameter) {
				is Number -> "$parameter"
				else -> "\"$parameter<reset>\""
			}
			}<${type.colour}>"
		)
	}

	return miniMessage().deserialize(newMessage)
}

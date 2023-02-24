open class Chat(
    val id: Int,
    val isRead: Boolean
)

class Message(
    id: Int,
    isRead: Boolean,
    var text: String,
    var isIncome: Boolean
): Chat(id, isRead)


object WallService {

    private val chats = mutableListOf<Chat>()
    private val messages = mutableListOf<Message>()

    class WriteSomethingToBeginChatException(message: String) : RuntimeException(message)
    class NoMessagesException(message: String) : RuntimeException(message)
    class NoUnreadChatsException(message: String) : RuntimeException(message)


    fun createChat(chat: Chat, message: Message): Int {

        if (message == messages.first()) {
            chats.add(chat)
            return chat.id
        }

        throw WriteSomethingToBeginChatException("Write something to begin chat")
    }


    fun getChats(): List<Chat> {

        val lastMsg = messages.last()

        val isContains = chats.contains(lastMsg)

        val chatsList = chats.filter { isContains }

        if (isContains) {
            return chatsList
        }

        throw NoMessagesException("Нет сообщений")
    }


    fun getUnreadChatsCount(): Int {

        val nonReadMsgsFilterIsRead = messages.filter { !it.isRead }
        val nonReadMessagesFilterIsIncome = nonReadMsgsFilterIsRead.filter { it.isIncome }

        if(nonReadMessagesFilterIsIncome.isNotEmpty()) {
            return chats.count()
        }

        throw NoUnreadChatsException("Нет непрочитанных чатов")
    }


    fun deleteChat(chatId: Int): Boolean {

        val chat = chats.find { it.id == chatId }
        chats.remove(chat)
        return true
    }



    private fun createMessage(message: Message): Int {
        messages.add(message)
        return message.id
    }



    fun updateMessage(messageId: Int, text: String): Int {
        val msg = messages.find { it.id == messageId }
        msg?.text = text
        return 1
    }



    fun deleteMessage(messageId: Int): Boolean {
        val msg = messages.find { it.id == messageId }
        messages.remove(msg)
        return true
    }


//    fun getChatMessages(chatId: Int, lastMsgId: Int, msgCount: Int): List<Message> {
//        val messagesList = mutableListOf<Message>()
//        val chat = chats.find {it.id == chatId}
//
//        return messagesList
//    }
}

fun main() {

}
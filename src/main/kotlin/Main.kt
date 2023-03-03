open class Chat(
    val id: Int,
    var isRead: Boolean,
    val messages: MutableCollection<Message>
)

class Message(
    id: Int,
    isRead: Boolean,
    var text: String,
    var isIncome: Boolean
): Chat(id, isRead, messages = mutableListOf())


object WallService {

    private val chats = mutableListOf<Chat>()
    private var chatPrivateId = 0;
    private var messagePrivateId = 0;

    class WriteSomethingToBeginChatException(message: String) : RuntimeException(message)
    class NoMessagesException(message: String) : RuntimeException(message)
    class NoUnreadChatsException(message: String) : RuntimeException(message)




    fun createChat(message: Message): Chat {

        println(message.id)

        val messagesCollection = mutableListOf<Message>()

        if (message.id == 0) {
            messagesCollection.add(message)
            val chat = Chat(id = chatPrivateId++, false, messages = messagesCollection)
            chats.add(chat)
            println(chats)
            return chat
        }

        throw WriteSomethingToBeginChatException("Write something to begin chat")
    }


    fun getChats(): List<Chat> {

        val getChats = chats.filter { it.messages.last() != null }

        if (getChats.isNotEmpty()) {
            println(getChats)
            return getChats
        }

        throw NoMessagesException("Нет сообщений")
    }


    fun getUnreadChatsCount(): Int {

        val findNonReadChats = chats.filter { it.messages.filter { message -> !message.isRead } .isNotEmpty() }

        val findNonReadChatsIsIncome = findNonReadChats.filter { it.messages.filter { message -> !message.isIncome  } .isNotEmpty()}

        if(findNonReadChatsIsIncome.isNotEmpty()) {
            println(findNonReadChatsIsIncome.count())
            return findNonReadChatsIsIncome.count()
        }


        throw NoUnreadChatsException("Нет непрочитанных чатов")
    }


    fun deleteChat(chatId: Int): Boolean {
        val chat = chats.find { it.id == chatId }
        chats.remove(chat)
        return true
    }



    fun createMessage(chatId: Int, text: String, isRead: Boolean, isIncome: Boolean): Message {
        val message = Message(id = messagePrivateId++, isRead, text, isIncome)
        println(message.text)
        return message
    }



    fun updateMessage(chatId: Int, messageId: Int, text: String): Int {

        val neededChat = chats.find { it.id == chatId }

        val msg = neededChat?.messages?.find { it.id == messageId }
        msg?.text = text
        return 1
    }



    fun deleteMessage(chatId: Int, messageId: Int): Boolean {

        val neededChat = chats.find { it.id == chatId }

        val msg = neededChat?.messages?.find { it.id == messageId }
        neededChat?.messages?.remove(msg)
        return true
    }


    fun getChatMessages(chatId: Int, lastMsgId: Int, msgCount: Int): List<Message>? {
        val earlierMessages = chats.find { it.id == chatId } ?.messages?.take(lastMsgId)
        val messagesWithCount = earlierMessages?.takeLast(msgCount)
        messagesWithCount?.forEach { it.isRead = true}
        return messagesWithCount
    }
}

fun main() {

}
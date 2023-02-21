open class Chat(
    val id: Int,
    val isRead: Int
)

class Message(
    id: Int,
    isRead: Int,
    var text: String,
    var type: String
): Chat(id, isRead)


object WallService {

    private val chats = mutableListOf<Chat>()
    private val messages = mutableListOf<Message>()
    private val indexes = arrayOf(0, 1)

    class WriteSomethingToBeginChatException(message: String) : RuntimeException(message)
    class NoUnreadChatsException(message: String) : RuntimeException(message)

    fun createChat(chat: Chat, message: Message): Int {
        if (message == messages.get(0)) {
            chats.add(chat)
            return chat.id
        }
        throw WriteSomethingToBeginChatException("Write something to begin chat")
    }


    fun getChats(): MutableCollection<Chat> {
        val chatsList = mutableListOf<Chat>()
        for (chat in chats) {
            if(messages.isNotEmpty()) {
                chatsList.add(chat)
                return chatsList
            }
            println("Нет сообщений")
        }
        return chats
    }

    fun getUnreadChatsCount(): Int {
        var count = 0;
        for (chat in chats) {
            if (chat.isRead == 0) {
                count++
                return count
            }
        }
        throw NoUnreadChatsException("You have not unread chats")
    }

    fun deleteChat(chatId: Int): Boolean {
        for (chat in chats) {
            if (chat.id == chatId) {
                chats.remove(chat)
                return true
            }
        }
        return false
    }

    private fun createMessage(message: Message): Int {
        messages.add(message)
        return message.id
    }

    fun updateMessage(messageId: Int, text: String): Int {

        val returnMsg = messages.filter { it.id == messageId }

        returnMsg.forEach { it.text == text }

//        for (message in messages) {
//            if (message.id == messageId) {
//                message.text = text
//                return 1
//            }
//        }
        return -1
    }

    fun deleteMessage(messageId: Int): Boolean {
        for (message in messages) {
            if (message.id == messageId) {
                messages.remove(message)
                return true
            }
        }
        return false
    }

//    fun getChatMessages(chatId: Int): MutableCollection<Chat> {
//        val messagesList = mutableListOf<Message>()
//
//        for (chat in chats) {
//            if (chatId == chat.id)
//        }
//    }
}

fun main() {

}
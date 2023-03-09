import javax.annotation.processing.Messager
import javax.swing.text.StyledEditorKit.BoldAction

data class Chat(val messages: MutableList<Message> = mutableListOf())
data class Message(val text: String, var income: Boolean = false, var red: Boolean = true)
class ChatNotFoundException() : RuntimeException()
object ChatService {

    private val chats = mutableMapOf<Int, Chat>()

    fun addMessage(userID: Int, message: Message) {
        chats.getOrPut(userID) { Chat() }.messages += message
    }

    fun getChatsWithNonReadMessage() = chats.values.filter { chat -> chat.messages.filter { !it.red }.isNotEmpty() }

    fun getChats() = chats.values.map { it }

//    fun getChats() = chats

    fun getLastMessages() = chats.values.map { chat -> chat.messages.lastOrNull { it.income }?.text ?: "No messages" }


    fun listOfMessagesFromChat(userID: Int, count: Int): List<Message> {
        val chat = chats[userID] ?: throw ChatNotFoundException()
        return chat.messages.filter { !it.income }.takeLast(count).onEach { it.red = true }
    }

    fun deleteMessage(userId: Int, message: Message) {
        val chat = chats[userId] ?: throw ChatNotFoundException()
        chat.messages.remove(message)
    }

    fun deleteChat(userId: Int) = chats.remove(userId)

    fun printChats() {
        println(chats)
    }
}

fun main() {

}



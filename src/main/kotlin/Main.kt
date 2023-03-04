import javax.annotation.processing.Messager
import javax.swing.text.StyledEditorKit.BoldAction

data class Chat(val messages: MutableList<Message> = mutableListOf())
data class Message(val text: String, var income: Boolean = false, var red: Boolean = true)

class ChatNotFoundException() : RuntimeException()
class NoMessagesException() : RuntimeException()
object ChatService {
    private val chats = mutableMapOf<Int, Chat>()

    fun addMessage(userID: Int, message: Message) {
        chats.getOrPut(userID) { Chat() }.messages += message
    }

    fun getChatsWithNonReadMessage() = chats.values.filter { chat -> chat.messages.filter { !it.red }.isNotEmpty() }

//    fun getChatsWithLastMessage(): List<Chat> {
//        val result = chats.values.filter { it.messages.isNotEmpty() }
//
//        if (result.isEmpty()) {
//            throw NoMessagesException()
//        }
//
//        return result
//    }

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
    println("Результат выполнения функции addMessage")
    ChatService.addMessage(1, Message("Hi"))
    ChatService.addMessage(1, Message("How r u doing"))
    ChatService.addMessage(2, Message("Hey dude", income = true, red = false))
    ChatService.addMessage(2, Message("Nice", income = true, red = false))
    ChatService.printChats()
    println()
    println("Результат выполнения функции getChatsWithLastMessage")
    println(ChatService.getChatsWithNonReadMessage())
    println()
    println("Результат выполнения функции getLastMessages")
    println(ChatService.getLastMessages())
    println()
    println("Результат выполнения функции listOfMessagesFromChat")
    println(ChatService.listOfMessagesFromChat(1, 1))
    println()
    println("Результат выполнения функции deleteMessage")
    ChatService.deleteMessage(1, Message("Hi"))
    ChatService.printChats()
    println()
    println("Результат выполнения функции deleteChat")
    ChatService.deleteChat(1)
    ChatService.printChats()
}



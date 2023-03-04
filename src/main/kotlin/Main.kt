data class Message(val text: String)
data class Chat(val messages: MutableList<Message> = mutableListOf())

object ChatService {

    private val chats = mutableMapOf<Int, Chat>()

    fun addMessage(userId: Int, message: Message) {
//        if (chats.containsKey(userId)) {
//            chats[userId]?.messages?.add(message)
//        } else {
//            val chat = Chat()
//            chat.messages += message
//            chats[userId] = chat
//        }
        chats.getOrPut(userId) { Chat() }.messages += message
    }
}


fun main() {

}




import org.junit.Test

import org.junit.Assert.*

class ChatServiceTest {

    @Test
    fun addMessage() {
        ChatService.addMessage(1, Message("Hi"))
        ChatService.addMessage(1, Message("How r u doing"))
        ChatService.addMessage(2, Message("Hey dude", income = true, red = false))
        ChatService.addMessage(2, Message("Nice", income = true, red = false))

        ChatService.printChats()
    }

    @Test
    fun getChatsWithNonReadMessage() {
        println(ChatService.getChatsWithNonReadMessage())
    }

    @Test
    fun getChats() {
        println(ChatService.getChats())
    }

    @Test
    fun getLastMessages() {
        println(ChatService.getLastMessages())
    }

    @Test
    fun listOfMessagesFromChat() {
        println(ChatService.listOfMessagesFromChat(1, 1))
    }

    @Test
    fun deleteMessage() {
        ChatService.deleteMessage(1, Message("Hi"))
        ChatService.printChats()
    }

//    @Test
//    fun deleteChat() {
//        ChatService.deleteChat(1)
//        ChatService.printChats()
//    }

}
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class WallServiceTest {

    @Test
    fun createChat() {
        val service = WallService

        val mes1 = service.createMessage(0, "Привет", isRead = false, isIncome = true)

        service.createChat(mes1)

    }

    @Test
    fun getChats() {
        val service = WallService
        service.getChats()
    }

    @Test
    fun getUnreadChatsCount() {
        val service = WallService
        service.getUnreadChatsCount()
    }

//    @Test
//    fun deleteChat() {
//        val service = WallService
//        service.deleteChat(0)
//    }


    @Test
    fun updateMessage() {
        val service = WallService
        service.updateMessage(0, 0, "Hello")
    }

    @Test
    fun deleteMessage() {
        val service = WallService
        service.deleteMessage(0, 0)
    }

    @Test
    fun getChatMessages() {
        val service = WallService
    }
}
package example.com.handler

import example.com.handler.commands.Command
import example.com.handler.commands.impl.CreateCommand
import example.com.handler.commands.impl.DeleteCommand
import example.com.payload.rabbit.RabbitMessage
import example.com.payload.rabbit.enums.MessageStatus

class RabbitMessageHandler {

    private var commandMap = HashMap<MessageStatus, Command>()
    init {
        commandMap[MessageStatus.CREATE] = CreateCommand()
        commandMap[MessageStatus.DELETE] = DeleteCommand()
    }

    fun handle(message: RabbitMessage<*>){
        commandMap[message.status]!!.execute(message.content!!)
    }

}

package com.example.listapersonagens.designpatterns.behavioral

import android.provider.ContactsContract.CommonDataKinds.Phone
import java.io.File
import java.util.logging.Logger

/**
 *
 * O padrão observer permite a definição de um mecanismo de assinatura para notificar multiplos objetos.
 * No ecossistema do android, podemos ver a utilização em mecanismo de click (listeners), classe Observable
 * muito utilizado na library do LiveData.
 *
 * Vantangens:
 *
 * - Podemos adicionar novas funcionalidades sem mudar as já criadas
 * - Possibilidade de estabelecer relações durante o tempo de execução.
 * */

class EventManager {

    private val eventListeners: HashMap<String, MyEventListener> = hashMapOf()

    fun subscribe(eventType: String, listener: MyEventListener) {
        eventListeners[eventType] = listener
    }

    fun unsubscribe(eventType: String, listener: MyEventListener) {
        eventListeners.remove(eventType, listener)
    }
    fun notify(eventType: String, data: Any) {
        eventListeners[eventType]?.update(data)
    }
}

interface MyEventListener {
    fun <T> update(data: T)
}

class Editor {
    val events = EventManager()
    private var file: File? = null

    fun openFile(file: String) {
        this.file = File(file)
        events.notify("open", this.file!!.name)
    }
    fun saveFile() {
        file?.writeText("")
        events.notify("save", this.file!!.name)
    }
}

class EmailListener : MyEventListener {

    private lateinit var log: Logger

    override fun <String> update(data: String) {
        log = Logger.getLogger("logger test")
        log.info("update")
        println("update ${this.javaClass}")
        println(data as kotlin.String)
        log.info(data as kotlin.String)
    }
}
class PhoneListener : MyEventListener {

    private lateinit var log: Logger

    override fun <String> update(data: String) {
        log = Logger.getLogger("logger test")
        log.info("update")
        println("update ${this.javaClass}")
        println(data as kotlin.String)
        log.info(data as kotlin.String)
    }
}

fun main(args: Array<String>) {
    val editor = Editor()

    val emailListener = EmailListener()
    val phoneListener = PhoneListener()

    editor.events.subscribe("save", emailListener)
    editor.events.subscribe("open", phoneListener)
    editor.events.subscribe("save", phoneListener)
    editor.openFile("hello")
    editor.saveFile()
}

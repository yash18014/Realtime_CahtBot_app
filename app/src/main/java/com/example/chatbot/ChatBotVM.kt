package com.example.chatbot

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.core.app.NotificationCompat.MessagingStyle.Message
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.ai.client.generativeai.Chat
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.content
import kotlinx.coroutines.launch

class ChatBotVM : ViewModel(){
    val list by lazy {
        mutableStateListOf<ChatData>()
    }
    private val genAI by lazy {
        GenerativeModel(
            modelName = "gemini-1.5-flash",
            apiKey = "AIzaSyDAGqdCAix7Ip6KbOzeHi6Vf1saJwbTT90"
        )

    }
    fun sendMessage(message: String) = viewModelScope.launch {

         val chat = genAI.startChat()
        list.add(ChatData(message, ChatRoleEnum.USER.role))

        chat.sendMessage(
            content (ChatRoleEnum.USER.role){ text(message) }
        ).text?.let{
            list.add(ChatData(it,ChatRoleEnum.MODEL.role))
        }
//        var response = genAI.startChat().sendMessage(prompt = message).text


    }
}
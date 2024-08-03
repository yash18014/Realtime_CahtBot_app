package com.example.chatbot

sealed interface ChatBotUIState  {
    data object Ideal : ChatBotUIState
    data object Loading : ChatBotUIState

     data class  Success ( val chatData: String): ChatBotUIState
    data class Error (val chatError : String): ChatBotUIState
}
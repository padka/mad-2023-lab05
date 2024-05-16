package com.example.myapplication5

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CardsListViewModel : ViewModel() {

    private val _cards = MutableLiveData<MutableList<Card>>()
    val cards: LiveData<MutableList<Card>> get() = _cards

    init {
        _cards.value = mutableListOf(
            Card(
                imageResId = R.drawable.ic_launcher_foreground,
                title = "Извиняться",
                subtitle = "Apologize",
                question = "To tell someone that you are sorry about something you have done",
                hint = "The bank ... for the error",
                answer = "Apologize",
                translation = "Извиняться",
                description = "Описание карточки"
            )
        )
    }

    fun addCard(card: Card) {
        _cards.value?.add(card)
        _cards.value = _cards.value
    }

    fun deleteCard(card: Card) {
        _cards.value?.remove(card)
        _cards.value = _cards.value
    }

    fun updateCard(updatedCard: Card) {
        _cards.value = _cards.value?.map { card ->
            if (card.title == updatedCard.title) {
                updatedCard
            } else {
                card
            }
        }?.toMutableList()
    }
}

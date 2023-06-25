package com.mizani.movieapp.ui.screen.detail

sealed class ActionDetail() {

    object Favorite : ActionDetail()
    object Share : ActionDetail()

}
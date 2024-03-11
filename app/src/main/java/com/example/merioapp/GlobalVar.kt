package com.example.merioapp

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*

object GlobalVar {
    var amountConvertion by mutableStateOf<Float>(0.0f)

    var isDolar by mutableStateOf(false)

    var showDialogConvertion by mutableStateOf(false)
}
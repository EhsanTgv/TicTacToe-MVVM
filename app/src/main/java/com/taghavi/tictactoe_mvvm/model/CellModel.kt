package com.taghavi.tictactoe_mvvm.model

import com.taghavi.tictactoe_mvvm.utilities.StringUtility

class CellModel(var playerModel: PlayerModel?) {
    val isEmpty: Boolean
        get() = playerModel == null || StringUtility.isNullOrEmpty(playerModel!!.value)

}
package com.taghavi.tictactoe_mvvm.viewModel

import androidx.databinding.ObservableArrayMap
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.taghavi.tictactoe_mvvm.model.CellModel
import com.taghavi.tictactoe_mvvm.model.GameModel
import com.taghavi.tictactoe_mvvm.model.PlayerModel
import com.taghavi.tictactoe_mvvm.utilities.StringUtility.stringFromNumbers


class GameViewModel : ViewModel() {
    var cells: ObservableArrayMap<String, String>? = null
    private var game: GameModel? = null
    fun init(player1: String?, player2: String?) {
        game = GameModel(player1, player2)
        cells = ObservableArrayMap()
    }

    fun onClickedCellAt(row: Int, column: Int) {
        if (game!!.cells!![row][column] == null) {
            game!!.cells!![row][column] = CellModel(game!!.currentPlayer)
            cells!![stringFromNumbers(row, column)] = game!!.currentPlayer!!.value
            if (game!!.hasGameEnded()) game!!.reset() else game!!.switchPlayer()
        }
    }

    val winner: MutableLiveData<PlayerModel>
        get() = game!!.winner
}
package com.taghavi.tictactoe_mvvm.model

import android.util.Log
import androidx.lifecycle.MutableLiveData

class GameModel(playerOne: String?, playerTwo: String?) {
    var player1: PlayerModel? = null
    var player2: PlayerModel? = null
    var currentPlayer: PlayerModel? = player1
    var cells: Array<Array<CellModel?>>?
    var winner: MutableLiveData<PlayerModel> = MutableLiveData()
    fun switchPlayer() {
        currentPlayer = if (currentPlayer === player1) player2 else player1
    }

    companion object {
        private val TAG = GameModel::class.java.simpleName
        private const val BOARD_SIZE = 3
    }

    init {
        cells = Array(
            BOARD_SIZE
        ) { arrayOfNulls<CellModel>(BOARD_SIZE) }
        player1 = PlayerModel(playerOne!!, "x")
        player2 = PlayerModel(playerTwo!!, "o")
        currentPlayer = player1
    }

    fun hasGameEnded(): Boolean {
        if (hasThreeSameHorizontalCells() || hasThreeSameVerticalCells() || hasThreeSameDiagonalCells()) {
            winner.value = currentPlayer
            return true
        }
        if (isBoardFull()) {
            winner.value = null
            return true
        }
        return false
    }

    private fun hasThreeSameHorizontalCells(): Boolean {
        return try {
            for (i in 0 until BOARD_SIZE) if (areEqual(
                    cells!![i][0]!!,
                    cells!![i][1]!!,
                    cells!![i][2]!!
                )
            ) return true
            false
        } catch (e: NullPointerException) {
            Log.e(TAG, e.message.toString())
            false
        }
    }

    private fun hasThreeSameVerticalCells(): Boolean {
        return try {
            for (i in 0 until BOARD_SIZE) if (areEqual(
                    cells!![0][i]!!,
                    cells!![1][i]!!,
                    cells!![2][i]!!
                )
            ) return true
            false
        } catch (e: NullPointerException) {
            Log.e(TAG, e.message.toString())
            false
        }
    }

    fun hasThreeSameDiagonalCells(): Boolean {
        return try {
            areEqual(cells!![0][0]!!, cells!![1][1]!!, cells!![2][2]!!) ||
                    areEqual(cells!![0][2]!!, cells!![1][1]!!, cells!![2][0]!!)
        } catch (e: NullPointerException) {
            Log.e(TAG, e.message.toString())
            false
        }
    }


    fun isBoardFull(): Boolean {
        for (row in cells!!) for (cell in row) if (cell == null || cell.isEmpty) return false
        return true
    }

    private fun areEqual(vararg cells: CellModel): Boolean {
        if (cells == null || cells.isEmpty()) return false
        for (cell in cells) if (cell == null || cell.playerModel!!.value == null || cell.playerModel!!.value.length === 0) return false
        val comparisonBase: CellModel = cells[0]
        for (i in 1 until cells.size) if (!comparisonBase.playerModel!!.value.equals(cells[i].playerModel!!.value)) return false
        return true
    }

    fun reset() {
        player1 = null
        player2 = null
        currentPlayer = null
        cells = null
    }
}
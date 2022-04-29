package com.atillaeren.tictactoeminimax


class BoardClass {

    companion object {
        const val PLAYER1 = "O"
        const val PLAYER2 = "x"
        const val COMPUTER = "X"
    }

    val board = Array(3) { arrayOfNulls<String>(3) }

    val emptyCells: List<CellClass>
        get() {
            val cells = mutableListOf<CellClass>()
            for (i in board.indices) {
                for (j in board.indices) {
                    if (board[i][j].isNullOrEmpty()) {
                        cells.add(CellClass(i, j))
                    }
                }
            }
            return cells
        }

    val endGame: Boolean
    get() = player1Win() || computerWin() || emptyCells.isEmpty()

    fun computerWin(): Boolean {
        if (board[0][0] == board[1][1] &&
            board[0][0] == board[2][2] &&
            board[0][0] == COMPUTER ||
            board[0][2] == board[1][1] &&
            board[0][2] == board[2][0] &&
            board[0][2] == COMPUTER
        ) {
            return true
        }
        for (i in board.indices) {
            if (board[i][0] == board[i][1] &&
                board[i][0] == board[i][2] &&
                board[i][0] == COMPUTER ||
                board[0][i] == board[1][i] &&
                board[0][i] == board[2][i] &&
                board[0][i] == COMPUTER
            ) {
                return true
            }
        }
        return false
    }

    fun player1Win(): Boolean {
        if (board[0][0] == board[1][1] &&
            board[0][0] == board[2][2] &&
            board[0][0] == PLAYER1 ||
            board[0][2] == board[1][1] &&
            board[0][2] == board[2][0] &&
            board[0][2] == PLAYER1
        ) {
            return true
        }

        for (i in board.indices) {
            if (board[i][0] == board[i][1] &&
                board[i][0] == board[i][2] &&
                board[i][0] == PLAYER1 ||
                board[0][i] == board[1][i] &&
                board[0][i] == board[2][i] &&
                board[0][i] == PLAYER1
            ) {
                return true
            }
        }

        return false
    }


    fun player2Win(): Boolean {
        if (board[0][0] == board[1][1] &&
            board[0][0] == board[2][2] &&
            board[0][0] == PLAYER2 ||
            board[0][2] == board[1][1] &&
            board[0][2] == board[2][0] &&
            board[0][2] == PLAYER2
        ) {
            return true
        }
        for (i in board.indices) {
            if (board[i][0] == board[i][1] &&
                board[i][0] == board[i][2] &&
                board[i][0] == PLAYER2 ||
                board[0][i] == board[1][i] &&
                board[0][i] == board[2][i] &&
                board[0][i] == PLAYER2
            ) {
                return true
            }
        }
        return false
    }

    var computerMove : CellClass? = null
    fun minimaxAlgorithm(depth: Int, player: String) : Int {
        if (player1Win()) return -1
        if (computerWin()) return +1
        if (emptyCells.isEmpty()) return 0

        var min = Integer.MAX_VALUE
        var max = Integer.MIN_VALUE

        for (i in emptyCells.indices) {
            val cell = emptyCells[i]

            if (player == COMPUTER) {
                placeMove(cell, COMPUTER)
                val currentScore = minimaxAlgorithm(depth + 1, PLAYER1)
                max = Math.max(currentScore, max)

                if (currentScore >=0 ) {
                    if (depth == 0) computerMove = cell
                }

                if (currentScore == 1) {
                    board[cell.i][cell.j] = ""
                    break
                }

                if (i == emptyCells.size - 1 && max < 0) {
                    if (depth == 0) computerMove = cell
                }

            }else if (player == PLAYER1) {
                placeMove(cell, PLAYER1)
                val currentScore = minimaxAlgorithm(depth + 1, COMPUTER)
                min = Math.min(currentScore, min)

                if (min == -1) {
                    board[cell.i][cell.j] = ""
                    break
                }
            }
            board[cell.i][cell.j] = ""
        }
        return  if (player == COMPUTER) max else min
    }

    fun placeMove(cell: CellClass, player: String) {
        board[cell.i][cell.j] = player
    }

}
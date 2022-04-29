package com.atillaeren.tictactoeminimax


import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.GridLayout
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private val boardCells = Array(3) { arrayOfNulls<ImageView>(3) }

    var boardClass = BoardClass()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button_restart.setOnClickListener {
            boardClass = BoardClass()
            text_view_result.text = ""
            boardMap()
        }
        val intent = intent
        when (intent.getIntExtra("Mode", 0)) {
            0 -> {
                //pvp
                loadBoard0()
                button_restart.setOnClickListener {
                    boardClass = BoardClass()
                    text_view_result.text = ""
                    boardMap2()
                }
            }
            1 -> loadBoard1()
            2 -> loadBoard2()
        }


    }

    private fun boardMap() {
        for (i in boardClass.board.indices) {
            for (j in boardClass.board.indices) {
                when (boardClass.board[i][j]) {
                    BoardClass.PLAYER1 -> {
                        boardCells[i][j]?.setImageResource(R.drawable.circle)
                        boardCells[i][j]?.isEnabled = false
                    }
                    BoardClass.COMPUTER -> {
                        boardCells[i][j]?.setImageResource(R.drawable.cross)
                        boardCells[i][j]?.isEnabled = false
                    }
                    else -> {
                        boardCells[i][j]?.setImageResource(0)
                        boardCells[i][j]?.isEnabled = true
                    }
                }
            }
        }
    }

    private fun boardMap2() {
        for (i in boardClass.board.indices) {
            for (j in boardClass.board.indices) {
                when (boardClass.board[i][j]) {
                    BoardClass.PLAYER1 -> {
                        boardCells[i][j]?.setImageResource(R.drawable.circle)
                        boardCells[i][j]?.isEnabled = false
                    }
                    BoardClass.PLAYER2 -> {
                        boardCells[i][j]?.setImageResource(R.drawable.cross)
                        boardCells[i][j]?.isEnabled = false
                    }
                    else -> {
                        boardCells[i][j]?.setImageResource(0)
                        boardCells[i][j]?.isEnabled = true
                    }
                }
            }
        }
    }

    private fun loadBoard0() {

        for (i in boardCells.indices) {
            for (j in boardCells.indices) {
                boardCells[i][j] = ImageView(this)
                boardCells[i][j]?.layoutParams = GridLayout.LayoutParams().apply {
                    rowSpec = GridLayout.spec(i)
                    columnSpec = GridLayout.spec(j)
                    width = 280
                    height = 260
                    bottomMargin = 5
                    topMargin = 5
                    leftMargin = 5
                    rightMargin = 5
                }
                boardCells[i][j]?.setBackgroundColor(
                    ContextCompat.getColor(
                        this,
                        R.color.colorPrimary
                    )
                )
                boardCells[i][j]?.setOnClickListener(CellClickListener0(i, j))
                layout_board.addView(boardCells[i][j])
            }
        }
    }

    private fun loadBoard1() {

        for (i in boardCells.indices) {
            for (j in boardCells.indices) {
                boardCells[i][j] = ImageView(this)
                boardCells[i][j]?.layoutParams = GridLayout.LayoutParams().apply {
                    rowSpec = GridLayout.spec(i)
                    columnSpec = GridLayout.spec(j)
                    width = 280
                    height = 260
                    bottomMargin = 5
                    topMargin = 5
                    leftMargin = 5
                    rightMargin = 5
                }
                boardCells[i][j]?.setBackgroundColor(
                    ContextCompat.getColor(
                        this,
                        R.color.colorPrimary
                    )
                )
                boardCells[i][j]?.setOnClickListener(CellClickListener1(i, j))
                layout_board.addView(boardCells[i][j])
            }
        }
    }

    private fun loadBoard2() {

        for (i in boardCells.indices) {
            for (j in boardCells.indices) {
                boardCells[i][j] = ImageView(this)
                boardCells[i][j]?.layoutParams = GridLayout.LayoutParams().apply {
                    rowSpec = GridLayout.spec(i)
                    columnSpec = GridLayout.spec(j)
                    width = 280
                    height = 260
                    bottomMargin = 5
                    topMargin = 5
                    leftMargin = 5
                    rightMargin = 5
                }
                boardCells[i][j]?.setBackgroundColor(
                    ContextCompat.getColor(
                        this,
                        R.color.colorPrimary
                    )
                )
                boardCells[i][j]?.setOnClickListener(CellClickListener2(i, j))
                layout_board.addView(boardCells[i][j])
            }
        }
    }

    var turn = 1

    inner class CellClickListener0(
        private val i: Int,
        private val j: Int
    ) : View.OnClickListener {
        @SuppressLint("SetTextI18n")
        override fun onClick(p0: View?) {
            when (turn) {
                1 -> if (!boardClass.endGame) {
                    val cell = CellClass(i, j)
                    boardClass.placeMove(cell, BoardClass.PLAYER1)
                    turn = 2
                    boardMap2()
                }
                2 -> if (!boardClass.endGame) {
                    val cell = CellClass(i, j)
                    boardClass.placeMove(cell, BoardClass.PLAYER2)
                    turn = 1
                    boardMap2()
                }
            }
            when {
                boardClass.player1Win() -> text_view_result.text = "Player1 Won"
                boardClass.player2Win() -> text_view_result.text = "Player2 Won"
                boardClass.endGame -> text_view_result.text = "Draw"
            }
        }
    }

    inner class CellClickListener1(
        private val i: Int,
        private val j: Int
    ) : View.OnClickListener {
        @SuppressLint("SetTextI18n")
        override fun onClick(p0: View?) {
            if (!boardClass.endGame) {
                val cell = CellClass(i, j)
                boardClass.placeMove(cell, BoardClass.PLAYER1)
                if (!boardClass.player1Win()) {
                    val cMove = boardClass.emptyCells[Random.nextInt(0, boardClass.emptyCells.size)]
                    boardClass.placeMove(cMove, BoardClass.COMPUTER)
                }
                boardMap()
            }
            when {
                boardClass.player1Win() -> text_view_result.text = "Player Won"
                boardClass.computerWin() -> text_view_result.text = "Computer Won"
                boardClass.endGame -> text_view_result.text = "Draw"
            }
        }
    }

    inner class CellClickListener2(
        private val i: Int,
        private val j: Int
    ) : View.OnClickListener {
        @SuppressLint("SetTextI18n")
        override fun onClick(p0: View?) {
            if (!boardClass.endGame) {
                val cell = CellClass(i, j)
                boardClass.placeMove(cell, BoardClass.PLAYER1)
                boardClass.minimaxAlgorithm(0, BoardClass.COMPUTER)
                boardClass.computerMove?.let {
                    boardClass.placeMove(it, BoardClass.COMPUTER)
                }
                boardMap()
            }
            when {
                boardClass.player1Win() -> text_view_result.text = "Player Won"
                boardClass.computerWin() -> text_view_result.text = "Computer Won"
                boardClass.endGame -> text_view_result.text = "Draw"
            }
        }
    }
}


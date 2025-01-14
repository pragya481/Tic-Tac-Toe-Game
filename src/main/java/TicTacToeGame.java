package src.main.java;

import src.main.java.Domain.Board;
import src.main.java.Domain.PieceType;
import src.main.java.Domain.Player;
import src.main.java.Domain.PlayingPieceO;
import src.main.java.Domain.PlayingPieceX;

import java.util.*;


public class TicTacToeGame {

    Deque<Player> players;
    Board gameBoard;

    // creating 2 player game
    public void initializeGame() {
        players = new LinkedList();
        PlayingPieceX pieceX = new PlayingPieceX();
        Player player1 = new Player("Player1", pieceX);
        PlayingPieceO pieceO = new PlayingPieceO();
        Player player2 = new Player("Player2", pieceO);
        players.add(player1);
        players.add(player2);

        gameBoard = new Board(3);
        player1.rowScore = new int[gameBoard.getSize()];
        player1.colScore = new int[gameBoard.getSize()];
        player2.rowScore = new int[gameBoard.getSize()];
        player2.colScore = new int[gameBoard.getSize()];
        
    }

    public String startGame() {
        boolean noWinner = true;
        while(noWinner) {
            //take out the player whose turn is and also put the player in the list back
            Player playerTurn = players.removeFirst();

            //get the free space from the board
            gameBoard.printBoard();
            List<AbstractMap.SimpleEntry<Integer, Integer>> freeSpaces =  gameBoard.getFreeCells();
            if(freeSpaces.isEmpty()) {
                noWinner = false;
                continue;
            }
            
            //read the user input
            System.out.print("Player:" + playerTurn.getName() + " Enter row,column: ");
            Scanner inputScanner = new Scanner(System.in);
            String s = inputScanner.nextLine();
            String[] values = s.split(",");
            int inputRow = Integer.valueOf(values[0]);
            int inputColumn = Integer.valueOf(values[1]);


            //place the piece
            boolean pieceAddedSuccessfully = gameBoard.addPiece(inputRow,inputColumn, playerTurn.getPlayingPiece());
            if(!pieceAddedSuccessfully) {
                //player can not insert the piece into this cell, player has to choose another cell
                System.out.println("Incorredt possition chosen, try again");
                players.addFirst(playerTurn);
                continue;
            }
            playerTurn.rowScore[inputRow]++;
            playerTurn.colScore[inputColumn]++;
            if (inputColumn == inputRow) {
                playerTurn.diagScore++;
            }
            if (inputRow == gameBoard.getSize()-inputColumn-1) {
                playerTurn.antiDiagScore++;
            }

            players.addLast(playerTurn);

            boolean winner = isThereWinner(inputRow, inputColumn, playerTurn);
            if (winner) {
                return playerTurn.getName();
            }
        }
        return "tie";
    }

    public boolean isThereWinner(int row, int column, Player player) {

        // boolean rowMatch = true;
        // boolean columnMatch = true;
        // boolean diagonalMatch = true;
        // boolean antiDiagonalMatch = true;

        boolean rowMatch = (player.rowScore[row] == gameBoard.getSize());
        boolean columnMatch = (player.colScore[column] == gameBoard.getSize());
        boolean diagonalMatch = (player.diagScore == gameBoard.getSize());
        boolean antiDiagonalMatch = (player.antiDiagScore == gameBoard.getSize());

        // PieceType pieceType = player.getPlayingPiece().getPieceType();



        //need to check in row
        // for(int i=0;i<gameBoard.getSize();i++) {

        //     if(gameBoard.board[row][i] == null || gameBoard.board[row][i].getPieceType() != pieceType) {
        //         rowMatch = false;
        //     }
        // }

        //need to check in column
        // for(int i=0;i<gameBoard.getSize();i++) {

        //     if(gameBoard.board[i][column] == null || gameBoard.board[i][column].getPieceType() != pieceType) {
        //         columnMatch = false;
        //     }
        // }

        //need to check diagonals
        // for(int i=0, j=0; i<gameBoard.getSize();i++,j++) {
        //     if (gameBoard.board[i][j] == null || gameBoard.board[i][j].getPieceType() != pieceType) {
        //         diagonalMatch = false;
        //     }
        // }

        //need to check anti-diagonals
        // for(int i=0, j=gameBoard.getSize()-1; i<gameBoard.getSize();i++,j--) {
        //     if (gameBoard.board[i][j] == null || gameBoard.board[i][j].getPieceType() != pieceType) {
        //         antiDiagonalMatch = false;
        //     }
        // }

        return rowMatch || columnMatch || diagonalMatch || antiDiagonalMatch;
    }

}
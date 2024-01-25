import java.util.Scanner;

    public class TheGame {
        private static final int ROWS = 3;
        private static final int COLS = 3;
        private static final char EMPTY = '-';
        private static final char X = 'X';
        private static final char O = 'O';

        final char[][] board = new char[ROWS][COLS];
        char currentPlayer = X;
        private static Scanner scanner;

        public TheGame() {
            scanner = new Scanner(System.in);
        }

        public static void newGame() {
            TheGame game = new TheGame();
            game.initBoard();
            boolean playAgain;
            do {
                game.printBoard();
                game.makeMove();
                if (game.checkForWin()) {
                    System.out.println(game.currentPlayer + " Победил!");
                    System.out.println("Хотите сыграть еще раз? (да/нет): ");
                    playAgain = scanner.next().equals("да");
                    if (playAgain) {
                        TheGame.newGame();
                    } else {
                        break;
                    }
                } else if (game.isBoardFull()) {
                    System.out.println("Ничья!");
                    System.out.println("Хотите сыграть еще раз? (да/нет): ");
                    playAgain = scanner.next().equals("да");
                    if (playAgain) {
                        game.initBoard();
                    } else {
                        break;
                    }
                } else {
                    game.changePlayer();
                }
            } while (true);
            scanner.close();
        }

        void initBoard() {
            for (int i = 0; i < ROWS; i++) {
                for (int j = 0; j < COLS; j++) {
                    board[i][j] = EMPTY;
                }
            }
        }

        private void printBoard() {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < ROWS; i++) {
                for (int j = 0; j < COLS; j++) {
                    sb.append(board[i][j]).append(" ");
                }
                sb.append("\n");
            }
            System.out.print(sb);
        }

        boolean isBoardFull() {
            for (int i = 0; i < ROWS; i++) {
                for (int j = 0; j < COLS; j++) {
                    if (board[i][j] == EMPTY) {
                        return false;
                    }
                }
            }
            return true;
        }

        boolean checkForWin() {
            // Check rows
            for (int i = 0; i < ROWS; i++) {
                if (board[i][0] == board[i][1] && board[i][1] == board[i][2] && board[i][0] != EMPTY) {
                    return true;
                }
            }

            // Check columns
            for (int j = 0; j < COLS; j++) {
                if (board[0][j] == board[1][j] && board[1][j] == board[2][j] && board[0][j] != EMPTY) {
                    return true;
                }
            }

            // Check diagonals
            if (board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] != EMPTY) {
                return true;
            }
            return board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[0][2] != EMPTY;
        }

        private void changePlayer() {
            currentPlayer = (currentPlayer == X) ? O : X;
        }

        void makeMove() {
            int row;
            int col;
            boolean validMove = false;
            do {
                System.out.print("Игрок " + currentPlayer + ", введите номер ряда (1-3): ");
                row = scanner.nextInt() - 1;
                System.out.print("Игрок " + currentPlayer + ", ведите номер столбца (1-3): ");
                col = scanner.nextInt() - 1;
                if (row >= 0 && row < ROWS && col >= 0 && col < COLS && board[row][col] == EMPTY) {
                    validMove = true;
                } else {
                    System.out.println("Неверный ход, повторите ввод.");
                }
            } while (!validMove);
            board[row][col] = currentPlayer;
        }

        public static void main(String[] args) {
            TheGame.newGame();
        }
    }



package ru.job4j.ChessBoard;

public class ImposibleMoveException extends RuntimeException {
    public ImposibleMoveException(String message) {
        super(message);
    }
}

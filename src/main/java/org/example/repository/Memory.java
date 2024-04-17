package org.example.repository;

import java.util.Stack;

public class Memory {

    private final Stack<char[]> history = new Stack<>();

    public void backup(char[] value) {
        history.push(value);
    }

    public char[] undo() {
        if (history.isEmpty()) {
            return null;
        }
        return history.pop();
    }


}

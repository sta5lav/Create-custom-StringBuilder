package org.example.service;


import org.example.entity.AbstractStringBuilderNew;
import org.example.repository.Memory;

import java.util.Arrays;

public final class StringBuilderNew implements AbstractStringBuilderNew {

    private final Memory memory = new Memory();

    private static final int INIT_CAPACITY = 1;

    private char[] value = new char[INIT_CAPACITY];


    public StringBuilderNew(String s) {
        int length = s.length();
        if (value.length + length > value.length) {
            value = new char[length];
            for (int i = 0; i < value.length; i++) {
                value[i] = s.charAt(i);
            }
        } else {
            value[0] = s.charAt(0);
        }
        memory.backup(value);

    }

    public StringBuilderNew(char[] chars) {
        value = chars;
        memory.backup(value);
    }

    public StringBuilderNew append(StringBuilderNew s) {
        int count = 0;
        char[] newValueString = Arrays.copyOfRange(value, 0, value.length + s.value.length);
        for (int i = value.length; i < newValueString.length; i++) {
            newValueString[i] = s.value[count++];
        }
        value = newValueString;
        memory.backup(value);
        return this;
    }

    public StringBuilderNew append(String s) {
        int count = 0;
        char[] newValueString = Arrays.copyOfRange(value, 0, value.length + s.length());
        for (int i = value.length; i < newValueString.length; i++) {
            newValueString[i] = s.charAt(count++);
        }
        value = newValueString;
        memory.backup(value);

        return this;
    }


    public StringBuilderNew insert(int offset, String s) {
        int count = 0;
        int countOffsetFor = offset;
        char[] newValueString = Arrays.copyOfRange(value, 0, value.length + s.length());
        for (int i = 0; i < s.length(); i++) {
            newValueString[countOffsetFor - 1] = s.charAt(count++);
            countOffsetFor++;
        }
        if (offset <= value.length) {
            count = 0;
            for (int i = countOffsetFor; i <= newValueString.length; i++) {
                newValueString[countOffsetFor - 1] = value[offset-1];
                offset++;
                countOffsetFor++;
            }
        }
        value = newValueString;
        memory.backup(value);
        return this;
    }

    public int length() {
        return value.length;
    }

    public StringBuilderNew reverse() {
        for (int i = 0; i < value.length/2; i++) {
            char j = value[i];
            value[i] = value[value.length -i -1];
            value[value.length -i -1] = j;
        }
        memory.backup(value);
        return this;
    }

    public StringBuilderNew toLowerCase() {
        for (int i = 0; i < value.length; i++) {
            value[i] = Character.toLowerCase(value[i]);
        }
        memory.backup(value);
        return this;
    }

    public StringBuilderNew toUpperCase() {
        for (int i = 0; i < value.length; i++) {
            value[i] = Character.toUpperCase(value[i]);
        }
        memory.backup(value);
        return this;
    }

    public StringBuilderNew substring(int begin, int end) {
        char[] newValueString = Arrays.copyOfRange(value, begin, end);
        return new StringBuilderNew(newValueString);
    }

    public StringBuilderNew substring(int begin) {
        char[] newValueString = Arrays.copyOfRange(value, begin, value.length);
        return new StringBuilderNew(newValueString);
    }

    public String toString() {
        return new String(value);
    }

    public StringBuilderNew undo(){
        memory.undo();
        this.value = memory.undo();
        return this;
    }


}


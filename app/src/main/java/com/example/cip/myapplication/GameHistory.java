package com.example.cip.myapplication;

import java.io.Serializable;

public class GameHistory implements Serializable {

    private int noteId;
    private String noteTitle;
    private String noteContent;

    public GameHistory()  {
    }

    public GameHistory(String noteTitle, String noteContent) {
        this.noteTitle= noteTitle;
        this.noteContent= noteContent;
    }

    public GameHistory(int noteId, String noteTitle, String noteContent) {
        this.noteId= noteId;
        this.noteTitle= noteTitle;
        this.noteContent= noteContent;
    }

    public int getNoteId() {
        return noteId;
    }

    public void setNoteId(int noteId) {
        this.noteId = noteId;
    }

    public String getNoteTitle() {
        return noteTitle;
    }

    public void setNoteTitle(String noteTitle) {
        this.noteTitle = noteTitle;
    }

    public String getNoteContent() {
        return noteContent;
    }

    public void setNoteContent(String noteContent) {
        this.noteContent = noteContent;
    }

    @Override
    public String toString()  {
        return this.noteTitle;
    }
}



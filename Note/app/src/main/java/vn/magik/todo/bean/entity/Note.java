package vn.magik.todo.bean.entity;

import java.io.Serializable;

/**
 * Created by TOAN on 10/2/2016.
 */

public class Note implements Serializable{
    private int id;
    private String title;
    private String time;
    private String text;
    private String level;
    private String status;

    public Note() {
    }

    public Note(String title, String time, String text, String level, String status) {
        this.title = title;
        this.time = time;
        this.text = text;
        this.level = level;
        this.status = status;
    }

    public Note(int id, String title, String time, String text, String level, String status) {
        this.id = id;
        this.title = title;
        this.time = time;
        this.text = text;
        this.level = level;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Note{" +
                "title='" + title + '\'' +
                ", time='" + time + '\'' +
                ", text='" + text + '\'' +
                ", level='" + level + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}

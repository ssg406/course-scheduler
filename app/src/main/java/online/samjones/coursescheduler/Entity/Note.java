package online.samjones.coursescheduler.Entity;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "notes",
        foreignKeys = {@ForeignKey(entity = Course.class, parentColumns = "courseId",
                childColumns = "courseId", onDelete = ForeignKey.CASCADE)})
public class Note {

    @PrimaryKey(autoGenerate = true)
    private int noteId;

    private String note;
    private int courseId;

    public Note(String note, int courseId) {
        this.note = note;
        this.courseId = courseId;
    }

    public int getNoteId() {
        return noteId;
    }

    public void setNoteId(int noteId) {
        this.noteId = noteId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    @Override
    public String toString() {
        return "Note{" +
                "noteId=" + noteId +
                ", note='" + note + '\'' +
                ", courseId=" + courseId +
                '}';
    }
}

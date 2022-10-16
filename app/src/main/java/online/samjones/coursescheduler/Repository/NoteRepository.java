package online.samjones.coursescheduler.Repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import online.samjones.coursescheduler.DAO.NoteDAO;
import online.samjones.coursescheduler.Database.AppDatabase;
import online.samjones.coursescheduler.Entity.Note;

public class NoteRepository {

    private NoteDAO noteDAO;
    private LiveData<List<Note>> allNotes;

    public NoteRepository(Application application){
        AppDatabase database = AppDatabase.getDatabase(application);
        noteDAO = database.noteDAO();
        allNotes = noteDAO.all();
    }

    public LiveData<List<Note>> getAllNotes() { return allNotes; }

    public void insert(Note note){
        AppDatabase.databaseWriteExecutor.execute(() -> {
            noteDAO.insert(note);
        });
    }

    public void update(Note note){
        AppDatabase.databaseWriteExecutor.execute(() -> {
            noteDAO.update(note);
        });
    }

    public void delete(Note note){
        AppDatabase.databaseWriteExecutor.execute(() -> {
            noteDAO.delete(note);
        });
    }
}

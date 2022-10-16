package online.samjones.coursescheduler.ViewModel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import online.samjones.coursescheduler.Entity.Note;
import online.samjones.coursescheduler.Repository.NoteRepository;

public class NoteViewModel extends AndroidViewModel {

    private NoteRepository repository;
    private final LiveData<List<Note>> allNotes;

    public NoteViewModel(Application application){
        super(application);
        repository = new NoteRepository(application);
        allNotes = repository.getAllNotes();
    }

    LiveData<List<Note>> getAllNotes() { return allNotes; }

    public void insert(Note note){ repository.insert(note); }
}

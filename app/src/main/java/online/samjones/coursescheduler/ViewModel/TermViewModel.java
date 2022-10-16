package online.samjones.coursescheduler.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import online.samjones.coursescheduler.Entity.Term;
import online.samjones.coursescheduler.Repository.TermRepository;

public class TermViewModel extends AndroidViewModel {

    private final TermRepository repository;
    private final LiveData<List<Term>> allTerms;

    public TermViewModel(@NonNull Application application) {
        super(application);
        repository = new TermRepository(application);
        allTerms = repository.getAllTerms();
    }

    public LiveData<List<Term>> getAllTerms() { return allTerms; }

    public LiveData<Term> getTerm(int id) { return repository.getTerm(id); }

    public void insert(Term term) { repository.insert(term); }

    public void update(Term term) { repository.update(term); }

    public void delete(Term term) { repository.delete(term); }
}

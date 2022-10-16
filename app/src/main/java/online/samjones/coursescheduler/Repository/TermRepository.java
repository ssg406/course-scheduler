package online.samjones.coursescheduler.Repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import online.samjones.coursescheduler.DAO.TermDAO;
import online.samjones.coursescheduler.Database.AppDatabase;
import online.samjones.coursescheduler.Entity.Term;

public class TermRepository {

    private TermDAO termDAO;
    private LiveData<List<Term>> allTerms;

    public TermRepository(Application application){
        AppDatabase database = AppDatabase.getDatabase(application);
        termDAO = database.termDAO();
        allTerms = termDAO.all();
    }

    public LiveData<List<Term>> getAllTerms(){ return allTerms; }

    public LiveData<Term> getTerm(int id) { return termDAO.one(id); }

    public void insert(Term term){
        AppDatabase.databaseWriteExecutor.execute(() -> {
            termDAO.insert(term);
        });
    }

    public void update(Term term){
        AppDatabase.databaseWriteExecutor.execute(() -> {
            termDAO.update(term);
        });
    }

    public void delete(Term term){
        AppDatabase.databaseWriteExecutor.execute(() -> {
            termDAO.delete(term);
        });
    }
}

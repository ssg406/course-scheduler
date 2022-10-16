package online.samjones.coursescheduler.Repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import online.samjones.coursescheduler.DAO.AssessmentDAO;
import online.samjones.coursescheduler.Database.AppDatabase;
import online.samjones.coursescheduler.Entity.Assessment;

public class AssessmentRepository {

    private AssessmentDAO assessmentDAO;
    private LiveData<List<Assessment>> allAssessments;

    public AssessmentRepository(Application application){
        AppDatabase database = AppDatabase.getDatabase(application);
        assessmentDAO = database.assessmentDAO();
        allAssessments = assessmentDAO.all();
    }

    public LiveData<List<Assessment>> getAllAssessments(){
        return allAssessments;
    }

    public void insert(Assessment assessment){
        AppDatabase.databaseWriteExecutor.execute(() ->{
            assessmentDAO.insert(assessment);
        });
    }

    public void update(Assessment assessment){
        AppDatabase.databaseWriteExecutor.execute(() ->{
            assessmentDAO.update(assessment);
        });
    }

    public void delete(Assessment assessment){
        AppDatabase.databaseWriteExecutor.execute(() -> {
            assessmentDAO.delete(assessment);
        });
    }
}

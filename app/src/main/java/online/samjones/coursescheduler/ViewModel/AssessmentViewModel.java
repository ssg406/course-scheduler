package online.samjones.coursescheduler.ViewModel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import online.samjones.coursescheduler.Entity.Assessment;
import online.samjones.coursescheduler.Repository.AssessmentRepository;

public class AssessmentViewModel extends AndroidViewModel {

    private AssessmentRepository repository;
    private final LiveData<List<Assessment>> allAssessments;

    public AssessmentViewModel(Application application){
        super(application);
        repository = new AssessmentRepository(application);
        allAssessments = repository.getAllAssessments();
    }

    LiveData<List<Assessment>> getAllAssessments() { return allAssessments; }

    public void insert(Assessment assessment) { repository.insert(assessment); }
}

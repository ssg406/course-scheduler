package online.samjones.coursescheduler.Repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import online.samjones.coursescheduler.DAO.CourseDAO;
import online.samjones.coursescheduler.Database.AppDatabase;
import online.samjones.coursescheduler.Entity.Course;

public class CourseRepository {

    private CourseDAO courseDAO;
    private LiveData<List<Course>> allCourses;

    public CourseRepository(Application application){
        AppDatabase database = AppDatabase.getDatabase(application);
        courseDAO = database.courseDAO();
        allCourses = courseDAO.all();
    }

    public LiveData<List<Course>> getAllCourses() { return allCourses; }

    public void insert(Course course){
        AppDatabase.databaseWriteExecutor.execute(() -> {
            courseDAO.insert(course);
        });
    }

    public void update(Course course){
        AppDatabase.databaseWriteExecutor.execute(() -> {
            courseDAO.update(course);
        });
    }

    public void delete(Course course){
        AppDatabase.databaseWriteExecutor.execute(() -> {
            courseDAO.delete(course);
        });
    }
}

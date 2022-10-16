package online.samjones.coursescheduler.Database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import online.samjones.coursescheduler.DAO.AssessmentDAO;
import online.samjones.coursescheduler.DAO.CourseDAO;
import online.samjones.coursescheduler.DAO.NoteDAO;
import online.samjones.coursescheduler.DAO.TermDAO;
import online.samjones.coursescheduler.Entity.Assessment;
import online.samjones.coursescheduler.Entity.Course;
import online.samjones.coursescheduler.Entity.DateConverter;
import online.samjones.coursescheduler.Entity.Note;
import online.samjones.coursescheduler.Entity.Term;

@Database(entities = {Assessment.class, Course.class, Note.class, Term.class}, version = 2)
@TypeConverters({DateConverter.class})
public abstract class AppDatabase extends RoomDatabase {

    public abstract AssessmentDAO assessmentDAO();
    public abstract CourseDAO courseDAO();
    public abstract NoteDAO noteDAO();
    public abstract TermDAO termDAO();

    private static volatile AppDatabase INSTANCE;
    public static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

//    //Pre-populate the database. Comment out callback for blank application.
//    private static final RoomDatabase.Callback roomDatabaseCallback = new RoomDatabase.Callback() {
//        @Override
//        public void onCreate(@NonNull SupportSQLiteDatabase db) {
//            super.onCreate(db);
//
//            databaseWriteExecutor.execute(() -> {
//                TermDAO termDAO = INSTANCE.termDAO();
//                termDAO.deleteAll();
//                termDAO.insert(new Term("Spring Term",
//                        LocalDate.of(2022, 3, 1),
//                        LocalDate.of(2022, 6, 1)));
//                termDAO.insert(new Term("Fall Term",
//                        LocalDate.of(2022, 9, 1),
//                        LocalDate.of(2022, 12, 1)));
//                termDAO.insert(new Term("Winter Term",
//                        LocalDate.of(2022, 1, 1),
//                        LocalDate.of(2022, 3, 1)));
//            });
//        }
//    };

    public static AppDatabase getDatabase(final Context context){
        if(INSTANCE == null){
            synchronized (AppDatabase.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "courseDatabase")
                            .fallbackToDestructiveMigration()
//                            .addCallback(roomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

}

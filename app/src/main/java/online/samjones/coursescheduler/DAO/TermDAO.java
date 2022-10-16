package online.samjones.coursescheduler.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.RewriteQueriesToDropUnusedColumns;
import androidx.room.Update;

import java.util.List;

import online.samjones.coursescheduler.Entity.Term;

@Dao
public interface TermDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Term term);

    @Update
    void update(Term term);

    @Delete
    void delete(Term term);

    @Query("SELECT * FROM terms")
    LiveData<List<Term>> all();

    @Query("SELECT * FROM terms WHERE termId = :id")
    LiveData<Term> one(int id);

    @Query("DELETE from terms")
    void deleteAll();
}

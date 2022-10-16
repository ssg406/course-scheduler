package online.samjones.coursescheduler.UI.archive;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import online.samjones.coursescheduler.R;

public class TermDetail extends AppCompatActivity {

    private TextView termName;
    private TextView startDate;
    private TextView endDate;
    private RecyclerView courseList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_detail);

        //Get views
        termName = findViewById(R.id.termDetailName);
        startDate = findViewById(R.id.termStartDetail);
        endDate = findViewById(R.id.termEndDetail);
        courseList = findViewById(R.id.termViewCourseList);
    }
}
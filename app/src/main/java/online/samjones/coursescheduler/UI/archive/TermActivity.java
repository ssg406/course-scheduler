package online.samjones.coursescheduler.UI.archive;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.time.LocalDate;
import java.util.List;

import online.samjones.coursescheduler.Entity.Term;
import online.samjones.coursescheduler.R;
import online.samjones.coursescheduler.Util.DateUtil;
import online.samjones.coursescheduler.ViewModel.TermViewModel;

public class TermActivity extends AppCompatActivity implements TermListAdapter.TermListClickListener {

    public static final String EXTRA_TERM_ID = "termId";
    public static final String EXTRA_TERM_NAME = "termName";
    public static final String EXTRA_TERM_START = "termStart";
    public static final String EXTRA_TERM_END = "termEnd";
    public static final String EXTRA_TERM_EDIT = "editTerm";

    private TermViewModel termViewModel;
    final TermListAdapter adapter = new TermListAdapter(TermActivity.this, this);
    private int adapterPosition;

    private final ActivityResultLauncher<Intent> startForResult = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if(result.getResultCode() == Activity.RESULT_OK){
                        Intent intent = result.getData();
                        if(intent == null){
                            throw new RuntimeException("Error getting data from activity");
                        }

                        //Get Term information from intent
                        String termName = intent.getStringExtra(EXTRA_TERM_NAME);
                        String termStartString = intent.getStringExtra(EXTRA_TERM_START);
                        LocalDate termStart = DateUtil.parseString(termStartString, DateUtil.FULL_TEXT);
                        String termEndString = intent.getStringExtra(EXTRA_TERM_END);
                        LocalDate termEnd = DateUtil.parseString(termEndString, DateUtil.FULL_TEXT);
                        int termId = intent.getIntExtra(EXTRA_TERM_ID, -1);

                        //Create term object
                        Term term = new Term(termName, termStart, termEnd);

                        if(termId != -1){
                            term.setTermId(termId);
                            termViewModel.update(term);
                        } else {
                            termViewModel.insert(term);
                        }
                    }
                }
            });

    @Override
    public void termListClicked(View view, int position){
        adapterPosition = position;
    }

    @Override
    public void editClicked(View view, int termId) {
        Toast.makeText(this, "Term id given is: " + termId, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void deleteClicked(View view, int termId) {
        Toast.makeText(this, "launch delete called with position " + adapterPosition, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term);

        //Get the recycler view and associate the adapter
        RecyclerView recyclerView = findViewById(R.id.allTermsView);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //Get the view model using the provider and add observer
        termViewModel = new ViewModelProvider(this).get(TermViewModel.class);
        termViewModel.getAllTerms().observe(this, adapter::submitList);
        Observer<List<Term>> termListUpdateObserver = new Observer<List<Term>>() {
            @Override
            public void onChanged(List<Term> terms) {
                adapter.submitList(terms);
            }
        };
    }


    public void clickedAddTermButton(View view) {
        startForResult.launch(new Intent(this, TermEditActivity.class));
    }

    public void launchEditTerm() {

    }

    public void deleteTerm() {

    }
}

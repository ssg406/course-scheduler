package online.samjones.coursescheduler.UI.archive;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.time.LocalDate;
import java.util.Calendar;

import online.samjones.coursescheduler.R;
import online.samjones.coursescheduler.Util.DateUtil;

public class TermEditActivity extends AppCompatActivity{

    private int currentDateField;
    private EditText editTermName;
    private TextView termEndDisplay;
    private TextView termStartDisplay;
    private DatePickerDialog datePickerDialog;
    private int termId;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_edit);
        editTermName = findViewById(R.id.editTermName);
        termStartDisplay = findViewById(R.id.termStartDisplay);
        termEndDisplay = findViewById(R.id.termEndDisplay);


        //Determine if editing or adding new term
        Intent intent = getIntent();
        termId = intent.getIntExtra(TermActivity.EXTRA_TERM_ID, -1);

        //Set fields if term is being edited
        if(termId != -1){
            editTermName.setText(intent.getStringExtra(TermActivity.EXTRA_TERM_NAME));
            termStartDisplay.setText(intent.getStringExtra(TermActivity.EXTRA_TERM_START));
            termEndDisplay.setText(intent.getStringExtra(TermActivity.EXTRA_TERM_END));
        }

        //Initialize the date picker
        initDatePicker();
    }


    public void showDatePicker(View view){
        currentDateField = view.getId();
        datePickerDialog.show();
    }

    private void initDatePicker() {

        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                LocalDate enteredDate = LocalDate.of(year, month + 1, dayOfMonth);

                if(currentDateField == R.id.startDateButton){
                    termStartDisplay.setText(
                            DateUtil.formatDate(enteredDate, DateUtil.FULL_TEXT)
                    );
                } else if (currentDateField == R.id.endDateButton){
                    termEndDisplay.setText(
                            DateUtil.formatDate(enteredDate, DateUtil.FULL_TEXT)
                    );
                }
            }
        };

        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH) + 1;
        int day = c.get(Calendar.DAY_OF_MONTH);

        datePickerDialog = new DatePickerDialog(this, dateSetListener, year, month, day);
    }

    public void submitButtonClicked(View view){
        //Check for empty fields
        String termName = editTermName.getText().toString().trim();
        String termStartString = termStartDisplay.getText().toString().trim();
        String termEndString = termEndDisplay.getText().toString().trim();

        if(termName.isEmpty()){
            showErrorToast(getString(R.string.blank_field, "term name"));
            return;
        } else if(termStartString.equals(getString(R.string.start_date))){
            showErrorToast(getString(R.string.blank_field, "start date"));
            return;
        } else if(termEndString.equals(getString(R.string.end_date))){
            showErrorToast(getString(R.string.blank_field, "end date"));
            return;
        }

        Intent intent = new Intent();
        intent.putExtra(TermActivity.EXTRA_TERM_NAME, termName);
        intent.putExtra(TermActivity.EXTRA_TERM_START, termStartString);
        intent.putExtra(TermActivity.EXTRA_TERM_END, termEndString);
        intent.putExtra(TermActivity.EXTRA_TERM_ID, termId);
        setResult(RESULT_OK, intent);
        finish();
    }

    private void showErrorToast(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

}

package online.samjones.coursescheduler.UI.archive;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.time.format.DateTimeFormatter;
import java.util.List;

import online.samjones.coursescheduler.Entity.Term;
import online.samjones.coursescheduler.R;

public class TermListAdapter extends RecyclerView.Adapter<TermListAdapter.TermViewHolder> {
    public static final String EXTRA_TERM_ID = "termId";

    private List<Term> allTerms;
    private final Context context;
    private static TermListClickListener listener;

    //List click listener interface to pass position back to TermActivity and call edit
    //and delete methods
    public interface TermListClickListener {
        void termListClicked(View view, int position);
        void editClicked(View view, int position);
        void deleteClicked(View view, int position);
    }


    public TermListAdapter(Context context, TermListClickListener listener){
        this.context = context;
        TermListAdapter.listener = listener;
    }


    public Term getItem(int position){
        return allTerms.get(position);
    }

    @NonNull
    @Override
    public TermViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        return TermViewHolder.create(parent);
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.term_list_item, parent, false);
        return new TermViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TermViewHolder holder, int position) {
        //Bind the view holder with the item
        holder.bind(getItem(position));
    }

    @Override
    public int getItemCount() {
        return allTerms == null ? 0 : allTerms.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void submitList(List<Term> terms){
        this.allTerms = terms;
        notifyDataSetChanged();
    }

    //ViewHolder inner class
    class TermViewHolder extends RecyclerView.ViewHolder{
        private final TextView termName;
        private final TextView termDates;
        private final TextView editTermButton;
        private final TextView deleteTermButton;


        private TermViewHolder(View itemView){
            super(itemView);
            termName = itemView.findViewById(R.id.termCardTitle);
            termDates = itemView.findViewById(R.id.termCardDates);
            editTermButton = itemView.findViewById(R.id.editTermButton);
            deleteTermButton = itemView.findViewById(R.id.deleteTermButton);

            //Click listener for each term card
            itemView.setOnClickListener(view -> {

                listener.termListClicked(itemView, getAdapterPosition());
                final Term current = getItem(getAdapterPosition());
                Intent intent = new Intent(context, TermDetail.class);
                intent.putExtra(EXTRA_TERM_ID, current.getTermId());
                context.startActivity(intent);
            });

            //Set listener for edit term button
            editTermButton.setOnClickListener(view -> {
                Term term = getItem(getAdapterPosition());
                listener.editClicked(view, term.getTermId());
            });

            //Set listener for delete button
            deleteTermButton.setOnClickListener(view -> {
                Term term = getItem(getAdapterPosition());
                listener.deleteClicked(view, term.getTermId());
            });

        }

        public void bind(Term term){

            //Format term date string for display in card
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMMM d yyyy");
            String dateString = term.getTermStart().format(dtf) +
                    " to " +
                    term.getTermEnd().format(dtf);

            //Bind term name and date to text views
            termName.setText(term.getTermName());
            termDates.setText(dateString);
        }

    }
}

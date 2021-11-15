package com.example.firebasetest;

import static android.content.ContentValues.TAG;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ItemViewHolder> {

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    ArrayList<Word> word_list = new ArrayList<>();



    RecyclerAdapter() {

    }

    void update(){
        CollectionReference colRef = db.collection("word");
        word_list.clear();
        colRef.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for(DocumentSnapshot doc : queryDocumentSnapshots){
                    Log.d("word_list", doc.toObject(Word.class).getWord() + " " + doc.toObject(Word.class).getMean());
                    word_list.add(doc.toObject(Word.class));
                }
                notifyDataSetChanged();
            }
        });
    }


    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent,false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        holder.onBind(word_list.get(position));
    }

    @Override
    public int getItemCount() {
        return word_list.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        private TextView word;
        private TextView mean;
        private CheckBox checkBox;

        ItemViewHolder(View view){
            super(view);

            word = itemView.findViewById(R.id.Word);
            mean = itemView.findViewById(R.id.Mean);
            checkBox = itemView.findViewById(R.id.isMem);
        }

        void onBind(Word word){
            this.word.setText(word.getWord());
            this.mean.setText(word.getMean());
            checkBox.setChecked(word.getIsMem());
        }
    }
}

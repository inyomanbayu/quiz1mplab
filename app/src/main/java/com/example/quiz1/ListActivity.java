package com.example.quiz1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.quiz1.Models.DataModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity{

    ArrayList<DataModel> dataModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_activity);

        String url = "https://reqres.in/api/users";
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(getStringRequest(url));

        dataModels = new ArrayList<DataModel>();

        prepareListView();
    }

    private StringRequest getStringRequest(String url){
        return new StringRequest(Request.Method.GET, url, (response) -> {
            try{
                JSONObject object = new JSONObject(response);
                JSONArray array = object.getJSONArray("data");

                for (int i = 0; i < 5; i++) {
                    JSONObject user = array.getJSONObject(i);
                    dataModels.add(new DataModel(user.getInt("id"), user.getString("first_name"), user.getString("last_name"), user.getString("avatar")));

                }

            } catch (JSONException e){
                e.printStackTrace();
            }

        }, (error) -> {

        });
    }

    private void prepareListView() {
        RecyclerView rv = findViewById(R.id.list_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        DataModelAdapter dataModelAdapter = new DataModelAdapter(dataModels);

        rv.setLayoutManager(layoutManager);
        rv.setAdapter(dataModelAdapter);
    }
}

class DataModelAdapter extends RecyclerView.Adapter<DataModelViewHolder> {

    ArrayList<DataModel> dataModels;

    public DataModelAdapter(ArrayList<DataModel> dataModels) {
        this.dataModels = dataModels;
    }

    @NonNull
    @Override
    public DataModelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.list_item_card_value, parent, false);

        return new DataModelViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DataModelViewHolder holder, int position) {
        DataModel model = dataModels.get(position);

        holder.id.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), Profile.class);
                intent.putExtra("userFirstName", model.getFirst_name());
                intent.putExtra("userLastName", model.getLast_name());
                intent.putExtra("userAvatar", model.getImage_view());
                view.getContext().startActivity(intent);
            }
        });

        holder.first_name.setText(model.getFirst_name());
        holder.last_name.setText(model.getLast_name());

        Glide.with(holder.parent.getContext())
                .load(model.getImage_view())
                .circleCrop()
                .into(holder.avatarDisplay);

    }

    @Override
    public int getItemCount() {
        return dataModels.size();
    }
}

class DataModelViewHolder extends RecyclerView.ViewHolder {
    View parent;
    TextView first_name;
    TextView last_name;
    ImageView avatarDisplay;
    LinearLayout id;

    public DataModelViewHolder(@NonNull View itemView) {
        super(itemView);
        parent = itemView;
        first_name = itemView.findViewById(R.id.first_name);
        last_name = itemView.findViewById(R.id.last_name);
        avatarDisplay = itemView.findViewById(R.id.image_view);
        id = itemView.findViewById(R.id.list_item_card_view);
    }
}
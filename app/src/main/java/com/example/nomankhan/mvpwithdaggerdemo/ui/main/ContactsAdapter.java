package com.example.nomankhan.mvpwithdaggerdemo.ui.main;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.example.nomankhan.mvpwithdaggerdemo.R;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

/**
 * Created by Noman Khan on 24/08/17.
 */

class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.CustomHolder> {

    private List<MyContact> modelList;
    private Context mContext;

    @Inject
    ContactsAdapter() {
        modelList = new ArrayList<>();
    }

    @Override
    public CustomHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (mContext == null) {
            mContext = parent.getContext();
        }
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_contacts, parent, false);
        return new CustomHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomHolder holder, int position) {

        MyContact contactModel = modelList.get(position);
        holder.tvName.setText(contactModel.getName());
        holder.textViewNUmber.setText(contactModel.getContactNumber());

    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    void updateAdapter(List<MyContact> list) {
        modelList.addAll(list);
        notifyDataSetChanged();
    }

    class CustomHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.tvName)
        TextView tvName;

        @BindView(R.id.textViewNumber)
        TextView textViewNUmber;

        CustomHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

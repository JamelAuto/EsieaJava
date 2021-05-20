package com.example.esieajava;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.lang.reflect.Array;
import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ImageViewHolder> {
        private  int[] images;
        private RecyclerViewClickInterface recyclerViewClickInterface;

        public ListAdapter(int[] images, RecyclerViewClickInterface recyclerViewClickInterface){
            this.images = images;
            this.recyclerViewClickInterface = recyclerViewClickInterface;
        }

        public class ImageViewHolder extends RecyclerView.ViewHolder {
            ImageView countryFlag;
            TextView countryName;

            public ImageViewHolder(View v) {
                super(v);
                countryFlag = v.findViewById(R.id.flag);
                countryName = v.findViewById(R.id.country);

                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View vee) {
                        recyclerViewClickInterface.onItemClick(getBindingAdapterPosition());
                    }
                });
            }
        }

        // Create new views (invoked by the layout manager)
        @Override
        public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout,parent,false);
            ImageViewHolder imageViewHolder = new ImageViewHolder(view);
            return imageViewHolder;
        }

        // Replace the contents of a view (invoked by the layout manager)
        @Override
        public void onBindViewHolder(ImageViewHolder holder, final int position) {
            int images_id = images[position];
            String[] arrayCountrie = {"Autriche","Bénin","Cameroun","Cuba","Egypte","Finlande","France","Allemagne","Irlande","Jordanie"
                                        ,"Lettonie","Malte","Méxique","Népal","Rwanda","Serbie","Singapoure","Espagne","Togo","Uruguay"};
            holder.countryFlag.setImageResource(images_id);
            holder.countryName.setText (arrayCountrie[position]);
        }

    @Override
    public int getItemCount() {
        return images.length;
    }



}


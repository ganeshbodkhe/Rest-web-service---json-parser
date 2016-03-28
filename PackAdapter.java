package com.example.apostle.jsonparser;

/**
 * Created by Apostle on 3/28/2016.
 */
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by ganesh on 2/1/2016.
 */
public class PackAdapter extends RecyclerView.Adapter<PackAdapter.DataObjectHolder> {


    private List<Pack_list> packlist;
    Context context;

    PackAdapter.MyClickListener myClickListener;

    public PackAdapter(Context context, List<Pack_list> pack_list) {
        packlist=pack_list;
        this.context=context;
    }

    public interface MyClickListener {
    }




    public static class DataObjectHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        Context con;

        TextView Pack_id;
        TextView Pack_name;
        TextView Pack_status;
        ImageView Pack_image;

        public DataObjectHolder(View itemView, final Context c){
            super(itemView);
            this.con=c;

            Pack_id=(TextView)itemView.findViewById(R.id.packlist_id);
            Pack_name=(TextView)itemView.findViewById(R.id.packlist_Name);
            Pack_status=(TextView)itemView.findViewById(R.id.packlist_status);
            Pack_image=(ImageView)itemView.findViewById(R.id.Packlist_image);

            Pack_image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Intent i= new Intent(c,PackActivity.class);
//                    c.startActivity(i);
                }
            });

        }

        @Override
        public void onClick(View v) {

        }
    }

    @Override
    public PackAdapter.DataObjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.packlist,parent, false);
        DataObjectHolder holder = new DataObjectHolder(v,parent.getContext());
        return holder;
    }

    @Override
    public void onBindViewHolder(PackAdapter.DataObjectHolder holder,final int position) {

        holder.Pack_id.setText(packlist.get(position).getPack_id());
        holder.Pack_name.setText(packlist.get(position).getPack_name());
        holder.Pack_status.setText(packlist.get(position).pack_status);

        String image= packlist.get(position).getPack_image();

        Picasso.with(context)
                .load(image)
                .into(holder.Pack_image);

    }



    @Override
    public int getItemCount() {
        return packlist.size();
    }





}

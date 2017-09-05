package kr.co.company.providertest2;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Twinny32_LJH on 2017-09-04.
 */

class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<Impo> ImpoList;
    private  int itemLayout;



    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView tvname;
        public TextView tvnumber;
        public ViewHolder(View v) {
            super(v);
            tvname =(TextView)v.findViewById(R.id.nameTV);
            tvnumber=(TextView)v.findViewById(R.id.numberTV);

        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(List<Impo> items, int itemLayout) {
        ImpoList = items;
        this.itemLayout =itemLayout;
    }

    // Create new views (invoked by the layout manager)

    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(itemLayout, parent, false);


        return new ViewHolder(v);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        Impo item =ImpoList.get(position);
        holder.tvname.setText(item.getNames());
        holder.tvnumber.setText(item.getNumber());

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {        return ImpoList.size();    }
}

package moveinn.par.thunderbolt.moveinn;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;


public class moveinnadapter extends RecyclerView.Adapter<moveinnadapter.MyViewHolder> {

    private LayoutInflater inflator;
    List<Information> data= Collections.emptyList();
    private Context context;
    private ClickListner clickListner;

    public moveinnadapter(Context context,List<Information> data){
        this.context=context;
        inflator=LayoutInflater.from(context);
        this.data=data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflator.inflate(R.layout.custom_row, parent, false);
        MyViewHolder holder=new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Information current=data.get(position);
        holder.title.setText(current.title);
        holder.icon.setImageResource(current.iconId);

    }

    public void setClickListner(ClickListner clickListner){
        this.clickListner=clickListner;
    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView title;
        ImageView icon;

        public MyViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            title= (TextView) itemView.findViewById(R.id.listMessage);
            icon= (ImageView) itemView.findViewById(R.id.listItem);
        }

        @Override
        public void onClick(View v) {
            //context.startActivity(new Intent(context,home.class));
           if(clickListner!=null){
            //\
               clickListner.itemClicked(v,getPosition());
            }
            else
            context.startActivity(new Intent(context,home.class));



        }
    }

    public interface ClickListner{
        public void itemClicked(View view,int position);
    }
}

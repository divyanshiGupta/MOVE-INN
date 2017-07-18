package moveinn.par.thunderbolt.moveinn;

import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;



public class ListsAdapter extends RecyclerView.Adapter<ListsAdapter.UserViewHolder>{

    private List<Details> listDetails;

    public ListsAdapter(List<Details> listUsers) {
        this.listDetails = listUsers;
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // inflating recycler item view
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_user_recycler, parent, false);

        return new UserViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        holder.textViewName.setText(listDetails.get(position).getType());
        holder.textViewEmail.setText(listDetails.get(position).getAmount());
        holder.textViewPassword.setText(listDetails.get(position).getRooms());
    }

    @Override
    public int getItemCount() {
        Log.v(ListsAdapter.class.getSimpleName(),""+listDetails.size());
        return listDetails.size();
    }


    /**
     * ViewHolder class
     */
    public class UserViewHolder extends RecyclerView.ViewHolder {

        public AppCompatTextView textViewName;
        public AppCompatTextView textViewEmail;
        public AppCompatTextView textViewPassword;

        public UserViewHolder(View view) {
            super(view);
            textViewName = (AppCompatTextView) view.findViewById(R.id.textViewType);
            textViewEmail = (AppCompatTextView) view.findViewById(R.id.textViewAmount);
            textViewPassword = (AppCompatTextView) view.findViewById(R.id.textViewRooms);
        }
    }
}

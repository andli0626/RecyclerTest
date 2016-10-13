package chenyu.com.apptest;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/5/7.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<String> mDataSet;
    private OnItemClickListener mOnItemClickListener;
    private OnItemLongClickListener mOnItemLongClickListener;
    private List<Integer> mHeight;

    public MyAdapter(List<String> data){
        mDataSet = data;
        mHeight = new ArrayList<Integer>();
        for (int i = 0; i < mDataSet.size(); i++)
        {
            mHeight.add( (int) (120 + Math.random() * 300));
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemlayout, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mTextView.setText(mDataSet.get(position));
//        ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();
//        lp.height = mHeight.get(position);
//        holder.itemView.setLayoutParams(lp);
        if(mOnItemClickListener != null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = holder.getLayoutPosition();
                    mOnItemClickListener.onItemClick(holder.itemView,position);
                }
            });
        }
        if(mOnItemLongClickListener != null){
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int position = holder.getLayoutPosition();
                    mOnItemLongClickListener.onItemLongClick(holder.itemView,position);
                    return false;
                }
            });
        }
    }

    private int count=0;

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    public void removeData(int position) {
        mDataSet.remove(position);
        notifyItemRemoved(position);
    }

    public void addData(int position){
        mDataSet.add(position,"Add One");
        notifyItemInserted(position);
    }

    public void changeData(int position){
        mDataSet.set(position,"Item has changed"+ count++);
        notifyItemChanged(position);
    }


    class ViewHolder extends RecyclerView.ViewHolder{

        public TextView mTextView;
        public ViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.num);
        }
    }

    public interface OnItemClickListener{
        void onItemClick(View view,int position);
    }

    public interface OnItemLongClickListener{
        void onItemLongClick(View view,int position);
    }

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener){
        this.mOnItemClickListener = mOnItemClickListener;
    }
    public void setOnItemLongClickListener(OnItemLongClickListener mOnItemLongClickListener) {
        this.mOnItemLongClickListener = mOnItemLongClickListener;
    }
}

package com.tms.recyclerviewdemo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Administrator on 2018/10/25.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsHolder> {


    private static final String TAG = "NewsAdapter";
    private final int newsHasReadcolor;
    private final int newsUnReadcolor;
    private Context context;
    private List<NewsBean> data;
    private LayoutInflater inflater;

    private OnClickItemListener onClickItemListener;
    private OnDeleteItemListener onDeleteItemListener;

    public NewsAdapter(Context context, List<NewsBean> data) {
        this.context = context;
        this.data = data;
        inflater = LayoutInflater.from(context);
        newsHasReadcolor = ContextCompat.getColor(context, R.color.colorHasReadNewsText);
        newsUnReadcolor = ContextCompat.getColor(context, R.color.colorUnreadNewsText);
    }


    @NonNull
    @Override
    public NewsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.item_view, parent, false);
        return new NewsHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsHolder holder, int position) {
        int i = position;
        NewsBean news = data.get(position);
        if (news.isHasRead()) {
            holder.newsTitle.setTextColor(newsHasReadcolor);
        } else {
            holder.newsTitle.setTextColor(newsUnReadcolor);
        }
        holder.newsTitle.setText(news.getNewsTitle());

    }

    public void setOnClickItemListener(OnClickItemListener onClickItemListener) {
        this.onClickItemListener = onClickItemListener;
    }

    public void setOnDeleteItemListener(OnDeleteItemListener onDeleteItemListener) {
        this.onDeleteItemListener = onDeleteItemListener;
    }

    /**
     * 添加数据并刷新
     *
     * @param list
     */
    public void addAll(List<NewsBean> list) {
        data.addAll(list);
        notifyDataSetChanged();
    }

    /**
     * 添加数据并刷新
     *
     * @param list
     */
    public void clearAddAll(List<NewsBean> list) {
        data.clear();
        data.addAll(list);
        notifyDataSetChanged();
    }


    /**
     * 将已读文章UI进行改变
     *
     * @param position
     */
    public void changeToHasReadUI(int position) {
        NewsBean newsBean = data.get(position);
        newsBean.setHasRead(true);
        data.set(position, newsBean);
        notifyItemChanged(position);
    }

    public NewsBean getItem(int position) {
        return data.get(position);
    }


    public void removeItemAndNotify(int position) {
        data.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, data.size() - position);
    }

    public void removeItem(int position) {
        data.remove(position);
        notifyItemRemoved(position);
    }


    @Override

    public int getItemCount() {
        return data.size();
    }

    class NewsHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView newsTitle;

        TextView delete;


        public NewsHolder(View itemView) {
            super(itemView);
            newsTitle = ((TextView) itemView.findViewById(R.id.text));
            delete = (TextView) itemView.findViewById(R.id.delete);
            newsTitle.setOnClickListener(this);
            delete.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.text:
                    if (onClickItemListener != null) {
                        onClickItemListener.onClickItem(v, getAdapterPosition());
                    }
                    break;
                case R.id.delete:
                    if (onDeleteItemListener != null) {
                        onDeleteItemListener.onDeleteItem(v, getAdapterPosition());
                    }
                    break;
            }

        }
    }

    public interface OnClickItemListener {
        void onClickItem(View view, int position);
    }

    public interface OnDeleteItemListener {
        void onDeleteItem(View view, int position);
    }
}

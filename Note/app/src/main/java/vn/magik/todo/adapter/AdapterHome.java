package vn.magik.todo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import vn.magik.todo.R;
import vn.magik.todo.bean.AppConstant;
import vn.magik.todo.bean.entity.Note;

/**
 * Created by TOAN on 10/2/2016.
 */

public class AdapterHome extends BaseAdapter{
    private List<Note> listData;
    private Context context;
    private LayoutInflater inflater;

    public AdapterHome(Context context, List<Note> listData) {
        this.context = context;
        this.listData = listData;
        this.inflater = LayoutInflater.from(context);
    }
    public void updateList(List<Note> list){
        this.listData = list;
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView==null){
            convertView = inflater.inflate(R.layout.item, null);
            holder = new ViewHolder();
            holder.level =(ImageView) convertView.findViewById(R.id.item_level);
            holder.title = (TextView) convertView.findViewById(R.id.item_title);
            holder.time = (TextView)  convertView.findViewById(R.id.item_time);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        Note note = listData.get(position);
        holder.title.setText(note.getTitle());
        holder.time.setText(note.getTime());
        setBkgStt(note.getLevel(), holder.level);
        return convertView;
    }

    static class ViewHolder{
        ImageView level;
        TextView title;
        TextView time;
    }
    private void setBkgStt(String stt, ImageView imageView){
        if (stt.equals(AppConstant.STATUS.LOW)){
            imageView.setBackgroundResource(R.drawable.bkg_low);
        } else if (stt.equals(AppConstant.STATUS.MEDIUM)){
            imageView.setBackgroundResource(R.drawable.bkg_medium);
        } else if (stt.equals(AppConstant.STATUS.HIGHT)){
            imageView.setBackgroundResource(R.drawable.bkg_height);
        }
    }
}

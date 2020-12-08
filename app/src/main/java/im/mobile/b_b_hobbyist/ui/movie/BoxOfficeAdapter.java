package im.mobile.b_b_hobbyist.ui.movie;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import im.mobile.b_b_hobbyist.R;

public class BoxOfficeAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private Context context;
    private int layout;
    private ArrayList<BoxOffice> list;

    public BoxOfficeAdapter(Context context, int layout, ArrayList<BoxOffice> list) {
        this.context = context;
        this.layout = layout;
        this.list = list;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public BoxOffice getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return list.get(i).get_id();
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        View view = convertView;
        ViewHolder viewHolder = null;

        if (view == null) {
            view = inflater.inflate(layout, viewGroup, false);
            viewHolder = new ViewHolder();
            viewHolder.tvRank = view.findViewById(R.id.text_box_rank);
            viewHolder.tvName = view.findViewById(R.id.text__box_name);
            viewHolder.tvNew = view.findViewById(R.id.text__box_new);
            viewHolder.tvOpen = view.findViewById(R.id.text__box_open);
            viewHolder.tvAudi = view.findViewById(R.id.text__box_audi);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder)view.getTag();
        }

        BoxOffice boxOffice = list.get(i);

        viewHolder.tvRank.setText(boxOffice.getRank());
        viewHolder.tvName.setText(boxOffice.getMovieNm());
        viewHolder.tvNew.setText(boxOffice.getRankOldAndNew());
        viewHolder.tvOpen.setText(boxOffice.getOpenDt());
        viewHolder.tvAudi.setText(boxOffice.getAudiAcc());

        return view;
    }

    public void setList(ArrayList<BoxOffice> list) {
        this.list = list;
    }

    static class ViewHolder {
        public TextView tvRank = null;
        public TextView tvName = null;
        public TextView tvNew = null;
        public TextView tvOpen = null;
        public TextView tvAudi = null;
    }

}

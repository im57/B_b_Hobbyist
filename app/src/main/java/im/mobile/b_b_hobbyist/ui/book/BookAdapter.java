package im.mobile.b_b_hobbyist.ui.book;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import im.mobile.b_b_hobbyist.R;

public class BookAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private Context context;
    private int layout;
    private ArrayList<Book> list;

    public BookAdapter(Context context, int layout, ArrayList<Book> list) {
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
    public Book getItem(int i) {
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
            viewHolder.tvTitle = view.findViewById(R.id.text_book_title);
            viewHolder.tvRights = view.findViewById(R.id.text_book_rights);
            viewHolder.tvAffiliation = view.findViewById(R.id.text_book_affiliation);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder)view.getTag();
        }

        Book book = list.get(i);

        viewHolder.tvTitle.setText(book.getTitle());
        viewHolder.tvRights.setText(book.getRights());
        viewHolder.tvAffiliation.setText(book.getAffiliation());

        return view;
    }

    public void setList(ArrayList<Book> list) {
        this.list = list;
    }

    static class ViewHolder {
        public TextView tvTitle = null;
        public TextView tvRights = null;
        public TextView tvAffiliation = null;
    }

}

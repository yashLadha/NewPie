package own.code.play_nigga;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created in Play_Nigga on 2/26/2017.
 */

public class DrawerItemCustomAdapter extends ArrayAdapter<DataModel> {

    private Context context;
    private int resource_id;

    public DrawerItemCustomAdapter(Context context, int resource, List<DataModel> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource_id = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            v = LayoutInflater.from(context).inflate(R.layout.list_view_item_row, null);
        }
        TextView textView = (TextView) v.findViewById(R.id.textViewName);
        DataModel temp = getItem(position);

        textView.setText(temp.getName());
        return v;
    }
}

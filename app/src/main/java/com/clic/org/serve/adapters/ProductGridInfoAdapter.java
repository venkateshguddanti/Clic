package com.clic.org.serve.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import com.clic.org.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Venkatesh on 12-06-2016.
 */
public class ProductGridInfoAdapter<T> extends BaseAdapter {

    private List<T> objects;
    private LayoutInflater inflater;
    private int resources;
    private ItemFilter mFilter = new ItemFilter();
    private List<String> originalList;
    private List<String> filteredData;

    public ProductGridInfoAdapter(Context context, List<T> objects,
                                  int resources) {

        this.resources = resources;
        this.objects = objects;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        originalList = getFilterList(objects);
    }
    public List<String> getFilterList(List<T> objects) {

        List<String> mlist = new ArrayList<>();
        for (int i=0 ; i<objects.size();i++)
        {
            mlist.add(objects.get(i).toString());
        }

        return  mlist;
    }
    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ItemHolder  itemHolder;


        if (convertView == null) {

            convertView = inflater.inflate(R.layout.recommennd_product_listitem, null);
            itemHolder = new ItemHolder();
            itemHolder.itemName = (TextView) convertView.findViewById(R.id.item_name);
            itemHolder.itemDes = (TextView) convertView.findViewById(R.id.item_des);
            itemHolder.itemPrice = (TextView) convertView.findViewById(R.id.item_price);
            itemHolder.itemViews = (TextView) convertView.findViewById(R.id.item_views);
            itemHolder.itemDate = (TextView) convertView.findViewById(R.id.item_date);
            itemHolder.itemIcon = (ImageView)convertView.findViewById(R.id.item_icon);
            convertView.setTag(itemHolder);

        } else {
            itemHolder = (ItemHolder) convertView.getTag();
        }


        itemHolder.itemName.setText(objects.get(position).toString());
        itemHolder.itemIcon.setBackgroundResource(R.drawable.accessories);
        itemHolder.itemDes.setText("Item Description");
        itemHolder.itemDate.setText("Date");
        itemHolder.itemPrice.setText("100");
        itemHolder.itemViews.setText("120");
        return convertView;
    }
    public Filter getFilter() {
        return mFilter;
    }

    private class ItemFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            String filterString = constraint.toString().toLowerCase();

            FilterResults results = new FilterResults();

            final List<String> list = originalList;

            int count = list.size();
            final ArrayList<String> nlist = new ArrayList<String>(count);

            String filterableString ;

            for (int i = 0; i < count; i++) {
                filterableString = list.get(i);
                if (filterableString.toLowerCase().contains(filterString)) {
                    nlist.add(filterableString);
                }
            }

            results.values = nlist;
            results.count = nlist.size();

            return results;
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint, Filter.FilterResults results) {
            filteredData = (ArrayList<String>) results.values;
            notifyDataSetChanged();
        }

    }


    static class ItemHolder {
        TextView itemName;
        ImageView itemIcon;
        TextView itemDes;
        TextView itemViews;
        TextView itemDate;
        TextView itemPrice;

    }
}

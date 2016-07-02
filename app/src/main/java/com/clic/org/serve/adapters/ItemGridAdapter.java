package com.clic.org.serve.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.clic.imageservices.utils.ImageServices;
import com.clic.org.R;
import com.clic.org.serve.Utils.ClicUtils;
import com.clic.org.serve.constants.ClicConstants;
import com.clic.org.serve.data.ItemDocs;
import com.clic.org.serve.data.UserItem;

import java.util.ArrayList;

/**
 * Created by Venkatesh on 21-05-2016.
 */
public class ItemGridAdapter extends BaseAdapter
{
    private Context mContext;
    private final ArrayList<UserItem> mUserItemsList;
    private final ArrayList<ItemDocs> mUserItemDocsList;
    private String gridType;

    public ItemGridAdapter(Context context,ArrayList<UserItem> mUserItemsList,ArrayList<ItemDocs> mUserItemDocsList,String gridType)
    {
        this.mContext = context;
        this.mUserItemsList = mUserItemsList;
        mUserItemsList.add(new UserItem());
        this.mUserItemDocsList = mUserItemDocsList;
        this.gridType = gridType;
    }
    @Override
    public int getCount() {
        return mUserItemsList.size();
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
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {


            if(gridType.equalsIgnoreCase(ClicConstants.CLIC_PRODUCTS)) {
                convertView = inflater.inflate(R.layout.grid_item, null);
                ClicUtils.setItemHeightWidth(mContext, convertView);
            }
            else
            {
                convertView = inflater.inflate(R.layout.clic_menu_item, null);
            }
            itemHolder = new ItemHolder();
            itemHolder.itemName = (TextView) convertView.findViewById(R.id.item_name);
            itemHolder.itemIcon = (ImageView)convertView.findViewById(R.id.item_icon);
            convertView.setTag(itemHolder);

        } else {
            itemHolder = (ItemHolder) convertView.getTag();
        }

        if(position == mUserItemsList.size()-1)
        {
            itemHolder.itemName.setText("ADD PRODUCT");
            // ImageServices.displayImageFromUrl(mContext, mUserItemDocsList.get(position).getFilePath(), itemHolder.itemIcon);
            itemHolder.itemIcon.setBackgroundResource(R.drawable.click);
        }
        else
        {
            itemHolder.itemName.setText(mUserItemsList.get(position).getModelNumber());
            // ImageServices.displayImageFromUrl(mContext, mUserItemDocsList.get(position).getFilePath(), itemHolder.itemIcon);
            itemHolder.itemIcon.setBackgroundResource(R.drawable.click);
        }

        return convertView;
    }

    static class ItemHolder {
        TextView itemName;
        ImageView itemIcon;

    }
}

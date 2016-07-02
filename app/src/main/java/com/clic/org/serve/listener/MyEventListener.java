package com.clic.org.serve.listener;

import com.clic.org.serve.data.ItemDocs;
import com.clic.org.serve.data.UserItem;

/**
 * Created by Venkatesh on 22-06-2016.
 */
public class MyEventListener {

    public final ItemDocs mItemDocs;
    public MyEventListener(ItemDocs mItemDocs)
    {
        this.mItemDocs = mItemDocs;
    }
    public ItemDocs getItemDocs()
    {
        return mItemDocs;
    }
}

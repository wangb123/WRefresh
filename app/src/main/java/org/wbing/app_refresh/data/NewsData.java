package org.wbing.app_refresh.data;

 import org.wbing.app_refresh.R;
 import org.wbing.view.list.IMultiItem;

/**
 * @author wangbing
 * @date 2018/8/23
 */
public class NewsData implements IMultiItem {
    public String text;
    public int color;

    public NewsData(int color, String text) {
        this.text = text;
        this.color = color;
    }

    @Override
    public int getItemType() {
        return R.layout.item_news;
    }

    @Override
    public Object getData() {
        return this;
    }
}

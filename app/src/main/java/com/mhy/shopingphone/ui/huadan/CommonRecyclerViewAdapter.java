package com.mhy.shopingphone.ui.huadan;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 小金子 on 2016/4/28.
 * 通用的RecyclerView的适配器
 * 1.支持添加头部
 * 2.支持监听条目点击事件{@link CommonRecyclerViewAdapter#setOnRecyclerViewItemClickListener(OnRecyclerViewItemClickListener)}
 * 3.支持监听条目中的控件的点击事件{@link CommonRecyclerViewAdapter#setOnViewInItemClickListener(OnViewInItemClickListener, int...)}
 * <p>
 * 注意:在使用这个通用适配器的时候,因为RecyclerView是根据ItemView的type来决定是否复用的,所以在这个里面
 * 所有的HEader都是一个类型{@link CommonRecyclerViewAdapter#HEADER_TYPE}那么久代表HeaderView都是会被复用的,所有暂时会有添加不同的HeadView会出现问题
 * 所有的Foots都是一个类型{@link CommonRecyclerViewAdapter#FOOT_TYPE}那么久代表FootView都是会被复用的,所有暂时会有添加不同的FootView会出现问题
 * 多个头部或者多个尾部会出现复用问题哦
 */
public abstract class CommonRecyclerViewAdapter<T> extends RecyclerView.Adapter<CommonRecyclerViewHolder> {

    public static int HEADER_TYPE = -11;
    public static int FOOT_TYPE = -17;
    public int selectPosition = 0;
    /**
     * 每一次添加Head或者Foot的时候,都会让这个变量--,分配一个唯一的viewType
     */
    private int mType = -1;

    /**
     * 记录头部的试图的ViewType
     */
    private List<Integer> headerList = new ArrayList<Integer>();

    /**
     * 记录尾部的试图的ViewType
     */
    private List<Integer> footList = new ArrayList<Integer>();

    /**
     * 条目的点击事件的监听接口
     */
    private OnRecyclerViewItemClickListener onRecyclerViewItemClickListener;

    /**
     * 条目里面的控件的点击事件的监听接口
     */
    private OnViewInItemClickListener onViewInItemClickListener;

    /**
     * item中的需要监听的view的id
     */
    private int[] viewIdsInItem;

    /**
     *
     */
    private int[] itemTypesForItem;

    /**
     *
     */
    private int[] itemTypesForViewInItem;

    /**
     * 上下文对象
     */
    protected Context context;

    /**
     * 要显示的数据
     */
    protected List<T> data;


    /**
     * 记录当前的position
     */
    private int mCurrentPosition = -1;

    /**
     * 头部的试图
     */
    private ArrayList<View> headers = new ArrayList<View>();

    /**
     * 尾部的试图
     */
    private ArrayList<View> foots = new ArrayList<View>();

    /**
     * 添加头部的试图,默认不通知适配器插入了一个条目
     *
     * @param view
     */
    public void addHeaderView(View view) {
        addHeaderView(view, false);
    }

    /**
     * 添加尾部的试图,默认不通知适配器插入了一个条目
     *
     * @param view
     */
    public void addFootView(View view) {
        addFootView(view, false);
    }

    public void notify(List<T> datas) {
        this.data = datas;
        notifyDataSetChanged();
    }

    /**
     * 添加头部的试图
     *
     * @param view
     * @param isNotify 是否通知插入一个条目
     */
    public void addHeaderView(View view, boolean isNotify) {
        if (view == null) {
            throw new NullPointerException("the header view can not be null");
        }
        headers.add(view);
        mType--;
        headerList.add(mType);
        if (isNotify) {
            notifyItemInserted(headers.size() - 1);
        }
    }


    /**
     * 添加尾部的试图
     *
     * @param view
     * @param isNotify 是否通知插入一个条目
     */
    public void addFootView(View view, boolean isNotify) {
        if (view == null) {
            throw new NullPointerException("the foot view can not be null");
        }
        foots.add(view);
        mType--;
        footList.add(mType);
        if (isNotify) {
            notifyItemInserted(getItemCount());
        }
    }

    /**
     * 删除一个试图,默认不通知删除一个条目
     *
     * @param position
     */
    public void removeHeaderView(int position) {
        removeHeaderView(position, false);
    }

    /**
     * 删除一个试图
     *
     * @param position
     */
    public void removeHeaderView(int position, boolean isNotify) {
        headers.remove(position);
        if (isNotify) {
            notifyItemRemoved(position);
        }
    }

    /**
     * 获取头部试图的个数
     *
     * @return
     */
    public int getHeaderCounts() {
        return headers.size();
    }


    /**
     * 获取尾部试图的个数
     *
     * @return
     */
    public int getFootCounts() {
        return foots.size();
    }

    /**
     * 构造函数
     *
     * @param context 上下文
     * @param data    显示的数据
     */
    public CommonRecyclerViewAdapter(Context context, List<T> data) {
        this.data = data;
        this.context = context;
    }

    /**
     * 构造函数
     *
     * @param context 上下文
     * @paramdata 显示的数据
     */
    public CommonRecyclerViewAdapter(Context context, T mData) {
        if (data == null) {
            data = new ArrayList<>();
        }
        data.clear();
        data.add(mData);
        this.context = context;
    }

    /**
     * viewType 是通过{@link RecyclerView.Adapter#getItemViewType(int)}获取到的
     *
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public CommonRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = null;
        if (mCurrentPosition < headers.size() && mCurrentPosition != -1 && viewType == headerList.get(mCurrentPosition)) { //说明是头部,并且下标在头部的集合大小之间的
            view = headers.get(mCurrentPosition);
        } else if (mCurrentPosition + getFootCounts() >= getItemCount() && mCurrentPosition != -1 && viewType == footList.get(mCurrentPosition - (getItemCount() - getFootCounts()))) { //说明是尾部,并且下标在尾部的集合大小之间的
            view = foots.get(mCurrentPosition - (getItemCount() - getFootCounts()));
        } else {
            view = View.inflate(context, getLayoutViewId(viewType), null);
        }

        CommonRecyclerViewHolder vh = new CommonRecyclerViewHolder(view);

        //试图被创建的时候调用
        viewCreated(vh, viewType);

        return vh;
    }

    /**
     * 获取条目的类型
     *
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        //记录当前的位置信息
        mCurrentPosition = position;
        //拿到头试图的长度
        int headerSize = headers.size();
        //拿到尾部的试图的个数
        int footSize = foots.size();
        //如果下标指向的是头的试图或者是尾部的试图的时候
        if (position < headerSize) {
            return headerList.get(position);
        } else if (footSize + position >= getItemCount()) {
            return footList.get(position - (getItemCount() - footSize));
        }
        return getItemType(position - headerSize);
    }

    /**
     * 获取条目的类型
     *
     * @return
     */
    public int getItemType(int position) {
        return 0;
    }

    @Override
    public void onBindViewHolder(CommonRecyclerViewHolder h, int position) {
        // 拿到头部试图个数
        int headerCounts = getHeaderCounts();
        if (position < headerCounts || position + getFootCounts() >= getItemCount()) {
            //如果是头部或者尾部,不做处理
        } else {
            position = position - headerCounts;
            if (isNeedSetClickListener(position)) { //如果需要设置监听
                h.itemView.setOnClickListener(new MyItemClickListenerAdapter(h));
            }
            if (viewIdsInItem != null && viewIdsInItem.length > 0 && isNeedSetViewInItemClickListener(position)) {
                for (int i = 0; i < viewIdsInItem.length; i++) {
                    View v = h.getView(viewIdsInItem[i]);
                    if (v != null) {
                        v.setOnClickListener(new MyViewInItemClickListenerAdapter(h, viewIdsInItem[i]));
                    }
                }
            }
            convert(h, data.get(position), position);
        }
    }

    /**
     * 判断是否需要设置监听的
     *
     * @param position
     * @return
     */
    private boolean isNeedSetClickListener(int position) {
        if (itemTypesForItem == null || itemTypesForItem.length == 0) {
            return true;
        }
        int itemType = getItemType(position);
        for (int i = 0; i < itemTypesForItem.length; i++) {
            if (itemType == itemTypesForItem[i]) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断是否需要设置item内的控件的监听事件
     *
     * @param position
     * @return
     */
    private boolean isNeedSetViewInItemClickListener(int position) {
        if (itemTypesForViewInItem == null || itemTypesForViewInItem.length == 0) {
            return true;
        }
        int itemType = getItemType(position);
        for (int i = 0; i < itemTypesForViewInItem.length; i++) {
            if (itemType == itemTypesForViewInItem[i]) {
                return true;
            }
        }
        return false;
    }

    /**
     * 实现列表的显示
     *
     * @param h        RecycleView的ViewHolder
     * @param entity   实体对象
     * @param position 当前的下标
     */
    public abstract void convert(CommonRecyclerViewHolder h, T entity, int position);

    /**
     * 布局文件被转化成View的时候调用
     *
     * @param vh
     * @param viewType
     */
    public void viewCreated(CommonRecyclerViewHolder vh, int viewType) {
    }

    /**
     * @param viewType 返回值就是根据这个值进行判断返回的
     *                 对头部不起作用
     * @return
     */
    public abstract int getLayoutViewId(int viewType);

    /**
     * 集合的长度和头部试图的个数
     *
     * @return
     */
    @Override
    public int getItemCount() {
        return data.size() + headers.size() + foots.size();
    }

    /**
     * 设置所有的条目的监听事件
     *
     * @param onRecyclerViewItemClickListener
     */
    public void setOnRecyclerViewItemClickListener(OnRecyclerViewItemClickListener onRecyclerViewItemClickListener) {
        this.onRecyclerViewItemClickListener = onRecyclerViewItemClickListener;
    }

    /**
     * 设置条目的监听,具有一个筛选作用<br>
     * 针对{@link CommonRecyclerViewAdapter#getItemType(int)}}的值在
     *
     * @param onRecyclerViewItemClickListener
     * @param itemTypes                       只有通过
     */
    public void setOnRecyclerViewItemClickListener(OnRecyclerViewItemClickListener onRecyclerViewItemClickListener, int... itemTypes) {
        this.onRecyclerViewItemClickListener = onRecyclerViewItemClickListener;
        this.itemTypesForItem = itemTypes;
    }

    public void setSelectPosition(int selectPosition) {
        this.selectPosition = selectPosition;
    }


    /**
     * 每一个item由于都是一样的,那么里面的有些控件有时候需要点击事件,那么这里框架代为处理
     */
    public interface OnViewInItemClickListener {

        /**
         * 回调的方法
         *
         * @param v
         * @param position
         */
        void onViewInItemClick(View v, int position);

    }

    /**
     * 设置条目里面的控件的监听事件
     *
     * @param onViewInItemClickListener 回掉接口
     * @param viewIdsInItem             item中需要监听的view的id数组,可以为null
     */
    public void setOnViewInItemClickListener(OnViewInItemClickListener onViewInItemClickListener, int... viewIdsInItem) {
        this.viewIdsInItem = viewIdsInItem;
        this.onViewInItemClickListener = onViewInItemClickListener;
    }

    /**
     * 设置item里面的控件的点击事件起作用的ItemType
     *
     * @param itemTypes
     */
    public void setItemTypesInItem(int... itemTypes) {
        this.itemTypesForViewInItem = itemTypes;
    }

    /**
     * 条目的点击事件
     */
    public interface OnRecyclerViewItemClickListener {

        /**
         * 回调的方法
         *
         * @param v
         * @param position
         */
        void onItemClick(View v, int position);

    }

    /**
     * 实现点击的接口,每一个ViewItem都对应一个这个类,每一个都不一样的对象
     */
    private class MyItemClickListenerAdapter implements View.OnClickListener {

        /**
         * 条目的下标
         */
        private CommonRecyclerViewHolder h;

        public MyItemClickListenerAdapter(CommonRecyclerViewHolder h) {
            this.h = h;
        }

        @Override
        public void onClick(View v) {
            if (onRecyclerViewItemClickListener != null) {
                //回调方法
                onRecyclerViewItemClickListener.onItemClick(v, h.getAdapterPosition() - headers.size());
            }
        }

    }

    /**
     * 实现点击的接口,每一个ViewInItem都对应一个这个类,每一个都不一样的对象
     */
    private class MyViewInItemClickListenerAdapter implements View.OnClickListener {

        /**
         * 条目的下标
         */
        private CommonRecyclerViewHolder h;

        private int viewId;

        public MyViewInItemClickListenerAdapter(CommonRecyclerViewHolder h, int viewId) {
            this.h = h;
            this.viewId = viewId;
        }

        @Override
        public void onClick(View v) {
            if (onViewInItemClickListener != null) {
                //回调方法
                onViewInItemClickListener.onViewInItemClick(v, h.getAdapterPosition() - headers.size());
            }
        }

    }

}

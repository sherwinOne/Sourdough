package com.sherwin.sourdough.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sherwin.sourdough.R

/**
 * Created by Administrator on 2017/11/20.
 */
abstract class RefreshAdapter<D> (list: ArrayList<D>?): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    open val list by lazy { list }

    private val VIEW_TYPE_NULLLIST = -1
    /**
     *  获取条目 View填充的类型
     *  默认返回0
     *  将lists为空返回-1
     * @param position
     * @return
     */
    override fun getItemViewType(position: Int): Int {
        return if (list == null ||list!!.size <= 0) VIEW_TYPE_NULLLIST else super.getItemViewType(position)
    }

    override fun getItemCount(): Int {
        return if (list?.size!! > 0) list?.size!! else 1
    }

    open var setNullDateLayout : Int = R.layout.layout_background_null

    abstract fun onCreateViewHolder(parent: ViewGroup?) : RecyclerView.ViewHolder

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == VIEW_TYPE_NULLLIST && setNullDateLayout != 0){
            val nullView = LayoutInflater.from(parent.context).inflate(setNullDateLayout,parent,false)
            MyEmptyHolder(nullView)
        }else{
            onCreateViewHolder(parent)
        }
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        val manager = recyclerView.layoutManager
        if (manager!= null && manager is GridLayoutManager){
            manager.spanSizeLookup = object  : GridLayoutManager.SpanSizeLookup(){
                override fun getSpanSize(position: Int): Int {
                    return if (getItemViewType(position) == VIEW_TYPE_NULLLIST) manager.spanCount else 1
                }
            }
        }
    }

    /**
     * 刷新列表
     */
    open fun refreshList(list: ArrayList<D>?){
        if (list != this.list){
            this.list?.clear()
            this.list?.addAll(list!!)
        }
        notifyDataSetChanged()
    }

    /**
     * 加载更多
     */
    fun loadMoreList(list: ArrayList<D>?){
        this.list?.addAll(list!!)
        notifyDataSetChanged()
    }

    abstract fun setMyEmptyLayout(itemView: View)

    inner class MyEmptyHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        init {
            setMyEmptyLayout(itemView)
        }

    }

}
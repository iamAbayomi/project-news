package com.oladiniabayomi.digitalnews.articles

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.DownsampleStrategy
import com.bumptech.glide.request.RequestOptions
import com.oladiniabayomi.digitalarticles.articles.Articles
import com.oladiniabayomi.digitalnews.R
import com.oladiniabayomi.digitalnews.interfaces.OnItemClickListener

public class ArticlesRecyclerViewAdapter(var context: Context, var currentArticles: ArrayList<Articles>?, val listener: OnItemClickListener ) : RecyclerView.Adapter<ArticlesRecyclerViewHolder>() {

    private val mInflater: LayoutInflater = LayoutInflater.from(context)


    internal fun setArticles(currentArticles: ArrayList<Articles>?){
        this.currentArticles = currentArticles
         notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticlesRecyclerViewHolder {
        val mItemView : View = mInflater.inflate(R.layout.article_item_view, parent, false)
        return  ArticlesRecyclerViewHolder(mItemView)

    }

    override fun getItemCount(): Int {
        return currentArticles!!.size
    }

    override fun onBindViewHolder(holder: ArticlesRecyclerViewHolder, position: Int) {
        var currentArticles : Articles = currentArticles!![position]
        holder.bindTo(currentArticles, context, listener)
    }
}


class ArticlesRecyclerViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

    val articleImage : ImageView =  itemView.findViewById(R.id.article_image)
    val articleTitle : TextView = itemView.findViewById(R.id.article_title)
    val articleDate : TextView = itemView.findViewById(R.id.article_date)



    fun bindTo(currentArticles: Articles, context: Context, listener: OnItemClickListener) {
     //   articleImage.setImageResource()

        Glide.with(context)
            .load(currentArticles.articlesThumbnailImage)
            .apply(RequestOptions().override(85,85).downsample(DownsampleStrategy.CENTER_INSIDE).skipMemoryCache(true).diskCacheStrategy(
                DiskCacheStrategy.NONE))

            .into(articleImage)

        articleTitle.text = currentArticles.articlesTitle!!.rendered
      //  articleDate.text = currentArticles.articlesTimeStamp
        articleDate.text = "by John Smith"

        itemView.setOnClickListener{
            listener.onItemClick( currentArticles)
        }
    }


}

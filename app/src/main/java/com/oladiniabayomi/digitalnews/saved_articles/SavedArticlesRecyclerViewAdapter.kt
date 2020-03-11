package com.oladiniabayomi.digitalnews.saved_articles

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.oladiniabayomi.digitalarticles.articles.Articles
import com.oladiniabayomi.digitalarticles.articles.SavedArticles
import com.oladiniabayomi.digitalnews.R
import com.oladiniabayomi.digitalnews.articles.ArticlesRecyclerViewHolder
import com.oladiniabayomi.digitalnews.interfaces.OnItemClickListener
import com.oladiniabayomi.digitalnews.interfaces.SavedOnItemClickListener

class SavedRecyclerViewAdapter(var context: Context, var currentArticles: ArrayList<SavedArticles>?, val listener: SavedOnItemClickListener) : RecyclerView.Adapter<SavedRecyclerViewHolder>() {

        private val mInflater: LayoutInflater = LayoutInflater.from(context)


        internal fun setArticles(currentArticles: ArrayList<SavedArticles>?){
            this.currentArticles = currentArticles
            notifyDataSetChanged()
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SavedRecyclerViewHolder {
            val mItemView : View = mInflater.inflate(R.layout.article_item_view, parent, false)
            return  SavedRecyclerViewHolder(mItemView)

        }

        override fun getItemCount(): Int {
            return currentArticles!!.size
        }

        override fun onBindViewHolder(holder:SavedRecyclerViewHolder, position: Int) {
            var currentArticles :SavedArticles = currentArticles!![position]
            holder.bindTo(currentArticles, context, listener)
        }
    }

class SavedRecyclerViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

    val articleImage : ImageView =  itemView.findViewById(R.id.article_image)
    val articleTitle : TextView = itemView.findViewById(R.id.article_title)
    val articleDate : TextView = itemView.findViewById(R.id.article_date)



    fun bindTo(currentArticles:SavedArticles, context: Context, listener: SavedOnItemClickListener) {
        //   articleImage.setImageResource()
        Glide.with(context)
            .load(currentArticles.articlesThumbnailImage)
            .into(articleImage)

        articleTitle.text = currentArticles.articlesTitle!!.rendered
        articleDate.text = currentArticles.articlesTimeStamp
        //Setting OnClickListener
        itemView.setOnClickListener{
            listener.onItemClick( currentArticles)
        }
    }
}

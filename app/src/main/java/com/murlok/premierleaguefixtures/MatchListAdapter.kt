package com.murlok.premierleaguefixtures

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MatchListAdapter(private val dataSet: Array<String>) :
    RecyclerView.Adapter<MatchListAdapter.ViewHolder>() {
        class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val homeTeamText: TextView = view.findViewById(R.id.homeTeamItem)
            val awayTeamText: TextView = view.findViewById(R.id.awayTeamItem)

            init {
                view.setOnClickListener {
                    val activity = view.context as? MainActivity
                    activity?.replaceFragment(DetailScreenFragment())
                }
            }

        }

        override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.item_match_layout, viewGroup, false)

            return ViewHolder(view)
        }

        override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
            viewHolder.homeTeamText.text = dataSet[position]
            viewHolder.awayTeamText.text = dataSet[position]

        }

        override fun getItemCount() = dataSet.size

}
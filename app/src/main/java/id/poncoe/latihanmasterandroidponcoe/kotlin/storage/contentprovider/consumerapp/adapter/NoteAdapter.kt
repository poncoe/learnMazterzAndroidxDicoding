package id.poncoe.latihanmasterandroidponcoe.kotlin.storage.contentprovider.consumerapp.adapter

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.poncoe.latihanmasterandroidponcoe.kotlin.storage.contentprovider.consumerapp.CustomOnItemClickListener
import id.poncoe.latihanmasterandroidponcoe.kotlin.storage.contentprovider.consumerapp.NoteAddUpdateActivity
import id.poncoe.latihanmasterandroidponcoe.kotlin.storage.contentprovider.consumerapp.entity.Note
import id.poncoe.latihanmasterandroidponcoe.R
import kotlinx.android.synthetic.main.item_note.view.*
import java.util.*

/**
 * Created by sidiqpermana on 11/23/16.
 */

class NoteAdapter(private val activity: Activity) : RecyclerView.Adapter<NoteAdapter.NoteViewholder>() {
    var listNotes = ArrayList<Note>()
        set(listNotes) {
            this.listNotes.clear()
            this.listNotes.addAll(listNotes)
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewholder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)
        return NoteViewholder(view)
    }

    override fun onBindViewHolder(holder: NoteViewholder, position: Int) {
        holder.bind(listNotes[position])
    }

    override fun getItemCount(): Int {
        return this.listNotes.size
    }

    inner class NoteViewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(note: Note) {
            with(itemView){
                tv_item_title.text = note.title
                tv_item_date.text = note.date
                tv_item_description.text = note.description
                cv_item_note.setOnClickListener(CustomOnItemClickListener(adapterPosition, object : CustomOnItemClickListener.OnItemClickCallback {
                    override fun onItemClicked(view: View, position: Int) {
                        val intent = Intent(activity, NoteAddUpdateActivity::class.java)
                        intent.putExtra(NoteAddUpdateActivity.EXTRA_POSITION, position)
                        intent.putExtra(NoteAddUpdateActivity.EXTRA_NOTE, note)
                        activity.startActivityForResult(intent, NoteAddUpdateActivity.REQUEST_UPDATE)
                    }
                }))
            }
        }
    }
}

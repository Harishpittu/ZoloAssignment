
package com.technologies.pittu.zoloassignment.view.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.technologies.pittu.zoloassignment.R;
import com.technologies.pittu.zoloassignment.databinding.ItemWordBinding;
import com.technologies.pittu.zoloassignment.model.Word;
import com.technologies.pittu.zoloassignment.viewmodel.ItemAbacusViewModel;

import java.util.Collections;
import java.util.List;

public class WordsAdapter extends RecyclerView.Adapter<WordsAdapter.WordAdapterViewHolder> {

    private List<Word> wordList = Collections.emptyList();

    public WordsAdapter() {
    }

    @Override
    public WordAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemWordBinding itemWordBinding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_word,
                        parent, false);
        return new WordAdapterViewHolder(itemWordBinding);
    }

    @Override
    public void onBindViewHolder(WordAdapterViewHolder holder, int position) {
        holder.bindWord(wordList.get(position));
    }

    @Override
    public int getItemCount() {
        return wordList.size();
    }

    public void setWordList(List<Word> wordList) {
        this.wordList = wordList;
        notifyDataSetChanged();
    }

    public static class WordAdapterViewHolder extends RecyclerView.ViewHolder {
        ItemWordBinding itemWordBinding;

        public WordAdapterViewHolder(ItemWordBinding itemWordBinding) {
            super(itemWordBinding.itemPeople);
            this.itemWordBinding = itemWordBinding;
        }

        void bindWord(Word word) {
            itemWordBinding.setAbacusViewModel(new ItemAbacusViewModel(word));
        }
    }
}

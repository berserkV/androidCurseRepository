package dev.berserk.firststeps.dcrecyclerview.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import dev.berserk.firststeps.R;
import dev.berserk.firststeps.dcrecyclerview.models.CharacterDC;

public class DCAdapter extends RecyclerView.Adapter<DCAdapter.ViewHolderAdapter> {

    private List<CharacterDC> characters;

    Context adapterContext;

    public DCAdapter(List<CharacterDC> characters, Context adapterContext) {
        this.characters = characters;
        this.adapterContext = adapterContext;
    }

    @NonNull
    @Override
    public DCAdapter.ViewHolderAdapter onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        adapterContext = viewGroup.getContext();

        View view = LayoutInflater.from(adapterContext).inflate(R.layout.item_dc_character, viewGroup, false);

        return new DCAdapter.ViewHolderAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DCAdapter.ViewHolderAdapter viewHolderAdapter, int i) {
        CharacterDC characterDC = characters.get(i);

        viewHolderAdapter.setTextsAndImage(characterDC);
    }

    @Override
    public int getItemCount() {
        return characters.size();
    }

    public class ViewHolderAdapter extends RecyclerView.ViewHolder {

        @BindView(R.id.imageViewBackGround)
        ImageView mImageViewBackground;

        @BindView(R.id.textViewCharacter)
        TextView mTextViewCharacter;

        @BindView(R.id.textViewTitle)
        TextView mTextViewTitle;

        @BindView(R.id.textViewYear)
        TextView mTextViewYear;

        View rootView;

        public ViewHolderAdapter(@NonNull View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
            rootView = itemView;
        }

        public void setTextsAndImage(CharacterDC characterDC) {
            Glide.with(adapterContext)
                    .load(characterDC.image)
                    .centerCrop()
                    .crossFade(1500)
                    .into(mImageViewBackground);
            mTextViewCharacter.setText(characterDC.mainCharacter);
            mTextViewTitle.setText(characterDC.title);
            mTextViewYear.setText(characterDC.year);
        }
    }
}

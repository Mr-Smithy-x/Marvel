package com.smith.ufc.data.viewmodels;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.smith.ufc.data.models.MarvelCharacter;
import com.smith.ufc.data.base.BaseViewModel;

import java.util.Locale;

/**
 * Created by Charlton on 10/29/17.
 */

public class CharacterViewModel extends BaseViewModel {

    private MarvelCharacter character;

    public CharacterViewModel(MarvelCharacter character) {
        super();
        this.character = character;
    }

    @BindingAdapter({"imageUrl"})
    public static void getImageUrl(ImageView imageView, String url) {
        if (url != null && url.length() != 0) Glide.with(imageView.getContext())
                .asBitmap()
                .load(url)
                .into(imageView);
    }

    public String getImage() {
        return character.getThumbnail().toString();
    }


    public String getName() {
        return String.format(Locale.getDefault(), "Character: %s", character.getName());
    }


}

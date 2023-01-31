package com.example.datingapp.Cards;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.example.datingapp.R;

import java.util.List;

public class CardsArrayAdapter extends ArrayAdapter<Cards> {
    Context context;

    public CardsArrayAdapter(Context context, int resourceID, List<Cards> items) {
        super(context, resourceID, items);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        Cards cardItem = getItem(position);

        if (convertView ==  null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item, parent, false);
        }

        TextView tvName = (TextView) convertView.findViewById(R.id.text_view_name_in_items);
        ImageView ivProfilePicture = (ImageView) convertView.findViewById(R.id.image_view_profile_picture_in_item);
        TextView tvBudget = (TextView) convertView.findViewById(R.id.text_view_budget_in_item);
        ImageView ivNeedImage = (ImageView) convertView.findViewById(R.id.image_view_need_image);
        ImageView ivGiveImage = (ImageView) convertView.findViewById(R.id.image_view_give_image);

        tvName.setText(cardItem.getName());
        tvBudget.setText(cardItem.getBudget());

        // need image
        switch (cardItem.getNeed()) {
            case "Netflix":
                ivNeedImage.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.netflix));
                break;
            case "Hulu":
                ivNeedImage.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.hulu));
                break;
            case "Vudu":
                ivNeedImage.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.vudu));
                break;
            case "HBO Now":
                ivNeedImage.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.hbo));
                break;
            case "YouTube Originals":
                ivNeedImage.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.youtube));
                break;
            default:
                ivNeedImage.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.none));
                break;
        }

        // give image
        switch (cardItem.getGive()) {
            case "Netflix":
                ivGiveImage.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.netflix));
                break;
            case "Amazon Prime":
                ivGiveImage.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.amazon));
                break;
            case "Hulu":
                ivGiveImage.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.hulu));
                break;
            case "Vudu":
                ivGiveImage.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.vudu));
                break;
            case "HBO Now":
                ivGiveImage.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.hbo));
                break;
            case "YouTube Originals":
                ivGiveImage.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.youtube));
                break;
            default:
                ivGiveImage.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.none));
                break;
        }

        switch (cardItem.getProfilePictureURL()) {
            case "default":
                Glide.with(convertView.getContext()).load(R.drawable.profile).into(ivProfilePicture);
                break;
            default:
                Glide.with(convertView.getContext()).clear(ivProfilePicture);
                Glide.with(convertView.getContext()).load(cardItem.getProfilePictureURL()).into(ivProfilePicture);
                break;
        }

        return convertView;
    }
}

package com.example.anil.shoppingApplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class SpeakerDataAdapter extends RecyclerView.Adapter<SpeakerDataAdapter.ViewHolder> {

    private List<Speakers> feedItemList;
    private Context mContext;

    public SpeakerDataAdapter(Context context, ArrayList<Speakers> speakers) {
        this.feedItemList = speakers;
        this.mContext = context;

    }

    // Create new views
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        // create a new view
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.item_speaker_list, null);

        // create ViewHolder

        ViewHolder viewHolder = new ViewHolder(itemLayoutView);

        return viewHolder;

    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
        //final int pos = position*3;
        Speakers item_speakers = feedItemList.get(position);

       /* viewHolder.tvSpeaker.setText(feedItemList.get(position).getName());
        viewHolder.tvPrice.setText(feedItemList.get(position).getPrice());*/
        viewHolder.chkSelected.setChecked(feedItemList.get(position).isSelected());


        if (position == 1) {
            viewHolder.imSpeaker.setImageResource(R.mipmap.speaker_no);
            viewHolder.tvPrice.setText("$200");
            viewHolder.tvSponser.setVisibility(View.GONE);
            viewHolder.imIdea.setVisibility(View.GONE);
        }
        if (position == 2) {
            viewHolder.imSpeaker.setImageResource(R.mipmap.speaker_black);
            viewHolder.tvPrice.setText("$50");
            viewHolder.tvSponser.setVisibility(View.GONE);
            viewHolder.imIdea.setVisibility(View.GONE);
        }

        if (position == 4) {
            viewHolder.imSpeaker.setImageResource(R.mipmap.speaker_no);
            viewHolder.tvPrice.setText("$200");
            viewHolder.tvSponser.setVisibility(View.GONE);
            viewHolder.imIdea.setVisibility(View.GONE);
        }


        viewHolder.chkSelected.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    viewHolder.chkSelected.setChecked(b);
                    String price = viewHolder.tvPrice.getText().toString();
                    String image = viewHolder.imSpeaker.getDrawable().toString();

                    //Save to device
                    SharedPreferences sharedPref = mContext.getSharedPreferences("SpeakerDetails", mContext.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString("Price", price);
                    editor.putString("Image", image);
                    editor.commit();

                } else {
                    viewHolder.chkSelected.setChecked(b);
                    SharedPreferences sharedPref = mContext.getSharedPreferences("SpeakerDetails", mContext.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.remove("Price");
                    editor.remove("Image");
                    editor.commit();

                    //Toast.makeText(mContext, "Removed Clicked on Checkbox:", Toast.LENGTH_LONG).show();
                }


            }
        });
    }

    // Return the size arraylist
    @Override
    public int getItemCount() {

        return feedItemList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvSponser;
        public TextView tvSpeaker;
        public TextView tvPrice;
        public TextView tvText;
        public TextView tvTextt;
        public TextView tvTexttt;
        public ImageView imIdea;
        public ImageView imSpeaker;
        public CheckBox chkSelected;

        public Speakers singlespeaker;

        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);

            tvSponser = (TextView) itemLayoutView.findViewById(R.id.tv_sponser);
            tvSpeaker = (TextView) itemLayoutView.findViewById(R.id.speaker);
            tvPrice = (TextView) itemLayoutView.findViewById(R.id.speaker_price);
            tvText = (TextView) itemLayoutView.findViewById(R.id.text1);
            tvTextt = (TextView) itemLayoutView.findViewById(R.id.text2);
            tvTexttt = (TextView) itemLayoutView.findViewById(R.id.text3);
            imIdea = (ImageView) itemLayoutView.findViewById(R.id.imIdea);
            imSpeaker = (ImageView) itemLayoutView.findViewById(R.id.imSpeaker);
            chkSelected = (CheckBox) itemLayoutView.findViewById(R.id.checkbox);
        }

    }

    // method to access in activity after updating selection
    public List<Speakers> getSpeakerList() {
        return feedItemList;
    }

}

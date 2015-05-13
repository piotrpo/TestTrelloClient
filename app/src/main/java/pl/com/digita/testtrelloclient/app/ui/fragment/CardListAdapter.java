package pl.com.digita.testtrelloclient.app.ui.fragment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import pl.com.digita.testtrelloclient.app.R;
import pl.com.digita.testtrelloclient.app.model.TrelloCard;

import java.util.ArrayList;

/**
 * Created by Piotr on 2015-05-13.
 *
 */
public class CardListAdapter extends RecyclerView.Adapter<CardListAdapter.ViewHolderCard>
{
    private ArrayList<TrelloCard> mTrelloCards;
    private Context mContext;

    public CardListAdapter(Context pContext)
    {
        mContext = pContext;
    }

    public ArrayList<TrelloCard> getTrelloCards()
    {
        return mTrelloCards;
    }

    public void setTrelloCards(ArrayList<TrelloCard> pTrelloCards)
    {
        mTrelloCards = pTrelloCards;
        notifyDataSetChanged();
    }


    @Override
    public ViewHolderCard onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_trello_card, parent, false);
        return new ViewHolderCard(view);
    }

    @Override
    public void onBindViewHolder(ViewHolderCard holder, int position)
    {
        holder.setData(mTrelloCards.get(position));
    }


    @Override
    public int getItemCount()
    {
        if(mTrelloCards != null)
        {
            return mTrelloCards.size();
        }
        else
        {
            return 0;
        }

    }

    public class ViewHolderCard extends RecyclerView.ViewHolder
    {
        @InjectView(R.id.text_view_title)
        public TextView mTextViewTitle;

        @InjectView(R.id.text_view_content)
        public TextView mTextViewContent;
        private TrelloCard mTrelloCard;

        public ViewHolderCard(View itemView)
        {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }

        public void setData(TrelloCard pTrelloCard)
        {
            mTrelloCard = pTrelloCard;
            mTextViewTitle.setText(mTrelloCard.mName);
            mTextViewContent.setText(mTrelloCard.mDescription);
        }
    }
}

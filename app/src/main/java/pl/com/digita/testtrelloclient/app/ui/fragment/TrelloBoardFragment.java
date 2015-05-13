package pl.com.digita.testtrelloclient.app.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;
import pl.com.digita.testtrelloclient.app.R;
import pl.com.digita.testtrelloclient.app.communication.CommunicationManager;
import pl.com.digita.testtrelloclient.app.dependencies.DaggerGraphManager;
import pl.com.digita.testtrelloclient.app.model.TrelloList;

import javax.inject.Inject;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TrelloBoardFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TrelloBoardFragment extends Fragment
{
    @Inject
    CommunicationManager mCommunicationManager;

    @Inject
    Bus mOttoBus;

    @InjectView(R.id.spinner_choose_list)
    Spinner mSpinnerChooseList;
    @InjectView(R.id.recycler_view_content)
    RecyclerView mRecyclerViewContent;

    private ArrayList<TrelloList> mTrelloLists;
    private CardListAdapter mContentAdapter;

    /**
     * Use this factory method to create a new instance of
     *
     * @return A new instance of fragment LoginFragment.
     */

    public static TrelloBoardFragment newInstance()
    {
        TrelloBoardFragment fragment = new TrelloBoardFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    {
        DaggerGraphManager.INSTANCE.getObjectGraph().inject(this);
    }

    public TrelloBoardFragment()
    {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.inject(this, view);
        mRecyclerViewContent.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }

    @Override
    public void onResume()
    {
        super.onResume();
        mOttoBus.register(this);
    }

    @Override
    public void onPause()
    {
        super.onPause();
        mOttoBus.unregister(this);
    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @Subscribe
    public void onBoardContentArrived(ArrayList<TrelloList> pTrelloLists)
    {
        mTrelloLists = pTrelloLists;
        mContentAdapter = new CardListAdapter(getActivity());
        if (pTrelloLists.size()>0)
        {
            mContentAdapter.setTrelloCards(pTrelloLists.get(0).mCards);
        }
        mRecyclerViewContent.setAdapter(mContentAdapter);
        initSpinner();
    }

    private void initSpinner()
    {

        ArrayAdapter<TrelloList> spinnerAdapter = new ArrayAdapter<>(getActivity(),  android.R.layout.simple_spinner_item);
        spinnerAdapter.addAll(mTrelloLists);
        mSpinnerChooseList.setAdapter(spinnerAdapter);

        mSpinnerChooseList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                setContent(mTrelloLists.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {
                //do nothing
            }
        });
    }

    public void setContent(TrelloList pContent)
    {
        mContentAdapter.setTrelloCards(pContent.mCards);
    }
}

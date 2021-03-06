package com.codecamp.codecamp12.ui.activity;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.codecamp.codecamp12.R;
import com.codecamp.codecamp12.mvp.presenter.AddBookPresenter;
import com.codecamp.codecamp12.mvp.view.IAddBookView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import nucleus.factory.RequiresPresenter;
import nucleus.view.NucleusAppCompatActivity;

/**
 * Activity that hosts add book ui
 * Created by David Bilik[david.bilik@ackee.cz] on {26/05/16}
 **/
@RequiresPresenter(AddBookPresenter.class)
public class AddBookActivity extends NucleusAppCompatActivity<AddBookPresenter> implements IAddBookView {
    public static final String TAG = AddBookActivity.class.getName();
    @BindView(R.id.text_input_author)
    TextInputLayout inputAuthor;
    @BindView(R.id.text_input_genre)
    TextInputLayout inputGenre;
    @BindView(R.id.text_input_title)
    TextInputLayout inputTitle;
    @BindView(android.R.id.progress)
    View progressView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);
        ButterKnife.bind(this);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle(R.string.add_book);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.btn_save)
    public void onSaveClicked() {
        getPresenter().onSaveClicked(inputTitle.getEditText().getText().toString(), inputGenre.getEditText().getText().toString(), inputAuthor.getEditText().getText().toString());
    }

    @Override
    public void clearErrors() {
        inputTitle.setError(null);
        inputAuthor.setError(null);
        inputGenre.setError(null);
    }

    @Override
    public void setErrorTitle(int resId) {
        inputTitle.setError(getString(resId));
    }

    @Override
    public void setErrorAuthor(int resId) {
        inputAuthor.setError(getString(resId));
    }

    @Override
    public void setErrorGenre(int resId) {
        inputGenre.setError(getString(resId));
    }

    @Override
    public void showProgress(boolean show) {
        progressView.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @Override
    public void close() {
        finish();
    }
}

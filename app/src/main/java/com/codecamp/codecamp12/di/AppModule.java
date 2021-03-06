package com.codecamp.codecamp12.di;

import android.app.Application;
import android.view.LayoutInflater;

import com.codecamp.codecamp12.App;
import com.codecamp.codecamp12.Constants;
import com.codecamp.codecamp12.db.dao.BookDao;
import com.codecamp.codecamp12.db.dao.FeatureDao;
import com.hannesdorfmann.sqlbrite.dao.DaoManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Module that handle injecting application class (ie Context)
 * Created by David Bilik[david.bilik@ackee.cz] on {2. 4. 2015}
 */
@Module(
        includes = {
                InteractorsModule.class
        }
)
public class AppModule {
    public static final String TAG = AppModule.class.getName();

    private final App app;
    private final BookDao bookDao;
    private final FeatureDao featuredDao;

    public AppModule(App app) {
        this.app = app;
        bookDao = new BookDao();
        featuredDao = new FeatureDao();
        DaoManager.with(app)
                .version(Constants.DB_VERSION)
                .databaseName(Constants.DB_NAME)
                .logging(false)
                .add(bookDao)
                .add(featuredDao)
                .build();
    }

    @Provides
    @Singleton
    public Application provideApplication() {
        return app;
    }

    @Provides
    @Singleton
    LayoutInflater provideLayoutInflater() {
        return LayoutInflater.from(app);
    }

    @Provides
    @Singleton
    public BookDao provideBookDao() {
        return bookDao;
    }


    @Provides
    @Singleton
    public FeatureDao provideFeaturedDao() {
        return featuredDao;
    }
}

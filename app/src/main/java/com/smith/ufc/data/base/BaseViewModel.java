package com.smith.ufc.data.base;

import android.databinding.BaseObservable;

import com.smith.ufc.Marvel;
import com.smith.ufc.data.di.MarvelModule;
import com.smith.ufc.data.service.MarvelDataSource;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by Charlton on 10/29/17.
 */

public class BaseViewModel extends BaseObservable {


    protected BaseViewModel(){

    }
}

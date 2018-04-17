package com.yafuquen.abril.presenter;

/**
 * Base presenter.
 *
 * @author yafuquen
 */
public abstract class BasePresenter {

    public abstract void destroy();

    protected boolean isViewReady() {
        return getView() != null && getView().isReady();
    }

    protected abstract BaseView getView();
}

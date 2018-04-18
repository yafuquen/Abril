package com.yafuquen.abril.presenter;

/**
 * Base presenter.
 *
 * @author yafuquen
 */
abstract class BasePresenter {

    public abstract void destroy();

    boolean isViewReady() {
        return getView() != null && getView().isReady();
    }

    abstract BaseView getView();
}

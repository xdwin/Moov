// Generated by Dagger (https://dagger.dev).
package com.xdwin.moov.features.home.search;

import dagger.internal.Factory;

@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class SearchRepositoryImpl_Factory implements Factory<SearchRepositoryImpl> {
  @Override
  public SearchRepositoryImpl get() {
    return newInstance();
  }

  public static SearchRepositoryImpl_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static SearchRepositoryImpl newInstance() {
    return new SearchRepositoryImpl();
  }

  private static final class InstanceHolder {
    private static final SearchRepositoryImpl_Factory INSTANCE = new SearchRepositoryImpl_Factory();
  }
}
// Generated by Dagger (https://dagger.dev).
package com.xdwin.moov.features.detail.detail;

import dagger.internal.Factory;

@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class DetailRepositoryImpl_Factory implements Factory<DetailRepositoryImpl> {
  @Override
  public DetailRepositoryImpl get() {
    return newInstance();
  }

  public static DetailRepositoryImpl_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static DetailRepositoryImpl newInstance() {
    return new DetailRepositoryImpl();
  }

  private static final class InstanceHolder {
    private static final DetailRepositoryImpl_Factory INSTANCE = new DetailRepositoryImpl_Factory();
  }
}

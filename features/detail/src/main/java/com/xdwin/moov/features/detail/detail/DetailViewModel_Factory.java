// Generated by Dagger (https://dagger.dev).
package com.xdwin.moov.features.detail.detail;

import dagger.internal.Factory;
import javax.inject.Provider;

@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class DetailViewModel_Factory implements Factory<DetailViewModel> {
  private final Provider<DetailRepository> repoProvider;

  public DetailViewModel_Factory(Provider<DetailRepository> repoProvider) {
    this.repoProvider = repoProvider;
  }

  @Override
  public DetailViewModel get() {
    return newInstance(repoProvider.get());
  }

  public static DetailViewModel_Factory create(Provider<DetailRepository> repoProvider) {
    return new DetailViewModel_Factory(repoProvider);
  }

  public static DetailViewModel newInstance(DetailRepository repo) {
    return new DetailViewModel(repo);
  }
}

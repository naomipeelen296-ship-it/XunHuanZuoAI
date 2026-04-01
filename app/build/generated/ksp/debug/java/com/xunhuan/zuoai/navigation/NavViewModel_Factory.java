package com.xunhuan.zuoai.navigation;

import com.xunhuan.zuoai.data.repository.UserRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast"
})
public final class NavViewModel_Factory implements Factory<NavViewModel> {
  private final Provider<UserRepository> userRepositoryProvider;

  public NavViewModel_Factory(Provider<UserRepository> userRepositoryProvider) {
    this.userRepositoryProvider = userRepositoryProvider;
  }

  @Override
  public NavViewModel get() {
    return newInstance(userRepositoryProvider.get());
  }

  public static NavViewModel_Factory create(Provider<UserRepository> userRepositoryProvider) {
    return new NavViewModel_Factory(userRepositoryProvider);
  }

  public static NavViewModel newInstance(UserRepository userRepository) {
    return new NavViewModel(userRepository);
  }
}

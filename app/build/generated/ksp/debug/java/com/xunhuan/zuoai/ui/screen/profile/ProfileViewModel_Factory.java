package com.xunhuan.zuoai.ui.screen.profile;

import com.xunhuan.zuoai.data.repository.ProfileRepository;
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
public final class ProfileViewModel_Factory implements Factory<ProfileViewModel> {
  private final Provider<UserRepository> userRepositoryProvider;

  private final Provider<ProfileRepository> profileRepositoryProvider;

  public ProfileViewModel_Factory(Provider<UserRepository> userRepositoryProvider,
      Provider<ProfileRepository> profileRepositoryProvider) {
    this.userRepositoryProvider = userRepositoryProvider;
    this.profileRepositoryProvider = profileRepositoryProvider;
  }

  @Override
  public ProfileViewModel get() {
    return newInstance(userRepositoryProvider.get(), profileRepositoryProvider.get());
  }

  public static ProfileViewModel_Factory create(Provider<UserRepository> userRepositoryProvider,
      Provider<ProfileRepository> profileRepositoryProvider) {
    return new ProfileViewModel_Factory(userRepositoryProvider, profileRepositoryProvider);
  }

  public static ProfileViewModel newInstance(UserRepository userRepository,
      ProfileRepository profileRepository) {
    return new ProfileViewModel(userRepository, profileRepository);
  }
}

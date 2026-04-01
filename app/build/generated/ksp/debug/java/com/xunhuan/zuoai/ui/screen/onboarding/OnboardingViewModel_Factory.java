package com.xunhuan.zuoai.ui.screen.onboarding;

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
public final class OnboardingViewModel_Factory implements Factory<OnboardingViewModel> {
  private final Provider<UserRepository> userRepositoryProvider;

  public OnboardingViewModel_Factory(Provider<UserRepository> userRepositoryProvider) {
    this.userRepositoryProvider = userRepositoryProvider;
  }

  @Override
  public OnboardingViewModel get() {
    return newInstance(userRepositoryProvider.get());
  }

  public static OnboardingViewModel_Factory create(
      Provider<UserRepository> userRepositoryProvider) {
    return new OnboardingViewModel_Factory(userRepositoryProvider);
  }

  public static OnboardingViewModel newInstance(UserRepository userRepository) {
    return new OnboardingViewModel(userRepository);
  }
}

package com.xunhuan.zuoai.ui.screen.home;

import com.xunhuan.zuoai.data.repository.TaskRepository;
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
public final class HomeViewModel_Factory implements Factory<HomeViewModel> {
  private final Provider<UserRepository> userRepositoryProvider;

  private final Provider<TaskRepository> taskRepositoryProvider;

  public HomeViewModel_Factory(Provider<UserRepository> userRepositoryProvider,
      Provider<TaskRepository> taskRepositoryProvider) {
    this.userRepositoryProvider = userRepositoryProvider;
    this.taskRepositoryProvider = taskRepositoryProvider;
  }

  @Override
  public HomeViewModel get() {
    return newInstance(userRepositoryProvider.get(), taskRepositoryProvider.get());
  }

  public static HomeViewModel_Factory create(Provider<UserRepository> userRepositoryProvider,
      Provider<TaskRepository> taskRepositoryProvider) {
    return new HomeViewModel_Factory(userRepositoryProvider, taskRepositoryProvider);
  }

  public static HomeViewModel newInstance(UserRepository userRepository,
      TaskRepository taskRepository) {
    return new HomeViewModel(userRepository, taskRepository);
  }
}

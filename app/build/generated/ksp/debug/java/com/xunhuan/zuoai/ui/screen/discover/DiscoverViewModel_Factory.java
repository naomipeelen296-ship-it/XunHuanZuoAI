package com.xunhuan.zuoai.ui.screen.discover;

import com.xunhuan.zuoai.data.repository.TaskRepository;
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
public final class DiscoverViewModel_Factory implements Factory<DiscoverViewModel> {
  private final Provider<TaskRepository> taskRepositoryProvider;

  public DiscoverViewModel_Factory(Provider<TaskRepository> taskRepositoryProvider) {
    this.taskRepositoryProvider = taskRepositoryProvider;
  }

  @Override
  public DiscoverViewModel get() {
    return newInstance(taskRepositoryProvider.get());
  }

  public static DiscoverViewModel_Factory create(Provider<TaskRepository> taskRepositoryProvider) {
    return new DiscoverViewModel_Factory(taskRepositoryProvider);
  }

  public static DiscoverViewModel newInstance(TaskRepository taskRepository) {
    return new DiscoverViewModel(taskRepository);
  }
}

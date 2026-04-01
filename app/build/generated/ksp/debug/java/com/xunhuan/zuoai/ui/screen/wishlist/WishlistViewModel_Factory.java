package com.xunhuan.zuoai.ui.screen.wishlist;

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
public final class WishlistViewModel_Factory implements Factory<WishlistViewModel> {
  private final Provider<TaskRepository> taskRepositoryProvider;

  public WishlistViewModel_Factory(Provider<TaskRepository> taskRepositoryProvider) {
    this.taskRepositoryProvider = taskRepositoryProvider;
  }

  @Override
  public WishlistViewModel get() {
    return newInstance(taskRepositoryProvider.get());
  }

  public static WishlistViewModel_Factory create(Provider<TaskRepository> taskRepositoryProvider) {
    return new WishlistViewModel_Factory(taskRepositoryProvider);
  }

  public static WishlistViewModel newInstance(TaskRepository taskRepository) {
    return new WishlistViewModel(taskRepository);
  }
}

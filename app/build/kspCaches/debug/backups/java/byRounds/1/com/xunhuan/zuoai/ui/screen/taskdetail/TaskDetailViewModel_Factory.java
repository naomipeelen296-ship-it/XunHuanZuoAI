package com.xunhuan.zuoai.ui.screen.taskdetail;

import androidx.lifecycle.SavedStateHandle;
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
public final class TaskDetailViewModel_Factory implements Factory<TaskDetailViewModel> {
  private final Provider<SavedStateHandle> savedStateHandleProvider;

  private final Provider<TaskRepository> taskRepositoryProvider;

  private final Provider<UserRepository> userRepositoryProvider;

  public TaskDetailViewModel_Factory(Provider<SavedStateHandle> savedStateHandleProvider,
      Provider<TaskRepository> taskRepositoryProvider,
      Provider<UserRepository> userRepositoryProvider) {
    this.savedStateHandleProvider = savedStateHandleProvider;
    this.taskRepositoryProvider = taskRepositoryProvider;
    this.userRepositoryProvider = userRepositoryProvider;
  }

  @Override
  public TaskDetailViewModel get() {
    return newInstance(savedStateHandleProvider.get(), taskRepositoryProvider.get(), userRepositoryProvider.get());
  }

  public static TaskDetailViewModel_Factory create(
      Provider<SavedStateHandle> savedStateHandleProvider,
      Provider<TaskRepository> taskRepositoryProvider,
      Provider<UserRepository> userRepositoryProvider) {
    return new TaskDetailViewModel_Factory(savedStateHandleProvider, taskRepositoryProvider, userRepositoryProvider);
  }

  public static TaskDetailViewModel newInstance(SavedStateHandle savedStateHandle,
      TaskRepository taskRepository, UserRepository userRepository) {
    return new TaskDetailViewModel(savedStateHandle, taskRepository, userRepository);
  }
}

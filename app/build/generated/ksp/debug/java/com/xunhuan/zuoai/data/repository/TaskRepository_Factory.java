package com.xunhuan.zuoai.data.repository;

import com.xunhuan.zuoai.data.local.dao.TaskDao;
import com.xunhuan.zuoai.data.local.dao.TaskRecordDao;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
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
public final class TaskRepository_Factory implements Factory<TaskRepository> {
  private final Provider<TaskDao> taskDaoProvider;

  private final Provider<TaskRecordDao> taskRecordDaoProvider;

  public TaskRepository_Factory(Provider<TaskDao> taskDaoProvider,
      Provider<TaskRecordDao> taskRecordDaoProvider) {
    this.taskDaoProvider = taskDaoProvider;
    this.taskRecordDaoProvider = taskRecordDaoProvider;
  }

  @Override
  public TaskRepository get() {
    return newInstance(taskDaoProvider.get(), taskRecordDaoProvider.get());
  }

  public static TaskRepository_Factory create(Provider<TaskDao> taskDaoProvider,
      Provider<TaskRecordDao> taskRecordDaoProvider) {
    return new TaskRepository_Factory(taskDaoProvider, taskRecordDaoProvider);
  }

  public static TaskRepository newInstance(TaskDao taskDao, TaskRecordDao taskRecordDao) {
    return new TaskRepository(taskDao, taskRecordDao);
  }
}

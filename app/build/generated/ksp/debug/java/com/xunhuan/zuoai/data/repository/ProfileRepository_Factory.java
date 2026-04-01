package com.xunhuan.zuoai.data.repository;

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
public final class ProfileRepository_Factory implements Factory<ProfileRepository> {
  private final Provider<TaskRecordDao> taskRecordDaoProvider;

  public ProfileRepository_Factory(Provider<TaskRecordDao> taskRecordDaoProvider) {
    this.taskRecordDaoProvider = taskRecordDaoProvider;
  }

  @Override
  public ProfileRepository get() {
    return newInstance(taskRecordDaoProvider.get());
  }

  public static ProfileRepository_Factory create(Provider<TaskRecordDao> taskRecordDaoProvider) {
    return new ProfileRepository_Factory(taskRecordDaoProvider);
  }

  public static ProfileRepository newInstance(TaskRecordDao taskRecordDao) {
    return new ProfileRepository(taskRecordDao);
  }
}

package com.xunhuan.zuoai.di;

import com.xunhuan.zuoai.data.local.AppDatabase;
import com.xunhuan.zuoai.data.local.dao.TaskRecordDao;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
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
public final class DatabaseModule_ProvideTaskRecordDaoFactory implements Factory<TaskRecordDao> {
  private final Provider<AppDatabase> dbProvider;

  public DatabaseModule_ProvideTaskRecordDaoFactory(Provider<AppDatabase> dbProvider) {
    this.dbProvider = dbProvider;
  }

  @Override
  public TaskRecordDao get() {
    return provideTaskRecordDao(dbProvider.get());
  }

  public static DatabaseModule_ProvideTaskRecordDaoFactory create(
      Provider<AppDatabase> dbProvider) {
    return new DatabaseModule_ProvideTaskRecordDaoFactory(dbProvider);
  }

  public static TaskRecordDao provideTaskRecordDao(AppDatabase db) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideTaskRecordDao(db));
  }
}

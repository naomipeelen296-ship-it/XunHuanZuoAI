package com.xunhuan.zuoai.di;

import com.xunhuan.zuoai.data.local.AppDatabase;
import com.xunhuan.zuoai.data.local.dao.ChatMessageDao;
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
public final class DatabaseModule_ProvideChatMessageDaoFactory implements Factory<ChatMessageDao> {
  private final Provider<AppDatabase> dbProvider;

  public DatabaseModule_ProvideChatMessageDaoFactory(Provider<AppDatabase> dbProvider) {
    this.dbProvider = dbProvider;
  }

  @Override
  public ChatMessageDao get() {
    return provideChatMessageDao(dbProvider.get());
  }

  public static DatabaseModule_ProvideChatMessageDaoFactory create(
      Provider<AppDatabase> dbProvider) {
    return new DatabaseModule_ProvideChatMessageDaoFactory(dbProvider);
  }

  public static ChatMessageDao provideChatMessageDao(AppDatabase db) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideChatMessageDao(db));
  }
}

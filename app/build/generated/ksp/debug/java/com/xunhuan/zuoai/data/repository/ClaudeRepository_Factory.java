package com.xunhuan.zuoai.data.repository;

import com.xunhuan.zuoai.data.local.dao.ChatMessageDao;
import com.xunhuan.zuoai.data.remote.ClaudeApiService;
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
public final class ClaudeRepository_Factory implements Factory<ClaudeRepository> {
  private final Provider<ClaudeApiService> apiProvider;

  private final Provider<ChatMessageDao> chatMessageDaoProvider;

  public ClaudeRepository_Factory(Provider<ClaudeApiService> apiProvider,
      Provider<ChatMessageDao> chatMessageDaoProvider) {
    this.apiProvider = apiProvider;
    this.chatMessageDaoProvider = chatMessageDaoProvider;
  }

  @Override
  public ClaudeRepository get() {
    return newInstance(apiProvider.get(), chatMessageDaoProvider.get());
  }

  public static ClaudeRepository_Factory create(Provider<ClaudeApiService> apiProvider,
      Provider<ChatMessageDao> chatMessageDaoProvider) {
    return new ClaudeRepository_Factory(apiProvider, chatMessageDaoProvider);
  }

  public static ClaudeRepository newInstance(ClaudeApiService api, ChatMessageDao chatMessageDao) {
    return new ClaudeRepository(api, chatMessageDao);
  }
}

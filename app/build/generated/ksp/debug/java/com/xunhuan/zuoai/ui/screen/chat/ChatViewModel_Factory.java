package com.xunhuan.zuoai.ui.screen.chat;

import com.xunhuan.zuoai.data.repository.ClaudeRepository;
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
public final class ChatViewModel_Factory implements Factory<ChatViewModel> {
  private final Provider<ClaudeRepository> claudeRepositoryProvider;

  public ChatViewModel_Factory(Provider<ClaudeRepository> claudeRepositoryProvider) {
    this.claudeRepositoryProvider = claudeRepositoryProvider;
  }

  @Override
  public ChatViewModel get() {
    return newInstance(claudeRepositoryProvider.get());
  }

  public static ChatViewModel_Factory create(Provider<ClaudeRepository> claudeRepositoryProvider) {
    return new ChatViewModel_Factory(claudeRepositoryProvider);
  }

  public static ChatViewModel newInstance(ClaudeRepository claudeRepository) {
    return new ChatViewModel(claudeRepository);
  }
}

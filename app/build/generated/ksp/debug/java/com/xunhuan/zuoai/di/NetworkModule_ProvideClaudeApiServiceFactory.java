package com.xunhuan.zuoai.di;

import com.xunhuan.zuoai.data.remote.ClaudeApiService;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import retrofit2.Retrofit;

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
public final class NetworkModule_ProvideClaudeApiServiceFactory implements Factory<ClaudeApiService> {
  private final Provider<Retrofit> retrofitProvider;

  public NetworkModule_ProvideClaudeApiServiceFactory(Provider<Retrofit> retrofitProvider) {
    this.retrofitProvider = retrofitProvider;
  }

  @Override
  public ClaudeApiService get() {
    return provideClaudeApiService(retrofitProvider.get());
  }

  public static NetworkModule_ProvideClaudeApiServiceFactory create(
      Provider<Retrofit> retrofitProvider) {
    return new NetworkModule_ProvideClaudeApiServiceFactory(retrofitProvider);
  }

  public static ClaudeApiService provideClaudeApiService(Retrofit retrofit) {
    return Preconditions.checkNotNullFromProvides(NetworkModule.INSTANCE.provideClaudeApiService(retrofit));
  }
}

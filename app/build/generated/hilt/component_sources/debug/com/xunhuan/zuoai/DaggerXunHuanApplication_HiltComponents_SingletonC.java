package com.xunhuan.zuoai;

import android.app.Activity;
import android.app.Service;
import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;
import com.xunhuan.zuoai.data.local.AppDatabase;
import com.xunhuan.zuoai.data.local.dao.ChatMessageDao;
import com.xunhuan.zuoai.data.local.dao.TaskDao;
import com.xunhuan.zuoai.data.local.dao.TaskRecordDao;
import com.xunhuan.zuoai.data.local.dao.UserDao;
import com.xunhuan.zuoai.data.remote.ClaudeApiService;
import com.xunhuan.zuoai.data.repository.ClaudeRepository;
import com.xunhuan.zuoai.data.repository.ProfileRepository;
import com.xunhuan.zuoai.data.repository.TaskRepository;
import com.xunhuan.zuoai.data.repository.UserRepository;
import com.xunhuan.zuoai.di.DatabaseModule_ProvideChatMessageDaoFactory;
import com.xunhuan.zuoai.di.DatabaseModule_ProvideDatabaseFactory;
import com.xunhuan.zuoai.di.DatabaseModule_ProvideTaskDaoFactory;
import com.xunhuan.zuoai.di.DatabaseModule_ProvideTaskRecordDaoFactory;
import com.xunhuan.zuoai.di.DatabaseModule_ProvideUserDaoFactory;
import com.xunhuan.zuoai.di.NetworkModule_ProvideClaudeApiServiceFactory;
import com.xunhuan.zuoai.di.NetworkModule_ProvideOkHttpClientFactory;
import com.xunhuan.zuoai.di.NetworkModule_ProvideRetrofitFactory;
import com.xunhuan.zuoai.navigation.NavViewModel;
import com.xunhuan.zuoai.navigation.NavViewModel_HiltModules;
import com.xunhuan.zuoai.ui.screen.chat.ChatViewModel;
import com.xunhuan.zuoai.ui.screen.chat.ChatViewModel_HiltModules;
import com.xunhuan.zuoai.ui.screen.discover.DiscoverViewModel;
import com.xunhuan.zuoai.ui.screen.discover.DiscoverViewModel_HiltModules;
import com.xunhuan.zuoai.ui.screen.home.HomeViewModel;
import com.xunhuan.zuoai.ui.screen.home.HomeViewModel_HiltModules;
import com.xunhuan.zuoai.ui.screen.onboarding.OnboardingViewModel;
import com.xunhuan.zuoai.ui.screen.onboarding.OnboardingViewModel_HiltModules;
import com.xunhuan.zuoai.ui.screen.profile.ProfileViewModel;
import com.xunhuan.zuoai.ui.screen.profile.ProfileViewModel_HiltModules;
import com.xunhuan.zuoai.ui.screen.taskdetail.TaskDetailViewModel;
import com.xunhuan.zuoai.ui.screen.taskdetail.TaskDetailViewModel_HiltModules;
import com.xunhuan.zuoai.ui.screen.wishlist.WishlistViewModel;
import com.xunhuan.zuoai.ui.screen.wishlist.WishlistViewModel_HiltModules;
import dagger.hilt.android.ActivityRetainedLifecycle;
import dagger.hilt.android.ViewModelLifecycle;
import dagger.hilt.android.internal.builders.ActivityComponentBuilder;
import dagger.hilt.android.internal.builders.ActivityRetainedComponentBuilder;
import dagger.hilt.android.internal.builders.FragmentComponentBuilder;
import dagger.hilt.android.internal.builders.ServiceComponentBuilder;
import dagger.hilt.android.internal.builders.ViewComponentBuilder;
import dagger.hilt.android.internal.builders.ViewModelComponentBuilder;
import dagger.hilt.android.internal.builders.ViewWithFragmentComponentBuilder;
import dagger.hilt.android.internal.lifecycle.DefaultViewModelFactories;
import dagger.hilt.android.internal.lifecycle.DefaultViewModelFactories_InternalFactoryFactory_Factory;
import dagger.hilt.android.internal.managers.ActivityRetainedComponentManager_LifecycleModule_ProvideActivityRetainedLifecycleFactory;
import dagger.hilt.android.internal.managers.SavedStateHandleHolder;
import dagger.hilt.android.internal.modules.ApplicationContextModule;
import dagger.hilt.android.internal.modules.ApplicationContextModule_ProvideContextFactory;
import dagger.internal.DaggerGenerated;
import dagger.internal.DoubleCheck;
import dagger.internal.IdentifierNameString;
import dagger.internal.KeepFieldType;
import dagger.internal.LazyClassKeyMap;
import dagger.internal.MapBuilder;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

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
public final class DaggerXunHuanApplication_HiltComponents_SingletonC {
  private DaggerXunHuanApplication_HiltComponents_SingletonC() {
  }

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder {
    private ApplicationContextModule applicationContextModule;

    private Builder() {
    }

    public Builder applicationContextModule(ApplicationContextModule applicationContextModule) {
      this.applicationContextModule = Preconditions.checkNotNull(applicationContextModule);
      return this;
    }

    public XunHuanApplication_HiltComponents.SingletonC build() {
      Preconditions.checkBuilderRequirement(applicationContextModule, ApplicationContextModule.class);
      return new SingletonCImpl(applicationContextModule);
    }
  }

  private static final class ActivityRetainedCBuilder implements XunHuanApplication_HiltComponents.ActivityRetainedC.Builder {
    private final SingletonCImpl singletonCImpl;

    private SavedStateHandleHolder savedStateHandleHolder;

    private ActivityRetainedCBuilder(SingletonCImpl singletonCImpl) {
      this.singletonCImpl = singletonCImpl;
    }

    @Override
    public ActivityRetainedCBuilder savedStateHandleHolder(
        SavedStateHandleHolder savedStateHandleHolder) {
      this.savedStateHandleHolder = Preconditions.checkNotNull(savedStateHandleHolder);
      return this;
    }

    @Override
    public XunHuanApplication_HiltComponents.ActivityRetainedC build() {
      Preconditions.checkBuilderRequirement(savedStateHandleHolder, SavedStateHandleHolder.class);
      return new ActivityRetainedCImpl(singletonCImpl, savedStateHandleHolder);
    }
  }

  private static final class ActivityCBuilder implements XunHuanApplication_HiltComponents.ActivityC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private Activity activity;

    private ActivityCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
    }

    @Override
    public ActivityCBuilder activity(Activity activity) {
      this.activity = Preconditions.checkNotNull(activity);
      return this;
    }

    @Override
    public XunHuanApplication_HiltComponents.ActivityC build() {
      Preconditions.checkBuilderRequirement(activity, Activity.class);
      return new ActivityCImpl(singletonCImpl, activityRetainedCImpl, activity);
    }
  }

  private static final class FragmentCBuilder implements XunHuanApplication_HiltComponents.FragmentC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private Fragment fragment;

    private FragmentCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
    }

    @Override
    public FragmentCBuilder fragment(Fragment fragment) {
      this.fragment = Preconditions.checkNotNull(fragment);
      return this;
    }

    @Override
    public XunHuanApplication_HiltComponents.FragmentC build() {
      Preconditions.checkBuilderRequirement(fragment, Fragment.class);
      return new FragmentCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, fragment);
    }
  }

  private static final class ViewWithFragmentCBuilder implements XunHuanApplication_HiltComponents.ViewWithFragmentC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl;

    private View view;

    private ViewWithFragmentCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl,
        FragmentCImpl fragmentCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
      this.fragmentCImpl = fragmentCImpl;
    }

    @Override
    public ViewWithFragmentCBuilder view(View view) {
      this.view = Preconditions.checkNotNull(view);
      return this;
    }

    @Override
    public XunHuanApplication_HiltComponents.ViewWithFragmentC build() {
      Preconditions.checkBuilderRequirement(view, View.class);
      return new ViewWithFragmentCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, fragmentCImpl, view);
    }
  }

  private static final class ViewCBuilder implements XunHuanApplication_HiltComponents.ViewC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private View view;

    private ViewCBuilder(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
        ActivityCImpl activityCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
    }

    @Override
    public ViewCBuilder view(View view) {
      this.view = Preconditions.checkNotNull(view);
      return this;
    }

    @Override
    public XunHuanApplication_HiltComponents.ViewC build() {
      Preconditions.checkBuilderRequirement(view, View.class);
      return new ViewCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, view);
    }
  }

  private static final class ViewModelCBuilder implements XunHuanApplication_HiltComponents.ViewModelC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private SavedStateHandle savedStateHandle;

    private ViewModelLifecycle viewModelLifecycle;

    private ViewModelCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
    }

    @Override
    public ViewModelCBuilder savedStateHandle(SavedStateHandle handle) {
      this.savedStateHandle = Preconditions.checkNotNull(handle);
      return this;
    }

    @Override
    public ViewModelCBuilder viewModelLifecycle(ViewModelLifecycle viewModelLifecycle) {
      this.viewModelLifecycle = Preconditions.checkNotNull(viewModelLifecycle);
      return this;
    }

    @Override
    public XunHuanApplication_HiltComponents.ViewModelC build() {
      Preconditions.checkBuilderRequirement(savedStateHandle, SavedStateHandle.class);
      Preconditions.checkBuilderRequirement(viewModelLifecycle, ViewModelLifecycle.class);
      return new ViewModelCImpl(singletonCImpl, activityRetainedCImpl, savedStateHandle, viewModelLifecycle);
    }
  }

  private static final class ServiceCBuilder implements XunHuanApplication_HiltComponents.ServiceC.Builder {
    private final SingletonCImpl singletonCImpl;

    private Service service;

    private ServiceCBuilder(SingletonCImpl singletonCImpl) {
      this.singletonCImpl = singletonCImpl;
    }

    @Override
    public ServiceCBuilder service(Service service) {
      this.service = Preconditions.checkNotNull(service);
      return this;
    }

    @Override
    public XunHuanApplication_HiltComponents.ServiceC build() {
      Preconditions.checkBuilderRequirement(service, Service.class);
      return new ServiceCImpl(singletonCImpl, service);
    }
  }

  private static final class ViewWithFragmentCImpl extends XunHuanApplication_HiltComponents.ViewWithFragmentC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl;

    private final ViewWithFragmentCImpl viewWithFragmentCImpl = this;

    private ViewWithFragmentCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl,
        FragmentCImpl fragmentCImpl, View viewParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
      this.fragmentCImpl = fragmentCImpl;


    }
  }

  private static final class FragmentCImpl extends XunHuanApplication_HiltComponents.FragmentC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl = this;

    private FragmentCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl,
        Fragment fragmentParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;


    }

    @Override
    public DefaultViewModelFactories.InternalFactoryFactory getHiltInternalFactoryFactory() {
      return activityCImpl.getHiltInternalFactoryFactory();
    }

    @Override
    public ViewWithFragmentComponentBuilder viewWithFragmentComponentBuilder() {
      return new ViewWithFragmentCBuilder(singletonCImpl, activityRetainedCImpl, activityCImpl, fragmentCImpl);
    }
  }

  private static final class ViewCImpl extends XunHuanApplication_HiltComponents.ViewC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final ViewCImpl viewCImpl = this;

    private ViewCImpl(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
        ActivityCImpl activityCImpl, View viewParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;


    }
  }

  private static final class ActivityCImpl extends XunHuanApplication_HiltComponents.ActivityC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl = this;

    private ActivityCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, Activity activityParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;


    }

    @Override
    public void injectMainActivity(MainActivity mainActivity) {
    }

    @Override
    public DefaultViewModelFactories.InternalFactoryFactory getHiltInternalFactoryFactory() {
      return DefaultViewModelFactories_InternalFactoryFactory_Factory.newInstance(getViewModelKeys(), new ViewModelCBuilder(singletonCImpl, activityRetainedCImpl));
    }

    @Override
    public Map<Class<?>, Boolean> getViewModelKeys() {
      return LazyClassKeyMap.<Boolean>of(MapBuilder.<String, Boolean>newMapBuilder(8).put(LazyClassKeyProvider.com_xunhuan_zuoai_ui_screen_chat_ChatViewModel, ChatViewModel_HiltModules.KeyModule.provide()).put(LazyClassKeyProvider.com_xunhuan_zuoai_ui_screen_discover_DiscoverViewModel, DiscoverViewModel_HiltModules.KeyModule.provide()).put(LazyClassKeyProvider.com_xunhuan_zuoai_ui_screen_home_HomeViewModel, HomeViewModel_HiltModules.KeyModule.provide()).put(LazyClassKeyProvider.com_xunhuan_zuoai_navigation_NavViewModel, NavViewModel_HiltModules.KeyModule.provide()).put(LazyClassKeyProvider.com_xunhuan_zuoai_ui_screen_onboarding_OnboardingViewModel, OnboardingViewModel_HiltModules.KeyModule.provide()).put(LazyClassKeyProvider.com_xunhuan_zuoai_ui_screen_profile_ProfileViewModel, ProfileViewModel_HiltModules.KeyModule.provide()).put(LazyClassKeyProvider.com_xunhuan_zuoai_ui_screen_taskdetail_TaskDetailViewModel, TaskDetailViewModel_HiltModules.KeyModule.provide()).put(LazyClassKeyProvider.com_xunhuan_zuoai_ui_screen_wishlist_WishlistViewModel, WishlistViewModel_HiltModules.KeyModule.provide()).build());
    }

    @Override
    public ViewModelComponentBuilder getViewModelComponentBuilder() {
      return new ViewModelCBuilder(singletonCImpl, activityRetainedCImpl);
    }

    @Override
    public FragmentComponentBuilder fragmentComponentBuilder() {
      return new FragmentCBuilder(singletonCImpl, activityRetainedCImpl, activityCImpl);
    }

    @Override
    public ViewComponentBuilder viewComponentBuilder() {
      return new ViewCBuilder(singletonCImpl, activityRetainedCImpl, activityCImpl);
    }

    @IdentifierNameString
    private static final class LazyClassKeyProvider {
      static String com_xunhuan_zuoai_ui_screen_taskdetail_TaskDetailViewModel = "com.xunhuan.zuoai.ui.screen.taskdetail.TaskDetailViewModel";

      static String com_xunhuan_zuoai_ui_screen_chat_ChatViewModel = "com.xunhuan.zuoai.ui.screen.chat.ChatViewModel";

      static String com_xunhuan_zuoai_navigation_NavViewModel = "com.xunhuan.zuoai.navigation.NavViewModel";

      static String com_xunhuan_zuoai_ui_screen_profile_ProfileViewModel = "com.xunhuan.zuoai.ui.screen.profile.ProfileViewModel";

      static String com_xunhuan_zuoai_ui_screen_wishlist_WishlistViewModel = "com.xunhuan.zuoai.ui.screen.wishlist.WishlistViewModel";

      static String com_xunhuan_zuoai_ui_screen_discover_DiscoverViewModel = "com.xunhuan.zuoai.ui.screen.discover.DiscoverViewModel";

      static String com_xunhuan_zuoai_ui_screen_home_HomeViewModel = "com.xunhuan.zuoai.ui.screen.home.HomeViewModel";

      static String com_xunhuan_zuoai_ui_screen_onboarding_OnboardingViewModel = "com.xunhuan.zuoai.ui.screen.onboarding.OnboardingViewModel";

      @KeepFieldType
      TaskDetailViewModel com_xunhuan_zuoai_ui_screen_taskdetail_TaskDetailViewModel2;

      @KeepFieldType
      ChatViewModel com_xunhuan_zuoai_ui_screen_chat_ChatViewModel2;

      @KeepFieldType
      NavViewModel com_xunhuan_zuoai_navigation_NavViewModel2;

      @KeepFieldType
      ProfileViewModel com_xunhuan_zuoai_ui_screen_profile_ProfileViewModel2;

      @KeepFieldType
      WishlistViewModel com_xunhuan_zuoai_ui_screen_wishlist_WishlistViewModel2;

      @KeepFieldType
      DiscoverViewModel com_xunhuan_zuoai_ui_screen_discover_DiscoverViewModel2;

      @KeepFieldType
      HomeViewModel com_xunhuan_zuoai_ui_screen_home_HomeViewModel2;

      @KeepFieldType
      OnboardingViewModel com_xunhuan_zuoai_ui_screen_onboarding_OnboardingViewModel2;
    }
  }

  private static final class ViewModelCImpl extends XunHuanApplication_HiltComponents.ViewModelC {
    private final SavedStateHandle savedStateHandle;

    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ViewModelCImpl viewModelCImpl = this;

    private Provider<ChatViewModel> chatViewModelProvider;

    private Provider<DiscoverViewModel> discoverViewModelProvider;

    private Provider<HomeViewModel> homeViewModelProvider;

    private Provider<NavViewModel> navViewModelProvider;

    private Provider<OnboardingViewModel> onboardingViewModelProvider;

    private Provider<ProfileViewModel> profileViewModelProvider;

    private Provider<TaskDetailViewModel> taskDetailViewModelProvider;

    private Provider<WishlistViewModel> wishlistViewModelProvider;

    private ViewModelCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, SavedStateHandle savedStateHandleParam,
        ViewModelLifecycle viewModelLifecycleParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.savedStateHandle = savedStateHandleParam;
      initialize(savedStateHandleParam, viewModelLifecycleParam);

    }

    @SuppressWarnings("unchecked")
    private void initialize(final SavedStateHandle savedStateHandleParam,
        final ViewModelLifecycle viewModelLifecycleParam) {
      this.chatViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 0);
      this.discoverViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 1);
      this.homeViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 2);
      this.navViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 3);
      this.onboardingViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 4);
      this.profileViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 5);
      this.taskDetailViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 6);
      this.wishlistViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 7);
    }

    @Override
    public Map<Class<?>, javax.inject.Provider<ViewModel>> getHiltViewModelMap() {
      return LazyClassKeyMap.<javax.inject.Provider<ViewModel>>of(MapBuilder.<String, javax.inject.Provider<ViewModel>>newMapBuilder(8).put(LazyClassKeyProvider.com_xunhuan_zuoai_ui_screen_chat_ChatViewModel, ((Provider) chatViewModelProvider)).put(LazyClassKeyProvider.com_xunhuan_zuoai_ui_screen_discover_DiscoverViewModel, ((Provider) discoverViewModelProvider)).put(LazyClassKeyProvider.com_xunhuan_zuoai_ui_screen_home_HomeViewModel, ((Provider) homeViewModelProvider)).put(LazyClassKeyProvider.com_xunhuan_zuoai_navigation_NavViewModel, ((Provider) navViewModelProvider)).put(LazyClassKeyProvider.com_xunhuan_zuoai_ui_screen_onboarding_OnboardingViewModel, ((Provider) onboardingViewModelProvider)).put(LazyClassKeyProvider.com_xunhuan_zuoai_ui_screen_profile_ProfileViewModel, ((Provider) profileViewModelProvider)).put(LazyClassKeyProvider.com_xunhuan_zuoai_ui_screen_taskdetail_TaskDetailViewModel, ((Provider) taskDetailViewModelProvider)).put(LazyClassKeyProvider.com_xunhuan_zuoai_ui_screen_wishlist_WishlistViewModel, ((Provider) wishlistViewModelProvider)).build());
    }

    @Override
    public Map<Class<?>, Object> getHiltViewModelAssistedMap() {
      return Collections.<Class<?>, Object>emptyMap();
    }

    @IdentifierNameString
    private static final class LazyClassKeyProvider {
      static String com_xunhuan_zuoai_navigation_NavViewModel = "com.xunhuan.zuoai.navigation.NavViewModel";

      static String com_xunhuan_zuoai_ui_screen_home_HomeViewModel = "com.xunhuan.zuoai.ui.screen.home.HomeViewModel";

      static String com_xunhuan_zuoai_ui_screen_onboarding_OnboardingViewModel = "com.xunhuan.zuoai.ui.screen.onboarding.OnboardingViewModel";

      static String com_xunhuan_zuoai_ui_screen_discover_DiscoverViewModel = "com.xunhuan.zuoai.ui.screen.discover.DiscoverViewModel";

      static String com_xunhuan_zuoai_ui_screen_chat_ChatViewModel = "com.xunhuan.zuoai.ui.screen.chat.ChatViewModel";

      static String com_xunhuan_zuoai_ui_screen_profile_ProfileViewModel = "com.xunhuan.zuoai.ui.screen.profile.ProfileViewModel";

      static String com_xunhuan_zuoai_ui_screen_taskdetail_TaskDetailViewModel = "com.xunhuan.zuoai.ui.screen.taskdetail.TaskDetailViewModel";

      static String com_xunhuan_zuoai_ui_screen_wishlist_WishlistViewModel = "com.xunhuan.zuoai.ui.screen.wishlist.WishlistViewModel";

      @KeepFieldType
      NavViewModel com_xunhuan_zuoai_navigation_NavViewModel2;

      @KeepFieldType
      HomeViewModel com_xunhuan_zuoai_ui_screen_home_HomeViewModel2;

      @KeepFieldType
      OnboardingViewModel com_xunhuan_zuoai_ui_screen_onboarding_OnboardingViewModel2;

      @KeepFieldType
      DiscoverViewModel com_xunhuan_zuoai_ui_screen_discover_DiscoverViewModel2;

      @KeepFieldType
      ChatViewModel com_xunhuan_zuoai_ui_screen_chat_ChatViewModel2;

      @KeepFieldType
      ProfileViewModel com_xunhuan_zuoai_ui_screen_profile_ProfileViewModel2;

      @KeepFieldType
      TaskDetailViewModel com_xunhuan_zuoai_ui_screen_taskdetail_TaskDetailViewModel2;

      @KeepFieldType
      WishlistViewModel com_xunhuan_zuoai_ui_screen_wishlist_WishlistViewModel2;
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final SingletonCImpl singletonCImpl;

      private final ActivityRetainedCImpl activityRetainedCImpl;

      private final ViewModelCImpl viewModelCImpl;

      private final int id;

      SwitchingProvider(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
          ViewModelCImpl viewModelCImpl, int id) {
        this.singletonCImpl = singletonCImpl;
        this.activityRetainedCImpl = activityRetainedCImpl;
        this.viewModelCImpl = viewModelCImpl;
        this.id = id;
      }

      @SuppressWarnings("unchecked")
      @Override
      public T get() {
        switch (id) {
          case 0: // com.xunhuan.zuoai.ui.screen.chat.ChatViewModel 
          return (T) new ChatViewModel(singletonCImpl.claudeRepositoryProvider.get());

          case 1: // com.xunhuan.zuoai.ui.screen.discover.DiscoverViewModel 
          return (T) new DiscoverViewModel(singletonCImpl.taskRepositoryProvider.get());

          case 2: // com.xunhuan.zuoai.ui.screen.home.HomeViewModel 
          return (T) new HomeViewModel(singletonCImpl.userRepositoryProvider.get(), singletonCImpl.taskRepositoryProvider.get());

          case 3: // com.xunhuan.zuoai.navigation.NavViewModel 
          return (T) new NavViewModel(singletonCImpl.userRepositoryProvider.get());

          case 4: // com.xunhuan.zuoai.ui.screen.onboarding.OnboardingViewModel 
          return (T) new OnboardingViewModel(singletonCImpl.userRepositoryProvider.get());

          case 5: // com.xunhuan.zuoai.ui.screen.profile.ProfileViewModel 
          return (T) new ProfileViewModel(singletonCImpl.userRepositoryProvider.get(), singletonCImpl.profileRepositoryProvider.get());

          case 6: // com.xunhuan.zuoai.ui.screen.taskdetail.TaskDetailViewModel 
          return (T) new TaskDetailViewModel(viewModelCImpl.savedStateHandle, singletonCImpl.taskRepositoryProvider.get(), singletonCImpl.userRepositoryProvider.get());

          case 7: // com.xunhuan.zuoai.ui.screen.wishlist.WishlistViewModel 
          return (T) new WishlistViewModel(singletonCImpl.taskRepositoryProvider.get());

          default: throw new AssertionError(id);
        }
      }
    }
  }

  private static final class ActivityRetainedCImpl extends XunHuanApplication_HiltComponents.ActivityRetainedC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl = this;

    private Provider<ActivityRetainedLifecycle> provideActivityRetainedLifecycleProvider;

    private ActivityRetainedCImpl(SingletonCImpl singletonCImpl,
        SavedStateHandleHolder savedStateHandleHolderParam) {
      this.singletonCImpl = singletonCImpl;

      initialize(savedStateHandleHolderParam);

    }

    @SuppressWarnings("unchecked")
    private void initialize(final SavedStateHandleHolder savedStateHandleHolderParam) {
      this.provideActivityRetainedLifecycleProvider = DoubleCheck.provider(new SwitchingProvider<ActivityRetainedLifecycle>(singletonCImpl, activityRetainedCImpl, 0));
    }

    @Override
    public ActivityComponentBuilder activityComponentBuilder() {
      return new ActivityCBuilder(singletonCImpl, activityRetainedCImpl);
    }

    @Override
    public ActivityRetainedLifecycle getActivityRetainedLifecycle() {
      return provideActivityRetainedLifecycleProvider.get();
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final SingletonCImpl singletonCImpl;

      private final ActivityRetainedCImpl activityRetainedCImpl;

      private final int id;

      SwitchingProvider(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
          int id) {
        this.singletonCImpl = singletonCImpl;
        this.activityRetainedCImpl = activityRetainedCImpl;
        this.id = id;
      }

      @SuppressWarnings("unchecked")
      @Override
      public T get() {
        switch (id) {
          case 0: // dagger.hilt.android.ActivityRetainedLifecycle 
          return (T) ActivityRetainedComponentManager_LifecycleModule_ProvideActivityRetainedLifecycleFactory.provideActivityRetainedLifecycle();

          default: throw new AssertionError(id);
        }
      }
    }
  }

  private static final class ServiceCImpl extends XunHuanApplication_HiltComponents.ServiceC {
    private final SingletonCImpl singletonCImpl;

    private final ServiceCImpl serviceCImpl = this;

    private ServiceCImpl(SingletonCImpl singletonCImpl, Service serviceParam) {
      this.singletonCImpl = singletonCImpl;


    }
  }

  private static final class SingletonCImpl extends XunHuanApplication_HiltComponents.SingletonC {
    private final ApplicationContextModule applicationContextModule;

    private final SingletonCImpl singletonCImpl = this;

    private Provider<OkHttpClient> provideOkHttpClientProvider;

    private Provider<Retrofit> provideRetrofitProvider;

    private Provider<ClaudeApiService> provideClaudeApiServiceProvider;

    private Provider<AppDatabase> provideDatabaseProvider;

    private Provider<ClaudeRepository> claudeRepositoryProvider;

    private Provider<TaskRepository> taskRepositoryProvider;

    private Provider<UserRepository> userRepositoryProvider;

    private Provider<ProfileRepository> profileRepositoryProvider;

    private SingletonCImpl(ApplicationContextModule applicationContextModuleParam) {
      this.applicationContextModule = applicationContextModuleParam;
      initialize(applicationContextModuleParam);

    }

    private ChatMessageDao chatMessageDao() {
      return DatabaseModule_ProvideChatMessageDaoFactory.provideChatMessageDao(provideDatabaseProvider.get());
    }

    private TaskDao taskDao() {
      return DatabaseModule_ProvideTaskDaoFactory.provideTaskDao(provideDatabaseProvider.get());
    }

    private TaskRecordDao taskRecordDao() {
      return DatabaseModule_ProvideTaskRecordDaoFactory.provideTaskRecordDao(provideDatabaseProvider.get());
    }

    private UserDao userDao() {
      return DatabaseModule_ProvideUserDaoFactory.provideUserDao(provideDatabaseProvider.get());
    }

    @SuppressWarnings("unchecked")
    private void initialize(final ApplicationContextModule applicationContextModuleParam) {
      this.provideOkHttpClientProvider = DoubleCheck.provider(new SwitchingProvider<OkHttpClient>(singletonCImpl, 3));
      this.provideRetrofitProvider = DoubleCheck.provider(new SwitchingProvider<Retrofit>(singletonCImpl, 2));
      this.provideClaudeApiServiceProvider = DoubleCheck.provider(new SwitchingProvider<ClaudeApiService>(singletonCImpl, 1));
      this.provideDatabaseProvider = DoubleCheck.provider(new SwitchingProvider<AppDatabase>(singletonCImpl, 4));
      this.claudeRepositoryProvider = DoubleCheck.provider(new SwitchingProvider<ClaudeRepository>(singletonCImpl, 0));
      this.taskRepositoryProvider = DoubleCheck.provider(new SwitchingProvider<TaskRepository>(singletonCImpl, 5));
      this.userRepositoryProvider = DoubleCheck.provider(new SwitchingProvider<UserRepository>(singletonCImpl, 6));
      this.profileRepositoryProvider = DoubleCheck.provider(new SwitchingProvider<ProfileRepository>(singletonCImpl, 7));
    }

    @Override
    public void injectXunHuanApplication(XunHuanApplication xunHuanApplication) {
    }

    @Override
    public Set<Boolean> getDisableFragmentGetContextFix() {
      return Collections.<Boolean>emptySet();
    }

    @Override
    public ActivityRetainedComponentBuilder retainedComponentBuilder() {
      return new ActivityRetainedCBuilder(singletonCImpl);
    }

    @Override
    public ServiceComponentBuilder serviceComponentBuilder() {
      return new ServiceCBuilder(singletonCImpl);
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final SingletonCImpl singletonCImpl;

      private final int id;

      SwitchingProvider(SingletonCImpl singletonCImpl, int id) {
        this.singletonCImpl = singletonCImpl;
        this.id = id;
      }

      @SuppressWarnings("unchecked")
      @Override
      public T get() {
        switch (id) {
          case 0: // com.xunhuan.zuoai.data.repository.ClaudeRepository 
          return (T) new ClaudeRepository(singletonCImpl.provideClaudeApiServiceProvider.get(), singletonCImpl.chatMessageDao());

          case 1: // com.xunhuan.zuoai.data.remote.ClaudeApiService 
          return (T) NetworkModule_ProvideClaudeApiServiceFactory.provideClaudeApiService(singletonCImpl.provideRetrofitProvider.get());

          case 2: // retrofit2.Retrofit 
          return (T) NetworkModule_ProvideRetrofitFactory.provideRetrofit(singletonCImpl.provideOkHttpClientProvider.get());

          case 3: // okhttp3.OkHttpClient 
          return (T) NetworkModule_ProvideOkHttpClientFactory.provideOkHttpClient();

          case 4: // com.xunhuan.zuoai.data.local.AppDatabase 
          return (T) DatabaseModule_ProvideDatabaseFactory.provideDatabase(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule));

          case 5: // com.xunhuan.zuoai.data.repository.TaskRepository 
          return (T) new TaskRepository(singletonCImpl.taskDao(), singletonCImpl.taskRecordDao());

          case 6: // com.xunhuan.zuoai.data.repository.UserRepository 
          return (T) new UserRepository(singletonCImpl.userDao(), ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule));

          case 7: // com.xunhuan.zuoai.data.repository.ProfileRepository 
          return (T) new ProfileRepository(singletonCImpl.taskRecordDao());

          default: throw new AssertionError(id);
        }
      }
    }
  }
}
